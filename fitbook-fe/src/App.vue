<template>
  <div>
    <div class="flex min-h-screen">
      <the-header
        v-if="!$route.meta.hideHeader"
        @showNotifications="showNotifications"
      ></the-header>
      <div
        :class="isNotificationsOpen ? 'translate-x-0' : 'translate-x-full'"
        class="fixed top-0 right-0 bottom-0 transform transition ease-in-out duration-500 sm:duration-700 pointer-events-auto w-screen max-w-md z-50"
      >
        <div
          class="flex h-full flex-col overflow-y-scroll bg-white dark:bg-darkSurface dark:text-onDarkSurface py-6 shadow-xl"
        >
          <div class="px-4 sm:px-6">
            <div class="flex items-start justify-between">
              <h2
                class="text-lg font-medium text-onLightBg dark:text-onDarkSurface"
                id="slide-over-title"
              >
                Notifications
              </h2>
              <div class="ml-3 flex h-7 items-center">
                <button
                  @click="hideNotifications"
                  type="button"
                  class="rounded-md bg-white dark:bg-darkSurface text-onLightBg dark:text-onDarkSurface focus:outline-none focus:ring-2 focus:ring-secondary focus:ring-offset-2"
                >
                  <span class="sr-only">Close panel</span>
                  <!-- Heroicon name: outline/x -->
                  <svg
                    class="h-6 w-6"
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke-width="2"
                    stroke="currentColor"
                    aria-hidden="true"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M6 18L18 6M6 6l12 12"
                    />
                  </svg>
                </button>
              </div>
            </div>
          </div>
          <div class="relative mt-6 flex-1 px-4 sm:px-6">
            <ul role="list">
              <notification-card
                v-for="notification in notifications"
                :key="notification.id"
                :clientName="notification.clientName"
                :trainerName="notification.trainerName"
                :type="notification.notificationType"
                :time="notification.createdTimeStamp"
                :clientId="notification.clientId"
              ></notification-card>
            </ul>
          </div>
        </div>
      </div>
      <router-view @openNotifications="showNotifications"></router-view>
    </div>
  </div>
</template>

<script>
import NotificationCard from "./components/NotificationCard.vue";
import TheHeader from "./components/TheHeader.vue";
export default {
  components: { TheHeader, NotificationCard },
  data() {
    return {
      isNotificationsOpen: false,
      received_messages: [],
      received_notifications: [],
      send_message: null,
      connected: false,
      notifications: [],
    };
  },
  methods: {
    async getNotifications() {
      const response = await fetch(
        `http://localhost:8081/api/notification/${this.$store.getters["auth/userId"]}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      if (response.ok) {
        let responseData = await response.json();
        return responseData;
      } else {
        console.log("error getting user data");
      }
    },
    showNotifications() {
      this.isNotificationsOpen = true;
    },
    hideNotifications() {
      this.isNotificationsOpen = false;
    },
  },
  created() {
    this.$store.dispatch("auth/autoLogin");
  },
  async mounted() {
    this.notifications = await this.getNotifications();
    console.log(this.notifications);
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
