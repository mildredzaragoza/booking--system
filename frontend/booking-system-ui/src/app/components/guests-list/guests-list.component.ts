import { Component, OnInit } from '@angular/core';
import { GuestsService } from 'src/app/services/guests.service';
import { Guest, GuestModel } from 'src/app/graphql/types';
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

  editGuest(id: number, guest: GuestModel){
    this.guestService.guestById(id);
    localStorage.setItem("guest", guest.toString());
    localStorage.setItem("id", id.toString());
    this.route.navigate(["guest-form"]);
  }

}
