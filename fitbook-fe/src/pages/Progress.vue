<template>
  <div class="w-full">
    <div
      id="defaultModal"
      tabindex="-1"
      aria-hidden="true"
      class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 w-full md:inset-0 h-modal md:h-full"
    >
      <div class="relative p-4 w-full max-w-2xl h-full md:h-auto">
        <!-- Modal content -->
        <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
          <!-- Modal header -->
          <div
            class="flex justify-between items-start p-4 rounded-t border-b dark:border-gray-600"
          >
            <h3 class="text-xl font-semibold text-gray-900 dark:text-white">
              Add today's progress
            </h3>
            <button
              type="button"
              class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center dark:hover:bg-gray-600 dark:hover:text-white"
              data-modal-toggle="defaultModal"
            >
              <svg
                class="w-5 h-5"
                fill="currentColor"
                viewBox="0 0 20 20"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  fill-rule="evenodd"
                  d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                  clip-rule="evenodd"
                ></path>
              </svg>
            </button>
          </div>
          <!-- Modal body -->
          <div class="p-6 space-y-6">
            <div>
              <label
                for="visitors"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >Weight (in kg)</label
              >
              <input
                v-model="weightInput"
                type="number"
                id="visitors"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder=""
                required
              />
            </div>
            <div>
              <label
                for="visitors"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >Calories Burnt</label
              >
              <input
                v-model="caloriesInput"
                type="number"
                id="visitors"
                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder=""
                required
              />
            </div>
          </div>
          <!-- Modal footer -->
          <div
            class="flex items-center p-6 space-x-2 rounded-b border-t border-gray-200 dark:border-gray-600"
          >
            <button
              @click="addProgress"
              data-modal-toggle="defaultModal"
              type="button"
              class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
            >
              Add
            </button>
            <button
              data-modal-toggle="defaultModal"
              type="button"
              class="text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600"
            >
              Cancel
            </button>
          </div>
        </div>
      </div>
    </div>
    <button
      data-modal-toggle="defaultModal"
      data-tooltip-target="tooltip-top"
      data-tooltip-placement="top"
      type="button"
      class="fixed right-10 bottom-10 inline-flex items-center p-4 border border-transparent rounded-full shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
    >
      <svg
        class="h-7 w-7"
        xmlns="http://www.w3.org/2000/svg"
        fill="none"
        viewBox="0 0 24 24"
        stroke-width="2"
        stroke="currentColor"
        aria-hidden="true"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="M12 6v6m0 0v6m0-6h6m-6 0H6"
        />
      </svg>
    </button>
    <div
      id="tooltip-top"
      role="tooltip"
      class="inline-block absolute invisible z-10 py-2 px-3 text-sm font-medium text-white bg-gray-900 rounded-lg shadow-sm opacity-0 tooltip dark:bg-gray-700"
    >
      Add Progress
      <div class="tooltip-arrow" data-popper-arrow></div>
    </div>
    <div
      class="shadow-lg border border-gray-100 rounded-sm w-10/12 mx-auto mt-10"
    >
      <div class="p-3 relative">
        <div class="absolute top-3 right-3"></div>
        <div class="w-full">
          <h5 class="w-max mx-auto dark:text-onDarkSurface text-lg">
            Your Line Chart
          </h5>
        </div>
        <div class="mx-auto mb-4">
          <line-chart
            :client="client"
            :weight="weight"
            :calories="caloriesBurnt"
            :bmi="bmi"
          ></line-chart>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LineChart from "../components/LineChart.vue";
export default {
  components: { LineChart },

  data() {
    return {
      progresses: [],
      weightInput: 0,
      caloriesInput: 0,
      caloriesBurnt: [100, 200, 300],
      weight: [80, 85, 90],
      bmi: [20, 30, 40],
      client: null,
      isAddProgress: false,
    };
  },
  async mounted() {
    this.client = await this.getClientId();
  },
  methods: {
    async addProgress(e) {
      console.log(
        JSON.stringify({
          clientId: this.client,
          weight: this.weightInput,
          caloriesBurned: this.caloriesInput,
        })
      );
      const response = await fetch(`http://localhost:8081/api/progress`, {
        method: "POST",
        mode: "cors",
        headers: {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          clientId: this.client,
          weight: this.weightInput,
          caloriesBurned: this.caloriesInput,
        }),
      });
      if (response.ok) {
        location.reload();
      } else {
        console.log("error adding progress");
      }
    },
    async getClientId() {
      const response = await fetch(
        `http://localhost:8081/api/client/user/${this.$store.getters["auth/userId"]}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      if (response.ok) {
        let responseData = await response.json();
        return responseData.id;
      } else {
        console.log("error getting user data");
      }
    },
    async getProgresses() {
      const response = await fetch(
        `http://localhost:8081/api/client/${this.client}/progress`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      if (response.ok) {
        let responseData = await response.json();
        for (const progress of responseData) {
          this.weight.push(progress.weight);
          this.caloriesBurnt.push(progress.caloriesBurned);
          this.bmi.push(progress.bmi);
        }
        return responseData;
      } else {
        console.log("error getting progresses");
      }
    },
  },
};
</script>
