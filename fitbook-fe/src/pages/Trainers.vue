<template>
  <div class="w-full flex">
    <div class="w-3/12 px-4">
      <div class="w-full mt-8">
        <form @submit.prevent="submitSearchForm" action="">
          <h4 class="text-lg mb-2">Filter By:</h4>
          <div class="relative mb-4">
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
            class="w-max mt-4 cursor-pointer transition-all px-4 py-1 text-lg rounded-md bg-mint text-syellow hover:bg-darkmint"
            type="submit"
            value="Filter"
          />
        </form>
      </div>
    </div>
    <div class="w-7/12 ml-10 mt-8">
      <ul class="w-full">
        <trainer-card
          v-for="trainer in trainers"
          :key="trainer.id"
          :name="trainer.firstName + ' ' + trainer.lastName"
          :bDate="trainer.birthDate"
          :gender="trainer.gender"
          :desc="trainer.description"
          class="w-full h-44 border border-darkmint rounded-md"
        ></trainer-card>
      </ul>
    </div>
  </div>
</template>

<script>
import TrainerCard from '../components/TrainerCard.vue';
export default {
  components: { TrainerCard },
  data() {
    return {
      trainers: [],
    };
  },
  async mounted() {
    this.trainers = await this.getTrainers();
  },
  methods: {
    async getTrainers() {
      const response = await fetch(
        `http://localhost:8081/api/trainer?page=0&size=10`,
        {
          method: 'POST',
          mode: 'cors',
          headers: {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({}),
        }
      );
      console.log(response);
      if (response.ok) {
        return await response.json();
      } else {
        console.log('error getting user data');
      }
    },
    async submitSearchForm(e) {
      console.log({
        name: e.target.search.value,
        city: e.target.addressFilter.value,
        gender: e.target.genderFilter.value,
      });
      const response = await fetch(
        `http://localhost:8081/api/trainer?page=0&size=10`,
        {
          method: 'POST',
          mode: 'cors',
          headers: {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            name: e.target.search.value,
            city: e.target.addressFilter.value,
            gender: e.target.genderFilter.value,
          }),
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
