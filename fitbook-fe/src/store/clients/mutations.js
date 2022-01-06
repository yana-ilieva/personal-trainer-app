export default {
  registerClient(state, payload) {
    state.clients.push(payload);
  },
  setClients(state, payload) {
    state.clients = payload;
  },
};
