<template>
  <div class="cart">
    <!--    占位-->
    <div style="width: 140px; height: 100px; "></div>
    <div style="">
      <span style="font-size: 40px;color: #ffffff">Check </span>
      <br>
      <span style="font-size: 20px;color: #ffffff">Order Id:   {{orderId}} </span>
      <br>
      <span style="color: #ffffff">{{order.orderDate}}</span>
    </div>
    <div style="position: absolute;">
      <el-image style="width: 160px; height: 150px;left: 500px;top: -120px;z-index:100" :src="url"></el-image>
    </div>
    <div style="position: absolute;">
      <el-link :underline="false" type="info" @click="deleteOrder" style="font-size: 20px; left: 1000px;top: -30px;z-index:100">
        <i class="el-icon-caret-left"></i>
        Click here to come back
      </el-link>
    </div>
    <el-card id="money" shadow="never" style="width: 90%;margin-left: 5%;background-color: #e2e2e2; border-radius: 5px;" >
      <!--      地址-->
      <el-card shadow="hover" style="border-radius: 10px">
        <el-row :gutter="1">
          <el-col :span="4">
            <div style="width: 80px;float: right;padding-top: 15px">
              <div class="circle">
                <div class="no-link">
                  <i class="el-icon-location-outline"></i>
                </div>
              </div>
            </div>
          </el-col>
          <el-col :span="20">
            <div style="text-align: left">
              <span style="font-size: 30px">{{order.username}}</span>
              <span style="font-size: 15px;color: #767676">{{phone}}</span>
              <div style="font-size: 22px"  @click="changeAddr">Send to: {{order.sendTo}}</div>
            </div>
          </el-col>
        </el-row>
      </el-card>
      <br>
      <!--      商品展示-->
      <div v-for="(cartItem,itemId) in item" :key="itemId">
        <el-card shadow="never" style="border-radius: 10px">
          <div style="text-align: left; width: 80%; padding-left: 10%;">
            <!--            productId-->
            <div style="font-size: 20px">
              <span class="product"><i class="el-icon-s-goods"></i>  </span>
              {{cartItem.productId}}
            </div>
            <!--            item-->
            <div style="position: relative">
            <span>
             <el-image :src="cartItem.url" style="width: 100px;height: 100px"></el-image>
            </span>
              <span style="position: absolute; right: 40px;width: 600px;font-size: 20px">
                <el-row>
                  <el-col :span="12">
                     {{cartItem.itemId}}
                    <br>
                    <span style="font-size: 15px;color: #767676;background-color: #e2e2e2;">
                      {{cartItem.text}}
                    </span>
                  </el-col>
                  <el-col :span="12" style="text-align: right;">
                   <span>
                  $ {{cartItem.totalCost}}
                     <br>
                  <span style="color: #767676;font-size: 15px;">
                    x {{cartItem.quantity}}
                  </span>
                    </span>
                  </el-col>
                </el-row>
            </span>
            </div>
          </div>
        </el-card>
        <br>
      </div>
      <div style="padding-left: 400px;">
        <el-link :underline="false"  style="font-size: 20px;color: red" @click="noPay(orderId)">
          If you have checked your order and don't want to pay now, please click here.
        </el-link>
      </div>
    </el-card>

    <!--这是付款的一行，是图钉-->
    <Affix :offset-bottom="-20">
      <el-card shadow="always">
        <el-row :gutter="20">
          <el-col :span="1">
            &nbsp;
          </el-col>
          <el-col :span="7">
            <div class="totalCount">
             You're buying {{order.totalCount}} item(s)
            </div>
          </el-col>
          <el-col :span="8">
            &nbsp;
          </el-col>
          <el-col :span="4">
            <div class="total">
              <span>Total:</span>
              ${{order.subTotal}}
              <br>
              <span style="color: #636363;font-size: 15px">Discount: $0</span>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="pay">
              <el-button type="danger" style="width: 120px;height: 70px;font-size: 40px" @click="pay" :loading="loading">
                Pay
              </el-button>
              <div class="payImg">
                <el-image style="width: 60px; height: 60px;" :src="url2"></el-image>
              </div>
            </div>
          </el-col>
        </el-row>

      </el-card>
    </Affix>
  </div>
</template>


<script>
  import panda from '../../assets/panda1.png'
  import panda2 from '../../assets/panda2.png'

  export default {
    inject:['reload'],
    data() {
      return {
        loading: false,
        url: panda,
        url2: panda2,
        order:[],
        item: [],
      }
    },
    computed: {
      username(){
        return sessionStorage.getItem('username');
      },
      orderId(){
        return sessionStorage.getItem('orderId');
      },
      paid(){
        sessionStorage.removeItem('orderId');
      }
    },
    created() {
      this.getOrder();
      this.getCart();
      this.getPhone();
    },
    methods: {
      //得到订单
      getOrder() {
        this.$axios.get('/api/order/order/' + this.orderId).then(res => {
          if (res.data.status !== 200) {
            return this.$message.error('获取order信息失败')
          }else
            this.order = res.data.data.order;
        }).catch(err=>{
          console.log(err);
        })
      },
      //得到商品
      getCart() {
        this.$axios.get('/api/order/itemShowList/' + this.orderId).then(res => {
          console.log(res.data);
          if (res.data.status !== 200) {
            return this.$message.error('Get item failed')
          }else
          this.item = res.data.data;
        }).catch(err=>{
          console.log(err);
        })
      },
      //得到电话
      getPhone(){
        this.$axios.get('/api/account/user/' + this.username).then(res => {
          console.log(res.data);
          if (res.data.status !== 200) {
            return this.$message.error('Get user failed')
          }else
            this.phone = res.data.data.user.phone;
        }).catch(err=>{
          console.log(err);
        })
      },
      //修改地址
      changeAddr(){
        this.$prompt('Please enter your new address', 'Change Address', {
          confirmButtonText: 'OK',
          cancelButtonText: 'Cancel',
        }).then(({ value }) => {
          this.$axios.put('/api/order/address/' + this.orderId, {"address": value}).then(res => {
            if (res.data.status !== 200) {
              return this.$message.error('Change address failed')
            }else
            {
              this.$message({
                type: 'success',
                message: 'Your new address is: ' + value
              });
              this.reload();
            }

          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'You cancelled'
          });
        });
      },
      //表示已确认并要付钱
      pay(){
        this.$axios.put('/api/order/checkout/' + this.orderId).then(res => {
          console.log(res.data);
          if (res.data.status !== 200) {
            return this.$message.error('Check failed')
          }else {
            this.$message({
              type: 'success',
              message: 'Check successfully'
            });
            this.$router.push('/pay');
            this.reload();
          }
        }).catch(err=>{
          console.log(err);
        })
      },
      //删除这个订单
      deleteOrder(){
        this.$axios.delete('/api/order/order/' + this.orderId).then(res => {
          if (res.data.status !== 200) {
            return this.$message.error('Delete failed')
          }else {
            this.$message({
              type: 'success',
              message: 'Delete successfully'
            });
            this.$router.push('/my/cart');
            sessionStorage.removeItem('orderId');
            this.reload();
          }
        }).catch(err=>{
          console.log(err);
        })
      },
      //不付钱
      noPay(id){
        this.$axios.put('/api/order/checkout/' + id).then(res => {
          console.log(res.data);
          if (res.data.status !== 200) {
            return this.$message.error('Check failed')
          }else {
            this.$message({
              type: 'success',
              message: 'Check successfully'
            });
            this.$router.push('/my/cart');
            sessionStorage.removeItem('orderId');
            this.reload();
          }
        }).catch(err=>{
          console.log(err);
        })


      }


    }
  }
</script>

<style scoped>
  .cart {
    width: 100%;
    height: auto;
    background-color: #447387;
    position: relative;
  }

  .circle {
    width: 50px;
    height: 50px;
    background: #ff7000;
    border-radius: 50px;
    position: relative;
  }

  .no-link {
    width: 50px;
    height: 50px;
    font-size: 40px;
    font-weight: bold;
    color: white;
    position: absolute;
    top: -3px;
    /*z-index: 100;*/
  }

  .product {
    font-size: 20px;
    font-weight: bold;
    color: red;
  }
/*  图钉*/
  .totalCount{
    text-align: left;
    font-size: 20px;
  }
  .total{
    text-align: right;
    font-size: 24px;
    color: red;
  }
  .total span{
    font-size: 20px;
    color: black;
  }
  .pay{
    width: 120px;
    height: 70px;

    position: relative;
  }
  .payImg{
    position: absolute;
    width: 60px;
    height: 60px;

    right: -40px;
    top: 0px;
    z-index: 100;

  }
</style>
