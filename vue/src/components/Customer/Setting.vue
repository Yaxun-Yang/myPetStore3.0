<template>
  <div class="cart">
    <!--    占位-->
    <div style="width: 140px; height: 260px; "></div>
    <div style="">
      <span style="font-size: 40px;color: #ffffff"> </span>
    </div>
    <div style="position: absolute;">
      <el-image style="width: 160px; height: 150px;left: 600px;top: -150px;z-index:100" :src="url"></el-image>
    </div>
    <div style="position: absolute">
      <el-link type="info" :underline="false" @click="getUserEdit"
               style="font-size: 60px;left: 1200px;top: 30px;z-index:100">
        <i class="el-icon-setting"></i> </el-link>
    </div>
    <el-card style="width: 90%; margin-left: 5%; height: 500px;font-size: 40px;color: red;">
      <el-row>
        <el-col :span="6">
          <el-image style="width: 160px; height: 150px;border-radius: 50%" :src="user.url"></el-image>
        </el-col>
        <el-col :span="18">
          <div style="color: #ff7a6e;font-size: 60px;text-align: left;">
            Hi~ Dear&nbsp;{{user.username}}
            <br>
            Welcome to <el-link @click="toHome" :underline="false" style="color: #ff7a6e;font-size: 60px;padding-bottom: 10px"> Tom's Dog!</el-link>
          </div>
          <div style="color: #575757;font-size: 40px;text-align: left;">
            Your Phone:&nbsp;{{user.phone}}
            <br>
            Your Address:&nbsp;{{user.address}}
            <br>
            Your Email:&nbsp;{{user.email}}
          </div>
        </el-col>
      </el-row>
    </el-card>
<!--    修改-->
    <el-dialog title="Edit your information" :visible.sync="dialog">
      <el-form :model="userForm" ref="userForm" :rules="rule">
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
        <el-button @click="dialog = false">Cancel</el-button>
        <el-button type="primary" @click="edit" :loading="loading">Yes</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    inject:['reload'],

    data(){
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('Repeat your password'));
        } else if (value !== this.userForm.password) {
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
      return{
        dialog: false,
        url: 'http://qbyy9dziv.bkt.clouddn.com/order.png',
        user:[],
        userForm:[],
        loading: false,
        rule: {
          password: [
            {
              required: true, message: 'Please enter password', trigger: 'blur'
            },
            {
              min: 6, max: 15, message: 'Between 6-15 characters', trigger: 'blur'
            }
          ],
          checkPass: [
            {
              required: true, validator: validatePass2, trigger: 'blur'
            }
          ],
          email: [
            {
              required: true, message: 'Please enter your email', trigger: 'blur'
            },
            {
              validator: checkEmail, trigger: 'blur'
            }
          ],
          phone: [
            {
              required: true, message: 'Please enter your phone number', trigger: 'blur'
            },
            {
              validator: checkPhone, trigger: 'blur'
            }
          ],

        }
      }
    },
    computed: {
      username(){
        return sessionStorage.getItem('username');
      },
    },
    created() {
      this.getUser();
    },
    methods:{
      //去home
      toHome(){
        this.$router.push('/home');
      },
      getUser(){
        let _this = this;
        this.$axios.get('/api/account/user/' + this.username).then(res => {
          console.log(res);
          if (res.data.status !== 200) {
            return this.$message.error('Get user failed')
          }else {
            _this.user = res.data.data.user;
            console.log(_this.user)
          }
        }).catch(err => {
          console.log(err);
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
      getUserEdit(){
        let _this=this;
        _this.dialog = true;
        this.$axios.get('/api/account/user/' + this.username).then(res => {
          console.log(res);
          if (res.data.status !== 200) {
            return this.$message.error('Get user failed')
          }else {
            _this.userForm = res.data.data.user;
          }
        }).catch(err => {
          console.log(err);
        })
      }
    }
  }

</script>

<style >
  .cart {
    width: 100%;
    height: auto;
    background-color: #ffa533;
  }
</style>
