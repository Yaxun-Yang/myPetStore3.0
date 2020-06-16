<template>
  <div class="cart">
    <el-image style="width: 150px; height: 100px;margin-top: 86px" :src="url"></el-image>
    <el-image style="width: 150px; height: 100px;margin-top: 86px" :src="url"></el-image>
    <el-image style="width: 150px; height: 100px;margin-top: 86px" :src="url"></el-image>
   id::::: {{id}}<el-button @click="ggg"></el-button>
  </div>
</template>


<script>

  export default {
    data() {
      return{


      }
    },

    methods: {


      ggg(){
      this.$axios.get('http://localhost:8082/pa').then(function (res) {
        console.log(res);
      }).catch(function(err){
        console.log(err)
        })
      }
    },

    //



  }
</script>

<style >




</style>
