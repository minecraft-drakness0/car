<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class='el-icon-arrow-right'>
            <el-breadcrumb-item :to="{path:'/home'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>权限管理</el-breadcrumb-item>
            <el-breadcrumb-item>汽车修理信息列表</el-breadcrumb-item>
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
                    <el-button type="primary" @click="addDialogVisible = true">添加修理信息</el-button>
                </el-col>
            </el-row>
            <!-- 零件入库列表部分 border边框 stripe隔行颜色 -->
            <el-table :data="CarrepList" border stripe>
                <el-table-column type="index"></el-table-column>    <!-- 索引列 -->
                <el-table-column label="车牌号" prop="car_number"></el-table-column>
                <el-table-column label="员工编号" prop="employee_id"></el-table-column>
                <el-table-column label="修理单编号" prop="repairlist_id"></el-table-column> 
                <el-table-column label="修理项目" prop="repair_items"></el-table-column>
                <el-table-column label="修理用时(小时)" prop="repair_time"></el-table-column>
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
        <el-dialog title="添加入库数量" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
            <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px">
                <!-- 姓名 -->
                <el-form-item label="车牌号" prop="car_number">
                    <el-input v-model="addForm.car_number"></el-input>
                </el-form-item>
                <!-- 车牌号 -->
                <el-form-item label="员工编号" prop="employee_id">
                    <el-input v-model="addForm.employee_id"></el-input>
                </el-form-item>
                <!-- 车型号 -->
                <el-form-item label="修理单编号" prop="repairlist_id">
                    <el-input v-model="addForm.repairlist_id"></el-input>
                </el-form-item>
                <!-- 登记时间 -->
                <el-form-item label="修理项目" prop="repair_items">
                    <el-input v-model="addForm.repair_items"></el-input>
                </el-form-item>
                <!-- 登记时间 -->
                <el-form-item label="修理用时" prop="repair_time">
                    <el-input v-model="addForm.repair_time"></el-input>
                </el-form-item>
            </el-form>
            <!-- 内容底部区域 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="addCarrep">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    created() {
        this.getCarrepList();
    },
    data() {
        return {
            // 查询信息实体
            queryInfo: {
                query: "", //  查询信息
                pageNum: 1, //  当前页
                pageSize: 5, //  每页最大数
            },
            CarrepList: [], //  用户列表
            total: 0, //  总记录数
            addDialogVisible: false, // 对话框状态

            //  添加表单信息
            addForm: {
                car_number:'',
                employee_id:'',
                repairlist_id:'',
                repair_items:'',
                repair_time:'',
            },
            addFormRules: {
                car_number: [
                    { required: true, message: '请输入车牌号', trigger: 'blur' },
                    { min: 1, max: 10, message: '请输入正确的车牌号', trigger: 'blur' }
                ],
                employee_id: [
                    { required: true, message: '请输入员工编号', trigger: 'blur' },
                    { min: 1, max: 10, message: '请输入正确的员工编号', trigger: 'blur' }
                ],
                repairlist_id: [
                    { required: true, message: '请输入修理单编号', trigger: 'blur' },
                    { min: 1, max: 10, message: '请输入正确的修理单编号', trigger: 'blur' }
                ],
                repair_items: [
                    { required: true, message: '请输入修理项目', trigger: 'blur' },
                    { min: 1, max: 10, message: '请输入正确的修理项目', trigger: 'blur' }
                ],
                repair_time: [
                    { required: true, message: '请输入修理用时', trigger: 'blur' },
                    { min: 1, max: 10, message: '请输入正确的修理用时', trigger: 'blur' }
                ],
            },
        };
    },
    methods: {
        // 获取所有员工
        async getCarrepList() {
            const {data:res} = await this.$http.get("getcarrep",{params: this.queryInfo});
            this.CarrepList = res.data;
            this.total = res.numbers;
        },
        // 监听pageSize改变的事件
        handleSizeChange(newSize) {
            this.queryInfo.pageSize = newSize;
            this.getCarrepList(); //  数据发生改变重新申请数据
        },
        // 监听pageNum改变的事件
        handleCurrentChange(newPage) {
            this.queryInfo.pageNum = newPage;
            this.getCarrepList();
        },
        // 监听添加用户
        addDialogClosed() {
            this.$refs.addFormRef.resetFields(); // 重置表单项
        },
        addCarrep() {
            this.$refs.addFormRef.validate(async valid=>{
                if (!valid) return;
                // 发起请求
                const {data:res} = await this.$http.post("addcarrep",this.addForm);
                if (res!="success") {
                    return this.$message.error("操作失败！！！");
                }
                this.$message.success("操作成功！！！");
                // 隐藏
                this.addDialogVisible = false;
                this.getCarrepList(); 
            });
        },
    },
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