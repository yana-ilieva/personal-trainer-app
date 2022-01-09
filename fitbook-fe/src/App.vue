<template>
  <div>
    <the-header @showNotifications="showNotifications"></the-header>
    <notifications
      v-if="isNotifications"
      @hideNotifications="hideNotifications"
      class="absolute top-0 right-0 w-1/4 h-screen overflow-auto bg-gray-100"
    ></notifications>
    <router-view></router-view>
  </div>

  <!-- <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </div>
  <router-view /> -->
</template>

<script>
import Notifications from './components/Notifications.vue';
import TheHeader from './components/TheHeader.vue';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';
export default {
  components: { TheHeader, Notifications },
  data() {
    return {
      isNotifications: false,
      received_messages: [],
      send_message: null,
      connected: false,
    };
  },
  methods: {
    showNotifications() {
      this.isNotifications = true;
    },
    hideNotifications() {
      this.isNotifications = false;
    },
    getEmail() {},
    send() {
      console.log('Send message:' + this.send_message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { name: this.send_message };
        console.log(JSON.stringify(msg));
        this.stompClient.send('/app/hello', JSON.stringify(msg), {});
      }
    },
    connect() {
      if (this.$store.getters['auth/isAuthenticated']) {
        this.socket = new SockJS('http://localhost:8081/ws');
        this.stompClient = Stomp.over(this.socket);
        console.log('before connect');
        this.stompClient.connect(
          {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
          },
          (frame) => {
            this.connected = true;
            console.log('in connect: ' + frame);
            this.stompClient.subscribe('/user/queue/notifications', (tick) => {
              console.log('tick: ', tick);
              this.received_messages.push(JSON.parse(tick.body).content);
            });
            this.stompClient.subscribe('/user/queue/messages', (tick) => {
              console.log('tick: ', tick);
              this.received_messages.push(JSON.parse(tick.body).content);
            });
          },
          (error) => {
            console.log(error);
            this.connected = false;
          }
        );
      }
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    tickleConnection() {
      this.connected ? this.disconnect() : this.connect();
    },
  },
  created() {
    this.$store.dispatch('auth/autoLogin');
  },
  mounted() {
    this.connect();
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
