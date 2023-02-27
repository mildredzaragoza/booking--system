import fetch from 'node-fetch';
const urlGuests = `http://localhost:8081/guests/`;
export function fetchGuests() {
    return fetch(urlGuests)
        .then(res => res.json())
        .then(res => res.result);
}
export function fecthGuestById({ id }) {
    return fetch(urlGuests + `${id}`)
        .then(res => res.json())
        .then(res => res.result);
}
export function fetchDeleteBook({ id }) {
    return fetch(urlGuests + `${id}`, { method: 'DELETE' })
        .then(res => res.json())
        .then(res => res.result);
}
