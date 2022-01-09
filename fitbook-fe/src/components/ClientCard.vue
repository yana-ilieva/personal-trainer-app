<template>
  <li class="w-full h-44 border border-darkmint rounded-md mb-6">
    <div class="p-4">
      <div class="flex justify-between">
        <div class="flex">
          <div class="w-32 h-32 bg-mint">
            <img src="" alt="" />
          </div>
          <div class="ml-4 text-left w-56">
            <p>Name: {{ firstName + ' ' + lastName }}</p>
            <p>Birth Date: {{ bDate }}</p>
            <p>Gender: {{ gender }}</p>
            <p>Description: {{ desc }}</p>
          </div>
        </div>
        <div class="self-end justify-self-end">
          <button
            class="px-3 py-0.5 bg-syellow text-darkmint rounded-md mr-4 hover:bg-darksyellow"
          >
            Chat
          </button>
          <router-link
            :to="{ name: 'edit', params: { id: firstName + lastName } }"
            class="px-3 py-1.5 hover:bg-mint rounded-md"
            >Edit</router-link
          >
        </div>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  props: ['firstName', 'lastName', 'bDate', 'gender', 'desc'],
};
</script>
