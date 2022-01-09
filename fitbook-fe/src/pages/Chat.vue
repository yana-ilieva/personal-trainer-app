<template>
  <div class="w-full">
    <div class="flex w-full">
      <!-- left section -->
      <div class="overflow-auto w-1/4">
        <ul class="border border-darkmint">
          <chat-card
            v-for="client of clients"
            :key="client.client.id"
            :name="client.client.name"
            @click="getChatMessages(client)"
          ></chat-card>
        </ul>
      </div>
      <!-- right section -->
      <div class="w-3/4">
        <div
          id="chat-section"
          :style="{ height: `${chatHeight}px` }"
          class="overflow-auto border border-darkmint px-2 py-5"
        >
          <div>
            <div class="">
              <ul>
                <li
                  class="w-full flex flex-col mb-1"
                  v-for="message in chatMessages"
                  :key="message.id"
                >
                  <div
                    :class="
                      message.sender ==
                      currentUser.firstName + ' ' + currentUser.lastName
                        ? 'self-end'
                        : 'self-start'
                    "
                    class="w-max max-w-xs"
                  >
                    <p
                      class="text-xs text-right"
                      v-if="
                        message.sender ==
                        currentUser.firstName + ' ' + currentUser.lastName
                      "
                    >
                      You
                    </p>
                    <p class="text-xs text-left" v-else>{{ message.sender }}</p>
                    <p
                      :class="
                        message.sender ==
                        currentUser.firstName + ' ' + currentUser.lastName
                          ? 'bg-mint'
                          : 'bg-gray-200'
                      "
                      class="text-left text-black rounded-md px-3 py-1"
                    >
                      {{ message.content }}
                    </p>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="w-full p-2">
          <form @submit.prevent="sendMessage" action="" class="flex w-full">
            <input
              class="rounded-bl-md rounded-tl-md px-3 py-0.5 bg-gray-300 w-11/12"
              type="text"
              name="chatSendInput"
              id="chatSendInput"
              placeholder="Write message..."
            />
            <input
              class="w-1/12 bg-mint rounded-br-md rounded-tr-md cursor-pointer"
              type="submit"
              value="Send"
            />
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ChatCard from '../components/ChatCard.vue';
export default {
  components: { ChatCard },
  computed: {
    chatHeight() {
      console.log(document.getElementById('chat-section'));
      return (
        window.innerHeight -
        Array.from(document.getElementsByTagName('nav'))[0].offsetHeight -
        45
      );
    },
  },
  data() {
    return {
      currentUser: {},
      currentChatName: '',
      clients: [],
      chatMessages: [],
      currentChatId: null,
    };
  },
  async mounted() {
    this.clients = await this.getClients();
    this.currentUser = await this.getUser();
    console.log(this.currentUser);
  },
  methods: {
    async sendMessage(e) {
      console.log(
        JSON.stringify({
          content: e.target.chatSendInput.value,
          sender: this.currentUser.firstName + ' ' + this.currentUser.lastName,
          receiver: this.currentChatName,
          chatId: this.currentChatId,
        })
      );
      const response = await fetch(`http://localhost:8081/api/chat/message`, {
        method: 'POST',
        mode: 'cors',
        headers: {
          Authorization: `Bearer ${this.$store.getters['auth/token']}`,
        },
        body: JSON.stringify({
          content: e.target.chatSendInput.value,
          sender: this.currentUser.firstName + ' ' + this.currentUser.lastName,
          receiver: this.currentChatName,
          chatId: this.currentChatId,
        }),
      });
      console.log(response);
      if (response.ok) {
        return await response.json();
      } else {
        console.log('error getting user data');
      }
    },
    async getUser() {
      let url = '';
      if (this.$store.getters['auth/role'] === 'ROLE_TRAINER') {
        url = `http://localhost:8081/api/trainer/user/${this.$store.getters['auth/userId']}`;
      } else {
        url = `http://localhost:8081/api/client/user/${this.$store.getters['auth/userId']}`;
      }
      const response = await fetch(url, {
        method: 'GET',
        headers: {
          Authorization: `Bearer ${this.$store.getters['auth/token']}`,
        },
      });
      console.log(response);
      if (response.ok) {
        return await response.json();
      } else {
        console.log('error getting user data');
      }
    },
    async getChatMessages(chat) {
      const response = await fetch(
        `http://localhost:8081/api/chat/${chat.id}/messages/0`,
        {
          method: 'GET',
          headers: {
            Authorization: `Bearer ${this.$store.getters['auth/token']}`,
          },
        }
      );
      if (response.ok) {
        this.chatMessages = await response.json();
        this.currentChatId = chat.id;
        this.currentChatName = chat.client.name;
      } else {
        console.log('error getting user data');
      }
    },
    async getClients() {
      const response = await fetch(
        `http://localhost:8081/api/trainer/chat_mates`,
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
