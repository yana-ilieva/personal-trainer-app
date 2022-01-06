import { createStore } from 'vuex';
import clientsModule from './clients/index';
import trainersModule from './trainers/index';

export default createStore({
  modules: {
    clients: clientsModule,
    trainers: trainersModule,
  },
});
