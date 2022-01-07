export default {
  userId(state) {
    return state.userId;
  },
  token(state) {
    return state.token;
  },
  isAuthenticated(state) {
    return !!state.token;
  },
  getUser(state) {
    return {
      token: state.token,
      userId: state.userId,
    };
  },
  didLogout(state) {
    return state.didLogout;
  },
};
