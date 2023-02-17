import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
import * as path from "path";

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../views/Home.vue'),
    children:[
      {
        path: '',
        name: 'Choose',
        component: ()=>import('../views/home/Choose')
      },
      {
        path: 'bookmarks',
        name: 'Bookmarks',
        component: ()=>import('../views/home/Bookmarks')
      },
      {
        path: 'easy',
        name: 'easy',
        component: ()=>import('../views/home/Choose_easy')
      },
    ]
  },
  {
    path: '/system',
    name: 'System',
    component: () => import('../views/index/System'),
    children:[
      {
        path: 'user',
        name: 'user',
        component: ()=>import('../views/index/User')
      },
      {
        path: 'menu',
        name: 'menu',
        component: ()=>import('../views/index/Menu')
      },
      {
        path: 'dict',
        name: 'dict',
        component: ()=>import('../views/index/Dict')
      },
    ]
  },
  {
    path: '/index',
    name: 'Index',
    component: () => import('../views/index/index'),
    children:[]
  },
  {
    path: '/login',
    name: 'Login',
    component: ()=>import('../views/manage/Login')
  },
  {
    path: '/wechar',
    name: 'wechar',
    component: ()=>import('../views/manage/Wechar')
  },
  {
    path: '/manages',
    name: 'Manages',
    component: ()=>import('../views/manage/Manages'),
    children:[
      // {
      //   path: 'logs',
      //   name: 'logs',
      //   component: ()=>import('../views/manage/Logs')
      // },
    ]
  },
  {
    path: '/logs',
    name: 'logs',
    component: ()=>import('../views/manage/Logs')
  },
  {
    path: '/wechatSuccess',
    name: 'wechatSuccess',
    component: () => import('../views/manage/wechatSuccess')
  },
  {
    path: '/map',
    name: 'Map',
    component: ()=>import('../views/map/Maps'),
    children:[
      // {
      //   path: 'mahome',
      //   name: 'MaHome',
      //   component: ()=>import('../views/manage/MaHome')
      // },
    ]
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  /*404路径访问 错误处理机制*/
  { path:'/:pathMatch(.*)',
    redirect: '/404',
  },
  {
    path:'/404',
    name:'404',
    component: () => import( '../views/404.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach(((to, from, next) => {
  if(to.path === "/manages") {
    /*判断交互过程中 token登录状态的记录*/
    let token = localStorage.getItem('loginToken');
    if (token === null || token === ''||token===undefined) {
      /*没有登录状态 表示当前用户未登录 则返回项目登录 首页*/
      alert("请先登录")
      next('/login');
    } else {
      /*存在token手令 表示当前用户已登录  访问路径放行*/
      next();
    }
  } else {
    next();
  }
}))

export default router
