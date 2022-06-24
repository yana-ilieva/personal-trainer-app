import mutations from './mutations.js';
import actions from './actions.js';
import getters from './getters.js';

export default {
  namespaced: true,
  state() {
    return {
      name: "",
      description: "",
      excercises: []
    };
  },
  actions,
  mutations,
  getters,
};
