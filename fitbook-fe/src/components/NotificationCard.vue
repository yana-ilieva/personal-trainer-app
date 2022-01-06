<template>
  <li class="w-full border-b border-darkmint">
    <div class="p-3">
      <div class="w-full flex justify-between">
        <div>
          <p class="text-mint text-xs">From: {{ sender }}</p>
        </div>
        <div>
          <p class="text-mint text-xs">At: {{ time }}</p>
        </div>
      </div>
      <div class="w-10/12">
        <p class="text-left">{{ sender }} accepted your request!</p>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  props: ['sender', 'time'],
};
</script>
