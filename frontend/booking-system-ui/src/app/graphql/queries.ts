import gql from 'graphql-tag';

export const GET_GUESTS = gql`
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

export const DELETE_GUEST = gql`
    mutation DeleteGuest($id: ID!){
        deleteGuest(id: $id)
    }
`;

export const GUEST_BY_ID = gql`
  query GuestById($id: ID!) {
    guestById(id: $id) {
      name
      email
      phoneNumber
      checkInDate
      checkOutDate
      typeGuest
    }
  }
`;

export const ADD_GUEST = gql`
  mutation AddGuest($guest: GuestInput!) {
    addGuest(guest: $guest ) {
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

export const UPDATE_GUEST = gql`
  mutation UpdateGuest($id: ID!, $guest: GuestInput!) {
    updateGuest(id: $id, guest: $guest) {
      id
    }
  }
`;

export const GET_USERS = gql`
    query{
      users{
        username
        role
      }
    }
  `;

export const USER_BY_USERNAME = gql`
    query UserByUsername($username: String!) {
      userByUsername(username: $username) {
        username
        password
        role  
      }
    }
`;

export const UPDATE_PASSWORD = gql`
  mutation UpdatePassword($username: String!, $password: String!) {
    updatePassword(username: $username, password: $password) {
      password
      username
    }
  }
`;