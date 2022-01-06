<template>
  <div class="w-full">
    <div class="w-1/2 mx-auto mt-8">
      <ul class="w-full">
        <client-card
          v-for="client in clients"
          :key="client.name"
          :name="client.name"
          :bDate="client.bDate"
          :gender="client.gender"
          :desc="client.desc"
          class="w-full h-44 border border-darkmint rounded-md"
        ></client-card>
      </ul>
    </div>
  </div>
</template>

<script>
import ClientCard from '../components/ClientCard.vue';
export default {
  components: { ClientCard },
  data() {
    return {
      clients: [
        {
          name: 'aaaa',
          bDate: '41421',
          gender: 'male',
          desc: 'wmvpawrmbpaemr prb iarobvma roiam erpi mapr',
        },
        {
          name: 'aaaa',
          bDate: '41421',
          gender: 'male',
          desc: 'wmvpawrmbpaemr prb iarobvma roiam erpi mapr',
        },
        {
          name: 'aaaa',
          bDate: '41421',
          gender: 'male',
          desc: 'wmvpawrmbpaemr prb iarobvma roiam erpi mapr',
        },
      ],
    };
  },
};
</script>
