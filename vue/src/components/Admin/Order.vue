<template>
  <div>
    <el-card shadow="hover">
      <div class="head">
        <!-- 搜索 -->
        <div>
          <el-input placeholder="Please enter" v-model="input" class="input-with-select" style="width: 450px">
            <el-select v-model="select" slot="prepend" placeholder="Please Choose" style="width: 150px;">
              <el-option label="By username" value="username"></el-option>
              <el-option label="By orderId" value="orderId"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-search" @click="getOrderList(select,input)"></el-button>
          </el-input>
        </div>
        <!-- 面包屑-->
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/home' }">Admin</el-breadcrumb-item>
          <el-breadcrumb-item>Order</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <el-divider><i class="el-icon-s-order"></i></el-divider>

      <!--这是按username的全部order-->
      <div v-if="allOrder" class="cart">
        <!--    占位-->
        <div style="width: 140px; height: 100px; "></div>
        <div>
          <span style="font-size: 40px;color: #ffffff">All Order </span>
        </div>
        <div style="position: absolute;">
          <el-image style="width: 160px; height: 150px;left: 400px;top: -80px;z-index:100" :src="url"></el-image>
        </div>
        <div style="position: absolute;">
        </div>
        <!--    order-->
        <div v-for="(order,orderId) in orderList" :key="orderId">
          <el-card id="money" shadow="never"
                   style="width: 90%;margin-left: 5%;background-color: #e2e2e2; border-radius: 5px; position: relative">
            <!--   支付否 -->
            <div
              style="text-align: center;color: #ffffff;position: absolute;font-size: 20px; left: 1200px;top: 120px;z-index:100">
              <div v-if="order.paid = 'Y' "
                   style="background-color: rgba(25,181,37,0.92); width: 50px;height: 50px; border-radius: 50%; padding-top: 10px;">
                Paid
              </div>
              <div v-else
                   style="background-color: red; width: 50px;height: 50px; border-radius: 50%; padding-top: 10px;">
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
                    <span style="font-size: 15px;color: #767676">{{getPhone(order.username)}}</span>
                    <div style="font-size: 22px">Send to: {{order.sendTo}}</div>
                  </div>
                </el-col>
                <el-col :span="6">
                  <div style="color: #636363; font-size: 20px;"> Click
                    <el-link :underline="false" type="success" @click="getItem(order.orderId)">
                      here&nbsp;
                    </el-link>
                    to see more details
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
<!--all结束-->
    </el-card>
    <!--  添加对话框-->
    <el-dialog title="Add" :visible.sync="addDialogVisible" @close="">
      <el-form :model="addForm" status-icon :rules="formRules" ref="addFormRef" label-width="100px">
        <el-form-item label="Order Id" prop="id">
          <el-input v-model="addForm.id"></el-input>
        </el-form-item>
        <el-form-item label="User Id" prop="userid">
          <el-input v-model="addForm.userid"></el-input>
        </el-form-item>
        <el-form-item label="Order Date" prop="orderDate">
          <el-input v-model="addForm.orderDate"></el-input>
        </el-form-item>
        <el-form-item label="Ship  Addr" prop="shipaddr">
          <el-input v-model="addForm.shipaddr"></el-input>
        </el-form-item>
        <el-form-item label="Ship to" prop="shiptoname">
          <el-input v-model="addForm.shiptoname"></el-input>
        </el-form-item>
        <el-form-item label="Bill Address" prop="billaddr">
          <el-input v-model="addForm.billaddr"></el-input>
        </el-form-item>
        <el-form-item label="Bill to" prop="billtoname">
          <el-input v-model="addForm.billtoname"></el-input>
        </el-form-item>
        <el-form-item label="Courier" prop="courier">
          <el-input v-model="addForm.courier"></el-input>
        </el-form-item>
        <el-form-item label="Total Price" prop="totalprice">
          <el-input v-model="addForm.totalprice"></el-input>
        </el-form-item>
        <el-form-item label="Credit Card" prop="creditcard">
          <el-input v-model="addForm.creditcard"></el-input>
        </el-form-item>
        <el-form-item label="Express Date" prop="exprdate">
          <el-input v-model="addForm.exprdate"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="addUser">Confirm</el-button>
      </div>
    </el-dialog>
    <!--  修改对话框-->
    <el-dialog title="Edit" :visible.sync="editDialogVisible" @close="">
      <el-form :model="editForm" status-icon :rules="formRules" ref="editFormRef" label-width="100px">
        <el-form-item label="Order Id">
          <el-input v-model="editForm.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="User Id" prop="userid">
          <el-input v-model="editForm.userid"></el-input>
        </el-form-item>
        <el-form-item label="Order Date" prop="orderDate">
          <el-input v-model="editForm.orderDate"></el-input>
        </el-form-item>
        <el-form-item label="Ship  Addr" prop="shipaddr">
          <el-input v-model="editForm.shipaddr"></el-input>
        </el-form-item>
        <el-form-item label="Ship to" prop="shiptoname">
          <el-input v-model="editForm.shiptoname"></el-input>
        </el-form-item>
        <el-form-item label="Bill Address" prop="billaddr">
          <el-input v-model="editForm.billaddr"></el-input>
        </el-form-item>
        <el-form-item label="Bill to" prop="billtoname">
          <el-input v-model="editForm.billtoname"></el-input>
        </el-form-item>
        <el-form-item label="Courier" prop="courier">
          <el-input v-model="editForm.courier"></el-input>
        </el-form-item>
        <el-form-item label="Total Price" prop="totalprice">
          <el-input v-model="editForm.totalprice"></el-input>
        </el-form-item>
        <el-form-item label="Credit Card" prop="creditcard">
          <el-input v-model="editForm.creditcard"></el-input>
        </el-form-item>
        <el-form-item label="Express Date" prop="exprdate">
          <el-input v-model="editForm.exprdate"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="editOrder">Confirm</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import panda from '../../assets/panda1.png'
  import panda2 from '../../assets/panda2.png'

  export default {
    data() {
      return {
        input: '',
        select: '',
        url: panda,
        url2: panda2,
        allOrder: false,
        oneOrder: false,
        orderList: [],
        item: [],
        // 添加新 对话框的显示隐藏
        addDialogVisible: false,
        // 新 数据表单
        addForm: {},
        //添加的验证规则
        formRules: {},
        // 修改 对话框的显示与隐藏
        editDialogVisible: false,
        //查询到的用户信息
        editForm: {}
      }
    },
    created() {

    },
    methods: {
      //按 username 得到所有order
      getOrderList(select, input) {
        if (select === 'username') {

          this.allOrder = true;
            this.$axios.get('/api/order/orderList/' + input).then(res => {
              console.log(res)
              if (res.data.status !== 200) {
                return this.$message.error('获取order信息失败')
              } else {
                this.orderList = res.data.data.orderList;
              }
            }).catch(err => {
              console.log(err);
            })
        } else {
          this.oneOrder = true,
            this.$axios.get('/api/order/order/' + input).then(res => {
              if (res.data.status !== 200) {
                return this.$message.error('获取order信息失败')
              } else {
                this.order = res.data.data.order;
                this.isPaid = res.data.data.order.paid !== 'N';
              }
            }).catch(err => {
              console.log(err);
            })
        }
      },
      //得到商品
      getItem(id) {
        this.$axios.get('/api/order/itemShowList/' + id).then(res => {
          if (res.data.status !== 200) {
            return this.$message.error('Get item failed')
          } else {
            this.item = res.data.data;

          }
        }).catch(err => {
          console.log(err);
        })
      },
      //得到电话
      getPhone(username) {
        this.$axios.get('/api/account/user/' + username).then(res => {
          if (res.data.status !== 200) {
            return this.$message.error('Get user failed')
          } else {
            return res.data.data.user.phone;
          }
        }).catch(err => {
          console.log(err);
        })
      },

      // 添加
      addUser() {
        this.$refs.addFormRef.validate(async valid => {
          if (!valid) return;
          // 可以发起add用户的网络请求
          const {data: res} = await this.$axios.post('order', this.addForm);
          if (res.meta.status !== 201) {
            this.$message.error('Add Order failed!');
          }
          this.$message.success('Add Order successfully');
          // 隐藏对话框
          this.addDialogVisible = false;
          // 刷新列表
          this.getOrderList();
        })
      },

      // 编辑
      editOrder() {

      },


      //删除订单
      deleteOrder(id) {
        const confirmResult = this.$confirm('Are you sure you want to delete it permanently?', 'message', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.$axios.delete('/api/order/order/' + id).then(res => {
            if (res.data.status !== 200) {
              return this.$message.error('Delete failed!')
            }
            this.$message({
              type: 'success',
              message: 'Delete successfully!'
            });
            this.$router.go(0)
          }).catch(err => {
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
