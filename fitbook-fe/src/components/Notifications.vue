<template>
  <div class="z-30">
    <div
      @click="$emit('hideNotifications')"
      class="absolute top-2 right-2 cursor-pointer"
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        height="24px"
        viewBox="0 0 24 24"
        width="24px"
        fill="#547d6d"
      >
        <path d="M0 0h24v24H0V0z" fill="none" />
        <path
          d="M19 6.41L17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12 19 6.41z"
        />
      </svg>
    </div>
    <ul class="mt-8">
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
</template>

<script>
import NotificationCard from './NotificationCard.vue';
export default {
  components: { NotificationCard },
  emits: ['hideNotifications'],
  data() {
    return {
      notifications: [],
    };
  },
  async mounted() {
    this.notifications = await this.getNotifications();
    console.log(this.notifications);
  },
  methods: {
    async getNotifications() {
      const response = await fetch(
        `http://localhost:8081/api/notification/${this.$store.getters['auth/userId']}`,
        {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
          },
        }
      );
      if (response.ok) {
        let responseData = await response.json();
        return responseData;
      } else {
        console.log('error getting user data');
      }
    },
  },
};
</script>
