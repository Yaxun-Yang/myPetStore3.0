<template>
  <div>
    <el-card shadow="hover">
      <div class="head">
        <!-- 搜索 -->
        <el-row :gutter="20">
          <el-col :span="18">
            <el-input placeholder="Please enter ID" v-model="quertInfo.query" clearable @clear="getOrderList"></el-input>
          </el-col>
          <el-col :span="6">
            <el-button type="primary" icon="el-icon-search" @click="getOrderList"></el-button>
          </el-col>
        </el-row>
        <!-- 面包屑-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/admin' }">Main</el-breadcrumb-item>
          <el-breadcrumb-item>Order</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <el-divider><i class="el-icon-s-order"></i></el-divider>

      <div class="add">
        <div class="title">
          Order List
        </div>
        <!--      添加新order-->
        <el-button type="primary" @click="addDialogVisible=true">Add New Order</el-button>
      </div>
      <!-- 列表 -->
      <el-table :data="orderList" border stripe>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="Order Id" prop="id"></el-table-column>
        <el-table-column label="User Id" prop="userid"></el-table-column>
        <el-table-column label="Order Date" prop="orderDate"></el-table-column>
        <el-table-column label="Ship  Addr" prop="shipaddr"></el-table-column>
        <el-table-column label="Ship to" prop="shiptoname"></el-table-column>
        <el-table-column label="Bill Address" prop="billaddr"></el-table-column>
        <el-table-column label="Bill to" prop="billtoname"></el-table-column>
        <el-table-column label="Courier" prop="courier"></el-table-column>快递员
        <el-table-column label="Total Price" prop="totalprice"></el-table-column>
        <el-table-column label="Credit Card" prop="creditcard"></el-table-column>
        <el-table-column label="ExpressDate" prop="exprdate"></el-table-column>
        <el-table-column label="Setting" width="130px">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeById(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination
        @size-change="handSizeChange"
        @current-change="handleCurrentChange"
        :current-page="quertInfo.pagenum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="quertInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </el-card>
    <!--  添加对话框-->
    <el-dialog title="Add" :visible.sync="addDialogVisible" @close="addDialogClosed">
      <el-form :model="addForm" status-icon :rules="formRules" ref="addFormRef" label-width="100px">
        <el-form-item label="Order Id" prop="id">
          <el-input v-model="addForm.id"></el-input>
        </el-form-item>
        <el-form-item label="User Id" prop="userid">
          <el-input v-model="addForm.userid"></el-input>
        </el-form-item>
        <el-form-item label="Order Date" prop="orderDate">
          <el-input v-model="addForm.orderDate"></el-input>
        </el-form-item>
        <el-form-item label="Ship  Addr" prop="shipaddr">
          <el-input v-model="addForm.shipaddr"></el-input>
        </el-form-item>
        <el-form-item label="Ship to" prop="shiptoname">
          <el-input v-model="addForm.shiptoname"></el-input>
        </el-form-item>
        <el-form-item label="Bill Address" prop="billaddr">
          <el-input v-model="addForm.billaddr"></el-input>
        </el-form-item>
        <el-form-item label="Bill to" prop="billtoname">
          <el-input v-model="addForm.billtoname"></el-input>
        </el-form-item>
        <el-form-item label="Courier" prop="courier">
          <el-input v-model="addForm.courier"></el-input>
        </el-form-item>
        <el-form-item label="Total Price" prop="totalprice">
          <el-input v-model="addForm.totalprice"></el-input>
        </el-form-item>
        <el-form-item label="Credit Card" prop="creditcard">
          <el-input v-model="addForm.creditcard"></el-input>
        </el-form-item>
        <el-form-item label="Express Date" prop="exprdate">
          <el-input v-model="addForm.exprdate"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="addUser">Confirm</el-button>
      </div>
    </el-dialog>
    <!--  修改对话框-->
    <el-dialog title="Edit" :visible.sync="editDialogVisible" @close="editDialogClosed">
      <el-form :model="editForm" status-icon :rules="formRules" ref="editFormRef" label-width="100px">
        <el-form-item label="Order Id">
          <el-input v-model="editForm.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="User Id" prop="userid">
          <el-input v-model="editForm.userid"></el-input>
        </el-form-item>
        <el-form-item label="Order Date" prop="orderDate">
          <el-input v-model="editForm.orderDate"></el-input>
        </el-form-item>
        <el-form-item label="Ship  Addr" prop="shipaddr">
          <el-input v-model="editForm.shipaddr"></el-input>
        </el-form-item>
        <el-form-item label="Ship to" prop="shiptoname">
          <el-input v-model="editForm.shiptoname"></el-input>
        </el-form-item>
        <el-form-item label="Bill Address" prop="billaddr">
          <el-input v-model="editForm.billaddr"></el-input>
        </el-form-item>
        <el-form-item label="Bill to" prop="billtoname">
          <el-input v-model="editForm.billtoname"></el-input>
        </el-form-item>
        <el-form-item label="Courier" prop="courier">
          <el-input v-model="editForm.courier"></el-input>
        </el-form-item>
        <el-form-item label="Total Price" prop="totalprice">
          <el-input v-model="editForm.totalprice"></el-input>
        </el-form-item>
        <el-form-item label="Credit Card" prop="creditcard">
          <el-input v-model="editForm.creditcard"></el-input>
        </el-form-item>
        <el-form-item label="Express Date" prop="exprdate">
          <el-input v-model="editForm.exprdate"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="editOrder">Confirm</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        // 获取用户列表参数
        quertInfo: {
          query: '',
          pagenum: 1, // 当前页数
          pagesize: 2 // 每页显示多少条数据
        },
        orderList: [{
          id: '1',   //order的id
          name: '1',  //用户名
          orderDate: '',//啥时候下单的
          totalprice: '',//一共多少钱
          paid: '',//付钱了吗
          sendForm: '',//供应商地址
          sendTo: '',//寄给哪里
          sendToWho: '',//寄给谁
          sended:'',//发货了吗
        }],
        //  一共多少页
        total: 0,
        // 添加新用户对话框的显示隐藏
        addDialogVisible: false,
        // 新用户数据表单
        addForm: {
          id: '',
          userid: '',
          orderDate: '',
          shipaddr: '',
          shiptoname: '',
          billaddr: '',
          billtoname: '',
          courier: '',
          totalprice: '',
          creditcard: '',
          exprdate: ''
        },
        //添加的验证规则
        formRules: {
          id: [
            {
              required: true, message: 'Please enter id',
              trigger: 'blur'
            },
            {
              min: 3,
              max: 10,
              message: 'Between 3-10 characters',
              trigger: 'blur'
            }
          ],
          userid: [
            {required: true, message: 'Please enter user id', trigger: 'blur'}
          ],
          orderDate: [
            {required: true, message: 'Please enter order date', trigger: 'blur'}
          ],
          shipaddr: [
            {required: true, message: 'Please enter ship address', trigger: 'blur'},
          ],
          shiptoname: [
            {required: true, message: 'Please enter ship to name', trigger: 'blur'}
          ],
          billaddr: [
            {required: true, message: 'Please enter bill address', trigger: 'blur'}
          ],
          billtoname: [
            {required: true, message: 'Please enter bill to name', trigger: 'blur'}
          ],
          totalprice: [
            {required: true, message: 'Please enter total price', trigger: 'blur'}
          ]
        },
        // 修改用户对话框的显示与隐藏
        editDialogVisible: false,
        //查询到的用户信息
        editForm: {}
      }
    },
    created() {
      this.getOrderList()
    },
    methods: {
      async getOrderList() {
        const {data: res} = await this.$axios.get('order', {params: this.quertInfo});
        if (res.meta.status !== 200) {
          return this.$message.error('获取order信息失败')
        }
        this.orderList = res.data.order;
        this.total = res.data.total;
        console.log(res)
      },
      // 监听
      handSizeChange(newSize) {
        // console.log(newSize)
        this.quertInfo.pagesize = newSize;
        this.getOrderList()
      },
      handleCurrentChange(newPage) {
        // console.log(newPage)
        this.quertInfo.pagenum = newPage;
        this.getOrderList()
      },
      // 监听添加用户对话框的关闭事件
      addDialogClosed() {
        this.$refs.addFormRef.resetFields();
      },
      // 添加
      addUser() {
        this.$refs.addFormRef.validate(async valid => {
          if (!valid) return;
          // 可以发起add用户的网络请求
          const {data: res} = await this.$axios.post('order', this.addForm);
          if (res.meta.status !== 201) {
            this.$message.error('Add Order failed!');
          }
          this.$message.success('Add Order successfully');
          // 隐藏对话框
          this.addDialogVisible = false;
          // 刷新列表
          this.getOrderList();
        })
      },
      //编辑的对话框
      async showEditDialog(id) {
        const {data: res} = await this.$axios.get('order/' + id);
        if (res.meta.status !== 200) {
          return this.$message.error('Query Failed!')
        }
        this.editForm = res.data;
        this.editDialogVisible = true;
      },
      // 监听编辑用户对话框的关闭事件
      editDialogClosed() {
        this.$refs.editFormRef.resetFields();
      },
      // 编辑
      editOrder() {
        this.$refs.editFormRef.validate(async valid => {
          if (!valid) return;
          // 可以发起edit用户的网络请求
          const {data: res} = await this.$axios.put('order/', this.editForm.id, {
            password: this.editForm.password,
            userid: this.editForm.userid,
            orderDate: this.editForm.orderDate,
            shiptoname: this.editForm.shiptoname,
            billaddr: this.editForm.billaddr,
            billtoname: this.editForm.billtoname,
            courier: this.editForm.courier,
            totalprice: this.editForm.totalprice,
            creditcard: this.editForm.creditcard,
            exprdate: this.editForm.exprdate,
          });
          if (res.meta.status !== 201) {
            this.$message.error('Edit Failed!');
          }
          this.$message.success('Edit successfully');
          // 隐藏对话框
          this.editDialogVisible = false;
          // 刷新列表
          this.getOrderList();
        })
      },
      //删除
      async removeById(id) {
        //弹窗
        const confirmResult = await this.$confirm('Are you sure you want to delete it permanently?', 'message', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: 'Delete successfully!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Deletion have been cancelled.'
          });
        });
        const {data: res} = await this.$axios.delete('order/' + id);
        if (res.meta.status !== 200) {
          return this.$message.error('Delete failed!')
        }
        this.$message.success('Delete successfully!');
        // 刷新列表
        this.getOrderList();
      }
    }
  }
</script>

<style scoped>

  .add {
    margin-top: 0px;
    background-color: #fff;
    width: 100%;
    height: 45px;
    display: flex;
    justify-content: space-between;
    padding-left: 10px;
    padding-right: 20px;
    align-items: center;
  }

  .el-table {
    margin-top: 15px;
    font-size: 12px;
  }


  .el-pagination {
    display: flex;
    justify-content: space-between;
    padding-top: 10px;
  }


</style>
