<template>
  <div class="footer">
    <h1>footer
    <br>
<!--    <a @click="totop">TOP</a>-->
    </h1>

  </div>
</template>
<script>
  export default {
    name: '',
    data() {
      return {}
    },
    methods:{
      // totop(){
      //   document.getElementById('topTitle').scrollIntoView({ block: 'start', behavior: 'smooth' });
      // }
    }
  }
</script>
<style scoped>
  .footer{
    /*background-color: #447387;*/
    color: #ffffff;
    text-align: center;
    /*position: absolute;*/
    bottom: 0;
    width: 100%;
    font-size: 20px;
  }
</style>
