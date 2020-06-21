<template>
  <el-card>
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
        <el-breadcrumb-item :to="{ path: '/home' }">Admin</el-breadcrumb-item>
        <el-breadcrumb-item>Goods</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-divider><i class="el-icon-s-goods"></i></el-divider>
    <el-tabs
      v-model="CategoryListValue"
      type="card"
      editable
      @edit="handleTabsEdit">
      <el-tab-pane
        :key="index"
        v-for="(category, index) in CategoryList"
        :label="category.id"
        :name="category.name">
        <!--            category展示-->
        <div class="cate-show">
          <div class="title">
            <span v-show="!editCategory">{{category.id}}</span>
            <el-input v-show="editCategory"
                      v-model="category.id"
                      style="width: 150px; height: 10px; font-size: 30px; font-weight: bold;">
            </el-input>
            <i
              :class="{'el-icon-edit': !editCategory, 'el-icon-check': editCategory}"
              @click="editCategory = !editCategory"
            > </i>
          </div>
          <span class="subtitle">
            <i class="el-icon-plus" @click="addProductDialogVisible = true"></i>
        </span>
        </div>

        <!--  添加product对话框-->
        <el-dialog title="Add Product" :visible.sync="addProductDialogVisible" @close="addProductClose">
          <el-form :model="addProductForm" ref="addProductFormRef" label-width="100px">
            <br>
            <el-form-item label="Product Id" prop="id">
              <el-input v-model="addItemForm.id"></el-input>
            </el-form-item>
            <el-form-item label="Name" prop="name">
              <el-input v-model="addItemForm.name"></el-input>
            </el-form-item>
            <el-form-item label="Description" prop="description">
              <el-input v-model="addItemForm.description"></el-input>
            </el-form-item>
          </el-form>
          <br>
          <div slot="footer" class="dialog-footer">
            <el-button @click="addProductDialogVisible = false">Cancel</el-button>
            <el-button type="primary" @click="addProduct(category.id)">Confirm</el-button>
          </div>
        </el-dialog>
        <el-card shadow="hover"
                 v-for="(product, index2) in category.productList"
                 :key="index2"
                 class="product">
          <!--            product展示-->
          <div class="cate-show">
            <div class="subtitle">
              <span v-show="!editItem">{{product.name}}</span>

              <el-input v-show="editItem" v-model="product.name"
                        style="width: 260px; height: 10px; font-size: 25px; font-weight: bolder;">
              </el-input>
              <i
                :class="{'el-icon-edit': !editItem, 'el-icon-check': editItem}"
                @click="editProductInfo(product.id)"
              >
              </i>
            </div>
            <span class="subtitle">
            <i class="el-icon-delete-solid" @click="removeProductById(product.id)"></i>
            <i class="el-icon-plus" @click="addItemDialogVisible = true"></i>
            </span>
          </div>
          <br>
          <div class="desciption">
            <span v-show="!editItem">  ✔ID:「{{product.id}}」</span>
            <el-input v-show="editItem" v-model="product.id" style="width: 50%"></el-input>
            <br>
            <span v-show="!editItem">✔{{product.text}}</span>
            <el-input v-show="editItem" v-model="product.text" style="width: 50%"></el-input>
          </div>
          <br>
          <!--  添加item对话框-->
          <el-dialog title="Add Item" :visible.sync="addItemDialogVisible" @close="addItemClose">
            <el-form :model="addItemForm" ref="additemFormRef" label-width="100px">
              <br>
              <el-form-item label="Picture">
                <el-upload
                  class="upload"
                  action="#"
                  multiple
                  :limit="1"
                  :on-exceed="handleExceed"
                  :http-request="httpRequest">
                  <el-button size="small" type="primary">Click to upload</el-button>
                </el-upload>
              </el-form-item>
              <el-form-item class="formitem" label="Item Id" prop="id">
                <el-input v-model="addItemForm.id"></el-input>
              </el-form-item>
              <el-form-item class="formitem" label="Unit Cost" prop="UnitCost">
                <el-input v-model="addItemForm.UnitCost"></el-input>
              </el-form-item>
              <el-form-item class="formitem" label="Supplier" prop="Supplier">
                <el-select v-model="addItemForm.Supplier">
                  <el-option label="sup1" value="1"></el-option>
                  <el-option label="sup2" value="2"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="Attribute" prop="Attribute">
                <el-input v-model="addItemForm.Attribute"></el-input>
              </el-form-item>
            </el-form>
            <br>
            <div slot="footer" class="dialog-footer">
              <el-button @click="addItemDialogVisible = false">Cancel</el-button>
              <el-button type="primary" @click="addItem(product.id)">Confirm</el-button>
            </div>
          </el-dialog>
          <!--            item展示-->
          <div class="item"  v-for="(itemList,index5) in product.itemList" :key="index5">
            <el-card shadow="hover"
                     v-for="(item, index4) in itemList.item"
                     :key="index4"
                     class="-item-content">
              <!-- 正面-->
              <div class="show">
                <el-row :gutter="5" :class="[item.rotate? 'go' : 'no' ]">
                  <el-col :span="16" style="padding-top: 20px">
                    <el-image style="width: 250px;height: 250px;" :src="item.url"></el-image>
                  </el-col>
                  <el-col :span="8">
                    <span class="subsubtitle">{{item.itemId}}</span>
                    <div class="desciption">
                      <br>
                      <!-- 这里有个假的"喜欢人数"-->
                      <span style="font-size: 20px">$&nbsp;{{item.price}}&nbsp;♡&nbsp;50</span>
                      <br> <br>
                      <span class="attr">✔{{item.attribute}}</span>
                      <br> <br>
                      <span class="ProductId">✔Quantity:</span>
                      <span>{{item.quantity}}</span>
                      <br> <br>

                      <div style=" padding-top: 10px">
                        <el-button @click="start(item,item.itemId)" type="text" icon="el-icon-edit" class="button">Edit
                        </el-button>
                      </div>
                      <el-button @click="deleteItem(item.itemId)" type="text" icon="el-icon-delete-solid" class="button"
                      >Delete&nbsp;
                      </el-button>
                    </div>
                  </el-col>
                </el-row>
              </div>
              <!--  反面-->
              <div class="change" :class="[item.rotate? 'no' : 'go' ]">
                <el-form :model="editItemForm" status-icon ref="editItemForm" :label-position="left"
                         label-width="80px" style="padding-top: 10px">
                  <br>
                  <el-row>
                    <el-col :span="12">
                      <el-form-item label="Picture" prop="fileName" label-width="20px">
                        <el-upload
                          class="upload"
                          action="#"
                          multiple
                          :limit="1"
                          :on-exceed="handleExceed"
                          :http-request="httpRequest">
                          <img :src="editItemForm.fileName" hidden>
                          <el-button size="small" type="primary">Click to upload</el-button>
                        </el-upload>
                      </el-form-item>
                    </el-col>
                    <el-col :span="12">
                      <el-form-item label="file name" prop="id" >
                        <el-input v-model="editItemForm.fileName" ></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>


                  <el-form-item class="formitem" label="Item Id" prop="id">
                    <el-input v-model="editItemForm.itemId" disabled></el-input>
                  </el-form-item>
                  <el-form-item class="formitem" label="Unit Cost" prop="UnitCost">
                    <el-input v-model="editItemForm.price"></el-input>
                  </el-form-item>
                  <el-form-item class="formitem" label="quantity" prop="UnitCost">
                    <el-input v-model="editItemForm.quantity"></el-input>
                  </el-form-item>
                  <el-form-item class="formitem" label="productId" prop="UnitCost">
                    <el-input v-model="editItemForm.productId"></el-input>
                  </el-form-item>
                  <el-form-item label="Attribute" prop="Attribute">
                    <el-input v-model="editItemForm.attribute"></el-input>
                  </el-form-item>
                  <br>
                  <el-form-item style=" padding-top: 10px;padding-left: 10px">
                    <el-button @click="start(item,item.itemId)" type="text" icon="el-icon-view" class="button">Show
                    </el-button>
                    <el-button @click="editItemInfo" type="text" icon="el-icon-finished" class="button">Confirm&nbsp;&nbsp;
                    </el-button>
                    <el-button @click="resetForm('editItemForm')" type="text" icon="el-icon-refresh-left"
                               class="button">Clear
                    </el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-card>
          </div>
          <!--            item结束-->
        </el-card>
        <!--            product结束-->
      </el-tab-pane>
      <!--            category结束-->
    </el-tabs>


  </el-card>


</template>

<script>
  export default {
    data() {
      return {
        // 添加新product对话框的显示隐藏
        addProductDialogVisible: false,
        // 添加新item对话框的显示隐藏
        addItemDialogVisible: false,
        editItem: false,
        editProduct: false,
        editCategory: false,
        CategoryListValue: '1',
        CategoryList: [],
        tabIndex: 2,
        // 获取列表参数
        quertInfo: {
          //查询的内容
          query: ''
        },
        //查询到的item信息对象
        editItemForm: {},
        //查询到的product信息对象
        editProductForm: {},
        //添加新Product
        addProductForm: {
          id: '',
          name: '',
          description: '',
        },
        //添加新Item
        addItemForm: {

          rotate: false,
        }
      }
    },
    created() {
      this.getCategoryList()
    },
    methods: {
      //获取CategoryList
      getCategoryList() {
        let _this = this;
        this.$axios.get('/api/catalog/').then(function (res) {
          console.log(res);
          if (res.status !== 200) {
            _this.$message({
              type: 'warning',
              message: "Cannot get category"
            });
          }
          _this.CategoryList = res.data.data;
          console.log(res.data.data);
        })
      },
      // 添加和删除tab(Category)
      handleTabsEdit(targetName, action) {
        //添加
        if (action === 'add') {
          let newTabName = ++this.tabIndex + '';
          this.CategoryList.push({
            id: 'New Tab',
            name: newTabName,
            content: 'New Tab content'
          });
          this.CategoryListValue = newTabName;
          //post
          this.$axios.post('/api/catalog/category', this.CategoryList).then(res=>{
            if (res.data.status !== 200) {
              this.$message.error('Add category failed!');
            }
            this.$message.success('Add category successfully');
          }).catch(err=>{
            console.log(err)
          })
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
          this.$axios.delete('/api/catalog/category/' + id).then(res=>{
            if (res.data.status !== 200) {
              return this.$message.error('Delete failed!')
            }
            this.$message.success('Delete successfully!');
            // 刷新列表
            this.getCategoryList();
          })
        }
      },
      /*  添加,编辑，删除Product */
      // 监听添加Product对话框的关闭事件
      addProductClose() {
        this.$refs.addProductFormRef.resetFields()
      },
      // 添加Product
      addProduct(id) {
        this.$refs.addProductFormRef.validate(async valid => {
          if (!valid) return;
          // 可以发起add的网络请求
          const {data: res} = await this.$axios.post('category' + id, this.addProductForm);
          if (res.meta.status !== 201) {
            this.$message.error('Add product failed!');
          }
          this.$message.success('Add product successfully');
          // 隐藏对话框
          this.addProductDialogVisible = false;
          // 刷新列表
          this.getCategoryList();
        })
      },
      //编辑Product
      async editProductInfo(id) {
        this.editItem = !this.editItem;
          // 发起修改用户信息的数据请求
          const { data: res } = await this.$axios.put(
            'category/' + id,
            {
              id: this.product.id,
              name: this.product.name,
              description: this.product.description
            }
          )
          if (res.meta.status !== 200) {
            return this.$message.error('更新Product失败！');
          }
          // 刷新数据列表
          this.getCategoryList();
          // 提示修改成功
          this.$message.success('更新Product成功！');
      },
      // 根据Id删除对应的Product信息
      async removeProductById(id) {
        //弹窗
        const confirmResult = await this.$confirm('Are you sure you want to delete this product permanently?', 'message', {
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
        const {data: res} = await this.$axios.delete('category/' + id);
        if (res.meta.status !== 200) {
          return this.$message.error('Delete failed!')
        }
        this.$message.success('Delete successfully!');
        // 刷新列表
        this.getCategoryList();
      },



      /* 添加 编辑 删除 item */
      // 监听添加item对话框的关闭事件
      addItemClose() {
        this.$refs.addItemFormRef.resetFields()
      },
      // 添加item
      addItem(id) {
        this.$refs.addItemFormRef.validate(async valid => {
          if (!valid) return;
          // 可以发起add的网络请求
          const {data: res} = await this.$axios.post('category' + id, this.addItemForm);
          if (res.meta.status !== 201) {
            this.$message.error('Add item failed!');
          }
          this.$message.success('Add item successfully');
          // 隐藏对话框
          this.addItemDialogVisible = false;
          // 刷新列表
          this.getCategoryList();
        })
      },
      //点一次Item旋转一次，编辑内容的请求
      start(item, id) {
        item.rotate = !item.rotate;
        this.$axios.get('/api/catalog/item/' + id).then(res=>{
          console.log(res);
          if (res.data.status !== 200) {
            return this.$message.error('Query Failed!')
          }
          this.editItemForm = res.data.data.item;
        }).catch(err=>{
          console.log(err)
        })

      },
      //只允许上传1张照片
      handleExceed(files) {
        this.$message.warning(`Only 1 picture is accessible`);
      },
      //处理显示缩略图，并且将需要用到的file转换成base64的文件格式
      httpRequest(editItemForm) {
        let _this = this;
        let rd = new FileReader(); // 创建文件读取对象
        let file = editItemForm.file;
        rd.readAsDataURL(file); // 文件读取装换为base64类型
        rd.onloadend = function (e) {
          _this.editItemForm.file = this.result // this指向当前方法onloadend的作用域
        };
        console.log(_this.editItemForm.file)
      },
      // 清除编辑内容
      resetForm(editItemForm) {
        this.$refs[editItemForm].resetFields();
      },

      // 提交item编辑内容
      editItemInfo() {
        this.$refs.editItemForm.validate(valid => {
          if (valid) {
          // 可以发起edit item 的网络请求
          this.$axios.post('/api/catalog/item/'+ this.editItemForm).then(res=>{
            console.log('itemmmm');
              console.log(res);
              if (res.data.status !== 200) {
                this.$message.error('Edit Failed!');
              }
              this.$message.success('Edit successfully');
              // 刷新列表
              this.getCategoryList();
          })
          }
        })

      },
      //删除item
      async deleteItem(itemId) {
        //弹窗
        const confirmResult = await this.$confirm('Are you sure you want to delete this item permanently?', 'message', {
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
        const {data: res} = await this.$axios.delete('category/' + id);
        if (res.meta.status !== 200) {
          return this.$message.error('Delete failed!')
        }
        this.$message.success('Delete successfully!');
        // 刷新列表
        this.getCategoryList();
      }
    }
  }
</script>
<style scoped>
  .el-tabs {
    padding: 30px;
    background-color: white;
  }

  .content {
    background-color: white;
  }

  .product {
    background-color: whitesmoke;
    height: 1000px;
  }

  .item {
    width: 100%;
    display: flex;
    justify-content: space-between;
    flex-direction: row;
    flex-wrap: wrap;
    padding-right: 80px;
  }

  .-item-content {
    width: 45%;
    height: 340px;
    position: relative
  }

  .show,
  .change {
    width: 100%;
    padding: 10px;
    backface-visibility: hidden;
    position: absolute;
    top: 0;
    left: 0;
    background-repeat: no-repeat;
  }

  .button {
    padding-right: 2px;
    float: right;
  }

  .image {
    padding: 20px;
  }

  .go {
    transform: rotateY(180deg);
    transition: all 2s;
    background-color: white;
  }

  .no {
    transition: all 2s;
    background-color: white;
  }

  .el-form {
    width: 100%;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
  }

  .el-form-item {
    width: 100%;
  }

  .formitem {
    width: 50%;
    padding-right: 10px;
  }

  .upload {
    align-items: center;
    display: flex;
    justify-content: space-between;
    padding-right: 20px;
    width: 40px;
  }

  .cate-show {
    align-items: center;
    display: flex;
    justify-content: space-between;
  }
</style>
