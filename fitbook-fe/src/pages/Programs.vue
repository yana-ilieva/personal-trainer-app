<template>
  <div class="w-full py-16 relative">
    <div
      v-if="isEdit"
      class="absolute w-full h-screen top-0 left-0 z-10"
      style="background-color: rgba(0, 0, 0, 0.6)"
    ></div>
    <edit-program
      @cancelEditProgram="cancelEditProgram"
      v-if="isEdit"
    ></edit-program>
    <div class="mx-auto w-9/12">
      <ul class="grid grid-cols-2 gap-5">
        <program-card
          @editProgram="editProgram"
          v-for="program in programs"
          :key="program.name"
          :name="program.name"
          :desc="program.desc"
        ></program-card>
      </ul>
    </div>
  </div>
</template>

<script>
import ProgramCard from '../components/ProgramCard.vue';
import EditProgram from '../components/EditProgram.vue';
export default {
  components: { ProgramCard, EditProgram },
  data() {
    return {
      isEdit: false,
      programs: [
        {
          name: 'Program1',
          desc: 'ivonrv eret  etbetbtasb a aetbbeat  eatbteabte abtaetb ebtaebe etb etab eb ebteb e e berbetgha ljnnljnl eattta teahaha etahaerth aetha aehtthaeth',
        },
        {
          name: 'Program2',
          desc: 'ivonrv eret  etbetbtasb a aetbbeat  eatbteabte abtaetb ebtaebe etb etab eb ebteb e e berbetgha ljnnljnl',
        },
        {
          name: 'Program3',
          desc: 'ivonrv eret  etbetbtasb a aetbbeat  eatbteabte abtaetb ebtaebe etb etab eb ebteb e e berbetgha ljnnljnl',
        },
      ],
    };
  },
  async mounted() {
    this.programs = await this.getPrograms();
  },
  methods: {
    editProgram() {
      this.isEdit = true;
    },
    cancelEditProgram() {
      this.isEdit = false;
    },
    async getPrograms() {
      const response = await fetch(`http://localhost:8081/api/program`);
      console.log(response);
      if (response.ok) {
        console.log(await response.json());
      } else {
        console.log('error getting user data');
      }
    },
  },
};
</script>
