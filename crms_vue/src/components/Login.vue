// 用户登录界面

<template>
    <!-- container 布局 -->
    <div class="login_container">
        <!-- box 登录用的盒子 -->
        <div class="login_box">
            <!-- avater_box 装头像的盒子 -->
            <div class="avater_box">
                <img src="../assets/head_portrait.png" alt/>
            </div>
            <!-- form表单区域 -->
            <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm" class="login_form" label-width="0">
                <!-- 用户名 引入了第三方图标iconfont -->
                <el-form-item prop="username">
                    <el-input v-model="loginForm.username" placeholder="用户名" prefix-icon="iconfont icon-denglu"></el-input>
                </el-form-item>
                <!-- 密码 引入了第三方图标iconfont -->
                <el-form-item prop="password">
                    <el-input v-model="loginForm.password" placeholder="密码" prefix-icon="iconfont icon-mima" type="password"></el-input>
                </el-form-item>
                <!-- 按钮 -->
                <el-form-item class="btns">
                    <el-button type="primary" @click="login">登录</el-button>
                    <el-button type="info" @click="resetLoginForm">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

// script是用来写js逻辑
<script>
export default {
    // 数据
    data() {
        return {
            // 表单数据对象
            loginForm:{
                username: "admin",
                password: "123456"
            },
            // 验证对象
            loginRules:{
                // 校验用户名
                username: [
                    { required: true, message: '用户名为必填项', trigger: 'blur' }, //必填项验证
                    { min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'blur' }  //长度验证
                ],
                // 校验密码
                password: [
                    { required: true, message: '用户密码为必填项', trigger: 'blur' }, //必填项验证
                    { min: 6, max: 10, message: '长度在 6 到 10 个字符', trigger: 'blur' }  //长度验证
                ],
            },
        };
    },
    // 方法
    methods: {
        // 重置表单内容
        resetLoginForm() {
            this.$refs.loginFormRef.resetFields();
        },
        // 登录方法
        login() {
            // 验证校验规则
            this.$refs.loginFormRef.validate(async valid => {
                if (!valid) return; // 验证失败则结束
                const {data:res} = await this.$http.post("login",this.loginForm);   // 访问后台
                if (res.flag == "ok") {     //  是否登入后台
                    this.$message.success("操作成功！！！"); // 信息提示

                    window.sessionStorage.setItem("user",res.user); //  存储user对象
                    console.log(res.user);
                    this.$router.push({path:"/home"});  // 页面路由跳转
                }else{
                    this.$message.error("操作失败！！！");  // 错误提示
                }
            })
        },
    },
}
</script>

// 用来写样式
<style lang="less" scoped>
    // 很结点样式，背景颜色和大小
    .login_container {
        background-color: #001f3f;
        height: 100%;
    }
    // 登录框 
    .login_box {
        // width 宽度
        width: 450px;
        // height 高度
        height: 300px;
        background-color: #fff;
        // border-radius 圆角
        border-radius: 8px;
        // 排列方式：绝对定位
        position: absolute;
        left: 50%;
        top: 50%;
        // 根据自己位置 以自己为长度位移
        transform: translate(-50%,-50%);
        // 头像样式
        .avater_box {
            width: 110px;
            height: 110px;
            // 头像边框
            border: 1px soild #eee;
            border-radius: 50%;
            // 边距
            padding: 6px;
            // 盒子阴影
            box-shadow: 0 0 10px #0ee;
            position: absolute;
            left: 50%;
            transform: translate(-50%,-50%);
            background-color: #0ee;
            img {
                width: 100%;
                height: 100%;
                border-radius: 50%;
                background-color: #eee;
            }
        }
    }
    // 按钮样式
    .btns {
        // 弹性布局
        display: flex;
        // 尾巴对齐
        justify-content: flex-end;
    }
    // 表单样式
    .login_form {
        position: absolute;
        // 距底部
        bottom: 0%;
        width: 100%;
        padding: 0 10px;
        // 边框（不设置这个，输入框会从盒子凸出）
        box-sizing: border-box;
    }

</style>