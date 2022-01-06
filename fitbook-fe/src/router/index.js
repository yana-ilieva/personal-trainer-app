import { createRouter, createWebHistory } from 'vue-router';
import Clients from '../pages/Clients.vue';
import Trainers from '../pages/Trainers.vue';
import Programs from '../pages/Programs.vue';
import EditClient from '../pages/EditClient.vue';
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
    path: '/clients/edit/:id',
    component: EditClient,
    name: 'edit',
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
  history: createWebHistory(),
  routes,
});

export default router;
