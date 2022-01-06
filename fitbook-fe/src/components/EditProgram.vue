<template>
  <div class="absolute left-0 top-10 w-full my-auto z-20">
    <div class="w-9/12 mx-auto px-20 py-5 bg-white rounded-md">
      <div class="flex flex-col items-center justify-center">
        <div>
          <h2 class="text-3xl text-center">Program Name</h2>
        </div>
        <div class="w-9/12 mx-auto mt-6">
          <p class="text-md text-mint text-justify">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Et dolorum
            ducimus sint eius repudiandae ut aut a architecto reiciendis natus?
          </p>
        </div>
        <div class="flex mt-10 border-2 border-darkmint">
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
          class="w-10/12 mt-4 border-2 border-darkmint p-5 flex flex-col items-center"
        >
          <div>
            <h3 class="text-xl">Monday</h3>
          </div>
          <div class="flex mt-5">
            <div class="mr-10">
              <ul>
                <li class="mb-3 flex">
                  <input
                    class="w-10 mr-2 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    name=""
                    id=""
                    value="1"
                  />
                  <p class="mr-2">reps of</p>
                  <select
                    class="border-2 border-darkmint rounded-md"
                    name=""
                    id=""
                  >
                    <option value="volvo">Volvo</option>
                    <option value="saab">Saab</option>
                    <option value="mercedes">Mercedes</option>
                    <option value="audi">Audi</option>
                  </select>
                </li>
              </ul>
            </div>
            <div>
              <ul>
                <li class="mb-3 flex">
                  <p class="mr-2">Rest between exersises</p>
                  <input
                    class="w-10 border-2 border-darkmint rounded-md pl-2"
                    type="number"
                    value="1"
                    name=""
                    id=""
                  />
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="mt-8 self-end">
          <button
            class="px-5 py-1 bg-darkmint text-syellow hover:bg-darkermint rounded-md mr-5"
          >
            Save
          </button>
          <button
            @click="$emit('cancelEditProgram')"
            class="px-5 py-1 bg-red-500 text-white hover:bg-red-600 rounded-md"
          >
            Cancel
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  emits: ['cancelEditProgram'],
  data() {
    return {
      isMonday: true,
      isTuesday: false,
      isWednesday: false,
      isThursday: false,
      isFriday: false,
      isSaturday: false,
      isSunday: false,
    };
  },
  methods: {
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
