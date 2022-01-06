export default {
  registerTrainer(state, payload) {
    state.trainers.push(payload);
  },
  setTrainers(state, payload) {
    state.trainers = payload;
  },
};
