export default {
  registerTrainer(context, payload) {
    context.commit('registerTrainer', payload);
  },
  async fetchTrainers(context) {
    const response = await fetch(
      `https://vue-dogs-258aa-default-rtdb.firebaseio.com/users.json`
    );

    const responseData = await response.json();

    if (!response.ok) {
      const error = new Error(responseData.message || 'Failed to fetch');
      throw error;
    }

    const trainers = [];
    for (const key in responseData) {
      const trainer = {
        userId: responseData[key].clientId,
        email: responseData[key].email,
        password: responseData[key].password,
      };

      trainers.push(trainer);
    }
    context.commit('setTrainers', trainers);
  },
};
