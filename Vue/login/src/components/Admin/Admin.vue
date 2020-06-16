<template>
  <div class="con">
    <div class="all">
      ss<br> ss<br> ss<br> ss<br> ss<br> ss<br>
    </div>
    <el-container>
      <el-header>
        <el-menu
         class="menu"
         mode="horizontal"
         @select="handleSelect"
         background-color="#447387"
         text-color="#fff"
         active-text-color="#ffd04b">
         <el-menu-item class="logo" @click="home">这里应该有一个超级好看的logo</el-menu-item>
<!--         商品-->
         <el-menu-item index="1" class="goods" @click="category">Category</el-menu-item>
<!--         用户-->
         <el-menu-item index="2" class="account" @click="account">Account</el-menu-item>
<!--         订单-->
         <el-menu-item index="3" class="order" @click="order">Order</el-menu-item>
         <el-menu-item>
<!--           <i class="el-icon-setting"></i>-->
         </el-menu-item>
         <el-menu-item>
           <i class="el-icon-bell"></i>
         </el-menu-item>
         <el-menu-item>
           <i class="el-icon-user-solid"></i>
         </el-menu-item>
         <el-menu-item>
           <el-dropdown>
              <span class="el-dropdown-link">
                Admin<i class="el-icon-arrow-down el-icon--right" ></i>
              </span>
             <el-dropdown-menu slot="dropdown">
               <el-dropdown-item icon="el-icon-user">Account</el-dropdown-item>
               <el-dropdown-item icon="el-icon-s-tools">Setting</el-dropdown-item>
               <el-dropdown-item icon="el-icon-switch-button">Layout</el-dropdown-item>
             </el-dropdown-menu>
           </el-dropdown>
         </el-menu-item>
       </el-menu>
     </el-header>
     <el-main>
       <!-- 路由占位符 -->
       <router-view></router-view>
     </el-main>
     <el-footer>
        CopyRight@CSU.yyx&wx
     </el-footer>
   </el-container>
 </div>
</template>
<script>
  export default {
    data() {
      return {
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      home () {
        this.$router.push({ path: '/admin' })
      },
      account () {
        this.$router.push({ path: '/admin/account' })
      },
      order () {
        this.$router.push({ path: '/admin/order' })
      },
      category(){
        this.$router.push({ path: '/admin/category' })
      }
    }
  }
</script>
<style>
  html{
    min-height:100%;
    position: relative;
    background-color: #e5e8ef;
  }
  body{
    margin-bottom: 60px;
    background-color: #e5e8ef;
  }

  /*#app,*/
  /*.con,*/
  .el-container{
    /*padding: 0;*/
    /*margin: 0;*/
    /*width: 100%;*/
    /*height: 100%;*/
    /*background-color: #e5e8ef;*/
  }
  .el-header{
    background-color: #447387;
    color: #ffffff;
    text-align: center
  }

  /*.el-footer{*/
  /*  background-color: #447387;*/
  /*  color: #ffffff;*/
  /*  text-align: center;*/
  /*  position: absolute;*/
  /*  bottom: 0;*/
  /*  width: 100%;*/
  /*  height: 60px;*/
  /*  font-size: 20px;*/

  /*}*/
  .menu{
    display: flex;
    justify-content: space-between;
    font-size: large;
    font-weight: bold;
  }
  .el-dropdown{
    color: white;
    font-weight: bold;
  }
  .logo,
  .goods,
  .account,
  .order{
    width: 300px;
  }
  .el-dropdown-link{
  }
</style>
