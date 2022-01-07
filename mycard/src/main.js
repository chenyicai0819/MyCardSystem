import { createApp } from 'vue'
import App from './App.vue'
import Vuex from 'vuex'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'
import axios from './axios/config'
import store from './store'
import * as icons from '@element-plus/icons-vue'

const app=createApp(App)
app.use(ElementPlus)
app.use(Vuex)
app.use(store)
app.config.globalProperties.$axios = axios
app.use(router).mount('#app')
Object.keys(icons).forEach(key => {
    app.component(key, icons[key])
})



// axios.defaults.baseURL = '/api'
