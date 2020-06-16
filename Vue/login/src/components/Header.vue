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
        <el-col :span="2" >
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
            active-text-color="#000"
          >
            <el-menu-item>
              <span class="header-title" @click="toAbout"><i class="el-icon-s-promotion"></i>AboutUs</span>
            </el-menu-item>
            <!-- 循环写的路由，其中路由中有  hidden：true 的就不加入循环 -->
            <template
              v-for="route in $router.options.routes"
              v-if="!route.hidden">
              <!-- 循环没有children的路由 -->
              <el-menu-item
                v-if="!route.hasChild"
                :key="route.path"
                :index="route.path" >
                <i :class="route.class"></i>
                {{ route.name }}
              </el-menu-item>
              <!-- 循环有children的路由 -->
              <el-submenu v-else :index="route.path">
                <template slot="title">
                  <i :class="route.class"></i>
                  {{ route.name }}</template>
                <el-menu-item
                  v-for="child in route.children"
                  :index="child.path"
                  :key="child.path">
                  <i :class="child.class"></i>
                  {{ child.name }}
                </el-menu-item>
              </el-submenu>
            </template>
          </el-menu>
        </el-col>
        <!-- 根据logined值的真假来判断是显示登录按钮还是用户信息
             以后根据登录状态来改变 -->
        <el-button-group v-if="!logined" >
          <el-button  class="button" @click.native="login" type="danger" size="small" round >Log In</el-button>
          <el-button  class="button" @click.native="signUp" type="success" size="small" round >Sign Up</el-button>
        </el-button-group>
        <div v-else>
          <el-dropdown>
            <img class="avatar" src="../assets/1.jpg" alt="">
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>Admin1</el-dropdown-item>
              <el-dropdown-item>Setting</el-dropdown-item>
              <el-dropdown-item divided>Log Out</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-row>
    </div>


  </div>


</template>
<script>
  export default {
    // ...
    data () {
      return {
        logined: false
      }
    },
    methods: {
      // 给用户一点提示
      handleSelect (key) {
        const tokenStr = sessionStorage.getItem('token');
        if (!tokenStr && (key === '/my/cart')) {
          let _this = this;
          _this.$message({
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

      toAbout(){
        this.$router.push('/');
        document.getElementById('aboutTitle').scrollIntoView({ block: 'start', behavior: 'smooth' });
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
  .header-title{
    font-size: 15px;
  }
</style>
