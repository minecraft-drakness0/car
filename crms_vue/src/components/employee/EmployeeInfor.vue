<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class='el-icon-arrow-right'>
            <el-breadcrumb-item :to="{path:'/home'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>权限管理</el-breadcrumb-item>
            <el-breadcrumb-item>员工信息列表</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- 卡片视图区 -->
        <el-card>
            <el-row :gutter="25">
                <!-- 搜索栏区域 -->
                <el-col :span="10">
                    <el-input placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getEmployeeList">
                        <el-button slot="append" icon="el-icon-search" @click="getEmployeeList"></el-button>
                    </el-input>
                </el-col>
                <!-- 添加员工按钮 -->
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible = true">添加员工</el-button>
                </el-col>
            </el-row>
            <!-- 员工信息列表部分 border边框 stripe隔行颜色 -->
            <el-table :data="employeeList" border stripe>
                <el-table-column type="index"></el-table-column>    <!-- 索引列 -->
                <el-table-column label="员工编号" prop="employee_id"></el-table-column>
                <el-table-column label="员工姓名" prop="employee_name"></el-table-column>
                <el-table-column label="工资" prop="employee_salary"></el-table-column> 
                <!-- 自定义插槽 -->
                <el-table-column label="操作">
                    <el-templete slot-scope="scope">
                        <!-- 修改 -->
                        <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
                        <!-- 删除 -->
                        <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteEmployee(scope.row.id)"></el-button>
                        <!-- 权限 -->
                        <el-tooltip effect="dark" content="分配权限" placement="top-start" 
                        :enterable="false">
                            <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
                        </el-tooltip>
                    </el-templete>
                </el-table-column>
            </el-table>
            <!-- 分页 size-change 每页最大变化 current-change 当前最大变化 layout 功能组件 -->
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
        <el-dialog title="添加员工" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
            <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
                <!-- 员工编号 -->
                <el-form-item label="编号" prop="employee_id">
                    <el-input v-model="addForm.employee_id"></el-input>
                </el-form-item>
                <!-- 员工姓名 -->
                <el-form-item label="姓名" prop="employee_name">
                    <el-input v-model="addForm.employee_name"></el-input>
                </el-form-item>
                <!-- 工资 -->
                <el-form-item label="每小时工资" prop="employee_salary">
                    <el-input v-model="addForm.employee_salary"></el-input>
                </el-form-item>
            </el-form>
            <!-- 内容底部区域 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="addEmployee">确定</el-button>
            </span>
        </el-dialog>
        <!-- 修改用户对话框 -->
        <el-dialog title="修改员工信息" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
            <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
                <!-- 员工编号 -->
                <el-form-item label="编号" prop="employee_id">
                    <el-input v-model="editForm.employee_id" disabled></el-input>
                </el-form-item>
                <!-- 员工姓名 -->
                <el-form-item label="姓名" prop="employee_name">
                    <el-input v-model="editForm.employee_name"></el-input>
                </el-form-item>
                <!-- 工资 -->
                <el-form-item label="每小时工资" prop="employee_salary" :rules="[
                    { required: true, message: '工资不能为空'},
                    { type: 'number', message: '工资必须为数字值'}
                ]">
                    <el-input v-model.number="editForm.employee_salary"></el-input>
                </el-form-item>
            </el-form>
            <!-- 内容底部区域 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="editUserInfo">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    created() {
        this.getEmployeeList();
    },
    data() {
        return {
            // 查询信息实体
            queryInfo: {
                query: "", //  查询信息
                pageNum: 1, //  当前页
                pageSize: 5, //  每页最大数
            },
            employeeList: [], //  用户列表
            total: 0, //  总记录数
            addDialogVisible: false, // 对话框状态

            //  添加表单信息
            addForm: {
                employee_id:'',
                employee_name:'',
                employee_salary:'',
            },
            //  修改用户信息
            editForm: {},
            //  显示/隐藏修改用户栏
            editDialogVisible: false,

            addFormRules: {
                employee_id: [
                    { required: true, message: '请输入编号', trigger: 'blur' },
                    { min: 1, max: 10, message: '请输入正确的员工编号', trigger: 'blur' }
                ],
                employee_name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' },
                    { min: 1, max: 8, message: '请输入正确的姓名', trigger: 'blur' }
                ],
                employee_salary: [
                    { required: true, message: '请输入工资', trigger: 'blur' },
                    { min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur' }
                ],
            },
            //  修改表单验证
            editFormRules: {
                employee_name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' },
                    { min: 1, max: 8, message: '请输入正确的姓名', trigger: 'blur' }
                ],
                employee_salary: '',
            },
        };
    },
    methods: {
        // 获取所有员工
        async getEmployeeList() {
            const {data:res} = await this.$http.get("allemployee",{params: this.queryInfo});
            this.employeeList = res.data;
            this.total = res.numbers;
        },
        // 监听pageSize改变的事件
        handleSizeChange(newSize) {
            this.queryInfo.pageSize = newSize;
            this.getEmployeeList(); //  数据发生改变重新申请数据
        },
        // 监听pageNum改变的事件
        handleCurrentChange(newPage) {
            this.queryInfo.pageNum = newPage;
            this.getEmployeeList();
        },
        // 监听添加用户
        addDialogClosed() {
            this.$refs.addFormRef.resetFields(); // 重置表单项
        },
        addEmployee() {
            this.$refs.addFormRef.validate(async valid=>{
                if (!valid) return;
                // 发起请求
                const {data:res} = await this.$http.post("addemployee",this.addForm);
                if (res!="success") {
                    return this.$message.error("操作失败！！！");
                }
                this.$message.success("操作成功！！！");
                // 隐藏
                this.addDialogVisible = false;
                this.getEmployeeList(); 
            });
        },
        // 删除按钮
        async deleteEmployee(id) {
            const confirmResult = await this.$confirm(' 此操作永久删除用户，是否继续？','提示',{
                confirmButtonText:'确定',
                cancelButtonText:'取消',
                type:'warning'
            }).catch(err => err)
            if (confirmResult != 'confirm') {
                return this.$message.info("已取消删除");
            }
            const {data:res} = await this.$http.delete("deleteemployee?id="+id);
            if (res != "success") {
                return this.$message.error("删除失败！！！");
            }
            this.$message.success("删除成功！！！");
            this.getEmployeeList();
        },
        //  展示修改框
        async showEditDialog(id) {
            const {data:res} = await this.$http.get("getupdateemployee?id="+id);
            this.editForm = res;
            this.editDialogVisible = true;
        },
        //  关闭窗口
        editDialogClosed() {
            this.$refs.editFormRef.resetFields();
        },
        //  确认修改
        editUserInfo() {
            this.$refs.editFormRef.validate(async valid => {
                if (!valid) return;
                const {data:res} = await this.$http.put("editemployee",this.editForm);
                if (res!="success") {
                    return this.$message.error("修改失败！！！");
                }
                this.$message.success("修改成功！！！");
                this.editDialogVisible = false;
                this.getUserList(); 
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