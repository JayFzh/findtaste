import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index'
import '@/styles/base.scss'
import store from "@/store";
import service from "@/utils/network";

import ElementPlus from 'element-plus';
import 'element-plus/theme-chalk/index.css';

import locale from 'element-plus/lib/locale/lang/zh-cn'



// import 'xe-utils'
// import VXETable from 'vxe-table'
// import 'vxe-table/lib/style.css'



const app = createApp(App)
// router.beforeEach((to,from,next) => {

//     if (to.meta.requireAuth) {
//         if (store.state.userInfo.data.token) {
//             if (to.path === '/login') {
//                 next('/home');
//             } else {
//                 next()
//             }
//         } else {
//             next('/login');
//         }
//     } else {
//         next()
//     }
// })


app.use(router)
app.use(store)
app.use(ElementPlus,{locale})

app.mount('#app')
app.config.globalProperties.$axios = service()

