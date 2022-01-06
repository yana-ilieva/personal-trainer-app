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
export default {
  components: { TheHeader, Notifications },
  data() {
    return {
      isNotifications: false,
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
