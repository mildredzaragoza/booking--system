import fetch from 'node-fetch';

const urlGuests = `http://localhost:8081/guests/`;

export function guests(){
  return fetch(urlGuests)
          .then(res => res.json())
          .then(json => json);
}
  
export function guestById({ id }) {
  return fetch(urlGuests + `${id}`)
          .then(res => res.json())
          .then(json => json);
}
  
export function deleteBook({ id }) {
  return fetch(urlGuests + `${id}`, {method: 'DELETE'} )
          .then(res => res.json())
          .then(json => json);
}

export function addGuest({ guest }) {
  return fetch(urlGuests, { 
            method: 'POST', 
            body: JSON.stringify(guest), 
            headers: {
              "Content-type": "application/json; charset=UTF-8"
            }
          })
          .then(res => res.json())
          .then(json => json);
}

export function updateGuest({ id, guest }) {
  return fetch(urlGuests + `${id}`, { 
            method: 'PUT', 
            body: JSON.stringify(guest), 
            headers: {
              "Content-type": "application/json; charset=UTF-8"
            }
          })
          .then(res => res.json())
          .then(json => json);
}