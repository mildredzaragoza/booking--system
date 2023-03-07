import fetch from 'node-fetch';

const urlUsers = `http://localhost:8082/users/`;

export function users(){
    return fetch(urlUsers)
            .then(res => res.json())
            .then(json => json);
  }
  
  export function userByUsername({ username }) {
    return fetch(urlUsers + `${username}`)
            .then(res => res.json())
            .then(json => console.log(json));
  }
  
  export function updatePassword({ username, password }) {
    return fetch(urlUsers + `${username}`, { 
              method: 'PUT', 
              body: JSON.stringify(password), 
              headers: {
                "Content-type": "application/json; charset=UTF-8"
              }
            })
            .then(res => res.json())
            .then(json => json);
  }