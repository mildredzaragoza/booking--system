import { guests, guestById, deleteBook, addGuest, updateGuest } from './fetchersGuests.js';

const resolvers = {
    Query: {
      guests: () => guests(),
      guestById: (root, { id }) => guestById({id}),
      
    },
    Mutation: {
        addGuest: (root, { guest }) => addGuest({ guest }), 
        deleteGuest: (root, { id }) => deleteBook({ id }),
        updateGuest: (root, { id, guest }) => updateGuest({ id, guest })
    },
};

export default resolvers;