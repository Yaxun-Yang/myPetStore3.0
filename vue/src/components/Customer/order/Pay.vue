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
    <el-card style="width: 90%; margin-left: 5%; height: 500px;font-size: 40px;color: red">
      <span>You are going to pay...</span>
      <br><br><br><br><br><br>
      <el-link :underline="false" @click="order" style="font-size: 35px;color: red">
      <i class="el-icon-caret-left"></i>
        If you have paid, please click here to see the latest order
    </el-link>
    </el-card>
  </div>
</template>

<script>
    export default {
      inject:['reload'],
      data(){
        return{
          url: 'http://qbyy9dziv.bkt.clouddn.com/order.png',
          alipay: '',
          isPaid:'',
        }
      },
      computed: {
        username(){
          return sessionStorage.getItem('username');
        },
        orderId(){
          return sessionStorage.getItem('orderId');
        },
      },
      created() {
        this.getPay();
      },
      methods:{
        getPay(){
          let _this = this;
          this.$axios.get('/api/order/paid/' + this.orderId).then(res => {
            console.log(res);
            if (res.data.status !== 200) {
              return this.$message.error('Get Alipay failed')
            }else {
              this.$message({
                type: 'success',
                message: 'Waiting'
              });
              _this.alipay = res.data.data.response;
              //打开阿里支付的标签页，需要等待一段时间
              let routeData = this.$router.resolve({ path: '/alipay', query: {  htmls: _this.alipay } });
              window.open(routeData.href, '_blank');
              sessionStorage.removeItem('orderId');
              this.reload();
            }
          }).catch(err => {
            console.log(err);
          })
        },
        order() {
          this.$router.push('/my/order');
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
