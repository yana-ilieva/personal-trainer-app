import { createRouter, createWebHistory } from 'vue-router';
import Clients from '../pages/Clients.vue';
import Trainers from '../pages/Trainers.vue';
import Programs from '../pages/Programs.vue';
import EditClient from '../pages/EditClient.vue';
import Chat from '../pages/Chat.vue';
import MyProfile from '../pages/MyProfile.vue';
import Login from '../components/Login.vue';
import Registration from '../components/Registration.vue';
import Progress from '../pages/Progress.vue';
import Payment from '../pages/Payment.vue';
import store from '../store/index';

const routes = [
  {
    path: '/',
    redirect:
      store.getters['auth/role'] === 'ROLE_TRAINER' ? '/clients' : '/dashboard',
  },
  {
    path: '/dashboard',
    component: Progress,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/registration',
    component: Registration,
    meta: {
      requiresUnauth: true,
      hideHeader: true,
    },
  },
  {
    path: '/login',
    component: Login,
    meta: {
      hideHeader: true,
      requiresUnauth: true,
    },
  },
  {
    path: '/payment',
    component: Payment,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/trainers',
    component: Trainers,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/clients',
    component: Clients,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/clients/edit/:id',
    component: EditClient,
    name: 'edit',
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/programs',
    component: Programs,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/chat',
    component: Chat,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: '/my-profile',
    component: MyProfile,
    meta: {
      requiresAuth: true,
    },
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, _, next) => {
  if (to.meta.requiresAuth && !store.getters['auth/isAuthenticated']) {
    next('/login');
  } else if (to.meta.requiresUnauth && store.getters['auth/isAuthenticated']) {
    console.log(store.getters['auth/role']);
    if (store.getters['auth/role'] === 'ROLE_TRAINER') {
      next('/dashboard');
    } else {
      next('/dashboard');
    }
  } else {
    next();
  }
});

export default router;
