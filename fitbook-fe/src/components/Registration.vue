<template>
  <div class="w-full p-12">
    <div class="flex justify-center items-center">
      <div class="flex w-full">
        <div class="h-11/12 w-2/5 rounded-md bg-mint"></div>
        <div class="h-10/12 w-full ml-10">
          <div
            class="p-20 border border-darkmint rounded-md flex flex-col items-center justify-center"
          >
            <!-- toggle button -->
            <div class="mb-8 flex self-end">
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
            <!-- main elements -->
            <div class="p-10 relative">
              <h1 class="text-center mb-10 text-3xl">
                Register as {{ isTrainer ? 'Trainer' : 'Client' }}
              </h1>
              <form @submit.prevent="submitForm" class="flex">
                <div class="flex flex-col">
                  <input
                    v-model.trim="user.firstName"
                    class="mb-8"
                    type="text"
                    placeholder="First Name"
                  />
                  <input
                    v-model.trim="user.lastName"
                    class="mb-8"
                    type="text"
                    placeholder="Last Name"
                  />
                  <input
                    v-model.trim="user.password"
                    class="mb-8"
                    type="password"
                    placeholder="Password"
                  />
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
                <div class="flex flex-col ml-10 items-center">
                  <input
                    v-model.trim="user.email"
                    class="mb-8"
                    type="email"
                    placeholder="Email"
                  />
                  <input
                    v-if="isTrainer"
                    v-model.trim="user.city"
                    class="mb-8"
                    type="text"
                    placeholder="City"
                  />
                  <input
                    class="mb-4 px-10 py-4 bg-mint hover:bg-darkmint cursor-pointer transition-all rounded-md text-white"
                    type="submit"
                    value="Register Now"
                  />
                  <router-link
                    class="bg-lbeige hover:bg-beige cursor-pointer transition-all rounded-md w-max px-4 py-1"
                    to="/login"
                    >Login</router-link
                  >
                </div>
              </form>
              <div
                v-if="isModal"
                :class="isModalError ? 'bg-red-100' : 'bg-green-100'"
                class="absolute bottom-0 left-0 px-3 py-0.5 rounded-md"
              >
                <p>{{ modalText }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isModal: false,
      modalText: '',
      isModalError: false,
      formIsValid: false,
      isTrainer: true,
      user: {
        firstName: '',
        lastName: '',
        gender: 'male',
        city: '',
        role: '',
        email: '',
        password: '',
      },
    };
  },
  methods: {
    async submitForm() {
      const role = this.isTrainer ? 'ROLE_TRAINER' : 'ROLE_CLIENT';
      this.formIsValid = true;
      if (this.isTrainer) {
        if (
          this.user.firstName === '' ||
          this.user.lastName === '' ||
          this.user.city === '' ||
          this.user.email === '' ||
          this.user.password === ''
        ) {
          this.formIsValid = false;
          this.isModal = true;
          this.modalText = 'Please, fill all fields.';
          this.isModalError = true;
          setTimeout(() => {
            this.isModal = false;
            this.modalText = '';
            this.isModalError = false;
          }, 3000);
          return;
        }
        try {
          await this.$store
            .dispatch('auth/signup', {
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
                this.modalText = 'Registration successfull.';
                this.isModalError = false;
                setTimeout(() => {
                  this.isModal = false;
                  this.modalText = '';
                }, 3000);
              } else {
                this.isModal = true;
                this.modalText = 'Registration successfull.';
                this.isModalError = true;
                setTimeout(() => {
                  this.isModal = false;
                  this.modalText = '';
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
          this.user.firstName === '' ||
          this.user.lastName === '' ||
          this.user.email === '' ||
          this.user.password === ''
        ) {
          this.formIsValid = false;
          this.isModal = true;
          this.modalText = 'Please, fill all fields.';
          this.isModalError = true;
          setTimeout(() => {
            this.isModal = false;
            this.modalText = '';
            this.isModalError = false;
          }, 3000);
          return;
        }
        try {
          await this.$store
            .dispatch('auth/signup', {
              email: this.user.email,
              password: this.user.password,
              role: role,
              firstName: this.user.firstName,
              lastName: this.user.lastName,
              gender: this.user.gender.toUpperCase(),
            })
            .then((res) => {
              if (res) {
                this.isModal = true;
                this.modalText = 'Registration successfull.';
                this.isModalError = false;
                setTimeout(() => {
                  this.isModal = false;
                  this.modalText = '';
                }, 3000);
              } else {
                this.isModal = true;
                this.modalText = 'Registration successfull.';
                this.isModalError = true;
                setTimeout(() => {
                  this.isModal = false;
                  this.modalText = '';
                  this.isModalError = false;
                }, 3000);
              }
            });
          //const redirectUrl = this.$route.query.redirect || 'coaches';
          //this.$router.replace(`/${redirectUrl}`);
        } catch (err) {
          console.log(err);
        }
      }
    },
  },
};
</script>
