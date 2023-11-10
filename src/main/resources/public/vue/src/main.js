// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import router from './router'
import Axios from 'axios';
import 'element-ui/lib/theme-chalk/index.css'
import moment from 'moment'

Vue.prototype.moment = moment

let url = process.env.VUE_APP_API_URL
Axios.defaults.baseURL = url

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.prototype.$axios = Axios;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
