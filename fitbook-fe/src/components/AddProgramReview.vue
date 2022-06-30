<template>
  <div>
    <div class="mb-3 text-gray-900">
      <p>Name: {{ name }}</p>
      <p>Description: {{ desc }}</p>
    </div>
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
        <div class="w-full h-52 max-h-fit flex justify-center items-center">
          <!-- This example requires Tailwind CSS v2.0+ -->
          <div class="w-full">
            <ul class="mx-auto">
              <li v-for="exercise of exercises[day]" :key="exercise">
                <p>{{ exercise.reps }} reps of {{ exercise.type }}</p>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <button
      @click="this.$emit('nextStep', { type: 'save' })"
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
</template>

<script>
export default {
  data() {
    return {
      day: "mon",
      name: "",
      desc: "",
      exercises: [],
    };
  },
  methods: {
    changeDay(day) {
      this.day = day;
    },
  },
  mounted() {
    const name = this.$store.getters["program/getName"];
    const description = this.$store.getters["program/getDescription"];
    const exercises = this.$store.getters["program/getExercises"];
    console.log("exercises preview: ", exercises);
    this.name = name;
    this.desc = description;
    for (const ex of exercises.exercises) {
      this.exercises[ex[0]] = ex[1];
    }
    console.log("preview exercises ready: ", this.exercises);
  },
};
</script>
