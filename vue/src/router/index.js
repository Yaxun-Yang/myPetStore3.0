import Vue from 'vue'
import Router from 'vue-router'
//
import All from "../components/All";
import Home from "../components/Home"
import Help from "../components/Help"
import Login from '../components/Login'
import SignUp from "../components/SignUp"
//客户
import My from "../components/Customer/My"
import Cart from "../components/Customer/Cart"
import Pay from "../components/Customer/order/Pay";
import Setting from "../components/Customer/Setting"
import Checkout from '../components/Customer/Checkout'
import MyOrder from "../components/Customer/order/MyOrder"
import AllOrder from "../components/Customer/order/AllOrder"
import Alipay from "../components/Customer/order/Alipay";
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
      redirect: '/all',
      hidden: true
    },
    {
      path: '/all',
      component: All,
      name: "all",
      hidden: false,
      redirect: '/home',
      children:[
        {
          path: '/home',
          component: Home,
          hidden: true,
          name: 'Home',
        },
        {
          path:'/commodity',
          component: Commodity,
          name: 'Commodity',
          class: "el-icon-s-goods"
        },
        {
          path: '/help',
          component: Help,
          name: 'Help',
          class: "el-icon-question"
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

        //admin
        {
          path: '/admin',
          component: Admin,
          name: 'Admin',
          class: "el-icon-user-solid",
          isUser: false,
          hasChild: true,
          meta: {
            requireUser: true
          },
          children: [
            {
              path: '/admin/account',
              component: Account,
              name: 'Account',
              class: "el-icon-user",
              meta: {
                requireUser: true
              },
            },
            {
              path: '/admin/order',
              component: Order,
              name: 'Order',
              class: "el-icon-s-order",
              meta: {
                requireUser: true
              },
            },
            {
              path: '/admin/category',
              component: Category,
              name: 'Category',
              class: "el-icon-goods",
              meta: {
                requireUser: true
              },
            }
          ]
        },

        //user
        {
          path: '/my',
          component: My,
          name: 'My',
          class: "el-icon-user-solid",
          hasChild: true,
          isUser: true,
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
              path: '/my/order',
              component: MyOrder,
              name: 'Order',
              class: "el-icon-notebook-2",
              meta: {
                requireUser: true
              },
            },
            {
              path: '/my/setting',
              component: Setting,
              name: 'Setting',
              class: "el-icon-setting",
              meta: {
                requireUser: true
              },
            },
            {
              path:'/checkout',
              component: Checkout,
              name: 'Checkout',
              hidden: true,
              meta: {
                requireUser: true
              },
            },
            {
              path:'/pay',
              component: Pay,
              name: 'Pay',
              hidden: true,
              meta: {
                requireUser: true
              },
              // children:[

              // ]
            },
              {
                path:'/alipay',
                component: Alipay,
                name: 'Alipay',
                hidden: true,
                meta: {
                  requireUser: true
                },
              },
            {
              path: '/my/order/all',
              component: AllOrder,
              hidden: true,
              name: 'AllOrder',
              meta: {
                requireUser: true
              },
            },

          ]
        },
      ]
    },
    // {
    //   path: '/help',
    //   component: Help,
    //   name: 'Help',
    //   class: "el-icon-question",
    // },
    {
      path: '*',
      component: Page404,
      name: '404',
      hidden: true
    },
  ]
})

// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/signUp') {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('usernameAdmin');
    next();
  }
  const tokenStr = sessionStorage.getItem('token');
  if (!tokenStr && to.meta.requireUser) {
    return next('/login');
  }
  next();
});



export default router;

