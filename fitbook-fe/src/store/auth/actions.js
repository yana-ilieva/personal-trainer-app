export default {
  logout(context) {
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
    localStorage.removeItem('tokenExpiration');

    context.commit('setUser', {
      token: null,
      userId: null,
      role: null,
    });
  },
  autoLogin(context) {
    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('id');
    const role = localStorage.getItem('userRole');

    if (token && userId) {
      context.commit('setUser', {
        token,
        userId,
        role,
      });
    }
  },
  async login(context, payload) {
    console.log({ ...payload });
    const response = await fetch('http://localhost:8081/api/login', {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        ...payload,
      }),
    });
    if (response.ok) {
      const responseData = await response.json();
      console.log('set user after login: ', responseData);
      context.commit('setUser', {
        token: responseData.jwt,
        userId: responseData.userId,
        role: responseData.role,
      });
      localStorage.setItem('token', responseData.jwt);
      localStorage.setItem('id', responseData.userId);
      localStorage.setItem('userRole', responseData.role);
      return responseData;
    } else {
      return null;
    }
  },
  async signup(context, payload) {
    const response = await fetch('http://localhost:8081/api/registration', {
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        ...payload,
      }),
    });
    if (response.ok) {
      return true;
    }
    return false;
  },
};
