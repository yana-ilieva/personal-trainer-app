<template>
  <div class="absolute left-0 top-10 w-full my-auto z-20">
    <div class="w-9/12 mx-auto px-20 py-5 bg-white rounded-md">
      <form class="flex flex-col items-center justify-center">
        <div class="mx-auto w-1/2">
          <label for="title" class="block text-sm font-medium text-gray-700"
            >Title</label
          >
          <div class="mt-1">
            <input
              v-model="name"
              type="text"
              name="title"
              id="title"
              class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full border border-gray-300 rounded-md px-5 py-3"
              placeholder="Program Title"
            />
          </div>
          <div>
            <label
              for="desc"
              class="block text-sm font-medium text-gray-700 mt-3"
              >Description</label
            >
            <div class="mt-1">
              <textarea
                v-model="description"
                placeholder="Some description..."
                rows="4"
                name="desc"
                id="desc"
                class="shadow-sm focus:ring-indigo-500 border focus:border-indigo-500 block w-full border-gray-300 rounded-md p-4"
              ></textarea>
            </div>
          </div>
        </div>
        <div>
          <div>
            <div class="border-b border-gray-200">
              <nav class="-mb-px flex space-x-8" aria-label="Tabs">
                <!-- Current: "border-indigo-500 text-indigo-600", Default: "border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300" -->
                <a
                  @click="changeDay('mon')"
                  :class="
                    day === 'mon'
                      ? 'border-indigo-500 text-indigo-600'
                      : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                  "
                  class="cursor-pointer whitespace-nowrap py-4 px-1 border-b-2 font-medium text-sm"
                  >Mon</a
                >
                <a
                  @click="changeDay('tue')"
                  :class="
                    day === 'tue'
                      ? 'border-indigo-500 text-indigo-600'
                      : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                  "
                  class="cursor-pointer whitespace-nowrap py-4 px-1 border-b-2 font-medium text-sm"
                  >Tue</a
                >
                <a
                  @click="changeDay('wed')"
                  :class="
                    day === 'wed'
                      ? 'border-indigo-500 text-indigo-600'
                      : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                  "
                  class="cursor-pointer whitespace-nowrap py-4 px-1 border-b-2 font-medium text-sm"
                  >Wed</a
                >
                <a
                  @click="changeDay('thu')"
                  :class="
                    day === 'thu'
                      ? 'border-indigo-500 text-indigo-600'
                      : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                  "
                  class="cursor-pointer whitespace-nowrap py-4 px-1 border-b-2 font-medium text-sm"
                  >Thu</a
                >
                <a
                  @click="changeDay('fri')"
                  :class="
                    day === 'fri'
                      ? 'border-indigo-500 text-indigo-600'
                      : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                  "
                  class="cursor-pointer whitespace-nowrap py-4 px-1 border-b-2 font-medium text-sm"
                  >Fri</a
                >
                <a
                  @click="changeDay('sat')"
                  :class="
                    day === 'sat'
                      ? 'border-indigo-500 text-indigo-600'
                      : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                  "
                  class="cursor-pointer whitespace-nowrap py-4 px-1 border-b-2 font-medium text-sm"
                  >Sat</a
                >
                <a
                  @click="changeDay('sun')"
                  :class="
                    day === 'sun'
                      ? 'border-indigo-500 text-indigo-600'
                      : 'border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300'
                  "
                  class="cursor-pointer whitespace-nowrap py-4 px-1 border-b-2 font-medium text-sm"
                  >Sun</a
                >
              </nav>
            </div>
          </div>
          <edit-program-content-table
            @saveDayExercises="saveDayExercises"
            :key="day"
            :day="day"
            :currentExercises="exercises[day] ? exercises[day] : []"
          ></edit-program-content-table>
        </div>
        <div class="w-full flex justify-end">
          <button
            @click="this.$emit('cancelEditProgram')"
            type="button"
            class="mr-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:w-auto sm:text-sm"
          >
            Cancel
          </button>
          <button
            @click="saveProgram"
            type="button"
            class="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
          >
            Save
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="ml-2 -mr-1 h-5 w-5"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
              stroke-width="2"
            >
              <path
                stroke-linecap="round"
                stroke-linejoin="round"
                d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"
              />
            </svg>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import EditProgramContentTable from "../components/EditProgramContentTable.vue";
export default {
  props: ["program"],
  emits: ["cancelEditProgram"],
  components: { EditProgramContentTable },
  data() {
    return {
      day: "mon",
      id: this.program.id,
      name: this.program.name,
      description: this.program.description,
      exercises: this.program.exercises,
      exercisesInput: [],
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
    async saveProgram() {
      let programParts = [];
      console.log("save edit: ", this.exercisesInput);
      for (const exercise of this.exercisesInput) {
        let exUnits = [];
        for (const unit of exercise.exercises) {
          exUnits.push({
            repetitions: unit.repetitions,
            exercise: { name: unit.exercise.name },
          });
        }
        programParts.push({
          weekDay: this.parseDay(exercise.day),
          exerciseUnits: exUnits,
        });
      }
      const program = {
        id: this.id,
        name: this.name,
        description: this.description,
        programParts,
      };
      console.log("body to send: ", program);
      console.log("initial exercises: ", this.program.exercises);
      const response = await fetch(
        `http://localhost:8081/api/program/${this.id}`,
        {
          method: "PUT",
          mode: "cors",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify(program),
        }
      );
      console.log(response);

      if (response.ok) {
        console.log("response is ok");
      } else {
        console.log("error getting user data");
      }
    },
    changeDay(day) {
      this.day = day;
    },
    parseDay(day) {
      switch (day) {
        case "mon":
          return "MONDAY";
        case "tue":
          return "TUESDAY";
        case "wed":
          return "WEDNESDAY";
        case "thu":
          return "THURSDAY";
        case "fri":
          return "FRIDAY";
        case "sat":
          return "SATURDAY";
        case "sun":
          return "SUNDAY";

        default:
          break;
      }
    },
    saveDayExercises(payload) {
      const isExerciseDayExist = this.exercisesInput.find(
        (el) => el.day === payload.day
      );
      if (isExerciseDayExist) {
        isExerciseDayExist.exercises = payload.exercises;
      } else {
        this.exercisesInput.push({
          day: payload.day,
          exercises: payload.exercises,
        });
      }
    },
  },
};
</script>
