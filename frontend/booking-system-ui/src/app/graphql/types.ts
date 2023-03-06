
export type Guest = {
    id: number
    name: string
    email: string
    phoneNumber: string
    checkInDate: string
    checkOutDate: string
    typeGuest: string
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