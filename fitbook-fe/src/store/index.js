import { createStore } from 'vuex';
import clientsModule from './clients/index';
import trainersModule from './trainers/index';
import programModule from './program/index';
import authModule from './auth/index';

export default createStore({
  modules: {
    auth: authModule,
    program: programModule,
    clients: clientsModule,
    trainers: trainersModule,
  },
});
