<template>
  <div
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
              Assign Program to <span class="font-semibold">{{ name }}</span>
            </h3>
            <div class="mt-6">
              <label
                for="program"
                class="block text-sm font-medium text-gray-700"
                >Location</label
              >
              <select
                v-model="selectedProgram"
                id="program"
                name="program"
                class="mt-1 block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm rounded-md"
              >
                <option
                  :value="program.id"
                  v-for="program of programs"
                  :key="program.id"
                >
                  {{ program.name }}
                </option>
              </select>
            </div>

            <div class="flex items-center justify-center mt-6 space-x-2">
              <button
                @click="this.$emit('assign', selectedProgram)"
                type="button"
                class="text-white bg-indigo-700 hover:bg-indigo-800 focus:ring-4 focus:outline-none focus:ring-indigo-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-indigo-600 dark:hover:bg-indigo-700 dark:focus:ring-indigo-800"
              >
                Save
              </button>
              <button
                @click="this.$emit('closeModal')"
                type="button"
                class="text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-gray-200 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600"
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["name"],
  emits: ["closeModal", "assign"],
  data() {
    return {
      programs: [],
      selectedProgram: {},
    };
  },
  methods: {
    async getPrograms() {
      const response = await fetch(
        `http://localhost:8081/api/trainer/${this.$store.getters["auth/userId"]}/programs`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      if (response.ok) {
        return await response.json();
      } else {
        console.log("error getting user data");
      }
    },
  },
  async mounted() {
    const programs = await this.getPrograms();
    this.programs = programs;
    console.log("programs: ", this.programs);
    this.selectedProgram = programs[0].id;
  },
};
</script>
