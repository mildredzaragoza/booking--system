const typeDefs = `#graphql
    type Query {
      guests: [Guest]
      guestById(id: ID): Guest
    }

    type Mutation {
      addGuest(guest: GuestInput): Guest
      updateGuest(id: ID, guest: GuestInput): Guest
      deleteGuest(id: ID): Boolean
    }

    type Guest {
      id: ID
      name: String
      email: String
      phoneNumber: String
      checkInDate: String
      checkOutDate: String
      typeGuest: String
    }

    input GuestInput{
      name: String
      email: String
      phoneNumber: String
      checkInDate: String
      checkOutDate: String
      typeGuest: String
    }
`;

export default typeDefs;