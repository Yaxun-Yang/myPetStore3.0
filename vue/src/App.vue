<template>
  <div id="app">
    <!-- 路由占位符 -->
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>
<script>

  export default {
    name: 'App',
    data() {
      return {
        isRouterAlive:true
      }
    },
    provide(){
      return{
        reload: this.reload
      }
    },
    methods:{
      reload(){
        this.isRouterAlive = false;
        this.$nextTick( () => {
            this.isRouterAlive = true
        })
      }
    }


  }
</script>
<style scoped>
  html{
    min-height:100%;
    position: relative;
    background-color: white;
  }

  #app {
    text-align: center;
  }


  /*<!--  #webId {-->*/
  /*      <!--    position: relative;-->*/
  /*      <!--  }-->*/
  /*<!--  .levitate {-->*/
  /*      <!--    !* 如果碰到滑动问题，请检查 z-index。z-index需比web大一级*!-->*/
  /*      <!--    z-index: 999;-->*/
  /*      <!--    position: fixed;-->*/
  /*      <!--    !*background-color: transparent;*!-->*/
  /*      <!--    background-color: rgba(0, 0, 0, 0.55);-->*/
  /*      <!--  }-->*/

  /*<!--  .el-container{-->*/
  /*      <!--    width: 100%;-->*/
  /*      <!--  }-->*/
  /*<!--  .el-footer{-->*/
  /*      <!--    width: 100%;-->*/
  /*      <!--    background-color: #111111;-->*/
  /*      <!--  }-->*/

</style>

