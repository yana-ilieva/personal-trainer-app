<template>
  <div class="w-full">
    <div class="w-1/2 mx-auto mt-8">
      <ul v-if="clients.length > 0" class="w-full">
        <client-card
          v-for="client of clients"
          :key="client.id"
          :id="client.id"
          :firstName="client.firstName"
          :lastName="client.lastName"
          :bDate="client.birthDate"
          :gender="client.gender"
          :height="client.height"
          :desc="client.description"
          @initializeChat="initializeChat(client.id)"
          class="w-full h-44 border border-darkmint rounded-md"
        ></client-card>
      </ul>
      <div v-else>
        <div class="text-center">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-12 w-12 mx-auto text-gray-400"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            stroke-width="2"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"
            />
          </svg>
          <h3 class="mt-2 font-medium text-gray-900">
            Currently you have no clients
          </h3>
          <p class="mt-1 text-sm text-gray-500">
            You can
            <span
              @click="redirectToEditProfile"
              class="cursor-pointer text-blue-500 underline"
              >edit</span
            >
            your profile information to look more appealing or check your
            <span
              @click="this.$emit('openNotifications')"
              class="cursor-pointer text-blue-500 underline"
              >notifications</span
            >
            to see if somebody send you request
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ClientCard from "../components/ClientCard.vue";
export default {
  components: { ClientCard },
  data() {
    return {
      user: {},
      clients: [],
    };
  },
  async mounted() {
    this.clients = await this.getClients();
    this.user = await this.getUser();
  },
  methods: {
    redirectToEditProfile() {
      this.$router.push({ path: "/my-profile" });
    },
    async getUser() {
      const response = await fetch(
        `http://localhost:8081/api/trainer/user/${this.$store.getters["auth/userId"]}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      console.log(response);
      if (response.ok) {
        return await response.json();
      } else {
        console.log("error getting user data");
      }
    },
    async getClients() {
      const response = await fetch(
        `http://localhost:8081/api/trainer/user/${this.$store.getters["auth/userId"]}/clients`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      console.log(response);
      if (response.ok) {
        return await response.json();
      } else {
        console.log("error getting user data");
      }
    },
    async initializeChat(id) {
      console.log(id);
      const response = await fetch(`http://localhost:8081/api/chat/`, {
        method: "POST",
        mode: "cors",
        headers: {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          trainer: { id: this.user.id },
          client: { id },
        }),
      });
      console.log(response);
      if (response.ok) {
        console.log(await response.json());
      } else {
        console.log("error getting user data");
      }
    },
  },
};
</script>
