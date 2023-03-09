import { Injectable } from '@angular/core';
import { User } from '../graphql/types';
import { Apollo } from 'apollo-angular';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { GET_USERS, USER_BY_USERNAME, UPDATE_PASSWORD } from '../graphql/queries';


@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private apollo: Apollo) { }

  getUsers(): Observable<[User]>{
    return this.apollo.watchQuery<any>({
      query: GET_USERS
    })
    .valueChanges
    .pipe(
      map(result =>  result.data.users)
    );
  }

  userByUsername(username: string) {
    return this.apollo.watchQuery<any>({
        query: USER_BY_USERNAME,
        variables: { 
          username: username 
        },
      }).valueChanges
        .pipe(
          map(result => console.log(result))
        )
  }

  updatePassword(username: string, password: string){
    this.apollo
        .mutate({
          mutation: UPDATE_PASSWORD,
          variables: {
            username: username,
            password: password
          },
        }).subscribe()
  }
}
