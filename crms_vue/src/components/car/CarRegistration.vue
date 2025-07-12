<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class='el-icon-arrow-right'>
            <el-breadcrumb-item :to="{path:'/home'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>权限管理</el-breadcrumb-item>
            <el-breadcrumb-item>汽车登记信息列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-row :gutter="25">
                <!-- 搜索栏区域 -->
                <el-col :span="10">
                    <el-input placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getCarregList">
                        <el-button slot="append" icon="el-icon-search" @click="getCarregList"></el-button>
                    </el-input>
                </el-col>
                <!-- 添加登记信息按钮 -->
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible = true">添加登记信息</el-button>
                </el-col>
            </el-row>
            <!-- 零件入库列表部分 border边框 stripe隔行颜色 -->
            <el-table :data="CarregList" border stripe>
                <el-table-column type="index"></el-table-column>    <!-- 索引列 -->
                <el-table-column label="车主姓名" prop="driver_name"></el-table-column>
                <el-table-column label="车牌号" prop="car_number"></el-table-column>
                <el-table-column label="汽车型号" prop="car_model"></el-table-column> 
                <el-table-column label="登记时间" prop="reg_time"></el-table-column>
                <!-- 自定义插槽 -->
                <el-table-column label="操作">
                    <el-templete slot-scope="scope">
                        <!-- 权限 -->
                        <el-tooltip effect="dark" content="分配权限" placement="top-start" 
                        :enterable="false">
                            <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
                        </el-tooltip>
                    </el-templete>
                </el-table-column>
             </el-table>
             <div>
                <el-pagination @size-change="handleSizeChange" 
                @current-change="handleCurrentChange"
                :current-page="queryInfo.pageNum" 
                :page-sizes="[1, 3, 6, 100]" 
                :page-size="queryInfo.pageSize"
                layout="total, sizes, prev, pager, next, jumper" 
                :total="total">
                </el-pagination>
            </div>
        </el-card>
        <!-- 新增用户对话框 -->
        <el-dialog title="添加汽车登记信息" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
            <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
                <!-- 零件型号 -->
                <el-form-item label="姓名" prop="driver_name">
                    <el-input v-model="addForm.driver_name"></el-input>
                </el-form-item>
                <!-- 入库数量 -->
                <el-form-item label="车牌号" prop="car_number">
                    <el-input v-model="addForm.car_number"></el-input>
                </el-form-item>
                <!-- 库存数量 -->
                <el-form-item label="车型号" prop="car_model">
                    <el-input v-model="addForm.car_model"></el-input>
                </el-form-item>
                <!-- 价格 -->
                <el-form-item label="登记时间" prop="reg_time">
                    <el-input v-model="addForm.reg_time"></el-input>
                </el-form-item>
            </el-form>
            <!-- 内容底部区域 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="addCarreg">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    created() {
        this.getCarregList();
    },
    data() {
        return {
            // 查询信息实体
            queryInfo: {
                query: "", //  查询信息
                pageNum: 1, //  当前页
                pageSize: 5, //  每页最大数
            },
            CarregList: [], //  用户列表
            total: 0, //  总记录数
            addDialogVisible: false, // 对话框状态

            //  添加表单信息
            addForm: {
                driver_name:'',
                car_number:'',
                car_model:'',
                reg_time:'',
            },
            addFormRules: {
                driver_name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' },
                    { min: 1, max: 10, message: '请输入正确的姓名', trigger: 'blur' }
                ],
                car_number: [
                    { required: true, message: '请输入车牌号', trigger: 'blur' },
                    { min: 1, max: 10, message: '请输入正确的车牌号', trigger: 'blur' }
                ],
                car_model: [
                    { required: true, message: '请输入车型号', trigger: 'blur' },
                    { min: 1, max: 10, message: '请输入正确的车型号', trigger: 'blur' }
                ],
                reg_time: [
                    { required: true, message: '请输入登记时间', trigger: 'blur' },
                    { min: 1, max: 10, message: '请输入正确的登记时间', trigger: 'blur' }
                ],
            },
        };
    },
    methods: {
        // 获取所有员工
        async getCarregList() {
            const {data:res} = await this.$http.get("getcar",{params: this.queryInfo});
            this.CarregList = res.data;
            this.total = res.numbers;
        },
        // 监听pageSize改变的事件
        handleSizeChange(newSize) {
            this.queryInfo.pageSize = newSize;
            this.getCarregList(); //  数据发生改变重新申请数据
        },
        // 监听pageNum改变的事件
        handleCurrentChange(newPage) {
            this.queryInfo.pageNum = newPage;
            this.getCarregList();
        },
        // 监听添加用户
        addDialogClosed() {
            this.$refs.addFormRef.resetFields(); // 重置表单项
        },
        addCarreg() {
            this.$refs.addFormRef.validate(async valid=>{
                if (!valid) return;
                // 发起请求
                const {data:res} = await this.$http.post("addcarreg",this.addForm);
                if (res!="success") {
                    return this.$message.error("操作失败！！！");
                }
                this.$message.success("操作成功！！！");
                // 隐藏
                this.addDialogVisible = false;
                this.getCarregList(); 
            });
        },
    }
}
</script>

<style lang="less" scoped>
 // 面包屑样式
.el-breadcrumb {
    margin-bottom: 15px;
    font-size: 16px;
    font-family: PingFang SC;
}   
</style>