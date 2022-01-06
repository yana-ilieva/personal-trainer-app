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
            <div class="p-10">
              <h1 class="text-center mb-10 text-3xl">
                Register as {{ isTrainer ? 'Trainer' : 'Client' }}
              </h1>
              <form class="flex">
                <div class="flex flex-col">
                  <input class="mb-8" type="text" placeholder="First Name" />
                  <input class="mb-8" type="text" placeholder="Last Name" />
                  <input class="mb-8" type="text" placeholder="Password" />
                  <input
                    class="mb-8"
                    type="text"
                    placeholder="Confirm Password"
                  />
                  <div class="flex items-center mb-4">
                    <input
                      type="radio"
                      id="male"
                      name="sex"
                      value="male"
                      checked
                    />
                    <label class="ml-4" for="male">Male</label>
                  </div>
                  <div class="flex items-center">
                    <input type="radio" id="female" name="sex" value="female" />
                    <label class="ml-4" for="female">Female</label>
                  </div>
                </div>
                <div class="flex flex-col ml-10 items-center">
                  <input class="mb-8" type="text" placeholder="Email" />
                  <input class="mb-8" type="text" placeholder="Address" />
                  <input class="mb-8" type="text" placeholder="Phone Number" />
                  <input
                    class="mb-4 px-10 py-4 bg-mint hover:bg-darkmint cursor-pointer transition-all rounded-md text-white"
                    type="submit"
                    value="Register Now"
                  />
                  <a
                    class="bg-lbeige hover:bg-beige cursor-pointer transition-all rounded-md w-max px-4 py-1"
                    href="#"
                    >Login</a
                  >
                </div>
              </form>
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
      isTrainer: true,
    };
  },
};
</script>
