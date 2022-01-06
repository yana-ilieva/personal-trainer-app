export default {
  registerClient(context, payload) {
    context.commit('registerClient', payload);
  },
  async fetchClients(context) {
    const response = await fetch(
      `https://vue-dogs-258aa-default-rtdb.firebaseio.com/users.json`
    );

    const responseData = await response.json();

    if (!response.ok) {
      const error = new Error(responseData.message || 'Failed to fetch');
      throw error;
    }

    const clients = [];
    for (const key in responseData) {
      const client = {
        userId: responseData[key].clientId,
        email: responseData[key].email,
        password: responseData[key].password,
      };

      clients.push(client);
    }
    context.commit('setClients', clients);
  },
};
