import { createApp } from 'vue'
import App from './App.vue'
import './index.css'

import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';

import router from './router/index.js'

import axios from 'axios'
import VueAxios from 'vue-axios'

const app = createApp(App)
app.use(ElementPlus)
app.use(router)
app.use(VueAxios,axios)
app.mount('#app')
