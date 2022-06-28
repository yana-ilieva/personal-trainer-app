<template>
  <div class="w-full">
    <div class="flex w-full">
      <!-- left section -->
      <div class="overflow-auto w-1/4 max-h-screen">
        <ul v-if="isTrainer" class="divide-y divide-gray-200">
          <chat-card
            v-for="chat of chats"
            :key="chat.client.id"
            :name="chat.client.name"
            @click="getChatMessages(chat)"
            :class="currentChatName === chat.client.name ? 'bg-gray-100' : ''"
          ></chat-card>
        </ul>
        <ul v-else class="border border-gray-200">
          <chat-card
            v-for="chat of chats"
            :key="chat.trainer.id"
            :name="chat.trainer.name"
            :class="currentChatName === chat.trainer.name ? 'bg-gray-100' : ''"
            @click="getChatMessages(chat)"
          ></chat-card>
        </ul>
      </div>
      <!-- right section -->
      <div
        class="w-3/4 max-h-screen overflow-y-auto border border-gray-300 relative"
      >
        <div v-if="currentChatName != ''" class="relative">
          <div id="chat-section" class="overflow-auto h-screen px-2 pt-5 pb-12">
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
                      <p class="text-xs text-left" v-else>
                        {{ message.sender }}
                      </p>
                      <!-- <p
                      class="text-xs text-right"
                      v-if="
                        message.sender ==
                        currentUser.firstName + ' ' + currentUser.lastName
                      "
                    >
                      {{ new Date() }}
                    </p>
                    <p class="text-xs text-left" v-else>
                      {{ message.createdTime }}
                    </p> -->
                      <p
                        :class="
                          message.sender ==
                          currentUser.firstName + ' ' + currentUser.lastName
                            ? 'bg-indigo-300'
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
          <div class="w-full p-2 absolute bottom-0 right-0">
            <form @submit.prevent="sendMessage" action="" class="flex w-full">
              <input
                class="rounded-bl-md rounded-tl-md px-3 py-0.5 bg-gray-50 border-gray-300 text-gray-500 w-11/12"
                type="text"
                name="chatSendInput"
                id="chatSendInput"
                placeholder="Write message..."
                ref="chatSendInput"
              />
              <input
                class="w-1/12 bg-indigo-600 hover:bg-indigo-700 text-white text-sm rounded-br-md rounded-tr-md cursor-pointer"
                type="submit"
                value="Send"
              />
            </form>
          </div>
        </div>
        <div v-else class="h-screen flex jsutify-center items-center">
          <p class="text-gray-500 font-semibold mx-auto">
            Click on any chat on the left to open it
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ChatCard from "../components/ChatCard.vue";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
export default {
  components: { ChatCard },
  computed: {
    chatHeight() {
      console.log(document.getElementById("chat-section"));
      return (
        window.innerHeight -
        Array.from(document.getElementsByTagName("nav"))[0].offsetHeight -
        45
      );
    },
    isTrainer() {
      return this.$store.getters["auth/role"] === "ROLE_TRAINER";
    },
  },
  data() {
    return {
      currentUser: {},
      currentChatName: "",
      chats: [],
      chatMessages: [],
      currentChatId: null,
    };
  },
  async mounted() {
    if (this.$route.query.user && this.$route.query.id) {
      this.getChatMessages({
        id: this.$route.query.id,
        trainer: { name: this.$route.query.user },
      });
    }
    this.chats = await this.getChats();
    this.currentUser = await this.getUser();
    this.subscribe();
  },
  methods: {
    subscribe() {
      this.socket = new SockJS("http://localhost:8081/ws");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
        },
        () => {
          this.connected = true;
          this.stompClient.subscribe("/user/queue/messages", (tick) => {
            this.chatMessages.push(JSON.parse(tick.body));
            this.$refs.chatSendInput.value = "";
          });
        },
        (error) => {
          console.log(error);
          this.connected = false;
        }
      );
    },
    async sendMessage(e) {
      const response = await fetch(`http://localhost:8081/api/chat/message`, {
        method: "POST",
        mode: "cors",
        headers: {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          content: e.target.chatSendInput.value,
          sender: this.currentUser.firstName + " " + this.currentUser.lastName,
          receiver: this.currentChatName,
          chatId: this.currentChatId,
        }),
      });
      console.log(response);
      if (response.ok) {
        return await response.json();
      } else {
        console.log("error getting user data");
      }
    },
    async getUser() {
      let url = "";
      if (this.$store.getters["auth/role"] === "ROLE_TRAINER") {
        url = `http://localhost:8081/api/trainer/user/${this.$store.getters["auth/userId"]}`;
      } else {
        url = `http://localhost:8081/api/client/user/${this.$store.getters["auth/userId"]}`;
      }
      const response = await fetch(url, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
        },
      });
      console.log(response);
      if (response.ok) {
        return await response.json();
      } else {
        console.log("error getting user data");
      }
    },
    async getChatMessages(chat) {
      console.log("chat: ", chat);
      const response = await fetch(
        `http://localhost:8081/api/chat/${chat.id}/messages/0`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
          },
        }
      );
      if (response.ok) {
        this.chatMessages = await response.json();
        this.currentChatId = chat.id;
        this.currentChatName = chat.trainer.name;
        console.log("current chat name: ", this.currentChatName);
      } else {
        console.log("error getting user data");
      }
    },
    async getChats() {
      let url = "";
      if (this.$store.getters["auth/role"] === "ROLE_TRAINER") {
        url = `http://localhost:8081/api/trainer/chat_mates`;
      } else {
        url = `http://localhost:8081/api/client/chat_mates`;
      }
      const response = await fetch(url, {
        method: "GET",
        headers: {
          Authorization: `Bearer ${this.$store.getters["auth/token"]}`,
        },
      });
      console.log(response);
      if (response.ok) {
        return await response.json();
      } else {
        console.log("error getting user data");
      }
    },
  },
};
</script>
