<template>
  <li class="w-full border-b border-darkmint">
    <div class="p-3">
      <div class="w-full flex justify-between">
        <div>
          <p class="text-mint text-xs">
            From: {{ type == 'REQUEST_SENT' ? clientName : trainerName }}
          </p>
        </div>
        <div>
          <p class="text-mint text-xs">At: {{ time }}</p>
        </div>
      </div>
      <div class="w-10/12">
        <p class="text-left">
          {{
            type == 'REQUEST_SENT'
              ? `${clientName} sent you a request`
              : `${trainerName} accepted your request`
          }}
        </p>
      </div>
      <button
        @click="acceptRequest"
        class="px-3 rounded-md transition-all text-syellow bg-darkmint hover:bg-darkermint"
        v-if="type == 'REQUEST_SENT'"
      >
        Accept
      </button>
    </div>
  </li>
</template>

<script>
export default {
  props: ['type', 'clientName', 'trainerName', 'clientId', 'time'],
  methods: {
    async getTrainerId() {
      const response = await fetch(
        `http://localhost:8081/api/trainer/user/${this.$store.getters['auth/userId']}`,
        {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
          },
        }
      );
      if (response.ok) {
        let responseData = await response.json();
        return responseData.id;
      } else {
        console.log('error getting user data');
      }
    },
    async acceptRequest() {
      let trainerId = await this.getTrainerId();
      const response = await fetch(
        `http://localhost:8081/api/trainer/user/${trainerId}/handle_request/${this.clientId}`,
        {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
          },
        }
      );
      if (response.ok) {
        location.reload();
      } else {
        console.log('error getting user data');
      }
    },
  },
};
</script>
