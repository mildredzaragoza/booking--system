import { ApolloServer } from '@apollo/server';
import { startStandaloneServer } from '@apollo/server/standalone';
import { __InputValue } from 'graphql';
import typeDefs from './typeDefs.js';
import resolvers from './resolvers.js';

const server = new ApolloServer({ typeDefs, resolvers, includeStacktraceInErrorResponses: false });

const { url } = await startStandaloneServer(server, {
  listen: { port: 4000 },
});
  
console.log(`🚀  Server ready at: ${url}`);