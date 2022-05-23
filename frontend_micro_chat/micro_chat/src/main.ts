import Vue from 'vue'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

Vue.config.productionTip = false
axios.interceptors.request.use(function (config) {
  if (store.state.token !== null) {
    config.headers!.Authorization = `Bearer ${store.state.token}`;
  }
  return config
})
axios.interceptors.response.use(res => {
  return res
}, error => {
  if (error.response.status === 403) {
    alert('Não autorizado!')
  }
  else if (error.response.status === 401) {
    store.commit('logout')
    router.push('/login')
  }
  throw error
})

axios.defaults.baseURL =
  "https://localhost:8085/microchat";

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')




