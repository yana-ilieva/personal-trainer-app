<template>
  <!-- This example requires Tailwind CSS v2.0+ -->
  <div class="flex-1 flex flex-col min-w-fifth bg-indigo-700">
    <div class="flex-1 flex flex-col pt-5 pb-4 overflow-y-auto">
      <div class="flex items-center flex-shrink-0 px-4">
        <img
          class="h-8 w-auto"
          src="https://tailwindui.com/img/logos/workflow-logo-indigo-300-mark-white-text.svg"
          alt="Workflow"
        />
      </div>
      <nav class="mt-5 flex-1 px-2 space-y-1" aria-label="Sidebar">
        <!-- Current: "bg-indigo-800 text-white", Default: "text-indigo-100 hover:bg-indigo-600 hover:bg-opacity-75" -->
        <router-link
          v-if="isAuthenticated && role === 'ROLE_CLIENT'"
          href="#"
          to="/dashboard"
          class="text-indigo-100 hover:bg-indigo-600 hover:bg-opacity-75 group flex items-center px-2 py-2 text-sm font-medium rounded-md"
        >
          <!-- Heroicon name: outline/home -->
          <svg
            class="mr-3 flex-shrink-0 h-6 w-6 text-indigo-300"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="2"
            stroke="currentColor"
            aria-hidden="true"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"
            />
          </svg>
          <span> Dashboard </span>
        </router-link>

        <router-link
          v-if="isAuthenticated && role === 'ROLE_TRAINER'"
          to="/clients"
          href="#"
          class="text-indigo-100 hover:bg-indigo-600 hover:bg-opacity-75 group flex items-center px-2 py-2 text-sm font-medium rounded-md"
        >
          <!-- Heroicon name: outline/users -->
          <svg
            class="mr-3 flex-shrink-0 h-6 w-6 text-indigo-300"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="2"
            stroke="currentColor"
            aria-hidden="true"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z"
            />
          </svg>
          <span> Clients </span>
        </router-link>

        <router-link
          v-if="isAuthenticated && role === 'ROLE_CLIENT'"
          to="/trainers"
          href="#"
          class="text-indigo-100 hover:bg-indigo-600 hover:bg-opacity-75 group flex items-center px-2 py-2 text-sm font-medium rounded-md"
        >
          <!-- Heroicon name: outline/folder -->
          <svg
            class="mr-3 flex-shrink-0 h-6 w-6 text-indigo-300"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="2"
            stroke="currentColor"
            aria-hidden="true"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z"
            />
          </svg>
          <span> Trainers </span>
        </router-link>

        <router-link
          v-if="isAuthenticated && role === 'ROLE_TRAINER'"
          to="/programs"
          href="#"
          class="active:bg-indigo-800 active:text-white text-indigo-100 hover:bg-indigo-600 hover:bg-opacity-75 group flex items-center px-2 py-2 text-sm font-medium rounded-md"
        >
          <!-- Heroicon name: outline/calendar -->
          <svg
            class="mr-3 flex-shrink-0 h-6 w-6 text-indigo-300"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="2"
            stroke="currentColor"
            aria-hidden="true"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"
            />
          </svg>
          <span> Programs </span>
        </router-link>

        <router-link
          to="/chat"
          href="#"
          class="text-indigo-100 hover:bg-indigo-600 hover:bg-opacity-75 group flex items-center px-2 py-2 text-sm font-medium rounded-md"
        >
          <!-- Heroicon name: outline/inbox -->
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="mr-3 flex-shrink-0 h-6 w-6 text-indigo-300"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            stroke-width="2"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z"
            />
          </svg>

          <span> Chat </span>
        </router-link>
        <router-link
          v-if="isAuthenticated && role === 'ROLE_CLIENT'"
          to="/payment"
          class="text-indigo-100 hover:bg-indigo-600 hover:bg-opacity-75 group flex items-center px-2 py-2 text-sm font-medium rounded-md"
        >
          <!-- Heroicon name: outline/inbox -->
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="mr-3 flex-shrink-0 h-6 w-6 text-indigo-300"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            stroke-width="2"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M3 10h18M7 15h1m4 0h1m-7 4h12a3 3 0 003-3V8a3 3 0 00-3-3H6a3 3 0 00-3 3v8a3 3 0 003 3z"
            />
          </svg>

          <span> Payment </span>
        </router-link>

        <button
          @click="this.$emit('showNotifications')"
          class="w-full text-indigo-100 hover:bg-indigo-600 hover:bg-opacity-75 group flex items-center px-2 py-2 text-sm font-medium rounded-md"
        >
          <!-- Heroicon name: outline/chart-bar -->
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="mr-3 flex-shrink-0 h-6 w-6 text-indigo-300"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
            stroke-width="2"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9"
            />
          </svg>
          <span> Notifications </span>
        </button>
      </nav>
    </div>
    <div
      class="flex justify-between border-t border-indigo-800 p-4 items-center"
    >
      <div class="flex-shrink-0 flex">
        <router-link
          to="/my-profile"
          class="cursor-pointer flex-shrink-0 w-full group block"
        >
          <div class="flex items-center">
            <div>
              <img
                class="inline-block h-9 w-9 rounded-full"
                :src="user.photo"
                alt=""
              />
            </div>
            <div class="ml-3">
              <p class="text-sm font-medium text-white">{{ user.firstName }}</p>
              <p
                class="text-xs font-medium text-indigo-200 group-hover:text-white"
              >
                View profile
              </p>
            </div>
          </div>
        </router-link>
      </div>
      <svg
        @click="logout"
        xmlns="http://www.w3.org/2000/svg"
        class="cursor-pointer h-6 w-6 text-red-400 flex-shrink"
        fill="none"
        viewBox="0 0 24 24"
        stroke="currentColor"
        stroke-width="2"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1"
        />
      </svg>
    </div>
  </div>
</template>

<script>
export default {
  emits: ["showNotifications"],
  data() {
    return {
      user: {},
    };
  },
  methods: {
    logout() {
      this.$store.dispatch("auth/logout");
      location.reload();
    },
    async fetchUserPhoto() {
      const res = await fetch(`http://localhost:8081/api/file`, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
        },
      });
      if (res.ok) {
        const resData = await res.blob();
        const imgUrl = URL.createObjectURL(resData);
        return imgUrl;
      } else {
        return null;
      }
    },
    async getUser() {
      let url = "";
      let userId = this.$store.getters["auth/userId"];
      let token = this.$store.getters["auth/token"];
      if (userId == null) {
        userId = localStorage.getItem("id");
      }
      if (token) {
        token = localStorage.getItem("token");
      }
      if (this.$store.getters["auth/role"] === "ROLE_TRAINER") {
        url = `http://localhost:8081/api/trainer/user/${userId}`;
      } else {
        url = `http://localhost:8081/api/client/user/${userId}`;
      }
      const response = await fetch(url, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      if (response.ok) {
        const responseData = await response.json();
        return responseData;
      } else {
        console.log("error getting user data");
        return {
          firstName: "",
          lastName: "",
          birthDate: "",
          gender: "",
          height: "",
          description: "",
          city: "",
          neighborhood: "",
        };
      }
    },
  },
  computed: {
    isAuthenticated() {
      return this.$store.getters["auth/isAuthenticated"];
    },
    role() {
      return this.$store.getters["auth/role"];
    },
  },
  async mounted() {
    this.user = await this.getUser();
    const photo = await this.fetchUserPhoto();
    if (photo) {
      this.user.photo = await this.fetchUserPhoto();
    } else {
      this.user.photo =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/Twemoji_1f600.svg/1200px-Twemoji_1f600.svg.png";
    }
  },
};
</script>

<style scoped>
a.router-link-active {
  color: #fff;
  background-color: rgb(55 48 163);
}
</style>
