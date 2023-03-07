
export type Guest = {
    id: number
    name: string
    email: string
    phoneNumber: string
    checkInDate: string
    checkOutDate: string
    typeGuest: string
}

export type User = {
    id: number
    username: string
    password: string
    role: string
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