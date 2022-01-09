<template>
  <div class="absolute left-0 top-10 w-full my-auto z-20">
    <div class="w-9/12 mx-auto px-20 py-5 bg-white rounded-md">
      <form class="flex flex-col items-center justify-center">
        <div>
          <input
            class="px-4 py-1 border-2 border-black rounded-md"
            type="text"
            name="programName"
            id="programName"
            placeholder="Program Name"
            v-model="programName"
          />
        </div>
        <div class="w-9/12 mx-auto mt-6">
          <textarea
            class="border-2 border-black p-2 rounded-md"
            name="programDesc"
            id="programDesc"
            cols="50"
            rows="5"
            placeholder="Description"
            v-model="programDesc"
          ></textarea>
        </div>
        <div class="flex mt-10 border-2 rounded-md border-darkmint">
          <div
            @click="changeDay('Monday')"
            :class="isMonday ? 'bg-mint text-white' : ''"
            class="cursor-pointer border-r-2 border-darkmint px-3 py-0.5 hover:bg-mint hover:text-white"
          >
            <p>Monday</p>
          </div>
          <div
            @click="changeDay('Tuesday')"
            :class="isTuesday ? 'bg-mint text-white' : ''"
            class="cursor-pointer border-r-2 border-darkmint px-3 py-0.5 hover:bg-mint hover:text-white"
          >
            <p>Tuesday</p>
          </div>
          <div
            @click="changeDay('Wednesday')"
            :class="isWednesday ? 'bg-mint text-white' : ''"
            class="cursor-pointer border-r-2 border-darkmint px-3 py-0.5 hover:bg-mint hover:text-white"
          >
            <p>Wednesday</p>
          </div>
          <div
            @click="changeDay('Thursday')"
            :class="isThursday ? 'bg-mint text-white' : ''"
            class="cursor-pointer border-r-2 border-darkmint px-3 py-0.5 hover:bg-mint hover:text-white"
          >
            <p>Thursday</p>
          </div>
          <div
            @click="changeDay('Friday')"
            :class="isFriday ? 'bg-mint text-white' : ''"
            class="cursor-pointer border-r-2 border-darkmint px-3 py-0.5 hover:bg-mint hover:text-white"
          >
            <p>Friday</p>
          </div>
          <div
            @click="changeDay('Saturday')"
            :class="isSaturday ? 'bg-mint text-white' : ''"
            class="cursor-pointer border-r-2 border-darkmint px-3 py-0.5 hover:bg-mint hover:text-white"
          >
            <p>Saturday</p>
          </div>
          <div
            @click="changeDay('Sunday')"
            :class="isSunday ? 'bg-mint text-white' : ''"
            class="cursor-pointer px-3 py-0.5 hover:bg-mint hover:text-white"
          >
            <p>Sunday</p>
          </div>
        </div>
        <div
          v-if="isMonday"
          class="w-10/12 mt-4 border-2 rounded-md border-darkmint p-5 flex flex-col items-center"
        >
          <div>
            <h3 class="text-xl">Monday</h3>
          </div>
          <div class="flex mt-5">
            <div class="mr-10">
              <ul>
                <li v-for="exer of mondayEx" :key="exer.id" class="mb-3 flex">
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="mondayEx[exer.id].repetitions"
                  />
                  <p class="mr-2">reps of</p>
                  <select
                    v-model="mondayEx[exer.id].exercise"
                    class="border-2 border-darkmint rounded-md"
                    name=""
                    id=""
                  >
                    <option
                      v-for="exercise in exercises"
                      :key="exercise.id"
                      :value="exercise"
                    >
                      {{ exercise.name }}
                    </option>
                  </select>
                  <p class="mr-2">with</p>
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="mondayEx[exer.id].restBetweenExercises"
                  />
                  <p class="mr-2">minutes rest</p>
                </li>
              </ul>
              <button
                @click.prevent="addEx('Monday')"
                class="mt-3 border-b border-darkmint hover:bg-mint transition-all"
              >
                Add
              </button>
            </div>
          </div>
        </div>
        <div
          v-if="isTuesday"
          class="w-10/12 mt-4 border-2 rounded-md border-darkmint p-5 flex flex-col items-center"
        >
          <div>
            <h3 class="text-xl">Tuesday</h3>
          </div>
          <div class="flex mt-5">
            <div class="mr-10">
              <ul>
                <li v-for="exer in tuesdayEx" :key="exer.id" class="mb-3 flex">
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="tuesdayEx[exer.id].repetitions"
                  />
                  <p class="mr-2">reps of</p>
                  <select
                    v-model="tuesdayEx[exer.id].exercise"
                    class="border-2 border-darkmint rounded-md"
                    name=""
                    id=""
                  >
                    <option
                      v-for="exercise in exercises"
                      :key="exercise.id"
                      :value="exercise.name"
                    >
                      {{ exercise.name }}
                    </option>
                  </select>
                  <p class="mr-2">with</p>
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="tuesdayEx[exer.id].restBetweenExercises"
                  />
                  <p class="mr-2">minutes rest</p>
                </li>
              </ul>
              <button
                @click.prevent="addEx('Tuesday')"
                class="mt-3 border-b border-darkmint hover:bg-mint transition-all"
              >
                Add
              </button>
            </div>
          </div>
        </div>
        <div
          v-if="isWednesday"
          class="w-10/12 mt-4 border-2 rounded-md border-darkmint p-5 flex flex-col items-center"
        >
          <div>
            <h3 class="text-xl">Wednesday</h3>
          </div>
          <div class="flex mt-5">
            <div class="mr-10">
              <ul>
                <li
                  v-for="exer in wednesdayEx"
                  :key="exer.id"
                  class="mb-3 flex"
                >
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="wednesdayEx[exer.id].repetitions"
                  />
                  <p class="mr-2">reps of</p>
                  <select
                    v-model="wednesdayEx[exer.id].exercise"
                    class="border-2 border-darkmint rounded-md"
                    name=""
                    id=""
                  >
                    <option
                      v-for="exercise in exercises"
                      :key="exercise.id"
                      :value="exercise.name"
                    >
                      {{ exercise.name }}
                    </option>
                  </select>
                  <p class="mr-2">with</p>
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="wednesdayEx[exer.id].restBetweenExercises"
                  />
                  <p class="mr-2">minutes rest</p>
                </li>
              </ul>
              <button
                @click.prevent="addEx('Wednesday')"
                class="mt-3 border-b border-darkmint hover:bg-mint transition-all"
              >
                Add
              </button>
            </div>
          </div>
        </div>
        <div
          v-if="isThursday"
          class="w-10/12 mt-4 border-2 rounded-md border-darkmint p-5 flex flex-col items-center"
        >
          <div>
            <h3 class="text-xl">Thursday</h3>
          </div>
          <div class="flex mt-5">
            <div class="mr-10">
              <ul>
                <li v-for="exer in thursdayEx" :key="exer.id" class="mb-3 flex">
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="thursdayEx[exer.id].repetitions"
                  />
                  <p class="mr-2">reps of</p>
                  <select
                    v-model="thursdayEx[exer.id].exercise"
                    class="border-2 border-darkmint rounded-md"
                    name=""
                    id=""
                  >
                    <option
                      v-for="exercise in exercises"
                      :key="exercise.id"
                      :value="exercise.name"
                    >
                      {{ exercise.name }}
                    </option>
                  </select>
                  <p class="mr-2">with</p>
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="thursdayEx[exer.id].restBetweenExercises"
                  />
                  <p class="mr-2">minutes rest</p>
                </li>
              </ul>
              <button
                @click.prevent="addEx('Thursday')"
                class="mt-3 border-b border-darkmint hover:bg-mint transition-all"
              >
                Add
              </button>
            </div>
          </div>
        </div>
        <div
          v-if="isFriday"
          class="w-10/12 mt-4 border-2 rounded-md border-darkmint p-5 flex flex-col items-center"
        >
          <div>
            <h3 class="text-xl">Friday</h3>
          </div>
          <div class="flex mt-5">
            <div class="mr-10">
              <ul>
                <li v-for="exer in fridayEx" :key="exer.id" class="mb-3 flex">
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="fridayEx[exer.id].repetitions"
                  />
                  <p class="mr-2">reps of</p>
                  <select
                    v-model="fridayEx[exer.id].exercise"
                    class="border-2 border-darkmint rounded-md"
                    name=""
                    id=""
                  >
                    <option
                      v-for="exercise in exercises"
                      :key="exercise.id"
                      :value="exercise.name"
                    >
                      {{ exercise.name }}
                    </option>
                  </select>
                  <p class="mr-2">with</p>
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="fridayEx[exer.id].restBetweenExercises"
                  />
                  <p class="mr-2">minutes rest</p>
                </li>
              </ul>
              <button
                @click.prevent="addEx('Friday')"
                class="mt-3 border-b border-darkmint hover:bg-mint transition-all"
              >
                Add
              </button>
            </div>
          </div>
        </div>
        <div
          v-if="isSaturday"
          class="w-10/12 mt-4 border-2 rounded-md border-darkmint p-5 flex flex-col items-center"
        >
          <div>
            <h3 class="text-xl">Saturday</h3>
          </div>
          <div class="flex mt-5">
            <div class="mr-10">
              <ul>
                <li v-for="exer in saturdayEx" :key="exer.id" class="mb-3 flex">
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="saturdayEx[exer.id].repetitions"
                  />
                  <p class="mr-2">reps of</p>
                  <select
                    v-model="saturdayEx[exer.id].exercise"
                    class="border-2 border-darkmint rounded-md"
                    name=""
                    id=""
                  >
                    <option
                      v-for="exercise in exercises"
                      :key="exercise.id"
                      :value="exercise.name"
                    >
                      {{ exercise.name }}
                    </option>
                  </select>
                  <p class="mr-2">with</p>
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="saturdayEx[exer.id].restBetweenExercises"
                  />
                  <p class="mr-2">minutes rest</p>
                </li>
              </ul>
              <button
                @click.prevent="addEx('Saturday')"
                class="mt-3 border-b border-darkmint hover:bg-mint transition-all"
              >
                Add
              </button>
            </div>
          </div>
        </div>
        <div
          v-if="isSunday"
          class="w-10/12 mt-4 border-2 rounded-md border-darkmint p-5 flex flex-col items-center"
        >
          <div>
            <h3 class="text-xl">Sunday</h3>
          </div>
          <div class="flex mt-5">
            <div class="mr-10">
              <ul>
                <li v-for="exer in sundayEx" :key="exer.id" class="mb-3 flex">
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="sundayEx[exer.id].repetitions"
                  />
                  <p class="mr-2">reps of</p>
                  <select
                    v-model="sundayEx[exer.id].exercise"
                    class="border-2 border-darkmint rounded-md"
                    name=""
                    id=""
                  >
                    <option
                      v-for="exercise in exercises"
                      :key="exercise.id"
                      :value="exercise"
                    >
                      {{ exercise.name }}
                    </option>
                  </select>
                  <p class="mr-2">with</p>
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    v-model="sundayEx[exer.id].restBetweenExercises"
                  />
                  <p class="mr-2">minutes rest</p>
                </li>
              </ul>
              <button
                @click.prevent="addEx('Sunday')"
                class="mt-3 border-b border-darkmint hover:bg-mint transition-all"
              >
                Add
              </button>
            </div>
          </div>
        </div>
        <div class="mt-8 self-end">
          <button
            @click.prevent="submitAddProgram"
            class="px-5 py-1 bg-darkmint text-syellow hover:bg-darkermint rounded-md mr-5"
          >
            Save
          </button>
          <button
            @click="$emit('cancelAddProgram')"
            class="px-5 py-1 bg-red-500 text-white hover:bg-red-600 rounded-md"
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  emits: ['cancelAddProgram'],
  data() {
    return {
      exercises: [],
      programName: '',
      programDesc: '',
      mondayId: 0,
      tuesdayId: 0,
      wednesdayId: 0,
      thursdayId: 0,
      fridayId: 0,
      saturdayId: 0,
      sundayId: 0,
      mondayEx: [],
      tuesdayEx: [],
      wednesdayEx: [],
      thursdayEx: [],
      fridayEx: [],
      saturdayEx: [],
      sundayEx: [],
      isMonday: true,
      isTuesday: false,
      isWednesday: false,
      isThursday: false,
      isFriday: false,
      isSaturday: false,
      isSunday: false,
    };
  },
  async mounted() {
    this.exercises = await this.getExercises();
  },
  methods: {
    async getExercises() {
      const response = await fetch(`http://localhost:8081/api/exercise`, {
        method: 'GET',
        headers: {
          Authorization: `Bearer ${this.$store.getters['auth/token']}`,
        },
      });
      console.log(response);
      if (response.ok) {
        const responseData = await response.json();

        return responseData;
      } else {
        console.log('error getting user data');
      }
    },
    async submitAddProgram() {
      let body = {};

      this.mondayEx.forEach((element) => {
        element.id = null;
      });

      let arr = [
        { weekDay: 'MONDAY', exerciseUnits: this.mondayEx },
        { weekDay: 'TUESDAY', exerciseUnits: this.tuesdayEx },
        { weekDay: 'WEDNESDAY', exerciseUnits: this.wednesdayEx },
        { weekDay: 'THURSDAY', exerciseUnits: this.thursdayEx },
        { weekDay: 'FRIDAY', exerciseUnits: this.fridayEx },
        { weekDay: 'SATURDAY', exerciseUnits: this.saturdayEx },
        { weekDay: 'SUNDAY', exerciseUnits: this.sundayEx },
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
        `http://localhost:8081/api/program/user/${this.$store.getters['auth/userId']}`,
        {
          method: 'POST',
          mode: 'cors',
          headers: {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            ...body,
          }),
        }
      );
      console.log(response);

      if (response.ok) {
        console.log(await response.json());
      } else {
        console.log('error getting user data');
      }
    },
    addEx() {
      if (this.isMonday) {
        this.mondayEx.push({
          id: this.mondayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      } else if (this.isTuesday) {
        this.tuesdayEx.push({
          id: this.tuesdayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      } else if (this.isWednesday) {
        this.wednesdayEx.push({
          id: this.wednesdayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      } else if (this.isThursday) {
        this.thursdayEx.push({
          id: this.thursdayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      } else if (this.isFriday) {
        this.fridayEx.push({
          id: this.fridayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      } else if (this.isSaturday) {
        this.saturdayEx.push({
          id: this.saturdayId++,
          repetitions: 0,
          exercise: {},
          restBetweenExercises: 0,
        });
      } else {
        this.sundayEx.push({
          id: this.sundayId++,
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
        return 'Monday';
      } else if (this.isTuesday) {
        return 'Tuesday';
      } else if (this.isWednesday) {
        return 'Wednesday';
      } else if (this.isThursday) {
        return 'Thursday';
      } else if (this.isFriday) {
        return 'Friday';
      } else if (this.isSaturday) {
        return 'Saturday';
      } else {
        return 'Sunday';
      }
    },
    changeDay(day) {
      switch (day) {
        case 'Monday':
          this.isMonday = true;
          this.isTuesday = false;
          this.isWednesday = false;
          this.isThursday = false;
          this.isFriday = false;
          this.isSaturday = false;
          this.isSunday = false;
          break;
        case 'Tuesday':
          this.isMonday = false;
          this.isTuesday = true;
          this.isWednesday = false;
          this.isThursday = false;
          this.isFriday = false;
          this.isSaturday = false;
          this.isSunday = false;
          break;
        case 'Wednesday':
          this.isMonday = false;
          this.isTuesday = false;
          this.isWednesday = true;
          this.isThursday = false;
          this.isFriday = false;
          this.isSaturday = false;
          this.isSunday = false;
          break;
        case 'Thursday':
          this.isMonday = false;
          this.isTuesday = false;
          this.isWednesday = false;
          this.isThursday = true;
          this.isFriday = false;
          this.isSaturday = false;
          this.isSunday = false;
          break;
        case 'Friday':
          this.isMonday = false;
          this.isTuesday = false;
          this.isWednesday = false;
          this.isThursday = false;
          this.isFriday = true;
          this.isSaturday = false;
          this.isSunday = false;
          break;
        case 'Saturday':
          this.isMonday = false;
          this.isTuesday = false;
          this.isWednesday = false;
          this.isThursday = false;
          this.isFriday = false;
          this.isSaturday = true;
          this.isSunday = false;
          break;
        case 'Sunday':
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
