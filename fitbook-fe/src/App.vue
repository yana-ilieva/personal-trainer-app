<template>
  <div>
    <div>
      <the-header
        v-if="!$route.meta.hideHeader"
        @showNotifications="showNotifications"
      ></the-header>
      <notifications
        v-if="isNotifications"
        @hideNotifications="hideNotifications"
        class="absolute top-0 right-0 w-1/4 h-screen overflow-auto bg-gray-100"
      ></notifications>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import Notifications from "./components/Notifications.vue";
import TheHeader from "./components/TheHeader.vue";
export default {
  components: { TheHeader, Notifications },
  data() {
    return {
      isNotifications: false,
      received_messages: [],
      received_notifications: [],
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
  },
  created() {
    this.$store.dispatch("auth/autoLogin");
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
