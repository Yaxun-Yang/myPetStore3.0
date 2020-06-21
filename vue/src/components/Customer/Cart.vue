<template>
  <div class="cart">
    <el-image style="width: 150px; height: 100px;margin-top: 86px" :src="url1"></el-image>
    <el-table
      id="money"
      :loading="loadingTable"
      ref="checkedCart"
      :data="itemShowList"
      tooltip-effect="dark"
      style="width: 90%;left: 5%"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" :selectable="checkSelectable" width="55">
      </el-table-column>
      <el-table-column label="" prop="url" width="90">
        <!--加入下面的内容,scope.row代表这一行,img是显示这一行的哪个字段-->
        <template slot-scope="scope">
          <img :src="scope.row.url" alt="" style="width: 80px;height: 80px">
        </template>
      </el-table-column>
      <el-table-column label="Item Id" prop="itemId" width="100"></el-table-column>
      <el-table-column label="Product ID" prop="productId"></el-table-column>
      <el-table-column label="Description" prop="text"></el-table-column>
      <el-table-column label="In Stock" prop="inStock"></el-table-column>
      <el-table-column label="Quantity" prop="quantity">
        <template slot-scope="scope">
          <el-input-number v-model="scope.row.quantity"
                           controls-position="right"
                           @change="handleChange"
                           :min="1" :max="50" style="width: 100px">
          </el-input-number>
        </template>
      </el-table-column>
      <el-table-column label="List Price" prop="price"></el-table-column>
      <el-table-column label="Total Cost" prop="totalCost">
        <template slot-scope="scope">
          {{ (scope.row.totalCost = scope.row.quantity *scope.row.price) }}
        </template>
      </el-table-column>
      <el-table-column label="Remove" width="130px">
        <template slot-scope="scope">
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeById(scope.row.itemId)"></el-button>
        </template>
      </el-table-column>
      <!--  你的购物车是空的 快去剁手吧！ -->
      <template slot="empty">
        <div class="noData">Your cart is empty. Click <a @click="commodity">here</a> to see more items</div>
      </template>
    </el-table>
    <!--这是付款的一行，是图钉-->
    <Affix :offset-bottom="0">
      <el-card shadow="always">
        <el-row :gutter="20">
          <el-col :span="1">
            &nbsp;
          </el-col>
          <el-col :span="7">
            <div class="totalcount">
              {{totalCount}}
            </div>
          </el-col>
          <el-col :span="8">
            &nbsp;
          </el-col>
          <el-col :span="4">
            <div class="total">
              <span>Total:</span>
              ${{subTotal}}
              <br>
              <span style="color: #636363;font-size: 15px">Discount: $0</span>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="pay">
              <el-button type="danger" style="width: 120px;height: 70px;font-size: 30px" @click="checkout"
                         :loading="loading"
                         v-bind:disabled="total < 1">
                Check
              </el-button>
              <div class="payImg">
                <el-image style="width: 60px; height: 60px;" :src="url2"></el-image>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-card>
    </Affix>
    <br>
    <div style="height: 250px; background-color: white;">
      <div style="font-size: 20px;color: #447387">Guess You Like</div>
      <div class="item">
        <el-carousel height="200px" arrow="never" indicator-position="outside" :autoplay="false">
          <el-carousel-item v-for="(itemList,index5) in ItemList" :key="index5">
            <el-row :gutter="20">
              <el-col :span="6" v-for="item in itemList.item" :key="item.id">
                <div class="img">
                  <img :src="item.url" alt="">
                  <div class="overlay-thumb">
                    <div class="details">
                      <span class="title">{{item.id}}</span>
                      <span class="info">
                                  <!-- 这里有个假的"喜欢人数"-->
                                  $&nbsp;{{item.UnitCost}}&nbsp;♡&nbsp;50
                                <span class="more">
                                  {{item.Attribute}}
                                  <br>
                                  Supplier:{{item.Supplier}}
                                  <br>
                                  Status:{{item.Status}}
                                  <br>
                                  <el-button type="warning"
                                             icon="el-icon-shopping-cart-2"
                                             circle
                                             style="float: right;font-size: 22px"
                                             @click="addToCart(item.id)">
                                  </el-button>
                                </span>
                                </span>
                      <!--  周围两根线-->
                    </div>
                    <span class="btnBefore"></span>
                    <span class="btnAfter"></span>
                  </div>
                </div>
              </el-col>
            </el-row>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        //加载crt
        loadingTable: true,
        //checkout
        loading: false,
        url1: require("@/assets/cart-panda.png"),
        url2: require("@/assets/panda2.png"),
        itemShowList: [],
        checkedCart: [],
        //猜你喜欢
        ItemList: [
          {
            index3: 1,
            item:
              [{
                id: 'EST-1',
                url: '',
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Spotted Adult Female',
                rotate: false,
              }, {
                id: 'item2',
                url: '',
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Venomless',
                rotate: false,
              }, {
                id: 'item3',
                url: 'http://qbyy9dziv.bkt.clouddn.com/login.JPG',
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Large',
                rotate: false,
              }, {
                id: '4',
                url: 'http://qbyy9dziv.bkt.clouddn.com/login.JPG',
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Large',
                rotate: false,
              }]
          },
          {
            index3: 2,
            item:
              [{
                id: '5',
                url: require("@/assets/item2.jpg"),
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Spotted Adult Female',
                rotate: false,
              }, {
                id: '6',
                url: require("@/assets/item3.jpg"),
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Spotted Adult Female',
                rotate: false,
              }, {
                id: '7',
                url: require("@/assets/item4.jpg"),
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Spotted Adult Female',
                rotate: false,
              }, {
                id: '8',
                url: require("@/assets/item4.jpg"),
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Spotted Adult Female',
                rotate: false,
              }
              ]
          },
        ],

      };
    },
    computed: {
      //获取用户
      name() {
        return sessionStorage.getItem('username');
      },
      //计算属性得出总价
      subTotal() {
        let allCost = 0;
        for (var index in this.checkedCart) {
          let single = this.checkedCart[index];
          allCost += single.totalCost;
        }
        return allCost
      },
      //买了多少件 左
      totalCount() {
        let checked = 0;
        for (var index in this.checkedCart) {
          let single = this.checkedCart[index];
          checked += single.quantity;
        }
        if (checked === 0 || checked === 1) {
          return "You choose " + checked + " item.";
        } else {
          return "You choose " + checked + " items.";
        }
      },
      //右边，单独的数字
      total() {
        let checked = 0;
        for (var index in this.checkedCart) {
          let single = this.checkedCart[index];
          checked += single.quantity;
        }
        return checked;
      },
    },
    created() {
      this.getCart();
    },
    methods: {
      //购物车是空的
      commodity() {
        this.$router.push('/commodity')
      },
      getCart() {
        //开始加载表
        this.loadingTable = true;
        this.$axios.get('/api/cart/itemShowList/' + this.name).then(res => {
          //不转了
          console.log(res);
          this.loadingTable = false;
          if (res.data.status !== 200) {
            return this.$message.error('获取cart信息失败')
          } else
            this.itemShowList = res.data.data;
          console.log(this.itemShowList);
        }).catch(err => {
          console.log(err)
        })
      },
      //这里是剁手了几件
      handleChange(value) {
        console.log('You buy ' + value);
      },
      //没货就不能选了
      checkSelectable(row) {
        return row.inStock === 'Y'
      },
      //删除
      removeById(id) {
        //弹窗
        const confirmResult = this.$confirm('Are you sure you want to delete it permanently?', 'message', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.$axios.delete('/api/cart/cartItem/'+ this.name + '/' + id).then(res=>{
            if (res.data.status !== 200) {
              return this.$message.error('Delete failed!')
            }
            this.$message.success('Delete successfully!');
            // 刷新列表
            this.getCart();
          }).catch(err=>{
            console.log(err);
            this.$message.error('Delete failed!');
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Deletion have been cancelled.'
          });
        });
      },
      //选中的
      handleSelectionChange(val) {
        this.checkedCart = val;
        console.log('You choose');
        console.log(this.checkedCart);
      },
      // 前往确认订单
      checkout() {
        this.loading = true;
        this.$axios.post('/api/order/order/' + this.name, this.checkedCart).then(res => {
          this.loading = false;
          console.log(res);
          if (res.data.status !== 200) {
            return this.$message.error('Checkout failed')
          } else
              this.$router.push('/checkout');
          // 将返回的orderId注入内存
          sessionStorage.setItem('orderId', res.data.data.orderId);
        }).catch(err => {
          console.log(err)
        })
      },

      //猜你喜欢
      addToCart(itemId, username) {
        if (!username) {
          this.$message({
            message: 'Please login in first',
            type: 'warning'
          });
          this.$router.push('/login');
        } else {
          this.$axios.post('/api/cart/cartItem/' + username + '/' + itemId).then(res => {
            console.log(res);
            if (res.data.status !== 200) {
              return this.$message.error('Add failed!')
            } else {
              this.$message({
                message: 'Add to cart successfully',
                type: 'success'
              });
            }
          }).catch(err => {
            console.log(err);
            return this.$message.error('Add failed!');
          })
        }
      }
      ,


    },

  }
</script>

<style scoped>
  .cart {
    width: 100%;
    height: auto;
    background-color: #447387;
  }

  .noData {
    font-size: 20px;
  }

  .totalcount {
    text-align: left;
    font-size: 20px;
  }

  .total {
    text-align: right;
    font-size: 24px;
    color: red;
  }

  .total span {
    font-size: 20px;
    color: black;
  }

  .pay {
    width: 120px;
    height: 70px;

    position: relative;
  }

  .payImg {
    position: absolute;
    width: 60px;
    height: 60px;

    right: -40px;
    top: 0px;
    z-index: 100;

  }
</style>
