<template>
  <div class="all">
    <div class="login-form">
      <el-form :model="loginForm" ref="loginForm" :rules="rule" label-width="0" class="innerForm">
        <h2>Log In</h2>
        <br>
        <el-form-item prop="username">
          <el-input type="text" v-model="loginForm.username" placeholder="User Name"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input  type="password" v-model="loginForm.password" placeholder="Password"></el-input>
        </el-form-item>
        <el-form-item prop="phone">
          <el-input  type="text" v-model="loginForm.phone" placeholder="Phone">
            <el-button slot="append" icon="el-icon-s-promotion" @click="sendCode"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item prop="verifyCode">
          <el-input  type="text" v-model="loginForm.code" placeholder="Identifying Code" :loading="logining1"></el-input>
        </el-form-item>
        <el-form-item>
          <div class="space">
            <a @click="signUp">Need a new account?</a> &nbsp&nbsp&nbsp&nbsp&nbsp
            <a @click="signUp">Forget Password?</a>
          </div>
        </el-form-item>
        <el-form-item>
          <div class="space">
            <el-button type="primary" class="resetBtn" round @click.native.prevent="reset">
              Reset
            </el-button>
            <el-button
              type="danger"
              class="submitBtn"
              round
              @click.native.prevent="login"
              :loading="logining2">
              Login
            </el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      //验证手机号
      var checkPhone = (rule, value, cb) => {
        const regPhone = /^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57]|17[678])[0-9]{8}$/;
        if (regPhone.test(value)) {
          return cb();
        }
        cb(new Error("Please enter legal phone number"));
      };
      return {
        loginForm: {
          username: 'j2',
          password: '',
          phone: '',
          verifyCode:'',
        },
        logining1: false,
        logining2: false,
        rule: {
          username: [
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
          phone: [
            {
              required: true, message: 'Please enter your phone number', trigger: 'blur'
            },
            {
              validator: checkPhone,
              trigger: 'blur'
            }
          ],
        }
      }
    },

    methods: {
      //验证码的发送
      sendCode(){
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
            //转
            this.logining1 = true;
            let _this = this;
            const {data: res} = this.$axios.post('/api/account/verifyCode', _this.loginForm);
              //按钮不转了
              this.logining = false;
              //423代表用户不存在
              if (res.status === 423) {
                _this.$message({
                  type: 'warning',
                  message: "No such account"
                });
              }
              else{
                _this.$message({
                  type: 'success',
                  message: "Send to your phone successfully"
                });
              }
              console.log(res);
          }
      })
      },
      login() {
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
            // 按钮开始转
            this.logining2 = true;
            let _this = this;
            const {data: res} = this.$axios.post('/api/account/token', _this.loginForm);
              //如果没这个人
              if (res.status === 423) {
                // 按钮不转了
                this.logining2 = false;
                _this.$message({
                  type: 'warning',
                  message: "No such account"
                });
                //423验证码错误
              }else if(res.status === 423) {
                // 注册按钮不转了
                this.logining2 = false;
                _this.$message({
                  type: 'error',
                  message: "Error in identifying code"
                });
                // 206 用户登录成功
              } else if(res.status === 206) {
                // 注册按钮不转了
                this.logining2 = false;
                this.$message({
                  type: 'success',
                  message: "Login as a user successfully"
                });
                // 将返回的数据注入内存
                sessionStorage.setItem('token', res.data.user.token);
                //跳转到我的信息的页面
                this.$router.push('/my/cart');
                //207管理员登陆成功
              }else if(res.status === 207){
                this.$message({
                  type: 'success',
                  message: "Login as an admin successfully"
                });
                // 将返回的数据注入内存
                sessionStorage.setItem('token', res.data.admin.token);
                //跳转到我的信息的页面
                this.$router.push('/admin');
              }
          }

        })
      },
      reset () {
        this.$refs.loginForm.resetFields()
      },
      signUp () {
        this.$router.push('/signUp')
      }
    }
  };
</script>
<style scoped>
  .image{
    width: 50px;
    height: 50px;
  }
  .all{
    background-image: url('http://qbyy9dziv.bkt.clouddn.com/login.JPG');
    height: 800px;
  }
</style>
