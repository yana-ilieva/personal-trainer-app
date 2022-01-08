import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import './index.css';

const app = createApp(App);

// app.use(
//   new VueSocketIO({
//     debug: true,
//     connection: 'http://localhost:8081/ws',
//     vuex: {
//       store,
//       actionPrefix: 'SOCKET_',
//       mutationPrefix: 'SOCKET_',
//     },
//     options: { path: '/' },
//   })
// );
app.use(store);
app.use(router);

app.mount('#app');
