<template>
  <div class="absolute left-0 top-10 w-full my-auto z-20">
    <div class="w-9/12 mx-auto px-10 py-5 bg-white rounded-md relative">
      <div
        @click="this.$emit('cancelAddProgram')"
        class="absolute top-2 right-2 cursor-pointer"
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
            d="M6 18L18 6M6 6l12 12"
          />
        </svg>
      </div>
      <add-program-name
        v-if="step == 0"
        @nextStep="handleNextStep"
      ></add-program-name>
      <add-program-content
        v-else-if="step == 1"
        @nextStep="handleNextStep"
      ></add-program-content>
      <add-program-review
        @nextStep="handleNextStep"
        v-else-if="step == 2"
      ></add-program-review>
      <div class="mt-4">
        <nav aria-label="Progress">
          <ol role="list" class="space-y-4 md:flex md:space-y-0 md:space-x-8">
            <li class="md:flex-1">
              <a
                class="group pl-4 py-2 flex flex-col border-l-4 border-indigo-600 md:pl-0 md:pt-4 md:pb-0 md:border-l-0 md:border-t-4"
              >
                <span
                  class="text-xs text-indigo-600 font-semibold tracking-wide uppercase group-hover:text-indigo-800"
                  >Step 1</span
                >
                <span class="text-sm font-medium">Name and Description</span>
              </a>
            </li>

            <li class="md:flex-1">
              <a
                :class="step >= 1 ? 'border-indigo-600' : 'border-gray-200'"
                class="pl-4 py-2 flex flex-col border-l-4 md:pl-0 md:pt-4 md:pb-0 md:border-l-0 md:border-t-4"
                aria-current="step"
              >
                <span
                  :class="step >= 1 ? 'text-indigo-600' : 'text-gray-500'"
                  class="text-xs font-semibold tracking-wide uppercase"
                  >Step 2</span
                >
                <span class="text-sm font-medium">Program</span>
              </a>
            </li>

            <li class="md:flex-1">
              <a
                :class="step == 2 ? 'border-indigo-600' : 'border-gray-200'"
                class="group pl-4 py-2 flex flex-col border-l-4 md:pl-0 md:pt-4 md:pb-0 md:border-l-0 md:border-t-4"
              >
                <span
                  :class="step == 2 ? 'text-indigo-600' : 'text-gray-500'"
                  class="text-xs font-semibold tracking-wide uppercase group-hover:text-gray-700"
                  >Step 3</span
                >
                <span class="text-sm font-medium">Preview</span>
              </a>
            </li>
          </ol>
        </nav>
      </div>
    </div>
  </div>
</template>

<script>
import AddProgramName from "./AddProgramName.vue";
import AddProgramContent from "./AddProgramContent.vue";
import AddProgramReview from "./AddProgramReview.vue";
export default {
  emits: ["cancelAddProgram", "saveAddProgram"],
  components: { AddProgramName, AddProgramContent, AddProgramReview },
  data() {
    return {
      step: 0,
      programName: "",
      programDesc: "",
    };
  },
  methods: {
    async handleNextStep(payload) {
      console.log("next step: ", payload.type);
      if (payload.type === "name") {
        this.$store.dispatch("program/setNameAndDesc", {
          name: payload.name,
          description: payload.description,
        });
        this.step++;
      }
      console.log("save exercises into store: ", payload);
      if (payload.type === "content") {
        this.$store.dispatch("program/setExercises", {
          exercises: payload.exercises,
        });
        this.step++;
      }
      if (payload.type === "save") {
        await this.saveProgram();
      }
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
    async saveProgram() {
      const dataFromStore = this.$store.getters["program/getProgram"];
      console.log("data from store: ", dataFromStore);
      let programParts = [];
      for (const exercise of dataFromStore.exercises.exercises) {
        let exUnits = [];
        for (const unit of exercise[1]) {
          exUnits.push({
            repetitions: unit.reps,
            exercise: { name: unit.type },
          });
        }
        programParts.push({
          weekDay: this.parseDay(exercise[0]),
          exerciseUnits: exUnits,
        });
      }
      const program = {
        name: dataFromStore.name,
        description: dataFromStore.description,
        programParts,
      };
      console.log("body to send: ", program);
      const response = await fetch(
        `http://localhost:8081/api/program/user/${this.$store.getters["auth/userId"]}`,
        {
          method: "POST",
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
        location.reload();
      } else {
        console.log("error getting user data");
      }
    },
  },
};
</script>
