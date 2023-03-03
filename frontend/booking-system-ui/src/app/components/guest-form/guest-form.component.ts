import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Guest, GuestModel } from 'src/app/graphql/types';
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
      this.guestService.guestById(Number(this.id)).subscribe((guestById: any) => this.model = guestById);
    }
  }
 

  saveGuest(guest: GuestModel){
    if(this.id == null){
      this.guestService.addGuest(guest);
    }else{
      this.guestService.updateGuest(Number(this.id), guest);
      localStorage.removeItem("id");
    }
    this.route.navigate(["guests"]);
  }

  cancel(){
    localStorage.removeItem("id");
    this.route.navigate(["main"]);
  }
}
