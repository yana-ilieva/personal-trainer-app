<template>
  <div>
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
      <add-program-content-table
        @saveDayExercises="saveDayExercises"
        :key="day"
        :day="day"
        :currentExercises="excercises[day] ? excercises[day] : []"
      ></add-program-content-table>
    </div>
    <div class="relative">
      <div class="absolute inset-0 flex items-center" aria-hidden="true">
        <div class="w-full border-t border-gray-300"></div>
      </div>
    </div>
    <div class="mt-4 w-full mx-auto justify-end flex">
      <button
        @click="
          this.$emit('nextStep', {
            type: 'content',
            exercises: parseProxy(excercises),
          })
        "
        type="button"
        class="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
      >
        Next
        <!-- Heroicon name: solid/mail -->
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
            d="M13 7l5 5m0 0l-5 5m5-5H6"
          />
        </svg>
      </button>
    </div>
  </div>
</template>

<script>
import AddProgramContentTable from "./AddProgramContentTable.vue";
export default {
  components: { AddProgramContentTable },
  data() {
    return {
      day: "mon",
      excercises: {},
    };
  },
  methods: {
    changeDay(day) {
      this.day = day;
    },
    saveDayExercises(payload) {
      console.log("save day exrcise: ", payload);
      this.excercises[payload.day] = payload.exercises;
    },
    parseProxy(exercisesProxy) {
      let exerciseArray = [];
      console.log("exercises proxy: ", exercisesProxy);
      for (const exercise of Object.entries(exercisesProxy)) {
        if (exercise) {
          exerciseArray.push(JSON.parse(JSON.stringify(exercise)));
        }
      }
      return exerciseArray;
    },
  },
};
</script>
