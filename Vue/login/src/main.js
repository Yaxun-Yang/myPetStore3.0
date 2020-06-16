// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import iView from 'iview'
import './assets/global.css' //全局css
import 'iview/dist/styles/iview.css' // 使用 CSS
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';  // 使用 CSS
import CollapseTransition from 'element-ui/lib/transitions/collapse-transition';  //这啥？
//这俩是啥来着？
Vue.config.productionTip = false;
Vue.component(CollapseTransition.name, CollapseTransition);

//平滑滚
import smoothscroll from 'smoothscroll-polyfill';
smoothscroll.polyfill();
// vuex
import Vuex from 'vuex';
Vue.use(Vuex);
//ui组件
Vue.use(ElementUI);
Vue.use(iView);
//axios
Vue.prototype.$axios = axios;

// 添加请求拦截器，在请求头中加token
axios.interceptors.request.use(config=>{
  //为请求头config添加Authorization
  config.headers.Authorization = sessionStorage.getItem('token');
  return config;
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
});
