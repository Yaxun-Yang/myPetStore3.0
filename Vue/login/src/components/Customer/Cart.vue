<template>
  <div class="cart">

    <el-image style="width: 150px; height: 100px;margin-top: 86px" :src="url1"></el-image>
    <el-table
      id="money"
      :loading="loading"
      ref="checkedCart"
      :data="cartList"
      tooltip-effect="dark"
      style="width: 90%;left: 5%"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" :selectable="checkSelectable" width="55">
      </el-table-column>
      <el-table-column label="" prop="ItemUrl" width="90">
        <!--加入下面的内容,scope.row代表这一行,img是显示这一行的哪个字段-->
        <template slot-scope="scope">
          <img :src="scope.row.ItemUrl" alt="" style="width: 80px;height: 80px">
        </template>
      </el-table-column>
      <el-table-column label="User Id" prop="ItemID" width="100"></el-table-column>
      <el-table-column label="Product ID" prop="ProductID"></el-table-column>
      <el-table-column label="Description" prop="Description"></el-table-column>
      <el-table-column label="In Stock" prop="InStock"></el-table-column>
      <el-table-column label="Quantity" prop="Quantity">
        <template slot-scope="scope">
          <el-input-number v-model="scope.row.Quantity"
                           controls-position="right"
                           @change="handleChange"
                           :min="1" :max="50" style="width: 100px">
          </el-input-number>
        </template>
      </el-table-column>
      <el-table-column label="List Price" prop="ListPrice"></el-table-column>
      <el-table-column label="Total Cost" prop="TotalCost">
        <template slot-scope="scope">
          {{ (scope.row.TotalCost = scope.row.Quantity *scope.row.ListPrice) }}
        </template>
      </el-table-column>
      <el-table-column label="Remove" width="130px">
        <template slot-scope="scope">
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeById(scope.row.id)"></el-button>
        </template>
      </el-table-column>

      <!--  你的购物车是空的 快去剁手吧！ -->
      <template slot="empty">
        <div class="noData" >Your cart is empty. Click <a @click="commodity">here</a> to see more items</div>
      </template>

    </el-table>


    <Affix :offset-bottom="10">
      <el-card shadow="always">
        <el-row :gutter="20">
          <el-col :span="1">
            &nbsp
          </el-col>
          <el-col :span="7">
            <div class="count">
              {{count}}
            </div>
          </el-col>
          <el-col :span="8">
            &nbsp
          </el-col>
          <el-col :span="4">
            <div class="total">
              <span>Total:</span>
                ${{total}}
              <br>
              <span style="color: #636363;font-size: 15px">Discount: $0</span>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="pay">
              <el-button type="danger" style="width: 120px;height: 70px;font-size: 40px" @click="pay" :loading="loading"
                         v-bind:disabled = "total < 1">
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
<br>
    <div style="height: 250px; background-color: white;">
      <div style="font-size: 20px;color: #447387">Guess You Like</div>
      <div class="item">
        <el-carousel height="200px" arrow="never" indicator-position="outside" :autoplay="false">
          <el-carousel-item v-for="(itemList,index3) in ItemList" :key="index3">
            <el-row :gutter="20">
              <el-col :span="6" v-for="item in itemList.item" :key="item.id">
                <div class="img">
                  <img :src="item.url" alt="">
                  <div class="overlay-thumb">
                    <div class="details">
                      <span class="title">{{item.id}}</span>
                      <span class="info">
                                  <!-- 这里有个假的"喜欢人数"-->
                                  $&nbsp{{item.UnitCost}}&nbsp♡&nbsp50
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
        //选中一个以上才能结账

        loading: false,
        url1: require("@/assets/cart-panda.png"),
        url2: require("@/assets/panda2.png"),
        cartList: [
          {
            ItemUrl: require("@/assets/item1.jpg"),
            ItemID: 'EST-1',
            ProductID: 'FI-SW-01',
            Description: 'Large',
            InStock: 'Y',
            Quantity: 1,
            ListPrice: 10,
            TotalCost: 10,
          },
          {
            ItemUrl: require("@/assets/item2.jpg"),
            ItemID: 'EST-2',
            ProductID: 'FI-SW-01',
            Description: 'Large',
            InStock: 'Y',
            Quantity: 1,
            ListPrice: 10,
            TotalCost: 20,
          },
          {
            ItemUrl: require("@/assets/item2.jpg"),
            ItemID: 'EST-2',
            ProductID: 'FI-SW-01',
            Description: 'Large',
            InStock: 'Y',
            Quantity: 1,
            ListPrice: 10,
            TotalCost: 20,
          },
          {
            ItemUrl: require("@/assets/item2.jpg"),
            ItemID: 'EST-2',
            ProductID: 'FI-SW-01',
            Description: 'Large',
            InStock: 'Y',
            Quantity: 1,
            ListPrice: 10,
            TotalCost: 20,
          },
          {
            ItemUrl: require("@/assets/item2.jpg"),
            ItemID: 'EST-2',
            ProductID: 'FI-SW-01',
            Description: 'Large',
            InStock: 'Y',
            Quantity: 1,
            ListPrice: 10,
            TotalCost: 20,
          },
          {
            ItemUrl: require("@/assets/item2.jpg"),
            ItemID: 'last-2',
            ProductID: 'FI-SW-01',
            Description: 'Large',
            InStock: 'Y',
            Quantity: 1,
            ListPrice: 10,
            TotalCost: 20,
          }
        ],
        checkedCart: [],
        ItemList: [
          {
            index3: 1,
            item:
              [{
                id: 'EST-1',
                url: require("@/assets/item3.jpg"),
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Spotted Adult Female',
                rotate: false,
              }, {
                id: 'item2',
                url: require("@/assets/item2.jpg"),
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Venomless',
                rotate: false,
              }, {
                id: 'item3',
                url: require("@/assets/item1.jpg"),
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Large',
                rotate: false,
              }, {
                id: 'item3',
                url: require("@/assets/item1.jpg"),
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
                id: 'item4',
                url: require("@/assets/item2.jpg"),
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Spotted Adult Female',
                rotate: false,
              }, {
                id: 'item5',
                url: require("@/assets/item3.jpg"),
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Spotted Adult Female',
                rotate: false,
              }, {
                id: 'item6',
                url: require("@/assets/item4.jpg"),
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Spotted Adult Female',
                rotate: false,
              }, {
                id: 'item6',
                url: require("@/assets/item4.jpg"),
                UnitCost: '10',
                Supplier: '1',
                Status: 'P',
                Attribute: 'Spotted Adult Female',
                rotate: false,
              }]
          },
        ],
        //////////////////////////////////            记得改
        loading: false
      };
    },

    methods: {
      commodity(){
        this.$router.push('/commodity')
      },
      change(status) {
        this.$Message.info(`Status: ${status}`);
      },
      async getCartList() {
        const {data: res} = await this.$axios.get('cart');
        if (res.meta.status !== 200) {
          return this.$message.error('获取cart信息失败')
        }
        this.cartList = res.data.cartList;
        //不转了
        this.loading = false;
        console.log(res);
      },
      //这里是剁手了几件
      handleChange(value) {
        console.log('You buy ' + value);
      },
      //没货就不能选了
      checkSelectable(row) {
        return row.InStock == 'Y'
      },
      //删除
      async removeById(id) {
        //弹窗
        const confirmResult = await this.$confirm('Are you sure you want to delete it permanently?', 'message', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: 'Delete successfully!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Deletion have been cancelled.'
          });
        });
        const {data: res} = await this.$axios.delete('cart/' + id);
        if (res.meta.status !== 200) {
          return this.$message.error('Delete failed!')
        }
        this.$message.success('Delete successfully!');
        // 刷新列表
        this.getCartList();
      },
      handleSelectionChange(val) {
        this.checkedCart = val;
      },
      // 结账
      pay() {
        this.loading = true;
        this.$router.push('/checkout');

      },

      //猜你喜欢
      async addToCart(id) {
        const {data: res} = await this.$axios.put('item/' + id);
        if (res.meta.status !== 200) {
          return this.$message.error('Add failed!')
        }
        this.$message({
          message: 'Add to cart successfully',
          type: 'success'
        });
      },


    },
    computed: {
      //计算属性得出总价
      total() {
        let totalCost = 0;
        for (var index in this.checkedCart) {
          let single = this.checkedCart[index];
          totalCost += single.TotalCost;
        }
        return totalCost
      },
      count(){
        let checked = 0;
        for (var index in this.checkedCart) {
          let single = this.checkedCart[index];
          checked += single.Quantity;
        }
        if(checked === 0 || checked === 1 ){
          return "You choose " + checked + " item." + this.checkout;
        }else{
          return "You choose " + checked + " items."+ this.checkout;
        }
      },

      },
  }
</script>

<style scoped>
  .cart {
    width: 100%;
    height: auto;
    background-color: #447387;
  }
  .noData{
    font-size: 20px;
  }
  .count{
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
