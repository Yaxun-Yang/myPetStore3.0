<template>
  <div class="all">
    <div class="signUp-form">
      <el-form
        :model="signUpForm"
        ref="signUpForm"
        :rules="rule"
        label-width="0"
        class="signUpForm">
        <div class="signUp-form-inner">
          <div class="space">
            <span style="font-size: 20px;font-weight: bold">Sign Up</span>
            <el-link type="primary" :underline="false" @click="login" style="font-size: 17px">Already have an account?
            </el-link>
          </div>
          <el-row :gutter="20" style="padding-top: 110px">
            <el-col :span="8" style="">
              <el-form-item prop="url">
                <el-image class="headImg" :src="signUpForm.url"></el-image>
              </el-form-item>
              <el-form-item>
<!--                <el-upload-->
<!--                  class="avatar-uploader"-->
<!--                  action="#"-->
<!--                  multiple-->
<!--                  :limit="1"-->
<!--                  :show-file-list="false"-->
<!--                  :on-success="httpRequest"-->
<!--                  :on-exceed="handleExceed">-->
<!--                  <img v-if="signUpForm.url.file" :src="signUpForm.url.file" class="avatar">-->
<!--                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
<!--                </el-upload>-->

                <!--                <el-upload-->
                <!--                  class="upload-demo"-->
                <!--                  action="#"-->
                <!--                  multiple-->
                <!--                  :limit="1"-->
<!--                                  :on-exceed="handleExceed"-->
<!--                                  :http-request="httpRequest">-->
                <!--                  <el-button size="small" type="primary">Click to upload</el-button>-->
                <!--                </el-upload>-->
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item  prop="username">
<!--               -->
<!--                <el-input type="text" v-model="signUpForm.username" placeholder="User Name"></el-input>-->
<!--                <span v-if="this.checkName = true">You cannot use it</span>-->
<!--                <span v-else >You can use it</span>-->
<!--                {{hasUser}}-->
                <el-input
                  v-model="signUpForm.username"
                  @blur="onInputBlur"
                  @focus="onInputFocus">
                </el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input type="password" v-model="signUpForm.password" placeholder="Password"></el-input>
              </el-form-item>
              <el-form-item prop="checkPass">
                <el-input type="password" v-model="signUpForm.checkPass" placeholder="Check Password"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item prop="phone">
                <el-input type="text" v-model="signUpForm.phone" placeholder="Phone"></el-input>
              </el-form-item>
              <el-form-item prop="email">
                <el-input type="text" v-model="signUpForm.email" placeholder="Email"></el-input>
              </el-form-item>
              <el-form-item prop="addr">
                <el-input type="text" v-model="signUpForm.address" placeholder="Address"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </div>
        <br>


        <el-form-item>
          <el-row :gutter="6">
            <el-col :span="12">
              <el-button type="primary" class="resetBtn" round @click.native.prevent="reset">
                Reset
              </el-button>
            </el-col>
            <el-col :span="12">
              <el-button type="danger" class="submitBtn" round @click.native.prevent="submitUser" :loading="logining">
                User
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
      // var validateUsername = (rule, value, callback) => {
      //   if (value === '') {
      //     callback(new Error('Please enter your username'));
      //   } else if (value.length < 3 || value.length > 10) {
      //     callback(new Error('self'));
      //     console.log('self')
      //   }
      //   callback();
      //
      // };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Repeat your password'));
        } else if (value !== this.signUpForm.password) {
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
        headerInput:'111',
        thisStyle:'',
        hasUser: false,
        signUpForm: {
          username: 'www',
          password: '111111',
          url: '',
          // url: {
          //   file: '',
          //   fileName:'',
          // },
          phone: '13988888888',
          email: '2@qq.com',
          addr: '22',
        },
        logining: false,
        rule: {
          username: [
            {
              required: false,
              // validator: validateUsername,
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
            {
              required: true,
              validator: validatePass2,
              trigger: 'blur'
            }
          ],
          email: [
            {required: true, message: 'Please enter your email', trigger: 'blur'},
            {validator: checkEmail, trigger: 'blur'}
          ],
          phone: [
            {required: true, message: 'Please enter your phone number', trigger: 'blur'},
            {validator: checkPhone, trigger: 'blur'}
          ],

        }
      }
    },

    methods: {
      // 失去焦点和得到焦点的验证方法，name为要验证的字段名,type为blur或focus
      onInputFocus() {
        let self = this;

          self.thisStyle = "outline: 0;box-shadow: 0px 0px 10px 0px #ffffff;transition: border ease-in-out 0.15s,box-shadow ease-in-out 0.15s;-moz-transition:border ease-in-out 0.15s,box-shadow ease-in-out 0.15s;-webkit-transition:border ease-in-out 0.15s,box-shadow ease-in-out 0.15s;"
        console.log('on')

      },
      //3.失去焦点事件
      onInputBlur() {
        let self = this;
        if (self.signUpForm.username === null){
        self.thisStyle = "width: 330px;"
          console.log('nooo')
        }
      },

      //检查用户名是否可用
      checkName() {
        let _this=this;
        //调用接口
        this.$axios.get('/api/account/user/' + _this.signUpForm.username).then(res=>{
         console.log(res);
          console.log('res');

          return  res.data.data.user !== null;
        })

      },
      // 只允许上传1张照片
      handleExceed(files) {
        this.$message.warning(`Only 1 picture is accessible`);
      },
      //处理显示缩略图，并且将需要用到的file转换成base64的文件格式
      httpRequest(signUpForm) {
        let _this = this;
        let rd = new FileReader(); // 创建文件读取对象
        let file = signUpForm.url.file;
        rd.readAsDataURL(file); // 文件读取装换为base64类型
        rd.onloadend = function (e) {
          _this.signUpForm.url.file = this.file // this指向当前方法onloadend的作用域
        },
          console.log(this.file);
      },
      //提交
      submitUser() {
        this.$refs.signUpForm.validate(valid => {
          if (valid) {
            this.logining = true;
            let _this=this;
            this.$axios.post('/api/account/user', _this.signUpForm).then(res => {
              this.logining = false;
              console.log(res);
              // 208 用户注册成功
              if (res.data.status === 200) {
                this.$message({
                  type: 'success',
                  message: "SignUp as a user successfully"
                });
                //跳转到我的信息的页面
                this.$router.push('/login');
              } else {
                this.$message({
                  type: 'error',
                  message: "SignUp as a user unsuccessfully"
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

        reset() {
          this.$refs.signUpForm.resetFields()
        },
        login() {
          this.$router.push('/login')
        },
    }
  }
</script>
<style scoped>
  .all {
    background-image: url('http://qbyy9dziv.bkt.clouddn.com/signUp.jpg');
    height: 800px;
  }

  .signUp-form {
    margin: 10px auto;
    width: 1000px;
    /*padding: 30px 30px 200px 30px;*/
    border-radius: 25px;
  }

  .signUp-form-inner {
    width: 100%;
    padding-top: 288px;
    padding-left: 50px;
  }

  .space {
    padding-left: 12%;
    width: 100%;
    justify-content: space-between;
    display: flex;
  }

  .headImg {
    width: 100px;
    height: 100px;
  }
/*  load*/
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
/*  up   结束*/
</style>
