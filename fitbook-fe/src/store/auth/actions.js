let timer;

export default {
  logout(context) {
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
    localStorage.removeItem('tokenExpiration');

    clearTimeout(timer);

    context.commit('setUser', {
      token: null,
      userId: null,
    });
  },
  async auth(context, payload) {
    console.log({ ...payload });

    // if (!response.ok) {
    //   const error = new Error(
    //     responseData.message || 'Failed to authenticate.'
    //   );
    //   throw error;
    // }

    //   context.dispatch(
    //     'users/registerUser',
    //     {
    //       id: responseData.localId,
    //       email: payload.email,
    //       password: payload.password,
    //       likes: [''],
    //     },
    //     { root: true }
    //   );

    // const expiresIn = +responseData.expiresIn * 1000;
    // const expirationDate = new Date().getTime() + expiresIn;

    // localStorage.setItem('token', responseData.idToken);
    // localStorage.setItem('userId', responseData.localId);
    // localStorage.setItem('tokenExpiration', expirationDate);

    // timer = setTimeout(() => {
    //   context.dispatch('didLogout');
    // }, expiresIn);

    // context.commit('setUser', {
    //   token: responseData.idToken,
    //   userId: responseData.localId,
    // });
  },
  autoLogin(context) {
    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('id');

    if (token && userId) {
      console.log('here');
      context.commit('setUser', {
        token,
        userId,
      });
    }
  },
  autoLogout(context) {
    context.dispatch('logout');
    context.commit('didLogout');
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
      console.log(responseData);
      context.commit('setUser', {
        token: responseData.jwt,
        userId: responseData.userId,
      });
      localStorage.setItem('token', responseData.jwt);
      localStorage.setItem('id', responseData.userId);
      return responseData;
    } else {
      return null;
    }

    // if (!response.ok) {
    //   const error = new Error(
    //     responseData.message || 'Failed to authenticate.'
    //   );
    //   throw error;
    // } else {
    //   console.log(responseData);
    // }
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
    // const responseData = await response.json();
    if (response.ok) {
      return true;
    }
    return false;
  },
};
