import { createRouter, createWebHashHistory } from 'vue-router';
import Clients from '../pages/Clients.vue';
import Trainers from '../pages/Trainers.vue';
import Programs from '../pages/Programs.vue';
import Chat from '../pages/Chat.vue';
import MyProfile from '../pages/MyProfile.vue';

const routes = [
  {
    path: '/',
    redirect: '/clients',
  },
  {
    path: '/trainers',
    component: Trainers,
  },
  {
    path: '/clients',
    component: Clients,
  },
  {
    path: '/programs',
    component: Programs,
  },
  {
    path: '/chat',
    component: Chat,
  },
  {
    path: '/my-profile',
    component: MyProfile,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
