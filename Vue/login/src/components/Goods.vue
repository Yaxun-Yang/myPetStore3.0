<template>
  <div>
    <div class="head">
      <!-- 搜索 -->
      <el-row :gutter="20">
        <el-col :span="18">
          <el-input placeholder="Please enter ID" clearable></el-input>
        </el-col>
        <el-col :span="6">
          <el-button type="primary" icon="el-icon-search"></el-button>
        </el-col>
      </el-row>
      <!-- 面包屑-->
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin' }">Main</el-breadcrumb-item>
        <el-breadcrumb-item>Goods</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <br>
    <div class="content">
      <el-tabs
        v-model="CategoryListValue"
        type="card"
        editable
        @edit="handleTabsEdit">
        <el-tab-pane
          :key="index"
          v-for="(category, index) in CategoryList"
          :label="category.id"
          :name="category.name"
        >
          <!--            category展示-->
          <span class="title">{{category.name}}</span>
          <el-card shadow="hover"
                   v-for="(product, index2) in category.ProductList"
                   :key="index2"
                   class="product">
            <!--            product展示-->
            <span class="subtitle">{{product.name}}</span>
            <div class="desciption">
              ✔ID:<span>「{{product.id}}」</span>
              <br>
              <span>✔{{product.description}}</span>
            </div>
            <br>
            <!--            item展示-->
            <div class="item">
              <el-card shadow="hover"
                       v-for="(item, index3) in product.itemList"
                       :key="index3"
                       class="-item-content">
                <el-row :gutter="5">
                  <el-col :span="16">
                    <el-image class="image" :src="item.url"></el-image>
                  </el-col>
                  <el-col :span="8">
                    <span class="subsubtitle">{{item.id}}</span>
                    <div class="desciption">
                      <!-- 正面-->
                      <div class="show" :class="[item.rotate? 'go' : 'no' ]">
                        <br>
                        <!-- 这里有个假的"喜欢人数"-->
                        <span style="font-size: 20px">$&nbsp{{item.UnitCost}}&nbsp♡&nbsp50</span>
                        <br> <br>
                        <span class="attr">✔{{item.Attribute}}</span>
                        <br> <br>
                        <span class="ProductId">✔Supplier:</span>
                        <span>{{item.Supplier}}</span>
                        <br> <br>
                        <span class="ProductId">✔Status:</span>
                        <span>{{item.Status}}</span>
                      </div>
                      <div>
                        <el-button @click="start(item,item.id)" type="text" class="button">Edit</el-button>
                        <el-button @click="deleteItem(item.id)" type="text" class="button">Delete</el-button>
                      </div>
                      <!--  反面-->
                      <div class="change" :class="[item.rotate? 'no' : 'go' ]">
                        <el-form :model="editItemForm" status-icon ref="editItemFormRef" :label-position="left"
                                 label-width="80px">
                          <br>
                          <el-form-item label="Picture">
                            <el-upload
                              class="upload-demo"
                              action="#"
                              multiple
                              :limit="1"
                              :on-exceed="handleExceed"
                              :http-request="httpRequest">
                              <el-button size="small" type="primary">Click to upload</el-button>
                            </el-upload>
                          </el-form-item>
                          <el-form-item label="Item Id">
                            <el-input v-model="editItemForm.id" disabled></el-input>
                          </el-form-item>
                          <el-form-item label="Unit Cost" prop="UnitCost">
                            <el-input v-model="editItemForm.UnitCost"></el-input>
                          </el-form-item>
                          <el-form-item label="Supplier" prop="Supplier">
                            <el-select v-model="editItemForm.Supplier" placeholder="Please choose Supplier">
                              <el-option label="1" value="sup1"></el-option>
                              <el-option label="2" value="sup2"></el-option>
                            </el-select>
                          </el-form-item>
                          <el-form-item label="Status" prop="Status">
                            <el-input v-model="editItemForm.Status"></el-input>
                          </el-form-item>
                          <el-form-item label="Attribute" prop="Attribute">
                            <el-input v-model="editItemForm.Attribute"></el-input>
                          </el-form-item>
                        </el-form>
                        <div class="foot">
                          <el-button @click="editItemClose">Clear</el-button>
                          <el-button type="primary" @click="editItem">Confirm</el-button>
                        </div>
                        <br>
                        <el-button @click="start(item,item.id)" type="text" class="button">Show</el-button>
                      </div>
                    </div>
                  </el-col>
                </el-row>
              </el-card>
            </div>

            <!--            item结束-->
          </el-card>
          <!--            product结束-->
        </el-tab-pane>
        <!--            category结束-->
      </el-tabs>
    </div>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        CategoryListValue: '2',
        CategoryList: [
          {
          id: 'BIRDS',
          name: '♫ Birds',
          ProductList: [{
            id: 'AV-CB-01',
            name: 'Amazon Parrot',
            description: 'Great companion for up to 75 years',
            itemList: [{
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
          }, {
            id: 'AV-CB-02',
            name: 'Finch',
            description: 'Great companion for up to 75 years',
          }]
        },
          {
          id: 'CATS',
          name: '🐾 Cats',
          ProductList: [{
            productId: '2pro1',
          }, {
            productId: '2pro2',
          }]
        }],
        tabIndex: 2
        // 获取列表参数
        quertInfo: {
          //查询的内容
          query: ''
        },
      }
    },
    created() {
      this.getGoods()
    },
    methods: {
      //获取CategoryList
      async getGoods() {
        const {data: res} = await this.$axios.get('goods', {params: this.quertInfo});
        if (res.meta.status !== 200) {
          return this.$message.error('获取Goods信息失败')
        }
        this.CategoryList = res.data.CategoryList;
        console.log(res)
      },
      // 添加tab
      handleTabsEdit(targetName, action) {
        if (action === 'add') {
          let newTabName = ++this.tabIndex + '';
          this.CategoryList.push({
            id: 'New Tab',
            name: newTabName,
            content: 'New Tab content'
          });
          this.CategoryListValue = newTabName;
        }
        if (action === 'remove') {
          //弹窗
          const confirmResult = this.$confirm('Are you sure you want to delete it permanently?', 'message', {
            confirmButtonText: 'Confirm',
            cancelButtonText: 'Cancel',
            type: 'warning'
          }).then(() => {
            //删除
            let tabs = this.CategoryList;
            let activeName = this.CategoryListValue;
            if (activeName === targetName) {
              tabs.forEach((tab, index) => {
                if (tab.name === targetName) {
                  let nextTab = tabs[index + 1] || tabs[index - 1];
                  if (nextTab) {
                    activeName = nextTab.name;
                  }
                }
              });
            }
            this.CategoryListValue = activeName;
            this.CategoryList = tabs.filter(tab => tab.name !== targetName);
            this.$message({
              type: 'success',
              message: 'Delete successfully!'
            });
          }).catch(() => {
            //不删除
            this.$message({
              type: 'info',
              message: 'Deletion have been cancelled.'
            });
          });

        }
      }
      //
    }
  }
</script>
<style scoped>
  .content {
    background-color: white;
  }

  .el-tabs {
    padding: 10px;
  }

  .item {
    width: 100%;
    height: auto;
    display: flex;
    justify-content: space-between;
    flex-direction: row;
    flex-wrap: wrap;
    padding-right: 80px;
  }

  .-item-content {
    width: 45%;
  }

  .button {
    padding: 2px;
    float: right;
  }

</style>
