import { ApolloServer } from '@apollo/server';
import { startStandaloneServer } from '@apollo/server/standalone';
import { __InputValue } from 'graphql';
import fetch from 'node-fetch';

const typeDefs = `#graphql
    type Query {
      guests: [Guest]
      guestById(id: ID): Guest
    }

    type Mutation {
      addGuest(guest: GuestInput): Guest
      updateGuest(id: ID, guest: GuestInput): Guest
      deleteGuest(id: ID): [Guest]
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

const books = [
    {
      id: 1,
      title: 'The Awakening',
      author: 'Kate Chopin',
    },
    {
      id: 2,
      title: 'City of Glass',
      author: 'Paul Auster',
    },
  ];

const resolvers = {
    Query: {
      guests: () => fetchGuests(),
    },
   /* Mutation: {
        addBook: (_, {title, author}) => {
            const sizee = books.length;
            const book = {id: books.length + 1, title, author};
            books.push(book);
            return book;
        },
        deleteBook: (root, { title }) => {
            const index = books.indexOf(title);
            books.splice(index,1);
            return books;
        },
        updateBook:(root, {id, title, author}) => {
            const bookUpdated = books.find(book => book.id == id);
            bookUpdated.author = author;
            bookUpdated.title = title;
            return bookUpdated;
        }
    },*/
};

const fetchGuests = () => {
  return fetch('http://localhost:8081/guests')
          .then(res => res.json())
          .then(res => res.result)
}

const server = new ApolloServer({
    typeDefs,
    resolvers,
  });

  const { url } = await startStandaloneServer(server, {
    listen: { port: 4000 },
  });
  
  console.log(`🚀  Server ready at: ${url}`);