<template>
  <li
    class="p-6 max-w-sm relative bg-white rounded-lg border border-gray-200 shadow-md dark:bg-gray-800 dark:border-gray-700"
  >
    <div
      @click="$emit('deleteProgram')"
      class="absolute top-2 right-2 text-red-600 cursor-pointer"
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        class="h-4 w-4"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
        stroke-width="2"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
        />
      </svg>
    </div>
    <a href="#">
      <h5
        class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white"
      >
        {{ name }}
      </h5>
    </a>
    <p class="mb-3 font-normal text-gray-700 dark:text-gray-400">{{ desc }}</p>
    <button
      @click="$emit('editProgram')"
      class="inline-flex items-center py-2 px-3 text-sm font-medium text-center text-white bg-indigo-600 rounded-lg hover:bg-indigo-700 focus:ring-4 focus:outline-none focus:ring-indigo-300 dark:bg-indigo-600 dark:hover:bg-indigo-700 dark:focus:ring-indigo-800"
    >
      Edit
      <svg
        xmlns="http://www.w3.org/2000/svg"
        class="ml-2 -mr-1 w-4 h-4"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
        stroke-width="2"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.572L16.732 3.732z"
        />
      </svg>
    </button>
  </li>
  <!-- <li class="border border-gray-100 rounded-md shadow-md">
    <div class="h-full p-4 flex flex-col justify-between">
      <div class="flex flex-col">
        <div>
          <h3 class="text-2xl">{{ name }}</h3>
        </div>
        <div class="mt-3">
          <p class="text-lg text-left text-mint">{{ desc }}</p>
        </div>
        <div class="mt-3 self-end justify-self-end"></div>
      </div>
      <div class="flex self-end">
        <button
          @click="$emit('editProgram')"
          class="px-3 py-0.5 bg-syellow text-darkmint rounded-md mr-4 hover:bg-darksyellow"
        >
          Edit
        </button>
        <button
          @click="$emit('deleteProgram')"
          class="px-3 py-0.5 hover:bg-red-500 hover:text-white rounded-md"
        >
          Delete
        </button>
      </div>
    </div>
  </li> -->
</template>

<script>
export default {
  props: ["name", "desc"],
  emits: ["editProgram", "deleteProgram"],
};
</script>
