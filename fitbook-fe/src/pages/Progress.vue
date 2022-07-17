<template>
  <div class="w-full relative">
    <div
      v-if="isAddProgress"
      class="relative z-10"
      aria-labelledby="modal-title"
      role="dialog"
      aria-modal="true"
    >
      <div
        class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"
      ></div>

      <div class="fixed z-10 inset-0 overflow-y-auto">
        <div
          class="flex items-end sm:items-center justify-center min-h-full p-4 text-center sm:p-0"
        >
          <div class="bg-white shadow sm:rounded-lg z-20 w-72">
            <div class="px-4 py-5 sm:p-6">
              <h3 class="text-lg leading-6 font-medium text-gray-900">
                Add Progress For Today
              </h3>

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
              <div class="flex items-center justify-center p-6 space-x-2">
                <button
                  @click="addProgress"
                  data-modal-toggle="defaultModal"
                  type="button"
                  class="text-white bg-indigo-700 hover:bg-indigo-800 focus:ring-4 focus:outline-none focus:ring-indigo-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-indigo-600 dark:hover:bg-indigo-700 dark:focus:ring-indigo-800"
                >
                  Add
                </button>
                <button
                  @click="closeAddProgressModal"
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
      </div>
    </div>

    <button
      @click="openAddProgressModal"
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
      caloriesBurnt: [],
      weight: [],
      bmi: [],
      client: null,
      isAddProgress: false,
    };
  },
  async mounted() {
    this.client = await this.getClientId();
  },
  methods: {
    openAddProgressModal() {
      this.isAddProgress = true;
    },
    closeAddProgressModal() {
      this.isAddProgress = false;
    },
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
      let userId = this.$store.getters["auth/userId"];
      if (!userId) {
        userId = localStorage.getItem("id");
      }
      const response = await fetch(
        `http://localhost:8081/api/client/user/${userId}`,
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
