<template>
  <div class="cart">
    <!--    占位-->
    <div style="width: 140px; height: 100px; "></div>
    <div>
      <span style="font-size: 40px;color: #ffffff">Recent Order </span>
      <br>
      <span style="font-size: 20px;color: #ffffff">Order Id:   {{order.orderId}} </span>
      <br>
      <span style="color: #ffffff">{{order.orderDate}}</span>
    </div>
    <div style="position: absolute;">
      <el-image style="width: 160px; height: 150px;left: 400px;top: -120px;z-index:100" :src="url"></el-image>
    </div>
    <div style="position: absolute;">

      <el-input v-model="input" placeholder="To search one order by orderId"
                style=" left: 1010px;top: -50px;z-index:100">
        <el-button slot="append" icon="el-icon-search" @click="getOneOrder(input)"></el-button>
      </el-input>
      <br>
      <el-link :underline="false" type="info" @click="all" style="font-size: 20px; left: 1000px;top: -30px;z-index:100">
        Click here to see all the order
        <i class="el-icon-caret-right"></i>
      </el-link>
    </div>

    <el-card id="money" shadow="never"
             style="width:88%; margin-left: 6%;background-color: #e2e2e2; border-radius: 5px;">
      <!--      是否付钱-->
      <div style="padding-left: 1000px; padding-top: 20px; font-size: 17px">
        <span v-if="isPaid" style="color:#19b525;">Paid</span>
        <span v-else style="color:  red;" @click="toPay(order.orderId)">Not Paid</span>

        <el-button type="danger" round @click="deleteOrder(order.orderId)">Delete</el-button>
      </div>
      <!--      地址-->
      <el-card shadow="never" style="border-radius: 10px">
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
              <div style="font-size: 22px">Send to: {{order.sendTo}}</div>
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
      <!--      cost-->
      <el-card shadow="never" style="border-radius: 10px">
        <div style="font-size: 20px;width: 87%;">
          <el-row>
            <el-col :span="8">
              Total: {{order.totalCount}}item(s)
            </el-col>
            <el-col :span="8">
              Discount:$0
            </el-col>
            <el-col :span="8">
              Total: $ {{order.subTotal}}
            </el-col>
          </el-row>
        </div>
      </el-card>
    </el-card>
  </div>
</template>


<script>
  import panda from '../../../assets/panda1.png'
  import panda2 from '../../../assets/panda2.png'

  export default {
    inject: ['reload'],
    data() {
      return {
        isPaid: true,
        input: '',
        loading: false,
        url: panda,
        url2: panda2,
        id: '',
        order: [],
        item: [],
      }
    },
    computed: {
      username() {
        return sessionStorage.getItem('username');
      },
    },
    created() {
      this.getRecentOrder();
      this.getOrderId();
      this.getPhone();
    },
    methods: {
      //最近一个
      getRecentOrder() {
        this.$axios.get('/api/order/recentOrder/' + this.username).then(res => {
          if (res.data.status !== 200) {
            return this.$message.error('获取order信息失败')
          } else {
            this.order = res.data.data.order;
            this.isPaid = res.data.data.order.paid !== 'N';
          }

        }).catch(err => {
          console.log(err);
        })
      },
      //得到一个
      getOneOrder(id) {
        this.$axios.get('/api/order/order/' + id).then(res => {
          if (res.data.status !== 200) {
            return this.$message.error('获取order信息失败')
          } else {
            this.order = res.data.data.order;
            this.isPaid = res.data.data.order.paid !== 'N';
          }
        }).catch(err => {
          console.log(err);
        })
      },
      //得到电话
      getPhone() {
        this.$axios.get('/api/account/user/' + this.username).then(res => {
          if (res.data.status !== 200) {
            return this.$message.error('Get user failed')
          } else {
            this.phone = res.data.data.user.phone;
          }
        }).catch(err => {
          console.log(err);
        })
      },
      //得到OrderId
      getOrderId() {
        this.$axios.get('/api/order/orderId/' + this.username).then(res => {

          let id = res.data.data.orderId;

          this.getItem(id);
        }).catch(err => {
          console.log(err);
        })
      },
      //展示商品
      getItem(id) {
        this.$axios.get('/api/order/itemShowList/' + id).then(res => {

          if (res.data.status !== 200) {
            return this.$message.error('获取order信息失败')
          } else
            this.item = res.data.data;
        }).catch(err => {
          console.log(err);
        })
      },
      //没付款就可以去付款
      toPay(id) {
        this.$router.push('/checkout');
        // 将返回的orderId注入内存
        sessionStorage.setItem('orderId', id);
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
      //所有
      all() {
        this.$router.push('/my/order/all')
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


</style>
