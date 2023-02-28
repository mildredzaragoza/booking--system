import { Injectable } from '@angular/core';
import { Guest, Query } from '../types';
import { Apollo, Mutation } from 'apollo-angular';
import gql from 'graphql-tag';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GuestsService {

  constructor(private apollo: Apollo) { }

  getGuestsQuery = gql`
    query{
      guests{
        id
        name
        email
        phoneNumber
        checkInDate
        checkOutDate
        typeGuest
      }
    }
  `;

  getGuests(): Observable<[Guest]>{
    return this.apollo.watchQuery<Query>({
      query: this.getGuestsQuery
    })
    .valueChanges
    .pipe(
      map(result => result.data.guests)
    );
  }
}
