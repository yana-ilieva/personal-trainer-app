<template>
  <div class="w-full py-16 relative">
    <div
      v-if="isEdit || isAdd"
      class="absolute w-full h-screen top-0 left-0 z-10"
      style="background-color: rgba(0, 0, 0, 0.6)"
    ></div>
    <add-program
      @cancelAddProgram="cancelAddProgram"
      v-if="isAdd"
    ></add-program>
    <edit-program
      @cancelEditProgram="cancelEditProgram"
      v-if="isEdit"
      :program="programToEdit"
    ></edit-program>
    <div class="mx-auto w-9/12">
      <div v-if="programs.length > 0">
        <ul class="grid grid-cols-2 gap-5">
          <program-card
            v-for="program in programs"
            :key="program.id"
            :name="program.name"
            :desc="program.description"
            @editProgram="editProgram(program)"
            @deleteProgram="deleteProgram(program.id)"
          ></program-card>
        </ul>
        <button
          @click="addProgram"
          type="button"
          class="mt-5 inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
        >
          <svg
            class="-ml-1 mr-2 h-5 w-5"
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
          New Program
        </button>
      </div>
      <div v-else>
        <button
          @click="addProgram"
          type="button"
          class="relative block w-full border-2 border-gray-300 border-dashed rounded-lg p-12 text-center hover:border-gray-400 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-12 w-12 mx-auto text-gray-400"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            stroke-width="1"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M12 9v3m0 0v3m0-3h3m-3 0H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z"
            />
          </svg>
          <span class="mt-2 block text-sm font-medium text-gray-800">
            Create a new program
          </span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import ProgramCard from "../components/ProgramCard.vue";
import EditProgram from "../components/EditProgram.vue";
import AddProgram from "../components/AddProgram.vue";
export default {
  components: { ProgramCard, EditProgram, AddProgram },
  data() {
    return {
      isEdit: false,
      isAdd: false,
      programs: [],
      programToEdit: {},
    };
  },
  async mounted() {
    this.programs = await this.getPrograms();
    console.log("programs: ", this.programs);
  },
  methods: {
    editProgram(program) {
      this.isEdit = true;
      this.programToEdit = program;
    },
    addProgram() {
      this.isAdd = true;
    },
    cancelEditProgram() {
      this.isEdit = false;
    },
    cancelAddProgram() {
      this.isAdd = false;
    },
    async deleteProgram(id) {
      const response = await fetch(
        `http://localhost:8081/api/trainer/program/${id}`,
        {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      if (response.ok) {
        location.reload();
      } else {
        console.log("error getting user data");
      }
    },
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
};
</script>
