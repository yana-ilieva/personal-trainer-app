<template>
  <div class="w-full relative">
    <assign-program-modal
      v-if="isAssignModalOpen"
      :name="assignClient.firstName + ' ' + assignClient.lastName"
      @assign="assignProgram"
      @closeModal="closeAssignModal"
    >
    </assign-program-modal>
    <div class="w-10/12 mx-auto mt-8">
      <div
        v-if="clients.length > 0"
        class="mt-5 w-full mx-auto bg-white shadow overflow-hidden sm:rounded-md"
      >
        <ul role="list" class="divide-y divide-gray-200">
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
            :photo="client.photo"
            :program="client.program"
            @initializeChat="initializeChat(client.id)"
            @openAssignProgram="openAssignModal(client.id)"
          ></client-card>
        </ul>
      </div>
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
import AssignProgramModal from "../components/AssignProgramModal";
export default {
  components: { ClientCard, AssignProgramModal },
  data() {
    return {
      user: {},
      clients: [],
      assignClient: {},
      isAssignModalOpen: false,
    };
  },
  async mounted() {
    this.clients = await this.getClients();
    this.user = await this.getUser();
    console.log("clients:", this.clients);
  },
  methods: {
    redirectToEditProfile() {
      this.$router.push({ path: "/my-profile" });
    },
    openAssignModal(id) {
      this.assignClient = this.clients.find((el) => el.id === id);
      this.isAssignModalOpen = true;
    },
    closeAssignModal() {
      this.isAssignModalOpen = false;
    },
    async assignProgram(program) {
      const response = await fetch(
        `http://localhost:8081/api/client/${this.assignClient.id}/program/${program}`,
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
        console.error("error assigning program");
      }
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
    async fetchUserPhoto(id) {
      const res = await fetch(`http://localhost:8081/api/file/${id}`, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
        },
      });
      if (res.ok) {
        const resData = await res.blob();
        const imgUrl = URL.createObjectURL(resData);
        return imgUrl;
      } else {
        return null;
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
        const data = await response.json();
        for (const client of data) {
          const photo = await this.fetchUserPhoto(client.profilePictureId);
          if (photo) {
            client.photo = photo;
          } else {
            client.photo = null;
          }
          const program = await this.getProgram(client.id);
          if (program) {
            client.program = program.programDto;
          } else {
            client.program = null;
          }
        }
        return data;
      } else {
        console.log("error getting user data");
      }
    },
    async initializeChat(id) {
      const response = await fetch(`http://localhost:8081/api/chat`, {
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
        const data = await response.json();
        this.$router.push({
          path: "chat",
          query: { user: data.client.name, id: data.id },
        });
      } else {
        console.log("error getting user data");
      }
    },
    async getProgram(id) {
      const response = await fetch(`http://localhost:8081/api/client/${id}`, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
        },
      });
      if (response.ok) {
        return await response.json();
      } else {
        console.log("error getting user data");
      }
    },
  },
};
</script>
