<template>
  <div class="w-11/12 h-11/12">
    <div class="flex">
      <div class="h-10/12 w-2/5 bg-mint"></div>
      <div class="h-10/12 p-10">
        <div class="flex flex-col items-center justify-center">
          <!-- toggle button -->
          <div>
            <div></div>
            <div></div>
          </div>
          <!-- main elements -->
          <div>
            <h1 class="text-center mb-16">Register as Trainer</h1>
            <form class="flex">
              <div>
                <input type="text" placeholder="First Name" />
                <input type="text" placeholder="Last Name" />
                <input type="text" placeholder="Password" />
                <input type="text" placeholder="Confirm Password" />
                <input type="radio" id="male" name="sex" value="male" checked />
                <input type="radio" id="female" name="sex" value="female" />
              </div>
              <div>
                <input type="text" placeholder="Email" />
                <input type="text" placeholder="Address" />
                <input type="text" placeholder="Phone Number" />
                <input type="submit" placeholder="Register Now" />
                <a href="#">or Login</a>
              </div>
            </form>
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
      test: 'test',
    };
  },
};
</script>
