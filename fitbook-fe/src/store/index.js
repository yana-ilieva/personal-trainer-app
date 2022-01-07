import { createStore } from 'vuex';
import clientsModule from './clients/index';
import trainersModule from './trainers/index';
import authModule from './auth/index';

export default createStore({
  modules: {
    auth: authModule,
    clients: clientsModule,
    trainers: trainersModule,
  },
});
