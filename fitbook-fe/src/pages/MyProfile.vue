<template>
  <div class="relative">
    <div
      v-if="isEdit"
      class="absolute w-full h-full top-0 left-0 z-10"
      style="background-color: rgba(0, 0, 0, 0.6)"
    ></div>
    <div class="w-full py-16">
      <div class="w-full absolute top-10 left-0 z-20" v-if="isEdit">
        <div class="mx-auto w-7/12 p-6 bg-white">
          <form @submit.prevent="submitEditUser" action="">
            <div
              v-if="role === 'ROLE_CLIENT'"
              class="flex flex-col items-center"
            >
              <input
                :value="user.firstName"
                class="mb-4 border-b border-black"
                type="text"
                name="firstNameClient"
                id="firstNameClient"
                placeholder="First Name"
              />
              <input
                :value="user.lastName"
                class="mb-4 border-b border-black"
                type="text"
                name="lastNameClient"
                id="lastNameClient"
                placeholder="Last Name"
              />
              <input
                :value="user.birthDate"
                class="mb-4 border-b border-black"
                type="text"
                name="birthDateClient"
                id="birthDateClient"
                placeholder="Birth Date"
              />
              <input
                :value="user.gender"
                class="mb-4 border-b border-black"
                type="text"
                name="genderClient"
                id="genderClient"
                placeholder="Gender"
              />
              <input
                :value="user.height"
                class="mb-4 border-b border-black"
                type="text"
                name="heightClient"
                id="heightClient"
                placeholder="Height"
              />
              <textarea
                :value="user.description"
                class="w-1/3 mb-4 rounded-md border border-black"
                type="text"
                name="descClient"
                id="descClient"
                placeholder="Description"
              />
            </div>
            <div
              v-if="role === 'ROLE_TRAINER'"
              class="flex flex-col items-center"
            >
              <input
                :value="user.firstName"
                class="mb-4 border-b border-black"
                type="text"
                name="firstNameTrainer"
                id="firstNameTrainer"
                placeholder="First Name"
              />
              <input
                :value="user.lastName"
                class="mb-4 border-b border-black"
                type="text"
                name="lastNameTrainer"
                id="lastNameTrainer"
                placeholder="Last Name"
              />
              <input
                :value="user.birthDate"
                class="mb-4 border-b border-black"
                type="text"
                name="birthDateTrainer"
                id="birthDateTrainer"
                placeholder="Birth Date"
              />
              <input
                :value="user.gender"
                class="mb-4 border-b border-black"
                type="text"
                name="genderTrainer"
                id="genderTrainer"
                placeholder="Gender"
              />
              <textarea
                :value="user.description"
                class="w-1/3 mb-4 rounded-md border border-black"
                type="text"
                name="descTrainer"
                id="descTrainer"
                placeholder="Description"
              />
              <input
                :value="user.city"
                class="mb-4 border-b border-black"
                type="text"
                name="cityTrainer"
                id="cityTrainer"
                placeholder="City"
              />
              <input
                :value="user.neighborhood"
                class="mb-4 border-b border-black"
                type="text"
                name="neighTrainer"
                id="neighTrainer"
                placeholder="Neighborhood"
              />
            </div>
            <div class="mt-5">
              <input
                class="mr-4 px-3 py-0.5 bg-darkmint rounded-md hover:darkermint text-darksyellow"
                type="submit"
                value="Save"
              />
              <button
                @click="closeEdit"
                class="px-3 py-0.5 bg-red-500 rounded-md hover:bg-red-600 text-white"
              >
                Cancel
              </button>
            </div>
          </form>
        </div>
      </div>
      <div class="mx-auto w-1/2 flex flex-col items-center">
        <div class="w-10/12 h-72 bg-mint">
          <img src="" alt="" />
        </div>
        <div
          v-if="role === 'ROLE_TRAINER'"
          class="mt-4 flex flex-col justify-center items-center"
        >
          <p class="mb-3">First Name: {{ user.firstName || '' }}</p>
          <p class="mb-3">Last Name: {{ user.lastName || '' }}</p>
          <p class="mb-3">Birth Date: {{ user.birthDate || '' }}</p>
          <p class="mb-3">Gender: {{ user.gender || '' }}</p>
          <p class="mb-3">Description: {{ user.description || '' }}</p>
          <p class="mb-3">City: {{ user.city || '' }}</p>
          <p class="mb-3">Neighborhood: {{ user.neighborhood || '' }}</p>
        </div>
        <div
          v-if="role === 'ROLE_CLIENT'"
          class="mt-4 flex flex-col justify-center items-center"
        >
          <p class="mb-3">First Name: {{ user.firstName || '' }}</p>
          <p class="mb-3">Last Name: {{ user.lastName || '' }}</p>
          <p class="mb-3">Birth Date: {{ user.birthDate || '' }}</p>
          <p class="mb-3">Gender: {{ user.gender || '' }}</p>
          <p class="mb-3">Height: {{ user.height || '' }}</p>
          <p class="mb-3">Description: {{ user.description || '' }}</p>
        </div>
        <div class="mt-8">
          <button
            @click="openEdit"
            class="text-xl px-8 py-3 bg-darkmint text-syellow hover:bg-syellow hover:text-darkmint transition-all rounded-md"
          >
            Edit
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {},
      isEdit: false,
    };
  },
  async mounted() {
    this.user = await this.getUser();
  },
  computed: {
    role() {
      console.log(this.$store.getters['auth/role']);
      return this.$store.getters['auth/role'];
    },
  },
  methods: {
    openEdit() {
      this.isEdit = true;
    },
    closeEdit() {
      this.isEdit = false;
    },
    parseDate(str) {
      let arr = str.split('-');
      let reversed = arr.reverse().join('-');
      return new Date(reversed).toISOString().split('T')[0];
    },
    async submitEditUser(e) {
      let url = '';
      let body = {};
      console.log();
      if (this.$store.getters['auth/role'] === 'ROLE_TRAINER') {
        url = `http://localhost:8081/api/trainer/${this.user.id}`;
        body = {
          firstName: e.target.firstNameTrainer.value,
          lastName: e.target.lastNameTrainer.value,
          gender: e.target.genderTrainer.value,
          birthDate: this.parseDate(e.target.birthDateClient.value),
          description: e.target.descTrainer.value,
          city: e.target.cityTrainer.value,
          neighborhood: e.target.neighTrainer.value,
        };
      } else {
        url = `http://localhost:8081/api/client/${this.user.id}`;
        body = {
          firstName: e.target.firstNameClient.value,
          lastName: e.target.lastNameClient.value,
          gender: e.target.genderClient.value,
          birthDate: this.parseDate(e.target.birthDateClient.value),
          description: e.target.descClient.value,
          height: +e.target.heightClient.value,
        };
      }
      console.log(body);
      const response = await fetch(url, {
        method: 'PUT',
        mode: 'cors',
        headers: {
          Authorization: `Bearer ${this.$store.getters['auth/token']}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          ...body,
        }),
      });
      console.log(response);
      if (response.ok) {
        const responseData = await response.json();
        this.user = responseData;
      } else {
        console.log('error editing user');
      }
      this.isEdit = false;
    },
    async getUser() {
      let url = '';
      if (this.$store.getters['auth/role'] === 'ROLE_TRAINER') {
        url = `http://localhost:8081/api/trainer/user/${this.$store.getters['auth/userId']}`;
      } else {
        url = `http://localhost:8081/api/client/user/${this.$store.getters['auth/userId']}`;
      }
      const response = await fetch(url, {
        method: 'GET',
        headers: {
          Authorization: `Bearer ${this.$store.getters['auth/token']}`,
        },
      });
      console.log(response);
      if (response.ok) {
        const responseData = await response.json();
        return responseData;
      } else {
        console.log('error getting user data');
        return {
          firstName: '',
          lastName: '',
          birthDate: '',
          gender: '',
          height: '',
          description: '',
          city: '',
          neighborhood: '',
        };
      }
    },
  },
};
</script>
