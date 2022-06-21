<template>
  <div class="mx-auto w-1/2">
    <label for="title" class="block text-sm font-medium text-gray-700"
      >Title</label
    >
    <div class="mt-1">
      <input
        type="text"
        name="title"
        id="title"
        class="shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full border border-gray-300 rounded-md px-5 py-3"
        placeholder="Program Title"
      />
    </div>
    <div>
      <label for="desc" class="block text-sm font-medium text-gray-700 mt-3"
        >Description</label
      >
      <div class="mt-1">
        <textarea
          placeholder="Some description..."
          rows="4"
          name="desc"
          id="desc"
          class="shadow-sm focus:ring-indigo-500 border focus:border-indigo-500 block w-full border-gray-300 rounded-md p-4"
        ></textarea>
      </div>
    </div>
    <div class="mt-4 w-full mx-auto justify-end flex">
      <button
        @click="this.$emit('nextStep')"
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
export default {
  data() {
    return {
      name: "",
      description: "",
    };
  },
  emits: ["nextStep"],
};
</script>
