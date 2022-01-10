<template>
  <div class="w-full relative">
    <div
      v-if="isEditProgram"
      class="absolute w-full h-screen top-0 left-0 z-10"
      style="background-color: rgba(0, 0, 0, 0.6)"
    ></div>
    <edit-program
      @cancelEditProgram="cancelEditProgram"
      v-if="isEditProgram"
    ></edit-program>
    <div class="w-9/12 py-4 mx-auto">
      <div class="border-2 border-darkmint rounded-md">
        <div class="p-2">
          <div class="flex">
            <div class="h-40 w-4/12 bg-mint">
              <img src="" alt="" />
            </div>
            <div class="ml-6 w-5/12">
              <p class="text-left text-mint text-lg mb-2">
                First Name: {{ client.firstName }}
              </p>
              <p class="text-left text-mint text-lg mb-2">
                Last Name: {{ client.lastName }}
              </p>
              <p class="text-left text-mint text-lg mb-2">
                Gender: {{ client.gender }}
              </p>
              <p class="text-left text-mint text-lg">
                Height: {{ client.height }}
              </p>
            </div>
          </div>
          <div class="mt-6">
            <div class="w-max flex border-2 border-darkmint border-b-0">
              <div
                @click="clickProgram"
                :class="isProgram ? 'bg-mint text-white' : ''"
                class="px-3 py-0.5 cursor-pointer hover:bg-mint hover:text-white"
              >
                <p>Program</p>
              </div>
            </div>
            <div class="w-full border-2 overflow-auto border-darkmint">
              <div v-if="client.programDto != null" class="flex flex-col">
                <div class="flex w-300 p-4">
                  <div
                    v-for="part of client.programDto.programParts"
                    :key="part.id"
                    @click="showProgramEdit"
                    class="w-1/3 h-48 mr-2 border-2 border-darksyellow rounded-md cursor-pointer hover:bg-gray-100"
                  >
                    <div class="p-2">
                      <p>
                        {{ part.weekDay }}
                      </p>
                      <div class="w-full">
                        <p
                          v-for="unit of part.exerciseUnits"
                          :key="unit.id"
                          class="text-sm"
                        >
                          {{ unit.repetitions }} reps of
                          {{ unit.exercise.name }} / rest
                          {{ unit.restBetweenExercises }} minutes
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
                <button
                  @click="removeAssignedProgram"
                  class="w-max ml-4 mb-2 bg-red-500 px-3 py-0.5 text-white hover:bg-red-600 rounded-md"
                >
                  Remove
                </button>
              </div>
              <div v-else class="h-20">
                <div class="mx-auto mt-10">
                  <form
                    class="flex flex-col w-max mx-auto"
                    @submit.prevent="assignProgram"
                    action=""
                  >
                    <select
                      class="px-4 py-1 border-2 border-darkmint rounded-md"
                      name="assignProgram"
                      ref="assignProgram"
                    >
                      <option
                        v-for="program of programs"
                        :key="program.id"
                        :value="program.id"
                      >
                        {{ program.name }}
                      </option>
                    </select>
                    <input
                      type="submit"
                      value="Assign"
                      class="mt-3 bg-darkmint cursor-pointer px-4 py-1 hover:bg-darkermint text-syellow rounded-md transition-all"
                    />
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import EditProgram from '../components/EditProgram.vue';
export default {
  components: { EditProgram },
  data() {
    return {
      client: {},
      programs: {},
      isProgram: true,
      isDiet: false,
      isEditProgram: false,
    };
  },
  async mounted() {
    this.client = await this.getClient();
    this.programs = await this.getPrograms();
    console.log(this.client);
  },
  methods: {
    removeAssignedProgram() {
      this.client.programDto = null;
    },
    async assignProgram() {
      const response = await fetch(
        `http://localhost:8081/api/client/${this.client.id}/program/${this.$refs.assignProgram.value}`,
        {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
          },
        }
      );
      console.log(response);
      if (response.ok) {
        location.reload();
      } else {
        console.log('error getting user data');
      }
    },
    async getPrograms() {
      const response = await fetch(
        `http://localhost:8081/api/trainer/${this.$store.getters['auth/userId']}/programs`,
        {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
          },
        }
      );
      if (response.ok) {
        return await response.json();
      } else {
        console.log('error getting user data');
      }
    },
    async getClient() {
      let url = window.location.href;
      console.log(url);
      console.log(url.substring(url.lastIndexOf('/') + 1));
      const response = await fetch(
        `http://localhost:8081/api/client/${+url.substring(
          url.lastIndexOf('/') + 1
        )}`,
        {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
          },
        }
      );
      if (response.ok) {
        return await response.json();
      } else {
        console.log('error getting user data');
      }
    },
    clickProgram() {
      this.isProgram = true;
      this.isDiet = false;
    },
    clickEdit() {
      this.isProgram = false;
      this.isDiet = true;
    },
    showProgramEdit() {
      this.isEditProgram = true;
    },
    cancelEditProgram() {
      this.isEditProgram = false;
    },
  },
};
</script>
