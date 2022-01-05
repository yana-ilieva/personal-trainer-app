<template>
  <li
    class="w-full h-32 border-b border-darkmint p-2 hover:bg-syellow cursor-pointer"
  >
    <div class="flex">
      <div class="w-20 h-28 bg-mint">
        <img src="" alt="" />
      </div>
      <div class="ml-4 flex my-auto justify-center items-center">
        <h4>{{ name }}</h4>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  props: ['name', 'img'],
};
</script>
