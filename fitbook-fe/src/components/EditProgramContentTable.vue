<template>
  <div class="w-full h-52 max-h-fit flex justify-center items-center">
    <!-- This example requires Tailwind CSS v2.0+ -->
    <div v-if="exercises.length == 0 && !addExerciseOpen" class="text-center">
      <h3 class="mt-2 text-sm font-medium text-gray-900">No exercises</h3>
      <p class="mt-1 text-sm text-gray-500">
        Leave it like this to count it as a rest day.
      </p>
      <div class="mt-6">
        <button
          @click="switchToInput"
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
          Add Exercises
        </button>
      </div>
    </div>
    <div class="w-full" v-else>
      <ul class="mx-auto">
        <li v-for="exercise of exercises" :key="exercise">
          <p>
            {{ exercise.repetitions }} reps of
            {{ exercise.exercise.name }}
          </p>
        </li>
      </ul>
      <div v-if="isExerciseInputOpen" class="flex justify-center items-center">
        <div>
          <div class="mt-1 relative rounded-md shadow-sm">
            <input
              v-model="exerciseRepsInput"
              type="number"
              name="reps"
              id="reps"
              class="focus:ring-indigo-500 focus:border-indigo-500 w-24 block pl-3 pr-12 sm:text-sm border-gray-300 rounded-md"
              placeholder="0"
              aria-describedby="price-currency"
            />
            <div
              class="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none"
            >
              <span class="text-gray-500 sm:text-sm" id="reps-text">
                reps
              </span>
            </div>
          </div>
        </div>
        <p class="mx-2">of</p>
        <div>
          <div>
            <select
              v-model="exerciseTypeInput"
              id="type"
              name="type"
              class="mt-1 block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm rounded-md"
            >
              <option v-for="type of exerciseTypes" :key="type">
                {{ type.name }}
              </option>
            </select>
          </div>
        </div>
      </div>
      <div v-if="isExerciseInputOpen" class="flex w-min mx-auto mt-1">
        <div
          @click="addExercise"
          class="border rounded-md border-gray-300 hover:bg-gray-100 mr-1 cursor-pointer px-1"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-5 w-5"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            stroke-width="2"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M5 13l4 4L19 7"
            />
          </svg>
        </div>
        <div
          @click="cancelInput"
          class="border rounded-md border-gray-300 hover:bg-gray-100 cursor-pointer px-1"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-5 w-5"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            stroke-width="2"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M6 18L18 6M6 6l12 12"
            />
          </svg>
        </div>
      </div>
      <div
        v-if="!isExerciseInputOpen"
        class="w-min mx-auto cursor-pointer mt-1"
        @click="openExerciseInput"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          class="h-6 w-6"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
          stroke-width="2"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M12 9v3m0 0v3m0-3h3m-3 0H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z"
          />
        </svg>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["day", "currentExercises"],
  data() {
    return {
      addExerciseOpen: false,
      isExerciseInputOpen: false,
      exercises: this.currentExercises,
      exerciseRepsInput: 0,
      exerciseTypeInput: "",
      exerciseTypes: [],
    };
  },
  methods: {
    switchToInput() {
      if (this.exercises.length === 0) {
        this.addExerciseOpen = true;
        this.isExerciseInputOpen = true;
      } else {
        this.openExerciseAdd();
      }
    },
    cancelInput() {
      if (this.exercises.length === 0) {
        this.addExerciseOpen = false;
        this.isExerciseInputOpen = false;
      } else {
        this.isExerciseInputOpen = false;
      }
    },
    addExercise() {
      console.log(
        "add exercise in table: ",
        this.exerciseRepsInput + " reps of " + this.exerciseTypeInput
      );
      this.exercises.push({
        repetitions: this.exerciseRepsInput,
        exercise: { name: this.exerciseTypeInput },
      });
      this.isExerciseInputOpen = false;
      this.exerciseRepsInput = 0;
      this.exerciseTypeInput = this.exerciseTypes[0];
    },
    openExerciseAdd() {
      this.addExerciseOpen = true;
    },
    openExerciseInput() {
      this.isExerciseInputOpen = true;
    },
    async getExerciseTypes() {
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
    const exerciseTypes = await this.getExerciseTypes();
    this.exerciseTypes = exerciseTypes;
    this.exerciseTypeInput = exerciseTypes[0].name;
  },
  beforeUnmount() {
    if (this.exercises.length > 0) {
      const exerciseArray = [];
      for (const exercise of this.exercises) {
        exerciseArray.push(JSON.parse(JSON.stringify(exercise)));
      }
      this.$emit("saveDayExercises", {
        day: this.day,
        exercises: exerciseArray,
      });
    } else {
      console.log("empty training day!");
    }
  },
};
</script>
