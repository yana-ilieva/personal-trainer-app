<template>
  <div class="w-full flex">
    <div class="w-3/12 px-4">
      <div class="w-full mt-8">
        <form action="">
          <div class="relative">
            <svg
              class="absolute left-2 top-0 bottom-0 my-auto"
              xmlns="http://www.w3.org/2000/svg"
              height="20px"
              viewBox="0 0 24 24"
              width="20px"
              fill="#000000"
            >
              <path d="M0 0h24v24H0V0z" fill="none" />
              <path
                d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"
              />
            </svg>
            <input
              class="w-full pl-9 py-1 bg-gray-100 rounded-md"
              placeholder="search by name"
              type="text"
              name="search"
              id="search"
            />
          </div>
        </form>
      </div>
      <div class="mt-10">
        <h4 class="text-left text-lg">Filter By:</h4>
        <form class="mt-4 flex flex-col" action="">
          <input
            class="self-start px-3 py-0.5 bg-gray-100 rounded-md mb-4"
            type="text"
            name="addressFilter"
            id="addressFilter"
            placeholder="Address"
          />

          <div class="flex items-center self-start">
            <input
              class="mr-1"
              type="radio"
              name="genderFilter"
              id="genderFilterMale"
              value="Male"
            />
            <label for="genderFilterMale">Male</label>
          </div>
          <div class="flex items-center self-start">
            <input
              class="mr-1"
              type="radio"
              name="genderFilter"
              id="genderFilterFemale"
              value="Female"
            />
            <label for="genderFilterFemale">Female</label>
          </div>
          <div class="flex items-center self-start">
            <input
              class="mr-1"
              type="radio"
              name="genderFilter"
              id="genderFilterAll"
              value="All"
              checked
            />
            <label for="genderFilterAll">All</label>
          </div>
          <input
            class="w-max mt-4 cursor-pointer transition-all px-4 py-1 text-lg rounded-md bg-mint text-syellow hover:bg-darkmint"
            type="submit"
            value="Filter"
          />
        </form>
      </div>
    </div>
    <div class="w-7/12 ml-10 mt-8">
      <ul class="w-full">
        <trainer-card
          v-for="trainer in trainers"
          :key="trainer.name"
          :name="trainer.name"
          :bDate="trainer.bDate"
          :gender="trainer.gender"
          :desc="trainer.desc"
          class="w-full h-44 border border-darkmint rounded-md"
        ></trainer-card>
      </ul>
    </div>
  </div>
</template>

<script>
import TrainerCard from '../components/TrainerCard.vue';
export default {
  components: { TrainerCard },
  data() {
    return {
      trainers: [
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
