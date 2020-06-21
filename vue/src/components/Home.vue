<template>
<!--
wen xin
fron-end
let we think about it that who is wx?
however, why wx look like it?
oh, we are also curious about it.
All above are about wx.
that`s all.thank you.

Yaxun Yang
java developer
who is yyx?
what`s yyx doing?
i will help you to learn more about yyx.
ok, now you know enough about yyx.
i really hope this helps

-->
  <!--   background-color: #f5b1aa-->
  <div class="all"  id="topTitle">
    <!--  上面滚动图-->
    <el-carousel height="900px">
      <el-carousel-item v-for="url in urls" :key="url">
        <div style="width: 100%; height: 800px;">
          <el-image :src="url" style="width: 100%">
          </el-image>
        </div>
      </el-carousel-item>
    </el-carousel>

    <!--  简介-->
    <div class="brief">
      MyPetStore is a website developed by
      <br>
      Vue + ElementUI + SSM
      <br>
      Aiming to serve you with the best user experience
    </div>

    <!--  小三块儿-->
    <div class="three">
      <div class="inner">
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="box">
              <img src="../assets/01.jpg" alt="">
              <div class="box-content">
                <h3 class="title">What's New</h3>
                <span class="post">New Items</span>
                <ul class="icon">
                  <li><a @click="commodity"><i class="el-icon-link"></i></a></li>
                </ul>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="box">
              <img src="../assets/01.jpg" alt="">
              <div class="box-content">
                <h3 class="title">Start With</h3>
                <span class="post">Guess What You Like</span>
                <ul class="icon">
                  <li><a @click="commodity"><i class="el-icon-link"></i></a></li>
                </ul>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="box">
              <img src="../assets/01.jpg" alt="">
              <div class="box-content">
                <h3 class="title">On Sales</h3>
                <span class="post">The Cheapest</span>
                <ul class="icon">
                  <li><a @click="commodity"><i class="el-icon-link"></i></a></li>
                </ul>
              </div>
            </div>
          </el-col>
        </el-row>

      </div>
    </div>

    <!--  购物车-->
    <div>
      <el-row>
        <el-col :span="12">
          <div class="where">
            <i>查看&nbsp&nbsp<br>购物车</i>
            <br>
            <div class="description">
              发现更多剁手好物
            </div>
            <el-link :underline="false">
            <div class="to" @click="cart">→</div>
            </el-link>
          </div>
        </el-col>
        <el-col :span="12">
          <img src="../assets/right.jpg" alt="">
        </el-col>
      </el-row>
    </div>
    <!-- 订单 -->
    <div>
      <el-row>
        <el-col :span="12">
          <img src="../assets/left.jpg">
        </el-col>
        <el-col :span="12">
          <div class="where">
            <i>查看&nbsp&nbsp<br>订单页</i>
            <br>
            <div class="description">
              看看宝贝发货了吗
            </div>
            <el-link :underline="false"  @click="order">
              <div class="to">
                ←
              </div>
            </el-link>

          </div>
        </el-col>
      </el-row>
    </div>

    <!--关于我们-->
    <div id="aboutTitle">
      <div class="about">
        <img src="../assets/about.jpg" style="width: 100%;" alt="">
      </div>
    </div>

    <!--  -->


  </div>
</template>

<script>
  export default {
    data() {
      return {
        message: 'hello',
        urls: [
          require("@/assets/01.jpg"),
          require("@/assets/02.jpg"),
          require("@/assets/03.jpg"),
          require("@/assets/04.jpg")
        ],


      }
    },
    computed: {
      //获取用户
      username() {
        return sessionStorage.getItem('username');
      },
      usernameAdmin(){
        return sessionStorage.getItem('usernameAdmin');
      }
    },
    methods:{
      commodity(){
        this.$router.push('/commodity')
      },
      cart(){
        if (!this.username) {
          if(this.usernameAdmin){
            this.$message({
              message: 'You cannot see it as an admin',
              type: 'warning'
            });
          }else
          {
            this.$message({
              message: 'Please login in first',
              type: 'warning'
            });
            this.$router.push('/login');
          }
        }else{
          this.$router.push('/my/cart');
        }
      },
      order(){
        if (!this.username) {
          if(this.usernameAdmin){
            this.$message({
              message: 'You cannot see it as an admin',
              type: 'warning'
            });
          }else
          {
            this.$message({
              message: 'Please login in first',
              type: 'warning'
            });
            this.$router.push('/login');
          }
        }else{
          this.$router.push('/my/order');
        }
      }
    },
  }
</script>

<style scoped>
  [v-cloak]{
    display: none;
  }
  .car-img {
    width: 100%;
    height: 700px;
  }

  .brief {
    padding-top: 40px;
    justify-content:center;
    font-size: 25px;
    font-weight: initial;
    color: #767676;
  }

  /* 三个遮罩 */
  .three {
    padding: 2em 0;
  }

  .inner {
    padding: 20px;
  }

  .box {
    font-family: 'Merriweather Sans', sans-serif;
    overflow: hidden;
    position: relative;
    z-index: 1;
    transition: all .1s;
  }

  .box:hover {
    box-shadow: 3px 3px 5px #999;
  }

  .box:before,
  .box:after {
    content: "";
    background: radial-gradient(circle at 23% 70%, rgba(255, 255, 255, 0.8), #fff 30%);
    width: 150%;
    height: 150%;
    opacity: 0;
    transform: rotate(45deg);
    position: absolute;
    top: -10.5%;
    right: -150%;
    z-index: 1;
    transition: all 0.35s ease;
  }

  .box:after {
    background: rgba(255, 255, 255, 0.5);
    width: 65%;
    height: 65%;
    right: auto;
    left: -20%;
    top: -65%;
  }

  .box:hover:before {
    opacity: 1;
    right: -84%;
  }

  .box:hover:after {
    opacity: 1;
    top: -50%;
  }

  .box img {
    width: 100%;
    height: auto;
    transition: all 0.5s ease;
  }

  .box:hover img {
    transform: scale(1.1);
  }

  .box .box-content {
    text-align: right;
    transform: translateY(-50%);
    position: absolute;
    top: 50%;
    right: -100%;
    z-index: 2;
    transition: all .5s;
  }

  .box:hover .box-content {
    right: 5%;
  }

  .box .title {
    color: #1e272e;
    font-size: 23px;
    font-weight: 700;
    text-transform: uppercase;
    margin: 0 0 3px 0;
  }

  .box .post {
    font-size: 16px;
    text-transform: capitalize;
    margin: 0 0 10px;
    display: block;
  }

  .box .icon {
    padding: 0;
    margin: 0;
    list-style: none;
  }

  .box .icon li {
    display: inline-block;
    margin: 0 4px;
  }

  .box .icon li a {
    color: #fff;
    background-color: #1e272e;
    font-size: 18px;
    text-align: center;
    line-height: 35px;
    height: 35px;
    width: 35px;
    border-radius: 50%;
    display: block;
    transition: all 0.3s;
  }

  .box .icon li a:hover {
    color: #1e272e;
    background-color: #fff;
    border-radius: 10%;
    box-shadow: 0 0 5px #1e272e inset;
  }

  @media only screen and (max-width: 990px) {
    .box {
      margin: 0 0 30px;
    }
  }

  @media only screen and (max-width: 479px) {
    .box .title {
      font-size: 20px;
    }
  }

  /* about us */
  .about {
    padding-top: 30px;
    width: 100%;
    height: 100%;
  }

  .where {
    padding-top: 200px;
    font-weight: bold;
    font-size: 60px;
  }

  .description {
    padding-top: 30px;
    font-weight: lighter;
    font-size: 30px;
  }

  .to {
    font-weight: bold;
    font-size: 60px;
  }

  /* commodity */
  .goods{
    padding-left: 20px;
    padding-right: 20px;
    background-color: #f79f31;
  }

  .btn {
    background-color: #1e272e;
    padding-left: 300px;
    padding-right: 300px;
    display: flex;
    justify-content: space-between;
    font-size: large;
    font-weight: bold;
  }
  .goods-items{
    background-color: #5bc0de;
  }

</style>
