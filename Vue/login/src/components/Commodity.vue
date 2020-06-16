<template>
  <div class="all-next">
    <div class='main-body'>

      <div class="category">
        <div class="d_jump" v-for="(category,index) in CategoryList" :key="index">
          <div class="top">
            &nbsp
          </div>
          <i class="el-icon-caret-right"></i>{{category.id}}<i class="el-icon-caret-left"></i>
          <!--          product-->
          <div class="product">
            <div
              v-for="(product, index2) in category.ProductList"
              :key="index2">
              <div>{{product.name}}
                <!--            item-->
                <div class="item">
                  <el-carousel height="200px" arrow="never" indicator-position="outside" :autoplay="false">
                    <el-carousel-item v-for="(itemList,index3) in product.ItemList" :key="index3">
                      <el-row :gutter="20">
                        <el-col :span="8" v-for="item in itemList.item" :key="item.id">
                          <div class="img">
                            <img :src="item.url" alt="">
                            <div class="overlay-thumb">
                              <div class="details">
                                <span class="title">{{item.id}}</span>
                                <span class="info">
                                  <!-- 这里有个假的"喜欢人数"    {{item.like}}-->
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
                <!--              item结束-->
              </div>
              <br>
            </div>
          </div>
          <!--              product结束-->
        </div>
        <!-- 左侧-基于element-ui的step组件封装 -->
        <div class="step">
          <left-step :steps="steps"></left-step>
        </div>
      </div>
    </div>
    <div class="top">
      &nbsp
    </div>
  </div>
</template>

<script>
  import leftSteps from './Steps'
  export default {
    name: 'app',
    data() {
      return {
        CategoryList:[],
        steps: {
          active: 0,
          step: [
            {title: 'BIRDS'},
            {title: 'CAT'},
            {title: 'DOG'},
            {title: 'FISH'},
            {title: 'REPTILES'},
            {title: 'HAMSTER'}
          ]
        }
      }
    },
    mounted: function () {
      this.$nextTick(function () {
        window.addEventListener('scroll', this.onScroll)
      })
    },
    created(){
      this.getCategoryList();
    },
    methods: {
      getCategoryList() {
        let _this = this;
        this.$axios.get('http://localhost:3000/CategoryList').then(function (res) {
          if (res.status !== 200) {
            _this.$message({
              type: 'warning',
              message: "aaa"
            });
          }
          _this.CategoryList = res.data;
          console.log(res);
        })
      },
      onScroll() {
        let scrolled = document.documentElement.scrollTop || document.body.scrollTop
        // 手动获取到各个锚点的距离
        if (scrolled >= 3000) {
          this.steps.active = 5
        } else if (scrolled < 3000 && scrolled >= 2400) {
          this.steps.active = 4
        } else if (scrolled < 2400 && scrolled >= 1800) {
          this.steps.active = 3
        } else if (scrolled < 1800 && scrolled >= 1200) {
          this.steps.active = 2
        } else if (scrolled < 1200 && scrolled >= 600) {
          this.steps.active = 1
        } else {
          this.steps.active = 0
        }
      },
      async addToCart(id) {
        // if(user == false){
        //   this.$router.push('/login');
        //     this.$message({
        //       message: 'Please login in first',
        //       type: 'warning'
        //     });
        // }
        // else{
        const {data: res} = await this.$axios.put('item/' + id);
        if (res.meta.status !== 200) {
          return this.$message.error('Add failed!')
        }
          this.$message({
            message: 'Add to cart successfully',
            type: 'success'
          });
        // }

      },
    },
    computed:{
      user(){
        return this.$store.state.user
      }
    },
    components: {
      'left-step': leftSteps
    }
  }
</script>

<style scoped>
  .top {
    height: 120px;
  }

  .product {
    height: 550px;
    background-color: #ffffff;
    color: #636363;
    font-size: 20px;
    align-items: center;
  }


  .main-body {
    padding: 0 180px;
    margin: 0 auto;

  }

  .d_jump {
    height: 600px;
    font-size: 30px;
    color: #ffffff;
    background-color: transparent;
    /*background-color: rgba(255, 255, 255, 0.90);*/

    text-align: center;
    /*border-bottom: 1px solid #333;*/
  }

  .step {
    position: fixed;
    left: 25px;
    top: 100px;
  }



</style>

