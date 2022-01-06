<template>
  <div class="w-full">
    <div class="flex w-full">
      <!-- left section -->
      <div class="overflow-auto w-1/4">
        <ul class="border border-darkmint">
          <chat-card
            v-for="chat in chats"
            :key="chat.name"
            :name="chat.name"
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
                  :key="message.text"
                >
                  <div
                    :class="
                      message.sender == currentUser ? 'self-end' : 'self-start'
                    "
                    class="w-max max-w-xs"
                  >
                    <p
                      class="text-xs text-right"
                      v-if="message.sender == currentUser"
                    >
                      You
                    </p>
                    <p class="text-xs text-left" v-else>{{ message.sender }}</p>
                    <p
                      :class="
                        message.sender == currentUser
                          ? 'bg-mint'
                          : 'bg-gray-200'
                      "
                      class="text-left text-black rounded-md px-3 py-1"
                    >
                      {{ message.text }}
                    </p>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="w-full p-2">
          <form action="" class="flex w-full">
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
      currentUser: 'Yana Ilieva',
      chats: [
        {
          name: 'Yana Ilieva',
          img: 'test',
        },
        {
          name: 'Mariyan Zhelyazkov',
          img: 'test',
        },
        {
          name: 'Marc Anthony',
          img: 'test',
        },
      ],
      chatMessages: [
        {
          text: 'iria ivarwnmbvai',
          sender: 'Yana Ilieva',
        },
        {
          text: 'shakira shakira',
          sender: 'Yana Ilieva',
        },
        {
          text: 'bailandoooo bailandoooo oooooooooo oooooooooooo oooooooooooooooo',
          sender: 'Mariyan Zhelyazkov',
        },
        {
          text: 'jeanyyy',
          sender: 'Yana Ilieva',
        },
        {
          text: 'send nudes',
          sender: 'Mariyan Zhelyazkov',
        },
        {
          text: 'fneg rsrvwgvrae',
          sender: 'Mariyan Zhelyazkov',
        },
        {
          text: 'fneg rsrvwgvrae',
          sender: 'Mariyan Zhelyazkov',
        },
        {
          text: 'fneg rsrvwgvrae',
          sender: 'Mariyan Zhelyazkov',
        },
        {
          text: 'fneg rsrvwgvrae',
          sender: 'Mariyan Zhelyazkov',
        },
        {
          text: 'fneg rsrvwgvrae',
          sender: 'Mariyan Zhelyazkov',
        },
        {
          text: 'fneg rsrvwgvrae',
          sender: 'Mariyan Zhelyazkov',
        },
        {
          text: 'fneg rsrvwgvrae',
          sender: 'Mariyan Zhelyazkov',
        },
      ],
    };
  },
};
</script>
