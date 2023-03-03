import { Component, OnInit } from '@angular/core';
import { GuestsService } from 'src/app/services/guests.service';
import { Guest } from 'src/app/graphql/types';
import { Router } from '@angular/router';

@Component({
  selector: 'app-guests-list',
  templateUrl: './guests-list.component.html',
  styleUrls: ['./guests-list.component.css']
})
export class GuestsListComponent implements OnInit{
  guests: Guest[] = [];

  constructor(private guestService: GuestsService, private route: Router){}

  ngOnInit(){
    this.guestService.getGuests().subscribe(guests => this.guests = guests);
  }

  deleteGuest(id: number){
    this.guestService.deleteGuest(id);
  }

  editGuest(id: number){
    this.guestService.guestById(id);
    localStorage.setItem("id", id.toString());
    this.route.navigate(["guest-form"]);
  }

}
