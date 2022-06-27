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
      <add-program-review v-else-if="step == 2"></add-program-review>
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
    handleNextStep(payload) {
      if (payload.type === "name") {
        this.$store.dispatch("program/setNameAndDesc", {
          name: payload.name,
          description: payload.description,
        });
      }
      this.step++;
    },
    async submitAddProgram() {
      let body = {};
      let mEx = JSON.parse(JSON.stringify(this.mondayEx));
      let tuEx = JSON.parse(JSON.stringify(this.tuesdayEx));
      let wEx = JSON.parse(JSON.stringify(this.wednesdayEx));
      let thEx = JSON.parse(JSON.stringify(this.thursdayEx));
      let fEx = JSON.parse(JSON.stringify(this.fridayEx));
      let saEx = JSON.parse(JSON.stringify(this.saturdayEx));
      let suEx = JSON.parse(JSON.stringify(this.sundayEx));

      for (const [element, value] of Object.entries(mEx)) {
        console.log(element);
        value.cid = null;
      }
      for (const [element, value] of Object.entries(tuEx)) {
        console.log(element);
        value.cid = null;
      }
      for (const [element, value] of Object.entries(wEx)) {
        console.log(element);
        value.cid = null;
      }
      for (const [element, value] of Object.entries(thEx)) {
        console.log(element);
        value.cid = null;
      }
      for (const [element, value] of Object.entries(fEx)) {
        console.log(element);
        value.cid = null;
      }
      for (const [element, value] of Object.entries(saEx)) {
        console.log(element);
        value.cid = null;
      }
      for (const [element, value] of Object.entries(suEx)) {
        console.log(element);
        value.cid = null;
      }

      let arr = [
        { weekDay: "MONDAY", exerciseUnits: mEx },
        { weekDay: "TUESDAY", exerciseUnits: tuEx },
        { weekDay: "WEDNESDAY", exerciseUnits: wEx },
        { weekDay: "THURSDAY", exerciseUnits: thEx },
        { weekDay: "FRIDAY", exerciseUnits: fEx },
        { weekDay: "SATURDAY", exerciseUnits: saEx },
        { weekDay: "SUNDAY", exerciseUnits: suEx },
      ];

      if (this.isMonday) {
        body = {
          name: this.programName,
          description: this.programDesc,
          programParts: arr,
        };
      } else if (this.isTuesday) {
        body = {
          name: this.programName,
          description: this.programDesc,
          programParts: arr,
        };
      } else if (this.isWednesday) {
        body = {
          name: this.programName,
          description: this.programDesc,
          programParts: arr,
        };
      } else if (this.isThursday) {
        body = {
          name: this.programName,
          description: this.programDesc,
          programParts: arr,
        };
      } else if (this.isFriday) {
        body = {
          name: this.programName,
          description: this.programDesc,
          programParts: arr,
        };
      } else if (this.isSaturday) {
        body = {
          name: this.programName,
          description: this.programDesc,
          programParts: arr,
        };
      } else {
        body = {
          name: this.programName,
          description: this.programDesc,
          programParts: arr,
        };
      }
      console.log(JSON.stringify({ ...body }));
      const response = await fetch(
        `http://localhost:8081/api/program/user/${this.$store.getters["auth/userId"]}`,
        {
          method: "POST",
          mode: "cors",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            ...body,
          }),
        }
      );
      console.log(response);

      if (response.ok) {
        location.reload();
      } else {
        console.log("error getting user data");
      }
    },
    addEx() {
      if (this.isMonday) {
        this.mondayEx.push({
          cid: this.mondayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      } else if (this.isTuesday) {
        this.tuesdayEx.push({
          cid: this.tuesdayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      } else if (this.isWednesday) {
        this.wednesdayEx.push({
          cid: this.wednesdayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      } else if (this.isThursday) {
        this.thursdayEx.push({
          cid: this.thursdayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      } else if (this.isFriday) {
        this.fridayEx.push({
          cid: this.fridayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      } else if (this.isSaturday) {
        this.saturdayEx.push({
          cid: this.saturdayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      } else {
        this.sundayEx.push({
          cid: this.sundayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      }
    },
    getEx() {
      if (this.isMonday) {
        return this.mondayEx;
      } else if (this.isTuesday) {
        return this.tuesdayEx;
      } else if (this.isWednesday) {
        return this.wednesdayEx;
      } else if (this.isThursday) {
        return this.thursdayEx;
      } else if (this.isFriday) {
        return this.fridayEx;
      } else if (this.isSaturday) {
        return this.saturdayEx;
      } else {
        return this.sundayEx;
      }
    },
    getDay() {
      if (this.isMonday) {
        return "Monday";
      } else if (this.isTuesday) {
        return "Tuesday";
      } else if (this.isWednesday) {
        return "Wednesday";
      } else if (this.isThursday) {
        return "Thursday";
      } else if (this.isFriday) {
        return "Friday";
      } else if (this.isSaturday) {
        return "Saturday";
      } else {
        return "Sunday";
      }
    },
    changeDay(day) {
      switch (day) {
        case "Monday":
          this.isMonday = true;
          this.isTuesday = false;
          this.isWednesday = false;
          this.isThursday = false;
          this.isFriday = false;
          this.isSaturday = false;
          this.isSunday = false;
          break;
        case "Tuesday":
          this.isMonday = false;
          this.isTuesday = true;
          this.isWednesday = false;
          this.isThursday = false;
          this.isFriday = false;
          this.isSaturday = false;
          this.isSunday = false;
          break;
        case "Wednesday":
          this.isMonday = false;
          this.isTuesday = false;
          this.isWednesday = true;
          this.isThursday = false;
          this.isFriday = false;
          this.isSaturday = false;
          this.isSunday = false;
          break;
        case "Thursday":
          this.isMonday = false;
          this.isTuesday = false;
          this.isWednesday = false;
          this.isThursday = true;
          this.isFriday = false;
          this.isSaturday = false;
          this.isSunday = false;
          break;
        case "Friday":
          this.isMonday = false;
          this.isTuesday = false;
          this.isWednesday = false;
          this.isThursday = false;
          this.isFriday = true;
          this.isSaturday = false;
          this.isSunday = false;
          break;
        case "Saturday":
          this.isMonday = false;
          this.isTuesday = false;
          this.isWednesday = false;
          this.isThursday = false;
          this.isFriday = false;
          this.isSaturday = true;
          this.isSunday = false;
          break;
        case "Sunday":
          this.isMonday = false;
          this.isTuesday = false;
          this.isWednesday = false;
          this.isThursday = false;
          this.isFriday = false;
          this.isSaturday = false;
          this.isSunday = true;
          break;

        default:
          break;
      }
    },
  },
};
</script>
