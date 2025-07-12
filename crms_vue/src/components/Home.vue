<template>
  <!-- 引入container布局 -->
  <el-container class="home-container">
    <!-- 头部 -->
    <el-header>
      <div>
        <img src="../assets/carlogo.png" alt />
        <span class="home_font">汽车修理管理平台</span>
      </div>
      <div class="but_2">
        <el-button type="primary" @click="dialogTableVisible = true" round>打印发票</el-button>
        <el-dialog title="发票" :visible.sync="dialogTableVisible" width="300px">
            修理费:<el-tag>{{printList.repair}}</el-tag><br><br>
          零件费:<el-tag>{{printList.parts}}</el-tag>
        </el-dialog>
      </div>

      <el-button type="info" @click="logout" icon="el-icon-switch-button" round
        >退出平台</el-button
      >
    </el-header>
    <!-- 主体 -->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside :width="isCollapase ? '64px' : '200px'">
        <!-- 伸缩按钮 -->
        <div class="toggle-button" @click="toggleCollapase">|||</div>
        <el-menu
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#409eff"
          unique-opened
          :collapse="isCollapase"
          :collapse-transition="false"
          :router="true"
          :default-active="activePath"
        >
          <!-- 一级菜单 -->
          <el-submenu
            :index="item.id + ''"
            v-for="item in menuList"
            :key="item.id"
          >
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span class="home_font">{{ item.title }}</span>
            </template>
            <!-- 二级菜单 -->
            <el-menu-item
              :index="it.path"
              v-for="it in item.sList"
              :key="it.id"
              @click="saveNavState(it.path)"
            >
              <template slot="title">
                <i class="el-icon-document"></i>
                <span class="home_font">{{ it.title }}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <!-- 主体内容 -->
      <el-main>
        <router-view>
          <!-- 内容在welcome.vue UserList.vue -->
        </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  created() {
    this.getprintingList();
  },
  data() {
    return {
      // 菜单列表
      menuList: [],
      // 伸缩
      isCollapase: false,
      // 默认路径
      activePath: "/welcome",

      printList: {
        parts: "",
        repair: "",
      },

      dialogTableVisible: false,
      dialogFormVisible: false,
    };
  },
  // 类似onload事件
  created() {
    // 查询menuList
    this.getMenuList();
    // 取出session里的path，动态修改activePath
    this.activePath = window.sessionStorage.getItem("activePath");
    this.getprintingListParts();
    this.getprintingListRepair();
    console.log(this.printList);
  },
  methods: {
    // 安全退出
    logout() {
      window.sessionStorage.clear(); //  清除session，安全退出后后台会留下登录痕迹，这个必须清除
      this.$router.push("/login"); //  回到首页
    },
    // 获取导航菜单方法
    async getMenuList() {
      const { data: res } = await this.$http.get("menus"); //  访问后台
      console.log(res);
      if (res.flag != 200) return this.$message.error("获取列表失败"); //  访问失败
      this.menuList = res.menus; // 数据回填
    },
    // 控制伸缩
    toggleCollapase() {
      this.isCollapase = !this.isCollapase;
    },
    // 保存二级菜单的路径
    saveNavState(activePath) {
      window.sessionStorage.setItem("activePath", activePath); //  存放点击的二级菜单
      this.activePath = activePath; //  给点击的菜单添加高亮
    },
    // 打印发票
    async getprintingListParts() {
      const { data: res } = await this.$http.get("listParts");
      this.printList.parts = res;
    },
    async getprintingListRepair() {
      const { data: res } = await this.$http.get("listRepair");
      this.printList.repair = res;
    },
  },
};
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}
// 头部样式
.el-header {
  background-color: #373b41;
  display: flex;
  // 左右贴边
  justify-content: space-between;
  // 左边界
  padding-left: 1%;
  align-items: center;
  color: #fff;
  font-size: 24px;
  // 左侧div加布局
  > div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
}
// 侧边样式
.el-aside {
  background-color: #333744;
  .el-menu {
    border-right: none;
  }
}
// 主体样式
.el-main {
  background-color: #eaedf1;
}
// logo样式
img {
  width: 40px;
  height: 40px;
}
// |||伸缩按钮样式
.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer; //  鼠标划到图标时，显示小手
}
// 黑体字
.home_font {
  font-family: PingFang SC;
}
.but_2 {
  position: absolute;
  right: 153px;
}
</style>