<template>
  <div>
    <div class="flex min-h-screen">
      <the-header
        v-if="!$route.meta.hideHeader"
        @showNotifications="showNotifications"
      ></the-header>
      <div
        v-if="isNotificationPopupOpen"
        id="alert-border-3"
        class="absolute top-10 w-9/12 left-0 right-0 mx-auto flex p-4 mb-4 bg-green-100 border-t-4 border-green-500 dark:bg-green-200 z-50"
        role="alert"
      >
        <svg
          class="flex-shrink-0 w-5 h-5 text-green-700"
          fill="currentColor"
          viewBox="0 0 20 20"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            fill-rule="evenodd"
            d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z"
            clip-rule="evenodd"
          ></path>
        </svg>
        <div class="ml-3 text-sm font-medium text-green-700">
          {{ notification.message }}
        </div>
        <button
          @click="closeNotificationPopup"
          type="button"
          class="ml-auto -mx-1.5 -my-1.5 bg-green-100 dark:bg-green-200 text-green-500 rounded-lg focus:ring-2 focus:ring-green-400 p-1.5 hover:bg-green-200 dark:hover:bg-green-300 inline-flex h-8 w-8"
          data-dismiss-target="#alert-border-3"
          aria-label="Close"
        >
          <span class="sr-only">Dismiss</span>
          <svg
            class="w-5 h-5"
            fill="currentColor"
            viewBox="0 0 20 20"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fill-rule="evenodd"
              d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
              clip-rule="evenodd"
            ></path>
          </svg>
        </button>
      </div>
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
                v-for="notification of notifications"
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
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import NotificationCard from "./components/NotificationCard.vue";
import TheHeader from "./components/TheHeader.vue";
export default {
  components: { TheHeader, NotificationCard },
  data() {
    return {
      notification: {
        id: 0,
        message: "",
      },
      isNotificationsOpen: false,
      isNotificationPopupOpen: false,
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
    async showNotifications() {
      this.notifications = await this.getNotifications();
      this.isNotificationsOpen = true;
    },
    hideNotifications() {
      this.isNotificationsOpen = false;
    },
    closeNotificationPopup() {
      this.isNotificationPopupOpen = false;
    },
    subscribe() {
      const token = this.$store.getters["auth/token"];
      this.socket = new SockJS("http://localhost:8081/ws");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {
          Authorization: `Bearer ${token}`,
        },
        () => {
          this.connected = true;
          this.stompClient.subscribe("/user/queue/notifications", (tick) => {
            const notification = JSON.parse(tick.body);
            console.log("websocket notification: ", notification);
            this.notification.message =
              notification.notificationType == "REQUEST_SENT"
                ? `${notification.clientName} send you a request!`
                : `${notification.trainerName} accepted your request`;
            this.notification.id = notification.id;
            this.isNotificationPopupOpen = true;
            setTimeout(() => {
              this.isNotificationPopupOpen = false;
            }, 7000);
          });
        },
        (error) => {
          console.log(error);
          this.connected = false;
        }
      );
    },
  },
  created() {
    this.$store.dispatch("auth/autoLogin");
  },
  async mounted() {
    console.log("notifications: ", this.notifications);
    this.subscribe();
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
