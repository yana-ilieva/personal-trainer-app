<template>
  <li class="w-full border-b border-gray-200">
    <div class="p-3">
      <div class="w-full flex justify-between">
        <div>
          <p class="text-gray-600 text-xs">
            From: {{ type == "REQUEST_SENT" ? clientName : trainerName }}
          </p>
        </div>
        <div>
          <p class="text-gray-600 text-xs">At: {{ time }}</p>
        </div>
      </div>
      <div class="w-10/12">
        <p class="text-left text-gray-800">
          {{
            type == "REQUEST_SENT"
              ? `${clientName} sent you a request`
              : `${trainerName} accepted your request`
          }}
        </p>
      </div>
      <div class="w-full flex justify-end">
        <button
          @click="declineRequest"
          class="inline-flex mr-3 justify-center rounded-md border border-gray-300 shadow-sm px-3 py-1 text-base font-medium text-gray-900 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500 sm:col-start-2 sm:text-sm"
          v-if="type == 'REQUEST_SENT'"
        >
          Decline
        </button>
        <button
          @click="acceptRequest"
          class="inline-flex mr-3 justify-center rounded-md border border-transparent shadow-sm px-3 py-1 bg-indigo-600 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:col-start-2 sm:text-sm"
          v-if="type == 'REQUEST_SENT'"
        >
          Accept
        </button>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  props: ["type", "clientName", "trainerName", "clientId", "time"],
  methods: {
    async acceptRequest() {
      const response = await fetch(
        `http://localhost:8081/api/trainer/user/${this.$store.getters["auth/userId"]}/handle_request/${this.clientId}?accept=true`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      if (response.ok) {
        location.reload();
      } else {
        console.log("error getting user data");
      }
    },
    async declineRequest() {
      const response = await fetch(
        `http://localhost:8081/api/trainer/user/${this.$store.getters["auth/userId"]}/handle_request/${this.clientId}?accept=false`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      if (response.ok) {
        location.reload();
      } else {
        console.log("error getting user data");
      }
    },
  },
};
</script>
