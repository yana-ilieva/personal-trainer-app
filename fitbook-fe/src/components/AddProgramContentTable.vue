<template>
  <div class="w-full h-52 max-h-fit flex justify-center items-center">
    <!-- This example requires Tailwind CSS v2.0+ -->
    <div v-if="excercises.length == 0" class="text-center">
      <h3 class="mt-2 text-sm font-medium text-gray-900">No exercises</h3>
      <p class="mt-1 text-sm text-gray-500">
        Leave it like this to count it as a rest day.
      </p>
      <div class="mt-6">
        <button
          type="button"
          class="inline-flex items-center px-3 py-1 border border-gray-300 shadow-sm text-sm font-medium rounded-md text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
        >
          <!-- Heroicon name: solid/plus -->
          <svg
            class="-ml-1 mr-2 h-4 w-4"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 20 20"
            fill="currentColor"
            aria-hidden="true"
          >
            <path
              fill-rule="evenodd"
              d="M10 3a1 1 0 011 1v5h5a1 1 0 110 2h-5v5a1 1 0 11-2 0v-5H4a1 1 0 110-2h5V4a1 1 0 011-1z"
              clip-rule="evenodd"
            />
          </svg>
          Add Excercise
        </button>
      </div>
    </div>
    <add-program-content-table v-else></add-program-content-table>
  </div>
</template>

<script>
export default {
  props: ["day"],
  data() {
    return {
      excercises: [],
      exerciseTypes: [],
    };
  },
  methods: {
    async getExercises() {
      const response = await fetch(`http://localhost:8081/api/exercise`, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
        },
      });
      if (response.ok) {
        const responseData = await response.json();

        return responseData;
      } else {
        console.log("error getting user data");
      }
    },
  },
  async mounted() {
    this.exerciseTypes = await this.getExercises();
  },
};
</script>
