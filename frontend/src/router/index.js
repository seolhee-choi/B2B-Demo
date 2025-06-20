import { createRouter, createWebHistory } from 'vue-router'
import { useAccountStore } from "@/stores/useAccountStore.js";
import DefaultLayout from '@/layouts/DefaultLayout.vue'
import LoginLayout from '@/layouts/LoginLayout.vue'
import TestLayout from '@/layouts/TestLayout.vue'

const routes = [
  {
    path: '/login',
    component: LoginLayout, // Login 전용 레이아웃
    children: [
      {
        path: '',
        name: 'login',
        component: () => import('@/views/Login.vue'),
        meta: { guestOnly: true },
      }
    ]
  },
  {
    path: '/',
    component: DefaultLayout, // 기본 레이아웃
    children: [
      {
        path: '',
        name: 'dashboard',
        component: () => import('@/views/Dashboard.vue'),
      },
      {
        path: 'clientMgmt/list',
        name: 'clientList',
        component: () => import('@/views/clientMgmt/ClientList.vue'),
      },
      {
        path: 'clientMgmt/create',
        name: 'clientCreate',
        component: () => import('@/views/clientMgmt/ClientCreate.vue'),
      },
      {
        path: 'clientMgmt/edit/:id',
        name: 'clientEdit',
        component: () => import('@/views/clientMgmt/ClientEdit.vue'),
      },
      {
        path: 'clientMgmt/modalSample01',
        name: 'modalSample01',
        component: () => import('@/views/clientMgmt/modalSample01.vue'),
      },
      {
        path: 'clientMgmt/modalSample02',
        name: 'modalSample02',
        component: () => import('@/views/clientMgmt/modalSample02.vue'),
      },
      {
        path: 'clientMgmt/modalSample03',
        name: 'modalSample03',
        component: () => import('@/views/clientMgmt/modalSample03.vue'),
      },
      {
        path: 'clientMgmt/modalSample04',
        name: 'modalSample04',
        component: () => import('@/views/clientMgmt/modalSample04.vue'),
      },
      {
        path: 'salesMgmt/list',
        name: 'salesList',
        component: () => import('@/views/salesMgmt/SalesList.vue'),
      },
      {
        path: 'salesMgmt/create',
        name: 'salesCreate',
        component: () => import('@/views/salesMgmt/SalesCreate.vue'),
      },
      {
        path: 'salesMgmt/edit/:id',
        name: 'salesEdit',
        component: () => import('@/views/salesMgmt/SalesEdit.vue'),
      },
      {
        path: 'sample',
        name: 'sampleList',
        component: () => import('@/views/Sample.vue'),
      },
      {
        path: 'dep/3',
        name: 'Test',
        component: () => import('@/views/test/Test.vue'),
      }
    ]
  },
  {
    path: '/popup-page',
    name: 'popupPage',
    component: () => import('@/views/test/Popup1.vue'),
  },
  {
    path: '/popup-page2',
    name: 'popupPage2',
    component: () => import('@/views/test/Popup2.vue'),
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

router.beforeEach(async(to, from, next) => {
  const guestOnly = to.matched.some((record) => record.meta.guestOnly);

  const accountStore = useAccountStore();
  const isLoggedIn = accountStore.isLoggedIn;

  if (guestOnly && isLoggedIn) {
    return next("/");
  }

  next();
})
export default router
