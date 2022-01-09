<template>
  <div class="w-full relative">
    <div
      v-if="isEditProgram"
      class="absolute w-full h-screen top-0 left-0 z-10"
      style="background-color: rgba(0, 0, 0, 0.6)"
    ></div>
    <edit-program
      @cancelEditProgram="cancelEditProgram"
      v-if="isEditProgram"
    ></edit-program>
    <div class="w-9/12 py-4 mx-auto">
      <div class="border-2 border-darkmint rounded-md">
        <div class="p-2">
          <div class="flex">
            <div class="h-40 w-4/12 bg-mint">
              <img src="" alt="" />
            </div>
            <div class="ml-6 w-5/12">
              <p class="text-mint text-lg text-justify">
                Lorem ipsum dolor sit amet consectetur, adipisicing elit.
                Possimus, in fuga? Commodi dolore magni reiciendis iste. Earum
                assumenda quaerat modi.
              </p>
            </div>
          </div>
          <div class="mt-6">
            <div class="w-max flex border-2 border-darkmint border-b-0">
              <div
                @click="clickProgram"
                :class="isProgram ? 'bg-mint text-white' : ''"
                class="px-3 py-0.5 border-r-2 cursor-pointer border-darkmint hover:bg-mint hover:text-white"
              >
                <p>Program</p>
              </div>
              <div
                :class="isDiet ? 'bg-mint text-white' : ''"
                @click="clickEdit"
                class="px-3 py-0.5 cursor-pointer hover:bg-mint hover:text-white"
              >
                <p>Diet</p>
              </div>
            </div>
            <div class="w-full border-2 overflow-auto border-darkmint">
              <div class="flex w-300 p-4">
                <div
                  @click="showProgramEdit"
                  class="w-1/3 h-48 mr-2 border-2 border-darksyellow rounded-md cursor-pointer hover:bg-gray-100"
                >
                  <div class="p-2">
                    <p>
                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
                      Rerum, possimus.
                    </p>
                  </div>
                </div>
                <div
                  class="w-1/3 mr-2 border-2 border-darksyellow rounded-md cursor-pointer hover:bg-gray-100"
                >
                  <div class="p-2">
                    <p>
                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
                      Rerum, possimus.
                    </p>
                  </div>
                </div>
                <div
                  class="w-1/3 mr-2 border-2 border-darksyellow rounded-md cursor-pointer hover:bg-gray-100"
                >
                  <div class="p-2">
                    <p>
                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
                      Rerum, possimus.
                    </p>
                  </div>
                </div>
                <div
                  class="w-1/3 mr-2 border-2 border-darksyellow rounded-md cursor-pointer hover:bg-gray-100"
                >
                  <div class="p-2">
                    <p>
                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
                      Rerum, possimus.
                    </p>
                  </div>
                </div>
                <div
                  class="w-1/3 mr-2 border-2 border-darksyellow rounded-md cursor-pointer hover:bg-gray-100"
                >
                  <div class="p-2">
                    <p>
                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
                      Rerum, possimus.
                    </p>
                  </div>
                </div>
                <div
                  class="w-1/3 mr-2 border-2 border-darksyellow rounded-md cursor-pointer hover:bg-gray-100"
                >
                  <div class="p-2">
                    <p>
                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
                      Rerum, possimus.
                    </p>
                  </div>
                </div>
                <div
                  class="w-1/3 mr-2 border-2 border-darksyellow rounded-md cursor-pointer hover:bg-gray-100"
                >
                  <div class="p-2">
                    <p>
                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
                      Rerum, possimus.
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import EditProgram from '../components/EditProgram.vue';
export default {
  components: { EditProgram },
  data() {
    return {
      isProgram: true,
      isDiet: false,
      isEditProgram: false,
    };
  },
  async mounted() {},
  methods: {
    clickProgram() {
      this.isProgram = true;
      this.isDiet = false;
    },
    clickEdit() {
      this.isProgram = false;
      this.isDiet = true;
    },
    showProgramEdit() {
      this.isEditProgram = true;
    },
    cancelEditProgram() {
      this.isEditProgram = false;
    },
  },
};
</script>
