<template>
  <div class="w-full py-16">
    <div class="mx-auto w-1/2 flex flex-col items-center">
      <div class="w-10/12 h-72 bg-mint">
        <img src="" alt="" />
      </div>
      <div class="mt-4 flex flex-col justify-center items-center">
        <p class="mb-3">First Name: {{ trainer.firstName }}</p>
        <p class="mb-3">Last Name: {{ trainer.lastName }}</p>
        <p class="mb-3">Birth Date: {{ trainer.birthDate }}</p>
        <p class="mb-3">Gender: {{ trainer.gender }}</p>
        <p class="mb-3">Description: {{ trainer.desc }}</p>
        <p class="mb-3">City: {{ trainer.city }}</p>
        <p class="mb-3">Neighborhood: {{ trainer.neigh }}</p>
      </div>
      <div class="mt-8">
        <button
          class="text-xl px-8 py-3 bg-darkmint text-syellow hover:bg-syellow hover:text-darkmint transition-all rounded-md"
        >
          Edit
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      trainer: {
        firstName: 'Yana',
        lastName: 'Ilieva',
        birthDate: '26-07-1999',
        gender: 'Female',
        desc: 'The most beutiful small kitten potato gattato',
        city: 'Varna',
        neigh: 'Levski',
      },
    };
  },
};
</script>
