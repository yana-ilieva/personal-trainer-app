<template>
  <!--
  This example requires Tailwind CSS v2.0+ 
  
  This example requires some changes to your config:
  
  ```
  // tailwind.config.js
  module.exports = {
    // ...
    plugins: [
      // ...
      require('@tailwindcss/forms'),
    ],
  }
  ```
-->
  <!--
  This example requires updating your template:

  ```
  <html class="h-full bg-white">
  <body class="h-full">
  ```
-->
  <div class="min-h-screen flex">
    <div
      class="flex-1 flex flex-col justify-center py-12 px-4 sm:px-6 lg:flex-none lg:px-20 xl:px-24"
    >
      <div class="mx-auto w-full max-w-sm lg:w-96">
        <div>
          <img
            class="h-12 w-auto"
            src="https://tailwindui.com/img/logos/workflow-mark-indigo-600.svg"
            alt="Workflow"
          />
          <h2 class="mt-6 text-3xl font-extrabold text-gray-900">
            Create your account
          </h2>
          <p class="mt-2 text-sm text-gray-600">
            Already have an account?
            <a
              @click="redirectToLogin"
              class="font-medium text-indigo-600 hover:text-indigo-500 cursor-pointer"
            >
              Login
            </a>
          </p>
        </div>

        <div class="mt-8">
          <div class="mb-4 relative">
            <div class="absolute inset-0 flex items-center" aria-hidden="true">
              <div class="w-full border-t border-gray-300"></div>
            </div>
            <div class="relative flex justify-center text-sm">
              <span class="px-2 bg-white text-gray-500"> Register as </span>
            </div>
          </div>
          <div class="mb-8 flex w-full">
            <div class="mx-auto flex">
              <div
                @click="isTrainer = true"
                class="border border-darkmint px-4 py-1 cursor-pointer rounded-tl-md rounded-bl-md"
                :class="isTrainer ? 'text-white bg-darkmint' : ''"
              >
                Trainer
              </div>
              <div
                @click="isTrainer = false"
                class="border border-darkmint cursor-pointer px-4 py-1 rounded-tr-md rounded-br-md"
                :class="isTrainer ? '' : 'text-white bg-darkmint'"
              >
                Client
              </div>
            </div>
          </div>

          <div class="mt-6">
            <form @submit.prevent="submitForm" class="space-y-6">
              <div>
                <label
                  for="email"
                  class="block text-sm font-medium text-gray-700"
                >
                  Email address
                </label>
                <div class="mt-1">
                  <input
                    v-model.trim="user.email"
                    id="email"
                    name="email"
                    type="email"
                    autocomplete="email"
                    required
                    class="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                  />
                </div>
              </div>

              <div class="space-y-1">
                <label
                  for="password"
                  class="block text-sm font-medium text-gray-700"
                >
                  Password
                </label>
                <div class="mt-1">
                  <input
                    v-model.trim="user.password"
                    id="password"
                    name="password"
                    type="password"
                    autocomplete="current-password"
                    required
                    class="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                  />
                </div>
              </div>
              <div class="space-y-1">
                <label
                  for="firstName"
                  class="block text-sm font-medium text-gray-700"
                >
                  First Name
                </label>
                <div class="mt-1">
                  <input
                    v-model.trim="user.firstName"
                    id="firstName"
                    name="firstName"
                    type="firstName"
                    required
                    class="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                  />
                </div>
              </div>
              <div class="space-y-1">
                <label
                  for="lastName"
                  class="block text-sm font-medium text-gray-700"
                >
                  Last Name
                </label>
                <div class="mt-1">
                  <input
                    v-model.trim="user.lastName"
                    id="lastName"
                    name="lastName"
                    type="text"
                    required
                    class="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                  />
                </div>
              </div>
              <div class="space-y-1">
                <label
                  for="city"
                  class="block text-sm font-medium text-gray-700"
                >
                  City
                </label>
                <div class="mt-1">
                  <input
                    v-model.trim="user.city"
                    id="city"
                    name="city"
                    type="text"
                    class="appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                  />
                </div>
              </div>
              <div class="space-y-1">
                <div class="flex items-center mb-4">
                  <input
                    type="radio"
                    id="male"
                    name="sex"
                    value="male"
                    v-model.trim="user.gender"
                    checked
                  />
                  <label class="ml-4" for="male">Male</label>
                </div>
                <div class="flex items-center">
                  <input
                    v-model.trim="user.gender"
                    type="radio"
                    id="female"
                    name="sex"
                    value="female"
                  />
                  <label class="ml-4" for="female">Female</label>
                </div>
              </div>

              <div>
                <button
                  type="submit"
                  class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  Sign up
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="hidden lg:block relative w-0 flex-1">
      <img
        class="absolute inset-0 h-full w-full object-cover"
        src="@/assets/registration-image.jpg"
        alt=""
      />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isModal: false,
      modalText: "",
      isModalError: false,
      formIsValid: false,
      isTrainer: true,
      user: {
        firstName: "",
        lastName: "",
        gender: "male",
        city: "",
        role: "",
        email: "",
        password: "",
      },
    };
  },
  methods: {
    redirectToLogin() {
      this.$router.push({ path: "/login" });
    },
    async submitForm() {
      const role = this.isTrainer ? "ROLE_TRAINER" : "ROLE_CLIENT";
      this.formIsValid = true;
      console.log("submit register form data: ", this.user);
      if (this.isTrainer) {
        if (
          this.user.firstName === "" ||
          this.user.lastName === "" ||
          this.user.city === "" ||
          this.user.email === "" ||
          this.user.password === ""
        ) {
          this.formIsValid = false;
          this.isModal = true;
          this.modalText = "Please, fill all fields.";
          this.isModalError = true;
          setTimeout(() => {
            this.isModal = false;
            this.modalText = "";
            this.isModalError = false;
          }, 3000);
          return;
        }
        try {
          await this.$store
            .dispatch("auth/signup", {
              email: this.user.email,
              password: this.user.password,
              role: role,
              firstName: this.user.firstName,
              lastName: this.user.lastName,
              city: this.user.city,
              gender: this.user.gender.toUpperCase(),
            })
            .then((res) => {
              if (res) {
                this.isModal = true;
                this.modalText = "Registration successfull.";
                this.isModalError = false;
                setTimeout(() => {
                  this.isModal = false;
                  this.modalText = "";
                }, 3000);
              } else {
                this.isModal = true;
                this.modalText = "Registration successfull.";
                this.isModalError = true;
                setTimeout(() => {
                  this.isModal = false;
                  this.modalText = "";
                  this.isModalError = false;
                }, 3000);
              }
            });
          //const redirectUrl = this.$route.query.redirect || 'coaches';
          //this.$router.replace(`/${redirectUrl}`);
        } catch (err) {
          console.log(err);
        }
      } else {
        if (
          this.user.firstName === "" ||
          this.user.lastName === "" ||
          this.user.email === "" ||
          this.user.password === ""
        ) {
          console.log("validation error: ", this.user);
          this.formIsValid = false;
          this.isModal = true;
          this.modalText = "Please, fill all fields.";
          this.isModalError = true;
          setTimeout(() => {
            this.isModal = false;
            this.modalText = "";
            this.isModalError = false;
          }, 3000);
          return;
        }
        try {
          await this.$store
            .dispatch("auth/signup", {
              email: this.user.email,
              password: this.user.password,
              role: role,
              firstName: this.user.firstName,
              lastName: this.user.lastName,
              gender: this.user.gender.toUpperCase(),
            })
            .then((res) => {
              if (res) {
                this.$router.push({ path: "/login" });
              } else {
                this.isModal = true;
                this.modalText = "Registration failed.";
                this.isModalError = true;
                setTimeout(() => {
                  this.isModal = false;
                  this.modalText = "";
                  this.isModalError = false;
                }, 3000);
              }
            });
        } catch (err) {
          console.log(err);
        }
      }
    },
  },
};
</script>
