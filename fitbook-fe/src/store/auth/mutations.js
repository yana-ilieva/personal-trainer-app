export default {
  setUser(state, payload) {
    console.log('setUser');
    state.token = payload.token;
    state.userId = payload.userId;
    state.role = payload.role;
    state.didLogout = false;
  },
  didLogout(state) {
    state.didLogout = true;
  },
};
