import Vue from 'vue'
import Router from 'vue-router'
//
import Home from "../components/Home"
import Help from "../components/Help"
import Login from '../components/Login'
import SignUp from "../components/SignUp"

//客户
import My from "../components/Customer/My"
import Cart from "../components/Customer/Cart"
import Address from "../components/Customer/Address"
import MyOrder from "../components/Customer/MyOrder"
import Checkout from '../components/Customer/Checkout'
//后台管理员
import Admin from "../components/Admin/Admin"
import Order from "../components/Admin/Order"
import Category from "../components/Admin/Category"
import Account from "../components/Admin/Account"


import Page404 from "../components/Page404";
import Commodity from "../components/Commodity";

Vue.use(Router);

const router = new Router({
  mode: 'history',
  routes: [

    {
      path:'/',
      redirect: '/home',
      hidden: true,
    },
    {
      path: '/home',
      component: Home,
      hidden: true,
      name: 'Home',
      class: "el-icon-view"
    },
    {
      path:'/commodity',
      component: Commodity,
      name: 'Commodity',
      class: "el-icon-s-goods",
    },
    {
      path: '/help',
      component: Help,
      name: 'Help',
      class: "el-icon-question",
    },
    {
      path: '/login',
      hidden: true,
      component: Login
    },
    {
      path: '/signUp',
      hidden: true,
      component: SignUp
    },
    {
      path: '/my',
      component: My,
      name: 'My',
      class: "el-icon-s-custom",
      hasChild: true,
      meta: {
        requireUser: true
      },
      children: [
        {
          path: '/my/cart',
          component: Cart,
          name: 'Cart',
          class: "el-icon-shopping-cart-2",
          meta: {
            requireUser: true
          },
        },
        {
          path: '/my/address',
          component: Address,
          name: 'Address',
          class: "el-icon-location-outline",
          meta: {
            requireUser: true
          },
        },
        {
          path: '/my/myOrder',
          component: MyOrder,
          name: 'Order',
          class: "el-icon-notebook-2",
          // meta: {
          //   requireUser: true
          // },
        },
      ]
    },

    //admin
    {
      path: '/admin',
      component: Admin,
      name: 'Admin',
      class: "",
      hidden: true,
      meta: {
        requireUser: true
      },
      children: [
        {
          path: '/admin/account',
          component: Account,
        },
        {
          path: '/admin/order',
          component: Order,
        },
        {
          path: '/admin/category',
          component: Category,
        }
        ]
    },
    {
      path: '*',
      component: Page404,
      name: '404',
      hidden: true
    },
    {
      path:'/checkout',
      component: Checkout,
      name: 'checkout',
      hidden: true,
    },
  ]
})

// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/signUp') {
    next();
  }
  const tokenStr = sessionStorage.getItem('token');
  if (!tokenStr && to.meta.requireUser) {
    return next('/login');
  }
  next();
});



export default router;

