<template>
  <li class="border-2 border-darkmint rounded-md">
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
          class="px-3 py-0.5 hover:bg-red-500 hover:text-white rounded-md"
        >
          Delete
        </button>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  props: ['name', 'desc'],
  emits: ['editPrograms'],
};
</script>
