import Vue from 'vue'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import App from './App.vue'
import router from './router'
import store from './store'
import axios from "axios";
const instance = axios.create({
  baseURL: 'http://localhost:8080/microchat',
  timeout: 10000,
  params: {} // do not remove this, its added to add params later in the config
});

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

instance.interceptors.request.use((config) => {
  console.log('interceptor funcionando')
  if (store.state.token) {
    config.headers!["Authorization"] = 'Bearer ' + store.state.token;
  }
  return config
},
  (error) => {
    return Promise.reject(error);
  }
)
instance.interceptors.response.use((res) => {
  console.log('interceptor responsefuncionando')
  return res
}, error => {
  if (error.response.status === 403) {
    alert('Não autorizado!')
  }
  else if (error.response.status === 401) {
    store.commit('logout')
    router.push('/about')
  }
  throw error
})


Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')




