<template>
  <div class="relative w-full">
    <div
      v-if="isEdit"
      class="relative z-10"
      aria-labelledby="modal-title"
      role="dialog"
      aria-modal="true"
    >
      <!--
    Background backdrop, show/hide based on modal state.

    Entering: "ease-out duration-300"
      From: "opacity-0"
      To: "opacity-100"
    Leaving: "ease-in duration-200"
      From: "opacity-100"
      To: "opacity-0"
  -->
      <div
        class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"
      ></div>

      <div class="fixed z-10 inset-0 overflow-y-auto">
        <div
          class="flex items-end sm:items-center justify-center min-h-full p-4 text-center sm:p-0"
        >
          <!--
        Modal panel, show/hide based on modal state.

        Entering: "ease-out duration-300"
          From: "opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
          To: "opacity-100 translate-y-0 sm:scale-100"
        Leaving: "ease-in duration-200"
          From: "opacity-100 translate-y-0 sm:scale-100"
          To: "opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
      -->
          <div class="bg-white shadow sm:rounded-lg z-20 w-72">
            <div class="px-4 py-5 sm:p-6">
              <h3 class="text-lg leading-6 font-medium text-gray-900">
                Update your profile
              </h3>

              <form class="mt-5">
                <div class="w-full">
                  <div>
                    <img
                      ref="profileImageEdit"
                      class="mx-auto h-24 w-24 ring-4 ring-white sm:h-32 sm:w-32 mb-3"
                      alt=""
                    />
                    <div class="flex justify-center items-center mb-5">
                      <input
                        @change="fileChosen"
                        type="file"
                        accept="image/*"
                        class="border border-gray-300 shadow-sm text-sm font-medium text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:secondary"
                      />
                    </div>
                  </div>
                  <div v-if="role === 'ROLE_CLIENT'">
                    <!-- This example requires Tailwind CSS v2.0+ -->
                    <ul role="list" class="divide-y divide-gray-200">
                      <li class="py-2">
                        <input
                          v-model="editUser.firstName"
                          class="appearance-none rounded-none relative block w-full px-3 py-2 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 border border-transparent focus:border-indigo-500 focus:z-10 sm:text-sm"
                          type="text"
                          name="firstNameClient"
                          id="firstNameClient"
                          placeholder="First Name"
                        />
                      </li>
                      <li class="py-2">
                        <input
                          v-model="editUser.lastName"
                          class="appearance-none rounded-none relative block w-full px-3 py-2 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 border border-transparent focus:border-indigo-500 focus:z-10 sm:text-sm"
                          type="text"
                          name="lastNameClient"
                          id="lastNameClient"
                          placeholder="Last Name"
                        />
                      </li>
                      <li class="py-2">
                        <input
                          @change="this.isBdateChanged = true"
                          v-model="editUser.birthDate"
                          class="appearance-none rounded-none relative block w-full px-3 py-2 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 border border-transparent focus:border-indigo-500 focus:z-10 sm:text-sm"
                          type="text"
                          name="birthDateClient"
                          id="birthDateClient"
                          placeholder="Birth Date"
                        />
                      </li>
                      <li class="py-2">
                        <input
                          v-model="editUser.height"
                          class="appearance-none rounded-none relative block w-full px-3 py-2 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 border border-transparent focus:border-indigo-500 focus:z-10 sm:text-sm"
                          type="text"
                          name="heightClient"
                          id="heightClient"
                          placeholder="Height"
                        />
                      </li>
                      <li class="py-3 px-3">
                        <div class="flex items-center">
                          <label for="gender" class="mr-3 text-sm"
                            >Gender:</label
                          >
                          <div class="flex mr-1 items-center">
                            <input
                              type="radio"
                              id="male"
                              name="sex"
                              value="male"
                              v-model.trim="editUser.gender"
                              checked
                            />
                            <label for="male" class="text-sm">Male</label>
                          </div>
                          <div class="flex items-center">
                            <input
                              v-model.trim="editUser.gender"
                              type="radio"
                              id="female"
                              name="sex"
                              value="female"
                            />
                            <label for="female" class="text-sm">Female</label>
                          </div>
                        </div>
                      </li>
                      <li class="py-2">
                        <textarea
                          v-model="editUser.description"
                          class="appearance-none rounded-none relative block w-full px-3 py-2 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 border border-transparent focus:border-indigo-500 focus:z-10 sm:text-sm"
                          type="text"
                          name="descClient"
                          id="descClient"
                          placeholder="Description"
                        />
                      </li>

                      <!-- More items... -->
                    </ul>
                  </div>
                  <div v-if="role === 'ROLE_TRAINER'">
                    <!-- This example requires Tailwind CSS v2.0+ -->
                    <ul role="list" class="divide-y divide-gray-200">
                      <li class="py-2">
                        <input
                          v-model="editUser.firstName"
                          class="appearance-none rounded-none relative block w-full px-3 py-2 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 border border-transparent focus:border-indigo-500 focus:z-10 sm:text-sm"
                          type="text"
                          name="firstNameClient"
                          id="firstNameClient"
                          placeholder="First Name"
                        />
                      </li>
                      <li class="py-2">
                        <input
                          v-model="editUser.lastName"
                          class="appearance-none rounded-none relative block w-full px-3 py-2 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 border border-transparent focus:border-indigo-500 focus:z-10 sm:text-sm"
                          type="text"
                          name="lastNameClient"
                          id="lastNameClient"
                          placeholder="Last Name"
                        />
                      </li>
                      <li class="py-2">
                        <input
                          @change="this.isBdateChanged = true"
                          v-model="editUser.birthDate"
                          class="appearance-none rounded-none relative block w-full px-3 py-2 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 border border-transparent focus:border-indigo-500 focus:z-10 sm:text-sm"
                          type="text"
                          name="birthDateClient"
                          id="birthDateClient"
                          placeholder="Birth Date"
                        />
                      </li>
                      <li class="py-2">
                        <input
                          v-model="editUser.city"
                          class="appearance-none rounded-none relative block w-full px-3 py-2 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 border border-transparent focus:border-indigo-500 focus:z-10 sm:text-sm"
                          type="text"
                          name="cityTrainer"
                          id="cityTrainer"
                          placeholder="City"
                        />
                      </li>
                      <li class="py-2">
                        <input
                          v-model="editUser.neighborhood"
                          class="appearance-none rounded-none relative block w-full px-3 py-2 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 border border-transparent focus:border-indigo-500 focus:z-10 sm:text-sm"
                          type="text"
                          name="neighborhood"
                          id="neighborhood"
                          placeholder="Neighborhood"
                        />
                      </li>
                      <li class="py-3 px-3">
                        <div class="flex items-center">
                          <label for="gender" class="mr-3 text-sm"
                            >Gender:</label
                          >
                          <div class="flex mr-1 items-center">
                            <input
                              type="radio"
                              id="male"
                              name="sex"
                              value="male"
                              v-model.trim="editUser.gender"
                              checked
                            />
                            <label for="male" class="text-sm">Male</label>
                          </div>
                          <div class="flex items-center">
                            <input
                              v-model.trim="editUser.gender"
                              type="radio"
                              id="female"
                              name="sex"
                              value="female"
                            />
                            <label for="female" class="text-sm">Female</label>
                          </div>
                        </div>
                      </li>
                      <li class="py-2">
                        <textarea
                          v-model="editUser.description"
                          class="appearance-none rounded-none relative block w-full px-3 py-2 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 border border-transparent focus:border-indigo-500 focus:z-10 sm:text-sm"
                          type="text"
                          name="descClient"
                          id="descClient"
                          placeholder="Description"
                        />
                      </li>

                      <!-- More items... -->
                    </ul>
                  </div>
                </div>
                <div class="flex justify-center">
                  <button
                    @click="submitEditUser"
                    type="button"
                    class="inline-flex mr-3 justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-indigo-600 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:col-start-2 sm:text-sm"
                  >
                    Save
                  </button>
                  <button
                    @click="closeEdit"
                    type="button"
                    class="mt-3 inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:w-auto sm:text-sm"
                  >
                    Cancel
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="w-full py-16">
      <div class="mx-auto w-1/2 flex flex-col items-center">
        <div class="w-10/12 mx-auto flex justify-center items-center mb-2">
          <img ref="profileImage" class="h-48 w-48 ring-4 ring-white" alt="" />
        </div>

        <div
          v-if="role === 'ROLE_TRAINER'"
          class="bg-white shadow w-3/5 mx-auto overflow-hidden rounded-md"
        >
          <ul role="list" class="divide-y divide-gray-200">
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">First Name:</p>
              <p>{{ user.firstName || "" }}</p>
            </li>
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">Last Name:</p>
              <p>{{ user.lastName || "" }}</p>
            </li>
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">Birth Date:</p>
              <p>{{ user.birthDate || "" }}</p>
            </li>
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">Gender:</p>
              <p>{{ user.gender || "" }}</p>
            </li>
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">Description:</p>
              <p>{{ user.description || "" }}</p>
            </li>
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">City:</p>
              <p>{{ user.city || "" }}</p>
            </li>
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">Neighborhood:</p>
              <p>{{ user.neighborhood || "" }}</p>
            </li>
          </ul>
        </div>

        <div
          v-if="role === 'ROLE_CLIENT'"
          class="bg-white shadow w-3/5 mx-auto overflow-hidden rounded-md"
        >
          <ul role="list" class="w-full divide-y divide-gray-200">
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">First Name:</p>
              <p>{{ user.firstName || "" }}</p>
            </li>
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">Last Name:</p>
              <p>{{ user.lastName || "" }}</p>
            </li>
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">Birth Date:</p>
              <p>{{ user.birthDate || "" }}</p>
            </li>
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">Gender:</p>
              <p>{{ user.gender || "" }}</p>
            </li>
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">Height:</p>
              <p>{{ user.height || "" }}</p>
            </li>
            <li class="px-6 py-4 w-full flex justify-between">
              <p class="">Description:</p>
              <p>{{ user.description || "" }}</p>
            </li>
          </ul>
        </div>
        <div class="mt-8 w-3/5">
          <button
            @click="openEdit"
            type="button"
            class="w-full py-1.5 border border-gray-300 shadow-sm text-base font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
          >
            Edit
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import structuredClone from "@ungap/structured-clone";
export default {
  data() {
    return {
      user: {},
      editUser: {},
      isEdit: false,
      isBdateChanged: false,
    };
  },
  async mounted() {
    const user = await this.getUser();
    this.user = structuredClone(user);
    this.editUser = structuredClone(user);
  },
  computed: {
    role() {
      console.log(this.$store.getters["auth/role"]);
      return this.$store.getters["auth/role"];
    },
  },
  methods: {
    saveEdit() {},
    openEdit() {
      this.isEdit = true;
    },
    closeEdit() {
      this.isEdit = false;
    },
    parseDate(str) {
      let arr = str.split("-");
      let reversed = arr.reverse().join("-");
      return new Date(reversed).toISOString().split("T")[0];
    },
    async submitEditUser(e) {
      let url = "";
      let body = {};
      console.log("editUser: ", this.editUser);
      if (this.$store.getters["auth/role"] === "ROLE_TRAINER") {
        url = `http://localhost:8081/api/trainer/${this.user.id}`;
        body = {
          firstName: this.editUser.firstName,
          lastName: this.editUser.lastName,
          gender: this.editUser.gender.toUpperCase(),
          birthDate: this.isBdateChanged
            ? this.parseDate(this.editUser.birthDate)
            : this.editUser.birthDate,
          description: this.editUser.description,
          city: this.editUser.city,
          neighborhood: this.editUser.neighborhood,
        };
      } else {
        url = `http://localhost:8081/api/client/${this.user.id}`;
        body = {
          firstName: this.editUser.firstName,
          lastName: this.editUser.lastName,
          gender: this.editUser.gender.toUpperCase(),
          birthDate: this.isBdateChanged
            ? this.parseDate(this.editUser.birthDate)
            : this.editUser.birthDate,
          description: this.editUser.description,
          height: this.editUser.height,
        };
      }
      console.log("edit user body: ", body);
      const response = await fetch(url, {
        method: "PUT",
        mode: "cors",
        headers: {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          ...body,
        }),
      });
      console.log(response);
      if (response.ok) {
        const responseData = await response.json();
        this.user = responseData;
        this.isBdateChanged = false;
      } else {
        console.log("error editing user");
      }
      this.isEdit = false;
    },
    async getUser() {
      let url = "";
      if (this.$store.getters["auth/role"] === "ROLE_TRAINER") {
        url = `http://localhost:8081/api/trainer/user/${this.$store.getters["auth/userId"]}`;
      } else {
        url = `http://localhost:8081/api/client/user/${this.$store.getters["auth/userId"]}`;
      }
      const response = await fetch(url, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
        },
      });
      console.log(response);
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
};
</script>
