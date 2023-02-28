import { Component, OnInit } from '@angular/core';
import { GuestsService } from 'src/app/services/guests.service';
import { Guest } from 'src/app/types';

@Component({
  selector: 'app-guests-list',
  templateUrl: './guests-list.component.html',
  styleUrls: ['./guests-list.component.css']
})
export class GuestsListComponent implements OnInit{
  guests: Guest[] = [];

  constructor(private guestService: GuestsService){}

  ngOnInit(){
    this.guestService.getGuests().subscribe(guests => this.guests = guests);
  }

}
