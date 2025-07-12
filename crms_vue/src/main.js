import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
// 引入全局样式
import './assets/css/global.css'
// 引入iconfont，第三方的在main.js中引入，iconfont是矢量图标（登录界面）
import './assets/iconfont_1/iconfont.css'

// 导入axios（创建项目是下载的依赖）
import axios from 'axios'
// 挂载axios
Vue.prototype.$http = axios
// 设置访问路径
axios.defaults.baseURL = "http://127.0.0.1:9000"

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
