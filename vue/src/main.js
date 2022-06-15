import Vue from 'vue';
import App from './App.vue';
import router from './router';
import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css';
import request from './utils/request';
//引入vuex
import store from './store'

//consolo.log关闭生产提示信息
Vue.config.productionTip = false;

Vue.use(Element, { size: 'small' });

//注册axios
Vue.prototype.request = request;

new Vue({
  router,
  render: h => h(App),
  store
}).$mount('#app');
