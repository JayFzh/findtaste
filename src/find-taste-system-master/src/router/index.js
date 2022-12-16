import {createRouter, createWebHistory } from 'vue-router';



const routes = [
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/login.vue'),
        meta: {
            title: '登录界面',
        },
        children: []
    },
    {
        path: '/home',
        name: 'Home',
        component: () => import('@/components/layout.vue'),
        meta: {
            title: '主页',
            requireAuth: true
        },
        children: [
            {
                path: '/changePwd',
                name: 'changePwd',
                component: () => import('@/views/changePwd'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/infoShow',
                name: 'infoShow',
                component: () => import('@/views/infoShow'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/postInfo',
                name: 'postInfo',
                component: () => import('@/views/postList'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/findPostInfo',
                name: 'findPostInfo',
                component: () => import('@/views/findPostList'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/reqPost',
                name: 'reqPost',
                component: () => import('@/components/reqModule'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/foundPost',
                name: 'foundPost',
                component: () => import('@/views/hasFound'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/getChart',
                name: 'getChart',
                component: () => import('@/views/getChart'),
                meta: {
                    requireAuth: true
                }
            },
            {
                path: '/getTable',
                name: 'getTable',
                component: () => import('@/views/getTable'),
                meta: {
                    requireAuth: true
                }
            }
        ]
    },

]

const router = new createRouter({
    history: createWebHistory(),
    routes: routes
})

export default router