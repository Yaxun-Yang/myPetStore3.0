<template>
  <div class="cart">
    <!--    占位-->
    <div style="width: 140px; height: 100px; "></div>
    <div>
      <span style="font-size: 40px;color: #ffffff">All Order </span>

    </div>
    <div style="position: absolute;">
      <el-image style="width: 160px; height: 150px;left: 400px;top: -80px;z-index:100" :src="url"></el-image>
    </div>
    <div style="position: absolute;">

      <el-link :underline="false" type="info" @click="recent" style="font-size: 20px; left: 1000px;top: -30px;z-index:100">
        <i class="el-icon-caret-left"></i>
        Click here to see the latest order
      </el-link>

    </div>

<!--    order-->
<div v-for="(order,orderId) in orderList" :key="orderId">
  <el-card id="money" shadow="never"
           style="width: 90%;margin-left: 5%;background-color: #e2e2e2; border-radius: 5px; position: relative">
<!--   支付否 -->
    <div style="text-align: center;color: #ffffff;position: absolute;font-size: 20px; left: 1200px;top: 120px;z-index:100">
      <div v-if="order.paid = 'Y' " style="background-color: rgba(25,181,37,0.92); width: 50px;height: 50px; border-radius: 50%; padding-top: 10px;">
        Paid
      </div>
      <div v-else  style="background-color: red; width: 50px;height: 50px; border-radius: 50%; padding-top: 10px;">
         Not paid
      </div>
    </div>
    <div style="position: absolute; left: 1180px;top: 20px;z-index:100">
      <el-button type="danger" round @click="deleteOrder(order.orderId)">Delete</el-button>
    </div>
<!--    id-->
    <span style="font-size: 20px;color: #575757">Order Id:   {{order.orderId}} </span>
    <br>
    <span style="color: #575757">{{order.orderDate}}</span>
    <!--      地址-->
    <el-card shadow="never" style="border-radius: 10px">
      <el-row :gutter="1" style="width: 88%">
        <el-col :span="4">
          <div style="width: 80px;float: right;padding-top: 15px">
            <div class="circle">
              <div class="no-link">
                <i class="el-icon-location-outline"></i>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :span="14">
          <div style="text-align: left">
            <span style="font-size: 30px">{{order.username}}</span>
            <span style="font-size: 15px;color: #767676">{{phone}}</span>
            <div style="font-size: 22px">Send to: {{order.sendTo}}</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="color: #636363; font-size: 20px;"> Click
            <el-link :underline="false" type="success" @click="getItem(order.orderId)">
              here&nbsp;
            </el-link> to see more details
          </div>
        </el-col>
      </el-row>

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


    <!--    cost-->

      <div style="font-size: 20px">
        <el-row>
          <el-col :span="8">
            Total: {{order.totalCount}}item(s)
          </el-col>
          <el-col :span="8">
            Discount:$0
          </el-col>
          <el-col :span="8">
            TotalCost: $
            <span v-if="order.paid = 'Y' ">
                 {{order.subTotal}}
            </span>
            <span v-else>
             0
           </span>
          </el-col>
        </el-row>
      </div>
    </el-card>


  </el-card>
  <br>

</div>
  </div>
</template>


<script>
  import panda from '../../../assets/panda1.png'
  import panda2 from '../../../assets/panda2.png'

  export default {
    inject: ['reload'],
    data() {
      return {
        loading: false,
        url: panda,
        url2: panda2,
        orderList: [],
        item: [],
      }
    },
    computed: {
      username() {
        return sessionStorage.getItem('username');
      },

    },
    created() {
      this.getAllOrder();
      this.getPhone();
      this.getCart();
    },
    methods: {
      recent(){
        this.$router.push('/my/order')
      },
      //得到商品
      getItem(id) {
        this.$axios.get('/api/order/itemShowList/' + id).then(res => {
          if (res.data.status !== 200) {
            return this.$message.error('Get item failed')
          }else{
            this.item = res.data.data;

          }
        }).catch(err=>{
          console.log(err);
        })
      },
      //得到所有order
      getAllOrder() {
        this.$axios.get('/api/order/orderList/' + this.username).then(res => {
          if (res.data.status !== 200) {
            return this.$message.error('获取order信息失败')
          } else {
            this.orderList = res.data.data.orderList;

          }
        }).catch(err => {
          console.log(err);
        })
      },

      //得到电话
      getPhone(){
        this.$axios.get('/api/account/user/' + this.username).then(res => {
          if (res.data.status !== 200) {
            return this.$message.error('Get user failed')
          }else {
            this.phone =  res.data.data.user.phone;
          }
        }).catch(err=>{
          console.log(err);
        })
      },
    //删除订单
      deleteOrder(id){
        const confirmResult = this.$confirm('Are you sure you want to delete it permanently?', 'message', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.$axios.delete('/api/order/order/' + id).then(res=>{
            if (res.data.status !== 200) {
              return this.$message.error('Delete failed!')
            }
            this.$message({
              type: 'success',
              message: 'Delete successfully!'
            });
            this.$router.go(0)
          }).catch(err=>{
            console.log(err);
            this.$message({
              type: 'error',
              message: 'Error!'
            });
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Deletion have been cancelled.'
          });
        });
      },

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


</style>
