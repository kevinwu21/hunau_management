import Vue from 'vue';
import VueRouter from 'vue-router';
import store from "@/store";

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'manage',
    component: () => import('../views/manage.vue'),
    redirect:'/homePage',
    children: [
      { path: 'homePage', name: '首页', component: () => import('../views/homePage.vue') },
      { path: 'userInfo', name: '用户管理', component: () => import('../views/userInfo.vue') },
      { path: 'personPage', name: '个人信息', component: () => import('../views/personPage.vue') },
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/loginView.vue'),
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/registerView.vue'),
  }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});


// 路由守卫
router.beforeEach((to, from, next) => {
  // 设置当前的路由名称，为了在Header组件中去使用
  localStorage.setItem("currentPathName", to.name)
  // 触发store的数据更新
  store.commit("setPath")
  // 放行路由
  next()
})

export default router;
