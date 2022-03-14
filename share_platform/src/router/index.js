import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);
//获取原型对象上的push函数
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

export default new VueRouter({
    mode: "hash",
    routes: [
        {
            name: "home",
            path: "/",
            component: () => import("../pages/home/home"),
            meta: {
                title: "共享平台首页"
            }
        },
        {
            name: "login",
            path: "/login",
            component: () => import("../pages/login/login"),
            meta: {
                title: "登录"
            }
        },
        {
            name: "register",
            path: "/register",
            component: () => import("../pages/register/register"),
            meta: {
                title: "注册"
            }
        },
        {
            name: "search",
            path: "/search",
            component: () => import("../pages/search/search"),
            meta: {
                title: "搜索"
            }
        },
        {
            name: "detail",
            path: "/detail",
            component: () => import("../pages/detail/detail"),
            meta: {
                title: "详情"
            }
        },
        {
            name: "myOrder",
            path: "/myOrder",
            component: () => import("../pages/myOrder/myOrder"),
            meta: {
                title: "我的订单"
            }
        },
        {
            name: "publishEntrust",
            path: "/publishEntrust",
            component: () => import("../pages/publishEntrust/publishEntrust"),
            meta: {
                title: "发布委托"
            }
        },
        {
            name: "verifyEntrust",
            path: "/verifyEntrust",
            component: () => import("../pages/verifyEntrust/verifyEntrust"),
            meta: {
                title: "审核委托"
            }
        },
        {
            name: "editEntrust",
            path: "/editEntrust",
            component: () => import("../pages/verifyEntrust/editEntrust"),
            meta: {
                title: "审核编辑委托"
            }
        },
        {
            name: "cart",
            path: "/cart",
            component: () => import("../pages/cart/cart"),
            meta: {
                title: "购物车"
            }
        },
        {
            name: "myInfo",
            path: "/myInfo",
            component: () => import("../pages/myInfo/myInfo"),
            meta: {
                title: "个人信息"
            }
        }


    ]
});
