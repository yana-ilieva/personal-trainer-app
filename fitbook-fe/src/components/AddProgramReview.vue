<template>
  <div>
    <div class="mb-3 text-gray-900">
      <p>Name: {{ name }}</p>
      <p>Description: {{ desc }}</p>
    </div>
    <button
      @click="this.$emit('nextStep')"
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
      name: "",
      desc: "",
    };
  },
  //   computed: {
  //     name() {
  //       return this.$store.getters["program/getName"];
  //     },
  //     description() {
  //       return this.$store.getters["program/getDescription"];
  //     },
  //   },
  mounted() {
    const name = this.$store.getters["program/getName"];
    const description = this.$store.getters["program/getDescription"];
    console.log("name desc: ", name + " " + description);
    this.name = name;
    this.desc = description;
  },
};
</script>
