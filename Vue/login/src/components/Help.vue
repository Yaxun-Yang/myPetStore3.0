<template>
  <div class="all-next">
    <div class='main-body'>
      <div class="category">
        <div class="d_jump" v-for="(category,index) in CategoryList" :key="index">
          <div class="top">
            &nbsp
          </div>
          <i class="el-icon-caret-right"></i> {{category.id}} <i class="el-icon-caret-left"></i>
          <!--          product-->
          <div class="product">
            <div
              v-for="(product, index2) in category.ProductList"
              :key="index2">
              <div>{{product.name}}
                <!--            item-->
                <div class="item">
                  <el-carousel height="200px" arrow="never" indicator-position="outside">
                    <el-carousel-item v-for="(itemList,index3) in product.ItemList" :key="index3">
                      <el-row :gutter="20">
                        <el-col :span="8" v-for="item in itemList.item" :key="item.id">
                          {{item.id}}
                          <el-image class="item-image" :src="item.url"></el-image>

<!--                          id: 'EST-1',-->
<!--                          url: require("@/assets/ham.png"),-->
<!--                          UnitCost: '10',-->
<!--                          Supplier: '1',-->
<!--                          Status: 'P',-->
<!--                          Attribute: 'Spotted Adult Female',-->
                        </el-col>
                      </el-row>
                    </el-carousel-item>
                  </el-carousel>

                </div>
                <!--              item-->
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
        CategoryList: [
          {
            id: 'BIRDS',
            ProductList: [
              {
                id: 'AV-CB-01',
                name: 'Amazon Parrot',
                description: 'Great companion for up to 75 years',
                ItemList: [
                  {
                    index3: 1,
                    item:
                      [{
                        id: 'EST-1',
                        url: require("@/assets/ham.png"),
                        UnitCost: '10',
                        Supplier: '1',
                        Status: 'P',
                        Attribute: 'Spotted Adult Female',
                        rotate: false,
                      }, {
                        id: 'item2',
                        url: require("@/assets/ham.png"),
                        UnitCost: '10',
                        Supplier: '1',
                        Status: 'P',
                        Attribute: 'Spotted Adult Female',
                        rotate: false,
                      }, {
                        id: 'item3',
                        url: require("@/assets/ham.png"),
                        UnitCost: '10',
                        Supplier: '1',
                        Status: 'P',
                        Attribute: 'Spotted Adult Female',
                        rotate: false,
                      }]
                  },
                  {
                    index3: 2,
                    item:
                      [{
                        id: 'item4',
                        url: require("@/assets/ham.png"),
                        UnitCost: '10',
                        Supplier: '1',
                        Status: 'P',
                        Attribute: 'Spotted Adult Female',
                        rotate: false,
                      }, {
                        id: 'item5',
                        url: require("@/assets/ham.png"),
                        UnitCost: '10',
                        Supplier: '1',
                        Status: 'P',
                        Attribute: 'Spotted Adult Female',
                        rotate: false,
                      }, {
                        id: 'item6',
                        url: require("@/assets/ham.png"),
                        UnitCost: '10',
                        Supplier: '1',
                        Status: 'P',
                        Attribute: 'Spotted Adult Female',
                        rotate: false,
                      }]
                  },
                ]
                //  item
              },
              {
                id: 'AV-CB-02',
                name: 'Finch',
                description:
                  'Great companion for up to 75 years',
              }]
            //product1
          },
          {
            id: 'Dogs',
            ProductList: [{
              id: 'AV-CB-01',
              name: 'Amazon Parrot',
              description: 'Great companion for up to 75 years',
              ItemList: [
                {
                  itemList1:
                    [{
                      id: 'EST-1',
                      url: require("@/assets/ham.png"),
                      UnitCost: '10',
                      Supplier: '1',
                      Status: 'P',
                      Attribute: 'Spotted Adult Female',
                      rotate: false,
                    }, {
                      id: 'item2',
                      url: require("@/assets/ham.png"),
                      UnitCost: '10',
                      Supplier: '1',
                      Status: 'P',
                      Attribute: 'Spotted Adult Female',
                      rotate: false,
                    }, {
                      id: 'item3',
                      url: require("@/assets/ham.png"),
                      UnitCost: '10',
                      Supplier: '1',
                      Status: 'P',
                      Attribute: 'Spotted Adult Female',
                      rotate: false,
                    }]
                },
                {
                  itemList2:
                    [{
                      id: 'EST-1',
                      url: require("@/assets/ham.png"),
                      UnitCost: '10',
                      Supplier: '1',
                      Status: 'P',
                      Attribute: 'Spotted Adult Female',
                      rotate: false,
                    }, {
                      id: 'item2',
                      url: require("@/assets/ham.png"),
                      UnitCost: '10',
                      Supplier: '1',
                      Status: 'P',
                      Attribute: 'Spotted Adult Female',
                      rotate: false,
                    }, {
                      id: 'item3',
                      url: require("@/assets/ham.png"),
                      UnitCost: '10',
                      Supplier: '1',
                      Status: 'P',
                      Attribute: 'Spotted Adult Female',
                      rotate: false,
                    }]
                },
              ]
              //  item
            },
              {
                id: 'AV-CB-02',
                name: 'Finch',
                description:
                  'Great companion for up to 75 years',
              }]
            //product2
          },

        ],


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
    methods: {
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
      }
    },
    computed: {},
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

  .item {
    height: 200px;
    padding-left: 20px;
    padding-right: 20px;
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
/*  item*/
  .item-image{
    height: 180px;
    width: 180px;
  }
</style>

