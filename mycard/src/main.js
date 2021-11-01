import { createApp } from 'vue'
import App from './App.vue'
import Vuex from 'vuex'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'
import axios from './axios/config'
import store from './store'

const app=createApp(App)
app.use(ElementPlus)
app.use(Vuex)
app.use(store)
app.config.globalProperties.$axios = axios
app.use(router).mount('#app')


