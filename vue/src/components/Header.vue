<template>
  <div class="header">
    <div class="long">
      <span>&nbsp</span>
      <div class="toptext">
        <span>Lauguage: English</span>
        <el-divider direction="vertical"></el-divider>
        <span>Copyright</span>
      </div>
    </div>
    <div class="row">
      <el-row>
        <!-- 左边logo -->
        <el-col :span="4">
          <img @click="home" src="../assets/logo.png" class="logo" alt="">

        </el-col>

        <!-- 中间导航区域 -->
        <el-col class="main" :span="14">
          <el-menu
            :default-active="$route.path"
            class="menu-header"
            router
            mode="horizontal"
            @select="handleSelect"
            active-text-color="#000">
            <!--这个是平滑滚动到aboutus-->
            <el-menu-item>
              <span  @click="toAbout"><i class="el-icon-s-promotion"></i>AboutUs</span>
            </el-menu-item>
              <!-- 循环写的路由，其中路由中有  hidden：true 的就不加入循环 -->
              <template
                v-for="routeAll in $router.options.routes"
                v-if="!routeAll.hidden">
                <template v-for="route in routeAll.children" v-if="!route.hidden">
                  <!-- 循环没有children的路由 -->
                  <el-menu-item
                    v-if="!route.hasChild"
                    :key="route.path"
                    :index="route.path" >
                    <template slot="title" style="font-size: 15px">
                      <i :class="route.class"></i>
                      {{ route.name }}
                    </template>
                  </el-menu-item>
                  <!-- 循环有children的路由 -->
<!--                  user-->
                  <el-submenu  :index="route.path" v-if="route.hasChild && username && route.isUser">
                    <template slot="title" style="font-size: 15px">
                      <i :class="route.class"></i>
                      {{ route.name }}
                    </template>
                    <el-menu-item
                      v-for="child in route.children"
                      v-if="!child.hidden"
                      :index="child.path"
                      :key="child.path"
                      style="font-size: 15px">
                      <i :class="child.class"></i>
                      {{ child.name }}
                    </el-menu-item>
                  </el-submenu>
<!--                 admin -->
                  <el-submenu  :index="route.path" v-if="route.hasChild && usernameAdmin  && !route.isUser">
                    <template slot="title" style="font-size: 15px">
                      <i :class="route.class"></i>
                      {{ route.name }}
                    </template>
                    <el-menu-item
                      v-for="child in route.children"
                      v-if="!child.hidden"
                      :index="child.path"
                      :key="child.path"
                      style="font-size: 15px">
                      <i :class="child.class"></i>
                      {{ child.name }}
                    </el-menu-item>

                  </el-submenu>
                </template>
                </template>


          </el-menu>
        </el-col>
        <!-- 根据logined值的真假来判断是显示登录按钮还是用户信息
             以后根据登录状态来改变 -->
        <el-col :span="6" class="user">

        <div v-if="username">
          <el-dropdown>
            <el-image class="avatar" :src="user.url" alt=""></el-image>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-text="username" disabled></el-dropdown-item>
              <el-dropdown-item @click.native="logout" divided>Log Out</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
          <div v-if="usernameAdmin">
            <el-dropdown>
              <el-image class="avatar" :src="admin.url" alt=""></el-image>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item v-text="usernameAdmin" disabled></el-dropdown-item>
                <el-dropdown-item @click.native="logout" divided>Log Out</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
          <el-button-group v-if="!username&&!usernameAdmin">
            <el-button  class="button" @click.native="login" type="danger" size="small" round >Log In</el-button>
            <el-button  class="button" @click.native="signUp" type="success" size="small" round >Sign Up</el-button>
          </el-button-group>
        </el-col>
      </el-row>
    </div>

  </div>


</template>
<script>
  export default {
    inject:['reload'],
    data () {
      return {
        user: {},
        admin: {},
      }
    },
    computed:{
      user(){
        return sessionStorage.getItem('user');
      },
      username(){
        return sessionStorage.getItem('username');
      },
      admin(){
        return sessionStorage.getItem('user');
      },
      usernameAdmin(){
        return sessionStorage.getItem('usernameAdmin');
      }
    },
    created() {
      this.getUser();
      this.getAdmin();
    },
    methods: {
      // 如果没登陆，给用户一点提示
      handleSelect (key) {
        const tokenStr = sessionStorage.getItem('token');
        if (!tokenStr && (key === '/my/cart' || key === '/my/order'  || key === '/my/setting' )){
          this.$message({
            type: 'warning',
            message: "Please login first"
          });

        }
      },
      home () {
        this.$router.push('/');
        console.log('home')
      },
      login () {
        this.$router.push('/login')
      },
      signUp () {
        this.$router.push('/signUp')
      },
      //这里是平滑滚动
      toAbout(){
        this.$router.push('/');
        document.getElementById('aboutTitle').scrollIntoView({ block: 'start', behavior: 'smooth' });
      },
      //登出
      logout() {
        this.$confirm('退出后将返回登录界面, 是否退出登录?', '真的要退出吗？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          sessionStorage.removeItem('username');
          this.$router.push('/login');
          this.$message({
            type: 'danger',
            message: '退出成功!'
          });
          this.reload();
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消退出'
          })
        })
      },
      getUser(){
        let _this = this;
        this.$axios.get('/api/account/user/' + this.username).then(res => {
          console.log(res);
          if (res.data.status !== 200) {
            return this.$message.error('Get user failed')
          }else {
            _this.user = res.data.data.user;
          }
        }).catch(err => {
          console.log(err);
        })
      },
      getAdmin(){
        let _this = this;
        this.$axios.get('/api/account/admin/' + this.username).then(res => {
          console.log(res);
          if (res.data.status !== 200) {
            return this.$message.error('Get admin failed')
          }else {
            _this.admin = res.data.data.admin;
          }
        }).catch(err => {
          console.log(err);
        })
      },

  }
  }
</script>
<style scoped>
  .header {
    width: 70%;
    height: 60px;
    margin: 0;
    background: transparent;
    position: fixed;
    top: 0;
    left: 15%;
    /*box-shadow: 0 0 25px #666;*/
  }

  .long{
    width: 100%;
    height: 25px;
  }
  .toptext{
    padding-right: 2px;
    float: right;
    color: white;
    font-size: 15px;
  }
  .row{
    background-color: rgba(255, 255, 255, 0.93);
    /*background-color: white;*/
    /*left: 15%;*/
    /*width: 70%;*/
    margin: 0;
    /*position: fixed;*/
    top: 20px;
  }
  .menu-header{
    background-color: transparent;

  }
  .menu-header template{
    font-size: 15px;
    background-color: transparent;
  }

  .logo-header{
    padding-top: 10px;
    padding-left: 40px;
  }
  /*.logo-header img {*/
  /*  width: 40px;*/
  /*  height: 40px;*/
  /*  cursor: pointer;*/
  /*}*/
  .button {
    margin: 15px 0;
  }
  /*.avatar {*/
  /*  width: 40px;*/
  /*  height: 40px;*/
  /*  border-radius: 20px;*/
  /*  margin: 10px 0;*/
  /*  cursor: pointer;*/
  /*}*/
  .el-row {
    float: bottom;
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .logo{
    width: 63px;
    height: 60px;
  }

  .button {
    margin: 15px 0;
  }

  .avatar {
    width: 40px;
    height: 40px;
    border-radius: 20px;
    margin: 10px 0;
    cursor: pointer;
  }
</style>
