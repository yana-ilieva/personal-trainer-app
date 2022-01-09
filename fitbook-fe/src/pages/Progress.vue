<template>
  <div class="w-full">
    <div class="mt-10 w-9/12 mx-auto border-2 border-darkmint rounded-md">
      <h3 class="text-center mx-auto text-2xl my-4">Progress</h3>
      <div>
        <ul>
          <progress-card
            v-for="progress of progresses"
            :key="progress.id"
            :weight="progress.weight"
            :time="progress.createdTimestamp"
            :chest="progress.chestMeasurement"
            :arm="progress.upperArmMeasurement"
            :hip="progress.hipMeasurement"
            :thigh="progress.thighMeasurement"
            :waist="progress.waistMeasurement"
          ></progress-card>
        </ul>
        <button
          @click="addProgressFormOpen"
          class="my-5 rounded-md px-3 py-0.5 bg-darkmint text-syellow hover:bg-darkermint cursor-pointer"
        >
          Add
        </button>
      </div>
      <form
        v-if="isAddProgress"
        @submit.prevent="addProgress"
        action=""
        class="mt-5"
      >
        <input
          class="mb-2 px-3 py-0.5 border-2 border-black rounded-md"
          type="text"
          name=""
          id="weight"
          placeholder="Weight"
        />
        <input
          class="mb-2 px-3 py-0.5 border-2 border-black rounded-md"
          type="text"
          name=""
          id="chestMeasurement"
          placeholder="Chest Measurement"
        />
        <input
          class="mb-2 px-3 py-0.5 border-2 border-black rounded-md"
          type="text"
          name=""
          id="waistMeasurement"
          placeholder="Waist Measurement"
        />
        <input
          class="mb-2 px-3 py-0.5 border-2 border-black rounded-md"
          type="text"
          name=""
          id="hipMeasurement"
          placeholder="Hip Measurement"
        />
        <input
          class="mb-2 px-3 py-0.5 border-2 border-black rounded-md"
          type="text"
          name=""
          id="thighMeasurement"
          placeholder="Thigh Measurement"
        />
        <input
          class="mb-2 px-3 py-0.5 border-2 border-black rounded-md"
          type="text"
          name=""
          id="upperArmMeasurement"
          placeholder="UpperArm Measurement"
        />
        <input
          class="px-3 py-0.5 bg-darkmint text-syellow hover:bg-darkermint cursor-pointer"
          type="submit"
          value="Submit"
        />
      </form>
    </div>
  </div>
</template>

<script>
import ProgressCard from '../components/ProgressCard.vue';
export default {
  components: { ProgressCard },
  data() {
    return {
      progresses: [],
      client: null,
      isAddProgress: false,
    };
  },
  async mounted() {
    this.client = await this.getClientId();
    this.progresses = await this.getProgresses();
    console.log(this.progresses);
  },
  methods: {
    addProgressFormOpen() {
      this.isAddProgress = true;
    },
    async addProgress(e) {
      console.log(
        JSON.stringify({
          clientId: this.client,
          weight: e.target.weight.value,
          chestMeasurement: e.target.chestMeasurement.value,
          waistMeasurement: e.target.waistMeasurement.value,
          hipMeasurement: e.target.hipMeasurement.value,
          thighMeasurement: e.target.thighMeasurement.value,
          upperArmMeasurement: e.target.upperArmMeasurement.value,
        })
      );
      const response = await fetch(`http://localhost:8081/api/progress`, {
        method: 'POST',
        mode: 'cors',
        headers: {
          Authorization: `Bearer ${this.$store.getters['auth/token']}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          clientId: this.client,
          weight: e.target.weight.value,
          chestMeasurement: e.target.chestMeasurement.value,
          waistMeasurement: e.target.waistMeasurement.value,
          hipMeasurement: e.target.hipMeasurement.value,
          thighMeasurement: e.target.thighMeasurement.value,
          upperArmMeasurement: e.target.upperArmMeasurement.value,
        }),
      });
      if (response.ok) {
        let responseData = await response.json();

        location.reload();
        console.log(responseData);
      } else {
        console.log('error getting user data');
      }
    },
    async getClientId() {
      const response = await fetch(
        `http://localhost:8081/api/client/user/${this.$store.getters['auth/userId']}`,
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
    async getProgresses() {
      const response = await fetch(
        `http://localhost:8081/api/client/${this.client}/progress`,
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
