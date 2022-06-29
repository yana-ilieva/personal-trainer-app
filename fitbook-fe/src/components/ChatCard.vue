<template>
  <li
    @click="this.$emit('getChatMessages')"
    class="py-4 px-2 flex cursor-pointer hover:bg-gray-100 active:bg-gray-100 items-center"
  >
    <img class="h-10 w-10 rounded-full" :src="photo" alt="" />
    <div class="ml-3">
      <p class="text-sm font-medium text-gray-900">{{ name }}</p>
    </div>
  </li>
</template>

<script>
export default {
  props: ["name", "photo"],
};
</script>
