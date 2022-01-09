<template>
  <li class="border border-mint rounded-md p-4">
    <p>Time: {{ time }}</p>
    <p>Weight: {{ weight }}</p>
    <p>Chest: {{ chest }}</p>
    <p>Waist: {{ waist }}</p>
    <p>Hip: {{ hip }}</p>
    <p>Thigh: {{ thigh }}</p>
    <p>Arm: {{ arm }}</p>
  </li>
</template>

<script>
export default {
  props: ['weight', 'time', 'chest', 'waist', 'hip', 'thigh', 'arm'],
};
</script>
