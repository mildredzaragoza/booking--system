import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GuestModel } from 'src/app/graphql/types';
import { GuestsService } from 'src/app/services/guests.service';

@Component({
  selector: 'app-guest-form',
  templateUrl: './guest-form.component.html',
  styleUrls: ['./guest-form.component.css']
})
export class GuestFormComponent {
  constructor(private guestService: GuestsService, private route: Router){}
  
  id = localStorage.getItem("id");
  model: GuestModel = new GuestModel('','','','','','');

  ngOnInit(): void{
    if(this.id != null){
      this.guestService.guestById(Number(this.id)).subscribe((guestById: any) => this.model = {...guestById});
    }
  }
 

  saveGuest(guest: GuestModel){
    if(this.id == null){
      this.guestService.addGuest(guest);
      this.route.navigate(["guests"]);
    }else{
      const {name, email, phoneNumber, checkInDate, checkOutDate, typeGuest} = {...guest};
      const newGuest = new GuestModel(name, email, phoneNumber, checkInDate, checkOutDate, typeGuest);
      this.guestService.updateGuest(Number(this.id), newGuest);
      localStorage.removeItem("id");
      this.route.navigate(["guests"]);
    }
  }

  cancel(){
    localStorage.removeItem("id");
    this.route.navigate(["main"]);
  }
}
