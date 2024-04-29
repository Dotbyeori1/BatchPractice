import { createRouter, createWebHistory } from 'vue-router';
import TableComponent from '../components/TableComponent.vue';

const routes = [
    {
        path: '/',
        name: 'Main',
        component: TableComponent
    }
    // 다른 라우트 설정들...
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;