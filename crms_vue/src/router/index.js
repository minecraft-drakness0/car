import Vue from 'vue'
import VueRouter from 'vue-router'
// 引入Login组件
import Login from '../components/Login.vue'
// 引入主页
import Home from '../components/Home.vue'
// 引入main主页
import Welcome from '../components/Welcome.vue'
// 引入用户列表
import UserList from '../components/admin/UserList.vue'
// 引入员工信息表
import EmployeeInfor from '../components/employee/EmployeeInfor.vue'
// 引入零件入库表
import PartsWarehousing from '../components/parts_warehouse/PartsWarehousing.vue'
// 引入零件出库表
import PartsDelivery from '../components/parts_warehouse/PartsDelivery.vue'
// 引入汽车登记表
import CarRegistration from '../components/car/CarRegistration.vue'
// 引入汽车修理表
import CarRepair from '../components/car/CarRepair.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    redirect: "/login"
  },
  {
    path: "/login",
    component: Login
  },
  {
    path: "/home",
    component: Home,
    redirect: "/welcome",
    children: [
      {path: "/welcome",component: Welcome,},
      {path: "/user",component: UserList,},
      {path: "/staff",component: EmployeeInfor,},
      {path: "/put",component: PartsWarehousing,},
      {path: "/out",component: PartsDelivery,},
      {path: "/register",component: CarRegistration,},
      {path: "/repair",component: CarRepair,},
    ],
  },
]

const router = new VueRouter({
  routes
})

/*
//  挂载路由导航守卫出现问题的时候使用
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).cathch(err => err)
}
*/

// 挂载路由导航守卫
router.beforeEach((to,from,next)=>{
  // to 将要访问
  // from 从哪访问
  // next 接着干，next(url) 重定向到url上，next() 继续访问 to路径
  if (to.path == '/login') return next();
  // 获取user
  const userFlag = window.sessionStorage.getItem("user"); //  取出当前用户
  if (!userFlag) return next('/login'); //  无值，返回登录页
  next(); //  符合要求 放行
})



export default router   //  暴露出去
