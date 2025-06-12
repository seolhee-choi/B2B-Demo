import { createRouter, createWebHistory } from 'vue-router'
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
        component: () => import('@/views/Test.vue'),
      }
    ]
  },
  // {
  //   path: '/dep',
  //   component: TestLayout,
  //   children: [
  //     {
  //       path: '/3',
  //       name: 'Dep3',
  //       component: () => import('@/views/Test.vue'),
  //     }
  //   ]
  // }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
