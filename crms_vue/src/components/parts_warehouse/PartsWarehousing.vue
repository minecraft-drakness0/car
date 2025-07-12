<template>
    <div>
        <!-- 面包屑导航 -->
        <el-breadcrumb separator-class='el-icon-arrow-right'>
            <el-breadcrumb-item :to="{path:'/home'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item>权限管理</el-breadcrumb-item>
            <el-breadcrumb-item>零件入库列表</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-row :gutter="25">
                <!-- 搜索栏区域 -->
                <el-col :span="10">
                    <el-input placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getPartsWList">
                        <el-button slot="append" icon="el-icon-search" @click="getPartsWList"></el-button>
                    </el-input>
                </el-col>
                <!-- 添加入库数量按钮 -->
                <el-col :span="4">
                    <el-button type="primary" @click="addDialogVisible = true">添加入库信息</el-button>
                </el-col>
            </el-row>
            <!-- 零件入库列表部分 border边框 stripe隔行颜色 -->
            <el-table :data="PartsWList" border stripe>
                <el-table-column type="index"></el-table-column>    <!-- 索引列 -->
                <el-table-column label="零件型号" prop="part_number"></el-table-column>
                <el-table-column label="入库数量" prop="storage_quantity"></el-table-column>
                <el-table-column label="库存数量" prop="inventory_quantity"></el-table-column> 
                <el-table-column label="价格" prop="price"></el-table-column>
                <!-- 自定义插槽 -->
                <el-table-column label="操作">
                    <el-templete slot-scope="scope">
                        <!-- 修改 -->
                        <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
                        <!-- 删除 -->
                        <el-button type="danger" icon="el-icon-delete" size="mini" @click="deletePartsW(scope.row.id)"></el-button>
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
        <el-dialog title="添加入库信息" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
            <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
                <!-- 零件型号 -->
                <el-form-item label="型号" prop="part_number">
                    <el-input v-model="addForm.part_number"></el-input>
                </el-form-item>
                <!-- 入库数量 -->
                <el-form-item label="入库" prop="storage_quantity">
                    <el-input v-model="addForm.storage_quantity"></el-input>
                </el-form-item>
                <!-- 库存数量 -->
                <el-form-item label="库存" prop="inventory_quantity">
                    <el-input v-model="addForm.inventory_quantity"></el-input>
                </el-form-item>
                <!-- 价格 -->
                <el-form-item label="价格" prop="price">
                    <el-input v-model="addForm.price"></el-input>
                </el-form-item>
            </el-form>
            <!-- 内容底部区域 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="addPartsW">确定</el-button>
            </span>
        </el-dialog>
        <!-- 修改用户对话框 -->
        <el-dialog title="修改入库信息" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
            <el-form :model="editForm" :rules="weditFormRules" ref="editFormRef" label-width="70px">
                <!-- 零件型号 -->
                <el-form-item label="零件" prop="part_number">
                    <el-input v-model="editForm.part_number"></el-input>
                </el-form-item>
                <!-- 入库数量 -->
                <el-form-item label="入库" prop="storage_quantity" :rules="[
                    { required: false, message: '入库数量不能为空'},
                    { type: 'number', message: '入库数量必须为数字值'}
                ]">
                    <el-input v-model.number="editForm.storage_quantity"></el-input>
                </el-form-item>
                <!-- 库存数量 -->
                <el-form-item label="库存" prop="inventory_quantity" :rules="[
                    { required: true, message: '库存数量不能为空'},
                    { type: 'number', message: '库存数量必须为数字值'}
                ]">
                    <el-input v-model.number="editForm.inventory_quantity"></el-input>
                </el-form-item>
                <!-- 价格 -->
                <el-form-item label="价格" prop="price" :rules="[
                    { required: true, message: '价格不能为空'},
                    { type: 'number', message: '价格必须为数字值'}
                ]">
                    <el-input v-model.number="editForm.price"></el-input>
                </el-form-item>
            </el-form>
            <!-- 内容底部区域 -->
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="editPartWInfo">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    created() {
        this.getPartsWList();
    },
    data() {
        return {
            // 查询信息实体
            queryInfo: {
                query: "", //  查询信息
                pageNum: 1, //  当前页
                pageSize: 5, //  每页最大数
            },
            PartsWList: [], //  用户列表
            total: 0, //  总记录数
            addDialogVisible: false, // 对话框状态

            //  添加表单信息
            addForm: {
                part_number:'',
                storage_quantity:'',
                inventory_quantity:'',
                price:'',
            },
            //  修改用户信息
            editForm: {},
            //  显示/隐藏修改用户栏
            editDialogVisible: false,
            addFormRules: {
                part_number: [
                    { required: true, message: '请输入零件型号', trigger: 'blur' },
                    { min: 1, max: 10, message: '请输入正确的零件型号', trigger: 'blur' }
                ],
                storage_quantity: [
                    { required: false, message: '请输入入库数量', trigger: 'blur' },
                    { min: 1, max: 10, message: '入库数量必须为数字值', trigger: 'blur' }
                ],
                inventory_quantity: [
                    { required: true, message: '请输入库存数量', trigger: 'blur' },
                    { min: 1, max: 10, message: '库存数量必须为数字值', trigger: 'blur' }
                ],
                price: [
                    { required: true, message: '请输入价格', trigger: 'blur' },
                    { min: 1, max: 10, message: '价格必须为数字值', trigger: 'blur' }
                ],
            },
            //  修改表单验证
            editFormRules: {
                part_number: [
                    { required: true, message: '请输入零件型号', trigger: 'blur' },
                    { min: 1, max: 10, message: '请输入正确的零件型号', trigger: 'blur' }
                ],
                storage_quantity: '',
                inventory_quantity: '',
                price: '',
            },
        };
    },
    methods: {
        // 获取所有员工
        async getPartsWList() {
            const {data:res} = await this.$http.get("allpartsw",{params: this.queryInfo});
            this.PartsWList = res.data;
            this.total = res.numbers;
        },
        // 监听pageSize改变的事件
        handleSizeChange(newSize) {
            this.queryInfo.pageSize = newSize;
            this.getPartsWList(); //  数据发生改变重新申请数据
        },
        // 监听pageNum改变的事件
        handleCurrentChange(newPage) {
            this.queryInfo.pageNum = newPage;
            this.getPartsWList();
        },
        // 监听添加用户
        addDialogClosed() {
            this.$refs.addFormRef.resetFields(); // 重置表单项
        },
        addPartsW() {
            this.$refs.addFormRef.validate(async valid=>{
                if (!valid) return;
                // 发起请求
                const {data:res} = await this.$http.post("addpw",this.addForm);
                if (res!="success") {
                    return this.$message.error("操作失败！！！");
                }
                this.$message.success("操作成功！！！");
                // 隐藏
                this.addDialogVisible = false;
                this.getPartsWList(); 
            });
        },
        // 删除按钮
        async deletePartsW(id) {
            const confirmResult = await this.$confirm(' 此操作永久删除用户，是否继续？','提示',{
                confirmButtonText:'确定',
                cancelButtonText:'取消',
                type:'warning'
            }).catch(err => err)
            if (confirmResult != 'confirm') {
                return this.$message.info("已取消删除");
            }
            const {data:res} = await this.$http.delete("deletepartsw?id="+id);
            if (res != "success") {
                return this.$message.error("删除失败！！！");
            }
            this.$message.success("删除成功！！！");
            this.getPartsWList();
        },
        //  展示修改框
        async showEditDialog(id) {
            const {data:res} = await this.$http.get("getupdatepartsw?id="+id);
            this.editForm = res;
            this.editDialogVisible = true;
        },
        //  关闭窗口
        editDialogClosed() {
            this.$refs.editFormRef.resetFields();
        },
        //  确认修改
        editPartWInfo() {
            this.$refs.editFormRef.validate(async valid => {
                if (!valid) return;
                const {data:res} = await this.$http.put("editpartsw",this.editForm);
                if (res!="success") {
                    return this.$message.error("修改失败！！！");
                }
                this.$message.success("修改成功！！！");
                this.editDialogVisible = false;
                this.getPartsWList(); 
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