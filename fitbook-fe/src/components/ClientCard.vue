<template>
  <li>
    <div class="block">
      <div class="flex items-center px-4 py-4 sm:px-6">
        <div class="min-w-0 flex-1 flex items-center">
          <div class="flex-shrink-0">
            <img
              class="h-12 w-12 rounded-full"
              src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
              alt=""
            />
          </div>
          <div class="min-w-0 flex-1 px-4 md:grid md:grid-cols-2 md:gap-4">
            <div class="flex flex-col items-start">
              <p class="text-sm font-medium text-indigo-600 truncate">
                {{ firstName + " " + lastName }}
              </p>
              <div class="mt-2 flex items-center text-sm text-gray-500">
                <!-- Heroicon name: solid/mail -->
                <p class="truncate">{{ bDate }}</p>
                <p :class="bDate ? 'ml-3' : ''" class="truncate">
                  {{ gender }}
                </p>
              </div>
            </div>
            <div>
              <div class="flex flex-col items-start">
                <button
                  @click="$emit('initializeChat')"
                  type="button"
                  class="mb-2 inline-flex items-center px-4 py-1 border border-gray-300 shadow-sm text-xs font-medium rounded text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  Chat
                </button>
                <router-link
                  :to="{ name: 'edit', params: { id: id } }"
                  class="inline-flex items-center px-4 py-1.5 border border-transparent shadow-sm text-sm leading-4 font-medium rounded text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                  >Edit</router-link
                >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </li>
</template>

<script>
export default {
  props: ["id", "firstName", "lastName", "bDate", "gender", "desc"],
  emits: ["initializeChat"],
};
</script>
