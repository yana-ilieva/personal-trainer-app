<template>
  <div class="w-full py-16 relative">
    <div
      v-if="isEdit || isAdd"
      class="absolute w-full h-screen top-0 left-0 z-10"
      style="background-color: rgba(0, 0, 0, 0.6)"
    ></div>
    <add-program
      @cancelAddProgram="cancelAddProgram"
      v-if="isAdd"
    ></add-program>
    <edit-program
      @cancelEditProgram="cancelEditProgram"
      v-if="isEdit"
    ></edit-program>
    <button
      @click="addProgram"
      class="w-7/12 mx-auto mb-10 bg-darkmint text-syellow py-1 rounded-md hover:bg-darkermint"
    >
      Add Program
    </button>
    <div class="mx-auto w-9/12">
      <ul class="grid grid-cols-2 gap-5">
        <program-card
          @editProgram="editProgram"
          v-for="program in programs"
          :key="program.id"
          :name="program.name"
          :desc="program.description"
        ></program-card>
      </ul>
    </div>
  </div>
</template>

<script>
import ProgramCard from '../components/ProgramCard.vue';
import EditProgram from '../components/EditProgram.vue';
import AddProgram from '../components/AddProgram.vue';
export default {
  components: { ProgramCard, EditProgram, AddProgram },
  data() {
    return {
      isEdit: false,
      isAdd: false,
      programs: [],
    };
  },
  async mounted() {
    this.programs = await this.getPrograms();
  },
  methods: {
    editProgram() {
      this.isEdit = true;
    },
    addProgram() {
      this.isAdd = true;
    },
    cancelEditProgram() {
      this.isEdit = false;
    },
    cancelAddProgram() {
      this.isAdd = false;
    },
    async getPrograms() {
      const response = await fetch(
        `http://localhost:8081/api/trainer/${this.$store.getters['auth/userId']}/programs`,
        {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
          },
        }
      );
      console.log(response);
      if (response.ok) {
        return await response.json();
      } else {
        console.log('error getting user data');
      }
    },
  },
};
</script>
