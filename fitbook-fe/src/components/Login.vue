<template>
  <div class="flex justify-center items-center p-10">
    <div class="p-20 border border-darkmint rounded-md relative">
      <h2 class="mb-10 text-3xl">Login</h2>
      <form @submit.prevent="submitLogin" class="flex flex-col" action="">
        <input
          v-model.trim="user.email"
          class="mb-8"
          type="text"
          placeholder="Email"
        />
        <input
          v-model.trim="user.password"
          class="mb-8"
          type="password"
          placeholder="Password"
        />
        <input
          class="mb-4 px-10 py-4 bg-mint hover:bg-darkmint cursor-pointer transition-all rounded-md text-white"
          type="submit"
          value="Login"
        />
      </form>
      <div
        v-if="isModal"
        class="absolute bottom-3 left-3 px-3 py-0.5 bg-red-100 rounded-md"
      >
        <p>{{ modalText }}</p>
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
      user: {
        email: '',
        password: '',
      },
    };
  },
  methods: {
    async submitLogin() {
      console.log(this.user);
      try {
        await this.$store
          .dispatch('auth/login', {
            email: this.user.email,
            password: this.user.password,
          })
          .then((res) => {
            console.log(res);
            if (res) {
              console.log('logged in');
              const redirectUrl = this.$route.query.redirect || 'clients';
              this.$router.replace(`/${redirectUrl}`);
            } else {
              this.isModal = true;
              this.modalText = 'Incorrect email or password';
              setTimeout(() => {
                this.isModal = false;
                this.modalText = '';
              }, 3000);
            }
          });
        //const redirectUrl = this.$route.query.redirect || 'coaches';
        //this.$router.replace(`/${redirectUrl}`);
      } catch (err) {
        this.isModal = true;
        this.modalText = 'Something went wrong. Try again.';
        setTimeout(() => {
          this.isModal = false;
          this.modalText = '';
        }, 3000);
        console.log(err);
      }
    },
  },
};
</script>
