<template>
  <div class="w-full">
    <div class="w-1/2 mx-auto mt-8">
      <ul class="w-full">
        <client-card
          v-for="client of clients"
          :key="client.birthDate"
          :firstName="client.firstName"
          :lastName="client.lastName"
          :bDate="client.birthDate"
          :gender="client.gender"
          :height="client.height"
          :desc="client.description"
          class="w-full h-44 border border-darkmint rounded-md"
        ></client-card>
      </ul>
    </div>
  </div>
</template>

<script>
import ClientCard from '../components/ClientCard.vue';
export default {
  components: { ClientCard },
  data() {
    return {
      clients: [],
    };
  },
  async mounted() {
    this.clients = await this.getClients();
    console.log(this.clients);
  },
  methods: {
    async getClients() {
      const response = await fetch(
        `http://localhost:8081/api/trainer/user/${this.$store.getters['auth/userId']}/clients`,
        {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
          },
        }
      );
      console.log(response);
      if (response.ok) {
        return await response.json();
      } else {
        console.log('error getting user data');
      }
    },
  },
};
</script>
