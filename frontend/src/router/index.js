import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'dashboard',
      component: import("@/views/Dashboard.vue"),
    },
    {
      path: '/login',
      name: 'login',
      component: import("@/views/Login.vue"),
    },
    {
      path: '/clientMgmt/list',
      name: 'clientList',
      component: import("@/views/clientMgmt/ClientList.vue"),
    },
    {
      path: '/clientMgmt/create',
      name: 'clientCreate',
      component: import("@/views/clientMgmt/ClientCreate.vue"),
    },
    {
      path: '/clientMgmt/edit/:id',
      name: 'clientEdit',
      component: import("@/views/clientMgmt/ClientEdit.vue"),
    },
    {
      path: '/salesMgmt/list',
      name: 'salesList',
      component: import("@/views/salesMgmt/SalesList.vue"),
    },
    {
      path: '/salesMgmt/create',
      name: 'salesCreate',
      component: import("@/views/salesMgmt/SalesCreate.vue"),
    },
    {
      path: '/salesMgmt/edit/:id',
      name: 'salesEdit',
      component: import("@/views/salesMgmt/SalesEdit.vue"),
    },
    {
      path: '/sample',
      name: 'sampleList',
      component: import("@/views/Sample.vue")
    }
  ],
})

export default router
