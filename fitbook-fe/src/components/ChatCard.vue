<template>
  <li
    @click="this.$emit('getChatMessages')"
    class="py-4 px-2 flex cursor-pointer hover:bg-gray-100 active:bg-gray-100 items-center"
  >
    <img
      class="h-10 w-10 rounded-full"
      src="https://images.unsplash.com/photo-1491528323818-fdd1faba62cc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
      alt=""
    />
    <div class="ml-3">
      <p class="text-sm font-medium text-gray-900">{{ name }}</p>
    </div>
  </li>
</template>

<script>
export default {
  props: ["name"],
};
</script>
