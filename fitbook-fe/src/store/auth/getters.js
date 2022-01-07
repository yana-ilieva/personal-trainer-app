export default {
  userId(state) {
    return state.userId;
  },
  token(state) {
    return state.token;
  },
  role(state) {
    return state.role;
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
};
