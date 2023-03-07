import { GraphQLError } from 'graphql';
import { guests, guestById, deleteBook, addGuest, updateGuest } from './fetchersGuests.js';
import { users, userByUsername, updatePassword } from './fetchersUsers.js';
const resolvers = {
    Query: {
        guests: () => guests(),
        guestById: (root, { id }) => guestById({ id }),
        users: () => users(),
        userByUsername: (root, { username }) => {
            userByUsername({ username });
        }
    },
    Mutation: {
        addGuest: (root, { guest }) => addGuest({ guest }),
        deleteGuest: (root, { id }) => deleteBook({ id }),
        updateGuest: (root, { id, guest }) => updateGuest({ id, guest }),
        updatePassword: (root, { username, password }) => {
            // var passStr = new String(password);
            if (String(password).length < 5) {
                throw new GraphQLError('Password must contain at least 5 digits', {
                    extensions: {
                        code: 'BAD_USER_INPUT'
                    },
                });
            }
            return updatePassword({ username, password });
        }
    },
};
export default resolvers;
