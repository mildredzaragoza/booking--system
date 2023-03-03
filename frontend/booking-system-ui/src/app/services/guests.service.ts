import { Injectable } from '@angular/core';
import { Guest, GuestModel } from '../graphql/types';
import { Apollo } from 'apollo-angular';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { GET_GUESTS, GUEST_BY_ID, DELETE_GUEST, ADD_GUEST, UPDATE_GUEST } from '../graphql/queries';
import { gql } from 'graphql-tag';

@Injectable({
  providedIn: 'root'
})
export class GuestsService {

  constructor(private apollo: Apollo) { }

  getGuests(): Observable<[Guest]>{
    return this.apollo.watchQuery<any>({
      query: GET_GUESTS
    })
    .valueChanges
    .pipe(
      map(result =>  result.data.guests)
    );
  }

  deleteGuest(deleteGuestId: number){
    this.apollo
        .mutate({
          mutation: DELETE_GUEST,
          variables: { id: deleteGuestId },
          refetchQueries: [{ query: GET_GUESTS }]
        }).subscribe()
  }

  guestById(guestId: number) {
  return this.apollo.watchQuery<any>({
      query: GUEST_BY_ID,
      variables: { 
        id: guestId 
      },
    }).valueChanges
      .pipe(
        map(result => result.data.guestById)
      )
  }

  addGuest(guest: GuestModel){
    this.apollo
        .mutate({
          mutation: ADD_GUEST,
          variables: { 
            guest: guest   
          },
          refetchQueries: [{ query: GET_GUESTS }]
        }).subscribe((result: any) => result.data.guests)
  }

  updateGuest(id: number, guest: GuestModel){
    this.apollo
        .mutate({
          mutation: UPDATE_GUEST,
          variables: {
            id: id,
            guest: guest
          },
          refetchQueries: [{ query: GET_GUESTS }]
        }).subscribe((result: any) =>  result.data.guests)
  }

  guestByIdNoParams(){
    this.apollo.watchQuery<any>({
      query: gql`query GuestById {
        guestById(id: 1) {
          id
          name
          email
          phoneNumber
          checkInDate
          checkOutDate
          typeGuest
        }}`
    }).valueChanges.subscribe(({data}) => {
      console.log(data.guestById);
    })
  }
}
