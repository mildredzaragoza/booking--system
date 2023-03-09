const typeDefs = `#graphql
  type Query {
    guests: [Guest]
    guestById(id: ID!): Guest!
    users: [User]
    validateUser(user: UserInput!): User!
    userByUsername(username: String!): User!
  }

  type Mutation {
    addGuest(guest: GuestInput!): Guest!
    updateGuest(id: ID!, guest: GuestInput!): Guest!
    deleteGuest(id: ID!): Boolean!
    updatePassword(username: String!, password: String!): User!
  }

  type Guest {
    id: ID!
    name: String!
    email: String!
    phoneNumber: String!
    checkInDate: String!
    checkOutDate: String!
    typeGuest: String!
  }

  type User{
    id: ID!
    username: String!
    password: String!
    role: String!
  }

  input GuestInput{
    name: String
    email: String
    phoneNumber: String
    checkInDate: String
    checkOutDate: String
    typeGuest: String
  }

  input UserInput{
    username: String
    password: String
  }
`;
export default typeDefs;
