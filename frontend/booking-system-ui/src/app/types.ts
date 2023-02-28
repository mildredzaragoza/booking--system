export type Query = {
    guests: [Guest];
    guestById(id: Number): Guest;
}

export type Mutation = {
    addGuest(guest: Guest): Guest;
    updateGuest(id: Number, guest: Guest): Guest;
    deleteGuest(id: Number): Boolean;
}

export type Guest = {
    id: Number
    name: String
    email: String
    phoneNumber: String
    checkInDate: String
    checkOutDate: String
    typeGuest: String
}

export class GuestModel {
    constructor(
        public name: string,
        public email: string,
        public phoneNumber: string,
        public checkInDate: string,
        public checkOutDate: string,
        public typeGuest: string,
    ){}
}