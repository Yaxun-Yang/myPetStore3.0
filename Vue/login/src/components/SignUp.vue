<template>
  <div class="all">
    <div class="signUp-form">
      <el-form
        :model="signUpForm"
        ref="signUpForm"
        :rules="rule"
        label-width="0"
        class="signUpForm">
        <div class="space">
          <h3>Sign Up</h3>
          <span class="to" @click="login">Already have an account?</span>
        </div>
        <br>
        <el-form-item>
          <el-image class="image" :src="signUpForm.url"></el-image>
        </el-form-item>
        <el-form-item label="Picture">
          <el-upload
            class="upload-demo"
            action="#"
            multiple
            :limit="1"
            :on-exceed="handleExceed"
            :http-request="httpRequest">
            <el-button size="small" type="primary">Click to upload</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item prop="username">
          <el-input type="text" v-model="signUpForm.username" placeholder="User Name"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="signUpForm.password" placeholder="Password"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass">
          <el-input type="password" v-model="signUpForm.checkPass" placeholder="Check Password"></el-input>
        </el-form-item>
        <el-form-item prop="phone">
          <el-input type="text" v-model="signUpForm.phone" placeholder="Phone"></el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input type="text" v-model="signUpForm.email" placeholder="Email"></el-input>
        </el-form-item>
        <el-form-item>
          <el-row :gutter="6">
            <el-col :span="8">
              <el-button type="primary" class="resetBtn" round @click.native.prevent="reset">
                Reset
              </el-button>
            </el-col>
            <el-col :span="8">
              <el-button type="danger" class="submitBtn" round @click.native.prevent="submitUser" :loading="logining">
                User
              </el-button>
            </el-col>
            <el-col :span="8">
              <el-button type="danger" class="submitBtn" round @click.native.prevent="submitAdmin" :loading="logining">
                Admin
              </el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>

    </div>
  </div>
</template>

<script>
  export default {
    data() {
      // 验证
      var validateUsername = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Please enter your username'));
        }else if(value.length >)
        else {
          if (this.methods.checkName(this.signUpForm.username) === false) {
            callback(new Error('The same username exists. Please Try again!'));
          }
          callback();
        }
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Please enter your password'));
        } else {
          if (this.signUpForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Repeat your password'));
        } else if (value !== this.signUpForm.password) {
          callback(new Error('Not the same. Try again!'));
        } else {
          callback();
        }
      };
      return {
        signUpForm: {
          username: '',
          password: '',
          url: '',
          phone: '',
          email: '',
        },
        logining: false,
        rule: {
          username: [
            {
              required: true,
              validator: validateUsername,
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
              min: 3,
              max: 15,
              message: 'Between 6-15 characters',
              trigger: 'blur'
            }
          ],
          checkPass: [
            {
              required: true,
              validator: validatePass2,
              trigger: 'blur'
            }
          ]

        }
      }
    },

    methods: {
      //检查用户名是否可用
      checkName(username) {
        //调用接口
        const {data: res} = this.$axios.get('/api/account/' + username);
        return !(res.data.user.username === null || res.data.admin.username === null);
      },
      // 只允许上传1张照片
      handleExceed(files) {
        this.$message.warning(`Only 1 picture is accessible`);
      },
      //处理显示缩略图，并且将需要用到的file转换成base64的文件格式
      httpRequest(signUpForm) {
        let _this = this;
        let rd = new FileReader(); // 创建文件读取对象
        let file = signUpForm.file;
        rd.readAsDataURL(file); // 文件读取装换为base64类型
        rd.onloadend = function (e) {
          _this.signUpForm.url = this.result // this指向当前方法onloadend的作用域
        }
      },
      //提交
      submitUser() {
        this.$refs.signUpForm.validate(valid => {
          if (valid) {
            this.logining = true;
            const {data: res} = this.$axios.post('/api/account/user', _this.loginForm);
            this.logining = false;
            // 208 用户注册成功
            if (res.status === 200) {
              this.$message({
                type: 'success',
                message: "SignUp as a user successfully"
              });
              //跳转到我的信息的页面
              this.$router.push('/login');
              //209管理员注册成功
            } else {
              console.log('submit error')
            }
          }else {
            this.$message({
              type: 'error',
              message: "Error"
            });
          }
        })
      },
      //提交
      submitAdmin() {
        this.$refs.signUpForm.validate(valid => {
          if (valid) {
            this.logining = true;
            const {data: res} = this.$axios.post('/api/account/admin', _this.loginForm);
            this.logining = false;
            // 200 用户注册成功
            if (res.status === 200) {
              this.$message({
                type: 'success',
                message: "SignUp as an admin successfully"
              });
              //跳转到我的信息的页面
              this.$router.push('/login');
              //209管理员注册成功
            } else {
              console.log('submit error')
            }
          }else{
            this.$message({
              type: 'error',
              message: "Error"
            });
          }
        })
      },
      reset() {
        this.$refs.signUpForm.resetFields()
      },
      login() {
        this.$router.push('/login')
      }
    }
  }
</script>
<style scoped>
  .all {
    background-image: url('http://qbyy9dziv.bkt.clouddn.com/signUp.jpg');
    height: 800px;
  }
</style>
