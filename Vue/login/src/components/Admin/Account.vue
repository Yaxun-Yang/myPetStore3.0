<template>
  <div>
    <el-card shadow="hover">
      <div class="head">
      <!-- 搜索 -->
      <el-row :gutter="20">
        <el-col :span="18">
          <el-input placeholder="Please enter ID" v-model="quertInfo.query" clearable @clear="getUserList"></el-input>
        </el-col>
        <el-col :span="6">
          <el-button type="primary" icon="el-icon-search" @click="getUserList"></el-button>
        </el-col>
      </el-row>
      <!-- 面包屑-->
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin' }">Main</el-breadcrumb-item>
        <el-breadcrumb-item>Account</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
      <el-divider><i class="el-icon-user-solid"></i></el-divider>
      <div class="add">
        <div class="title">
          User List
        </div>
        <!--      添加新用户-->
        <el-button type="primary" @click="addDialogVisible=true">Add New User</el-button>
      </div>
      <!-- 列表 -->
      <el-table :data="userList" border stripe>
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="User Id" prop="id"></el-table-column>
        <el-table-column label="First Name" prop="firstName"></el-table-column>
        <el-table-column label="Last Name" prop="lastName"></el-table-column>
        <el-table-column label="Email" prop="email"></el-table-column>
        <el-table-column label="Phone" prop="phone"></el-table-column>
        <el-table-column label="Address1" prop="addr1"></el-table-column>
        <el-table-column label="Address2" prop="addr2"></el-table-column>
        <el-table-column label="City" prop="city"></el-table-column>
        <el-table-column label="State" prop="state"></el-table-column>
        <el-table-column label="Zip" prop="zip"></el-table-column>
        <el-table-column label="Country" prop="country"></el-table-column>
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
        <el-form-item label="User Id" prop="id">
          <el-input v-model="addForm.id"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="addForm.password" type="password"></el-input>
        </el-form-item>
        <el-form-item label="Repeat" prop="checkPass">
          <el-input v-model="addForm.checkPass" type="password"></el-input>
        </el-form-item>
        <el-form-item label="First Name" prop="firstName">
          <el-input v-model="addForm.firstName"></el-input>
        </el-form-item>
        <el-form-item label="Last Name" prop="lastName">
          <el-input v-model="addForm.lastName"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
        <el-form-item label="Phone" prop="phone">
          <el-input v-model="addForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="Address1" prop="addr1">
          <el-input v-model="addForm.addr1"></el-input>
        </el-form-item>
        <el-form-item label="Address2" prop="addr2">
          <el-input v-model="addForm.addr2"></el-input>
        </el-form-item>
        <el-form-item label="City" prop="city">
          <el-input v-model="addForm.city"></el-input>
        </el-form-item>
        <el-form-item label="State" prop="state">
          <el-input v-model="addForm.state"></el-input>
        </el-form-item>
        <el-form-item label="Zip" prop="zip">
          <el-input v-model="addForm.zip"></el-input>
        </el-form-item>
        <el-form-item label="Country" prop="country">
          <el-input v-model="addForm.country"></el-input>
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
        <el-form-item label="User Id">
          <el-input v-model="editForm.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="editForm.password" type="password"></el-input>
        </el-form-item>
        <el-form-item label="Repeat" prop="checkPass">
          <el-input v-model="editForm.checkPass" type="password"></el-input>
        </el-form-item>
        <el-form-item label="First Name" prop="firstName">
          <el-input v-model="editForm.firstName"></el-input>
        </el-form-item>
        <el-form-item label="Last Name" prop="lastName">
          <el-input v-model="editForm.lastName"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
        <el-form-item label="Phone" prop="phone">
          <el-input v-model="editForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="editress1" prop="addr1">
          <el-input v-model="editForm.addr1"></el-input>
        </el-form-item>
        <el-form-item label="Address2" prop="addr2">
          <el-input v-model="editForm.addr2"></el-input>
        </el-form-item>
        <el-form-item label="City" prop="city">
          <el-input v-model="editForm.city"></el-input>
        </el-form-item>
        <el-form-item label="State" prop="state">
          <el-input v-model="editForm.state"></el-input>
        </el-form-item>
        <el-form-item label="Zip" prop="zip">
          <el-input v-model="editForm.zip"></el-input>
        </el-form-item>
        <el-form-item label="Country" prop="country">
          <el-input v-model="editForm.country"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="editUser">Confirm</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      // 验证

      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Please enter your password'));
        } else {
          if (this.addForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Repeat your password'));
        } else if (value !== this.addForm.password) {
          callback(new Error('Not the same. Try again!'));
        } else {
          callback();
        }
      };
      var checkEmail = (rule, value, cb) => {
        const regEmail = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        if (regEmail.test(value)) {
          return cb();
        }
        cb(new Error("Please enter legal Email"));
      };
      var checkPhone = (rule, value, cb) => {
        const regPhone = /^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57]|17[678])[0-9]{8}$/;
        if (regPhone.test(value)) {
          return cb();
        }
        cb(new Error("Please enter legal phone number"));
      };
      return {
        // 获取用户列表参数
        quertInfo: {
          query: '',
          pagenum: 1, // 当前页数
          pagesize: 2 // 每页显示多少条数据
        },
        userList: [{
          id: '',
          firstName: '',
          lastName: '',
          email: '',
          phone: '',
          addr1: '',
          addr2: '',
          city: '',
          state: '',
          zip: '',
          country: ''
        }],
        //  一共多少页
        total: 0,
        // 添加新用户对话框的显示隐藏
        addDialogVisible: false,
        // 新用户数据表单
        addForm: {
          id: '',
          password: '',
          checkPass: '',
          firstName: '',
          lastName: '',
          email: '',
          phone: '',
          addr1: '',
          addr2: '',
          city: '',
          state: '',
          zip: '',
          country: ''
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
          password: [
            {
              required: true, message: 'Please enter password', trigger: 'blur'
            },
            {
              min: 6,
              max: 15,
              message: 'Between 6-15 characters',
              trigger: 'blur'
            }
          ],
          checkPass: [
            {required: true, validator: validatePass2, trigger: 'blur'}
          ],
          firstName: [
            {required: true, message: 'Please enter your first name', trigger: 'blur'}
          ],
          lastName: [
            {required: true, message: 'Please enter your last name', trigger: 'blur'}
          ],
          email: [
            {required: true, message: 'Please enter your email', trigger: 'blur'},
            {validator: checkEmail, trigger: 'blur'}
          ],
          phone: [
            {required: true, message: 'Please enter your phone number', trigger: 'blur'},
            {validator: checkPhone, trigger: 'blur'}
          ],
          addr1: [
            {required: true, message: 'Please enter your address', trigger: 'blur'}
          ],
          city: [
            {required: true, message: 'Please enter your city', trigger: 'blur'}
          ]
        },
        // 修改用户对话框的显示与隐藏
        editDialogVisible: false,
        // 查询到的用户信息对象
        editForm: {},
      }
    },
    created() {
      this.getUserList()
    },
    methods: {
      async getUserList() {
        const {data: res} = await this.$axios.get('account', {params: this.quertInfo});
        if (res.meta.status !== 200) {
          return this.$message.error('获取信account息失败')
        }
        this.userList = res.data.account;
        this.total = res.data.total;
        console.log(res)
      },
      // 监听
      handSizeChange(newSize) {
        // console.log(newSize)
        this.quertInfo.pagesize = newSize;
        this.getUserList()
      },
      handleCurrentChange(newPage) {
        // console.log(newPage)
        this.quertInfo.pagenum = newPage;
        this.getUserList()
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
          const {data: res} = await this.$axios.post('account', this.addForm);
          if (res.meta.status !== 201) {
            this.$message.error('Add user failed!');
          }
          this.$message.success('Add user successfully');
          // 隐藏对话框
          this.addDialogVisible = false;
          // 刷新列表
          this.getUserList();
        })
      },
      // 展示编辑用户的对话框
      async showEditDialog(id) {
        const {data: res} = await this.$axios.get('account/' + id);
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
      editUser() {
        this.$refs.editFormRef.validate(async valid => {
          if (!valid) return;
          // 可以发起edit用户的网络请求
          const {data: res} = await this.$axios.put('account/', this.editForm.id, {
            password: this.editForm.password,
            firstName: this.editForm.firstName,
            lastName: this.editForm.lastName,
            phone: this.editForm.phone,
            addr1: this.editForm.addr1,
            addr2: this.editForm.addr2,
            city: this.editForm.city,
            state: this.editForm.state,
            zip: this.editForm.zip,
            country: this.editForm.country,
          });
          if (res.meta.status !== 201) {
            this.$message.error('Edit Failed!');
          }
          this.$message.success('Edit successfully');
          // 隐藏对话框
          this.editDialogVisible = false;
          // 刷新列表
          this.getUserList();
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
        const {data: res} = await this.$axios.delete('account/' + id);
        if (res.meta.status !== 200) {
          return this.$message.error('Delete failed!')
        }
        this.$message.success('Delete successfully!');
        // 刷新列表
        this.getUserList();
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
