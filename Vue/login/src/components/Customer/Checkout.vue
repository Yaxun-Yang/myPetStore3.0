<template>
    <div class="all-next">
      <div class="for-header"></div>
      <div style="  padding-left: 15%;">
        <el-card class="checkOrder">
          <el-form :model="editForm" status-icon :rules="formRules" ref="editFormRef" label-width="100px">
            <el-form-item label="Order Id">
              <el-input v-model="editForm.id" disabled></el-input>
            </el-form-item>
            <el-form-item label="User Id" prop="userid">
              <el-input v-model="editForm.userid" disabled></el-input>
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
        </el-card>
      </div>
      <div class="for-header"></div>
    </div>
</template>


<script>
    export default {
        data() {
            return {
              editForm:{
                id: '1',
                userid: '1',
                orderDate: '',
                shipaddr: '',
                shiptoname: '',
                billaddr: '',
                billtoname: '',
                courier: '',
                totalprice: '',
                creditcard: '',
                exprdate: ''
              }

            }
        },

        methods: {}
    }
</script>

<style scoped>
.checkOrder{
  width: 80%;
}
</style>
