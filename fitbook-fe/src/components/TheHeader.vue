<template>
  <header class="bg-darkmint text-syellow h-20 flex">
    <nav class="flex w-full justify-center pl-2">
      <ul class="w-full flex justify-start items-center list-none m-0 p-0">
        <li v-if="isAuthenticated">
          <router-link class="px-4 py-2 mr-4" to="/clients"
            >Clients</router-link
          >
        </li>
        <li v-if="isAuthenticated">
          <router-link class="px-4 py-2 mr-4" to="/trainers"
            >Trainers</router-link
          >
        </li>
        <li v-if="isAuthenticated">
          <router-link class="px-4 py-2 mr-4" to="/programs"
            >Programs</router-link
          >
        </li>
        <li v-if="isAuthenticated">
          <router-link class="px-4 py-2 mr-4" to="/chat">Chat</router-link>
        </li>
        <li v-if="isAuthenticated">
          <router-link class="px-4 py-2 mr-4" to="/my-profile"
            >My Profile</router-link
          >
        </li>
        <li v-if="!isAuthenticated">
          <router-link class="px-4 py-2 mr-4" to="/registration"
            >Registration</router-link
          >
        </li>
        <li v-if="!isAuthenticated">
          <router-link class="px-4 py-2 mr-4" to="/login">Login</router-link>
        </li>
      </ul>
      <div
        v-if="isAuthenticated"
        @click="$emit('showNotifications')"
        class="self-center mr-4"
      >
        <svg
          class="cursor-pointer"
          xmlns="http://www.w3.org/2000/svg"
          height="36px"
          viewBox="0 0 24 24"
          width="36px"
          fill="#f7dc70"
        >
          <path d="M0 0h24v24H0V0z" fill="none" />
          <path
            d="M12 22c1.1 0 2-.9 2-2h-4c0 1.1.9 2 2 2zm6-6v-5c0-3.07-1.63-5.64-4.5-6.32V4c0-.83-.67-1.5-1.5-1.5s-1.5.67-1.5 1.5v.68C7.64 5.36 6 7.92 6 11v5l-2 2v1h16v-1l-2-2zm-2 1H8v-6c0-2.48 1.51-4.5 4-4.5s4 2.02 4 4.5v6z"
          />
        </svg>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  emits: ['showNotifications'],
  computed: {
    isAuthenticated() {
      return this.$store.getters['auth/isAuthenticated'];
    },
  },
};
</script>

<style scoped>
header a:hover,
header a:focus,
header a.router-link-active {
  border-radius: 0.25rem;
  color: #547d6d;
  background-color: #f7dc70;
  padding: 0.5rem, 1rem, 0.5rem, 1rem;
}
</style>
