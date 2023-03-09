import { GraphQLError } from 'graphql';

import { guests, guestById, deleteBook, addGuest, updateGuest } from './fetchersGuests.js';
import { users, userByUsername, updatePassword, validateUser } from './fetchersUsers.js';

const resolvers = {
    Query: {
      guests: () => guests(),
      guestById: (root, { id }) => guestById({ id }),
      users: () => users(),
      validateUser: (root, { user }) => validateUser({ user }),
      userByUsername: (root, { username }) => {
        try{
         return userByUsername({ username })
        }catch(error){
          console.log(error);
        }
      }
      
    },
    Mutation: {
        addGuest: (root, { guest }) => addGuest({ guest }),
        deleteGuest: (root, { id }) => deleteBook({ id }),
        updateGuest: (root, { id, guest }) => updateGuest({ id, guest }),
        updatePassword: (root, { username, password }) => {
          if(String(password).length < 5){
            throw new GraphQLError('Password must contain at least 5 digits', {
              extensions: {
                code: 'BAD_USER_INPUT'
              },
            });
          }
          return updatePassword({ username, password })
        }
      },
};

export default resolvers;