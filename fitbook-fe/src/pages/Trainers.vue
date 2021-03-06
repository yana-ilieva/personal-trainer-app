<template>
  <div class="w-full">
    <div class="w-10/12 mx-auto px-4">
      <div class="w-10/12 mx-auto mt-8">
        <h4 class="text-lg mb-2">Filter By:</h4>
        <form
          @submit.prevent="submitSearchForm"
          class="flex flex-col items-start"
        >
          <div class="self-center w-full relative mb-4">
            <svg
              class="absolute left-2 top-0 bottom-0 my-auto"
              xmlns="http://www.w3.org/2000/svg"
              height="20px"
              viewBox="0 0 24 24"
              width="20px"
              fill="#000000"
            >
              <path d="M0 0h24v24H0V0z" fill="none" />
              <path
                d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"
              />
            </svg>
            <input
              class="w-full pl-9 py-1 bg-gray-100 rounded-md"
              placeholder="search by name"
              type="text"
              name="search"
              id="search"
            />
          </div>
          <input
            class="self-start px-3 py-0.5 bg-gray-100 rounded-md mb-4"
            type="text"
            name="addressFilter"
            id="addressFilter"
            placeholder="Address"
          />

          <div class="flex items-center self-start">
            <input
              class="mr-1"
              type="radio"
              name="genderFilter"
              id="genderFilterMale"
              value="Male"
            />
            <label for="genderFilterMale">Male</label>
          </div>
          <div class="flex items-center self-start">
            <input
              class="mr-1"
              type="radio"
              name="genderFilter"
              id="genderFilterFemale"
              value="Female"
            />
            <label for="genderFilterFemale">Female</label>
          </div>
          <div class="flex items-center self-start">
            <input
              class="mr-1"
              type="radio"
              name="genderFilter"
              id="genderFilterAll"
              value="All"
              checked
            />
            <label for="genderFilterAll">All</label>
          </div>
          <input
            class="self-start w-max mt-2 cursor-pointer underline bg-transparent"
            type="submit"
            value="Filter"
          />
        </form>
      </div>
    </div>
    <!-- This example requires Tailwind CSS v2.0+ -->
    <div
      class="mt-5 w-10/12 mx-auto bg-white shadow overflow-hidden sm:rounded-md"
    >
      <ul role="list" class="divide-y divide-gray-200">
        <trainer-card
          v-for="trainer in trainers"
          :key="trainer.id"
          :id="trainer.id"
          :photo="trainer.photo"
          :name="trainer.firstName + ' ' + trainer.lastName"
          :bDate="trainer.birthDate"
          :gender="trainer.gender"
          :desc="trainer.description"
          @sendRequest="sendRequest(trainer.id)"
          @initializeChat="initializeChat(trainer.id)"
        ></trainer-card>
      </ul>
    </div>
  </div>
</template>

<script>
import TrainerCard from "../components/TrainerCard.vue";

export default {
  components: { TrainerCard },
  data() {
    return {
      trainers: [],
      currentUser: {},
    };
  },
  async mounted() {
    if (!this.$store.getters["connection/isConnected"]) {
      this.$emit("initWs");
    }
    this.trainers = await this.getTrainers();
    this.currentUser = await this.getUser();
  },
  methods: {
    async getTrainers() {
      const response = await fetch(
        `http://localhost:8081/api/trainer?page=0&size=20`,
        {
          method: "POST",
          mode: "cors",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify({}),
        }
      );
      console.log(response);
      if (response.ok) {
        const data = await response.json();
        for (const trainer of data) {
          const photo = await this.fetchUserPhoto(trainer.profilePictureId);
          if (photo) {
            trainer.photo = photo;
          } else {
            trainer.photo = null;
          }
        }
        return data;
      } else {
        console.log("error getting trainers data");
        return [];
      }
    },
    async getUser() {
      const response = await fetch(
        `http://localhost:8081/api/client/user/${this.$store.getters["auth/userId"]}`,
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
    async submitSearchForm(e) {
      const response = await fetch(
        `http://localhost:8081/api/trainer?page=0&size=20`,
        {
          method: "POST",
          mode: "cors",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            name: e.target.search.value,
            city: e.target.addressFilter.value,
            gender:
              e.target.genderFilter.value == "All"
                ? null
                : e.target.genderFilter.value.toUpperCase(),
          }),
        }
      );
      if (response.ok) {
        const data = await response.json();
        for (const trainer of data) {
          const photo = await this.fetchUserPhoto(trainer.profilePictureId);
          if (photo) {
            trainer.photo = photo;
          } else {
            trainer.photo = null;
          }
        }
        this.trainers = data;
      } else {
        console.log("error getting trainers data");
        this.trainers = [];
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
    async initializeChat(id) {
      console.log("initialize id: ", this.currentUser);
      const response = await fetch(`http://localhost:8081/api/chat`, {
        method: "POST",
        mode: "cors",
        headers: {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          trainer: { id },
          client: { id: this.currentUser.id },
        }),
      });
      console.log(response);
      if (response.ok) {
        const data = await response.json();
        this.$router.push({
          path: "chat",
          query: { user: data.trainer.name, id: data.id },
        });
      } else {
        console.log("error getting user data");
      }
    },
    async sendRequest(id) {
      console.log("trainer id: ", id);
      const response = await fetch(
        `http://localhost:8081/api/trainer/${id}/request/user/${this.$store.getters["auth/userId"]}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      if (response.ok) {
        alert("Request sent Successfully.");
      } else {
        alert("Failure. Try again.");
      }
    },
  },
};
</script>
