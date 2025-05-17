import HomeView from '@/views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import {RouteRecordRaw} from "vue-router";

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'home',
        component: HomeView,
        meta: { requiresAuth: true }
    },
    {
        path: '/login',
        name: 'login',
        component: LoginView
    },
    {
        path: '/:pathMatch(.*)*',
        redirect: '/login'
    }
];

export default routes
