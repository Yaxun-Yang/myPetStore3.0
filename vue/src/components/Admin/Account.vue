<template>
  <div>
    <el-card shadow="hover">
      <div class="head">
      <!-- 搜索 -->
      <el-row :gutter="20">
        <el-col :span="18">
          <el-input placeholder="Please enter Username" v-model="input" clearable @clear="userShow = false"></el-input>
        </el-col>
        <el-col :span="6">
          <el-button type="primary" icon="el-icon-search" @click="getUser(input)"></el-button>
        </el-col>
      </el-row>
      <!-- 面包屑-->
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">Admin</el-breadcrumb-item>
        <el-breadcrumb-item>Account</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

      <el-card v-if="userShow" style="font-size: 15px;text-align: center">
        <el-row>
          <el-col :span="6">
            头像
          </el-col>
          <el-col :span="4">
            username
          </el-col>
          <el-col :span="4">
            email
          </el-col>
          <el-col :span="4">
            phone
          </el-col>
          <el-col :span="4">
            address
          </el-col>
        </el-row>
        <br>
        <el-row>
          <el-col :span="6">
            <el-image style="width: 50px; height: 50px;" :src="user.url"></el-image>
          </el-col>
          <el-col :span="4">
            {{user.username}}
          </el-col>
          <el-col :span="4">
            {{user.email}}
          </el-col>
          <el-col :span="4">
            {{user.phone}}
          </el-col>
          <el-col :span="4">
            {{user.address}}
          </el-col>
          <el-col :span="2">
            <el-button @click="removeByName(user.username)">Delete</el-button>
          </el-col>
        </el-row>
      </el-card>
      <el-divider><i class="el-icon-user-solid"></i></el-divider>
      <div class="add">
        <div class="title">
          User List
        </div>
        <!--      添加新用户-->
        <el-button type="primary" @click="addDialog = true">Add New User</el-button>
      </div>
      <!-- 列表 -->
      <el-table :data="userList" border stripe>
        <el-table-column type="index" label="#" prop="file" width="130px">
          <el-image :src="file" style="width: 100px;height: 100px"></el-image></el-table-column>
        <el-table-column label="User Name" prop="username"></el-table-column>
        <el-table-column label="Email" prop="email"></el-table-column>
        <el-table-column label="Phone" prop="phone"></el-table-column>
        <el-table-column label="Address" prop="address"></el-table-column>
<!--        <el-table-column label="Filenaem" prop="filename"></el-table-column>-->
        <el-table-column label="Setting" width="130px">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="getUserEdit(scope.row.username)"></el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeByName(scope.row.username)"></el-button>
          </template>
        </el-table-column>
      </el-table>


    </el-card>
    <!--  添加对话框-->
    <el-dialog title="Add" :visible.sync="addDialog" @close="addDialog = false">
      <el-form :model="addForm" status-icon :rules="formRules" ref="addForm" label-width="100px">
        <el-form-item label="URL" prop="url" label-width="120">
          <el-input v-model="addForm.url" ></el-input>
        </el-form-item>
        <el-form-item label="User Name" prop="username">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input v-model="addForm.password" type="password"></el-input>
        </el-form-item>
        <el-form-item label="CheckPass" prop="checkPass">
          <el-input v-model="addForm.checkPass" type="password"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
        <el-form-item label="Phone" prop="phone">
          <el-input v-model="addForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="Address" prop="address">
          <el-input v-model="addForm.address"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialog = false">Cancel</el-button>
        <el-button type="primary" @click="addUser" :loading="loading1">Confirm</el-button>
      </div>
    </el-dialog>
    <!--  修改对话框-->
      <el-dialog title="Edit your information" :visible.sync="editDialog">
      <el-form :model="userForm" ref="userForm" :rules="formRules">
          <el-form-item label="Username" label-width="120" prop="username">
            <el-input v-model="userForm.username" disabled></el-input>
          </el-form-item>
          <el-form-item label="URL" prop="url" label-width="120">
            <el-input v-model="userForm.url" ></el-input>
          </el-form-item>
          <el-form-item label="Password"  prop="password" label-width="120">
            <el-input type="password" v-model="userForm.password" ></el-input>
          </el-form-item>
          <el-form-item label="checkPass"  prop="checkPass"  label-width="120">
            <el-input type="password" v-model="userForm.checkPass" ></el-input>
          </el-form-item>
          <el-form-item label="Email"  prop="email" label-width="120">
            <el-input v-model="userForm.email" ></el-input>
          </el-form-item>
          <el-form-item label="Address" prop="address"  label-width="120">
            <el-input v-model="userForm.address" ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="editDialog = false">Cancel</el-button>
          <el-button type="primary" @click="edit" :loading="loading">Yes</el-button>
        </div>

    </el-dialog>
  </div>
</template>

<script>
  export default {
    inject: ['reload'],
    data() {
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
        input: '',
        userShow: false,
        loading1: false,
        loading: false,
        userList: [],
        user:[],
        userForm:[],
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
          email: [
            {required: true, message: 'Please enter your email', trigger: 'blur'},
            {validator: checkEmail, trigger: 'blur'}
          ],
          phone: [
            {required: true, message: 'Please enter your phone number', trigger: 'blur'},
            {validator: checkPhone, trigger: 'blur'}
          ],
          address: [
            {required: true, message: 'Please enter your address', trigger: 'blur'}
          ],

        },
        // 添加新用户对话框的显示隐藏
        addDialog: false,
        // 新用户数据表单
        addForm: {
          username:"eee",
          password:"111111",
          email:"wx@csu.deu.cn",
          address:"csu",
          phone:"13980217777",
          url:"/Users/meowo/Downloads/vuuue/public/favicon.png"
        },
        // 修改用户对话框的显示与隐藏
        editDialog: false,
        // 查询到的用户信息对象
        userForm: [],
      }
    },
    created() {
      this.getUserList()
    },
    methods: {
      //得到list
      getUserList() {
        let _this = this;
        this.$axios.get('/api/account/userList').then(res=>{
          if (res.data.status !== 200) {
            return this.$message.error('Cannot get user information')
          }
          this.$message.success('Get user information')
          _this.userList = res.data.data.userList;
        }).catch(err=>{
          console.log(err);
        })
      },
      //得到单个人
      getUser(username){
        let _this = this;
        _this.userShow = true;
          this.$axios.get('/api/account/user/' + username).then(res => {
            if (res.data.status !== 200) {
              return this.$message.error('获取user信息失败')
            } else {
              _this.user = res.data.data.user;
            }
          }).catch(err => {
            console.log(err);
          })
        },


      // 添加
      addUser() {
        this.$refs.addForm.validate(valid => {
          if (valid) {
            this.logining1 = true;
            let _this=this;
            this.$axios.post('/api/account/user', _this.addForm).then(res => {
              this.logining1 = false;
              console.log(res);
              if (res.data.status === 200) {
                this.$message({
                  type: 'success',
                  message: "Add a user successfully"
                });
                // 刷新列表
                this.getUserList();
              } else {
                this.$message({
                  type: 'error',
                  message: "Add a user unsuccessfully"
                });
              }
            }).catch(err => {
              console.log(err);
            })
          } else {
            this.$message({
              type: 'error',
              message: "error"
            });
          }
        })
      },


      //修改
      edit() {
        this.$refs.userForm.validate(valid => {
          if (valid) {
            let _this=this;
            this.logining = true;
            this.$axios.put('/api/account/user', _this.userForm).then(res => {
              this.logining = false;
              console.log(res);
              if (res.data.status === 200) {
                this.$message({
                  type: 'success',
                  message: "Edit successfully"
                });
                _this.dialog = false;
                this.$router.push('/login');
                this.reload();
              } else {
                this.$message({
                  type: 'error',
                  message: "Edit unsuccessfully"
                });
                console.log(res)
              }
            }).catch(err => {
              console.log(err);
            })
          } else {
            this.$message({
              type: 'error',
              message: "error"
            });
          }
        })
      },
      //修改的得到
      getUserEdit(username){
        let _this=this;
        _this.editDialog = true;
        this.$axios.get('/api/account/user/' + username).then(res => {
          console.log(res);
          if (res.data.status !== 200) {
            return this.$message.error('Get user failed')
          }else {
            _this.userForm = res.data.data.user;
          }
        }).catch(err => {
          console.log(err);
        })
      },
      //删除
      removeByName(name) {
        //弹窗
        const confirmResult =  this.$confirm('Are you sure you want to delete it permanently?', 'message', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.$axios.delete('/api/account/user/' + name).then(res=>{
            if (res.data.status !== 200) {
              return this.$message.error('Delete failed!')
            }
            this.$message.success('Delete successfully!');
            // 刷新列表
            this.getUserList();
          }).catch(err=>{
            console.log(err)
          })

        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Deletion have been cancelled.'
          });
        });


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
