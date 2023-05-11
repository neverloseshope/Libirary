import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import axios from "axios";
import VueResource from "vue-resource";
import i18n from './i18n/i18n'

Vue.config.productionTip = false;

axios.defaults.baseURL = "http://localhost:8088";
Vue.prototype.$axios = axios;

Vue.use(ElementUI);

Vue.use(VueResource);

new Vue({
  router,
  store,
  axios,
  i18n,
  render: (h) => h(App)
}).$mount("#app");
