<template>
  <div class="w-full py-16">
    <div class="mx-auto w-9/12">
      <ul class="grid grid-cols-2 gap-5">
        <program-card
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
export default {
  components: { ProgramCard },
  data() {
    return {
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
};
</script>
