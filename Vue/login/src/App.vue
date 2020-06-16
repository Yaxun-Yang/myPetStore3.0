<template>
  <div id="app">

    <!-- element-ui 容器布局 -->
    <el-container>
      <!-- 头部 -->
      <div id="webId">
        <!-- 悬浮的HTML -->
        <div class="levitate">
          <!-- 头部组件渲染 -->
          <header-ly></header-ly>
        </div>
      </div>
      <!-- 中间主要区域容器 -->
      <el-container>
        <!--        &lt;!&ndash; 添加一个element-ui内置的过渡动画 &ndash;&gt;-->
        <!--        <transition name="el-zoom-in-center">-->
        <!--        </transition>-->
        <!-- 路由占位符 -->
        <router-view></router-view>
      </el-container>
      <!-- 底部 -->
      <el-footer>
        <!-- 底部组件渲染 -->
        <footer-ly></footer-ly>
      </el-footer>
    </el-container>
  </div>
</template>
<script>
  // 导入组件
  import HeaderLy from './components/Header'
  import FooterLy from './components/Footer'
  export default {
    data() {
      return {
      }
    },
    name: 'App',
    components: {
      HeaderLy,
      FooterLy
    }

  }
</script>
<style>
  html{
    min-height:100%;
    position: relative;
    background-color: white;
  }

  #app {
    text-align: center;

  }

  #webId {
    position: relative;
  }
  .levitate {
    /* 如果碰到滑动问题，请检查 z-index。z-index需比web大一级*/
    z-index: 999;
    position: fixed;
    /*background-color: transparent;*/
    background-color: rgba(0, 0, 0, 0.55);
  }

  .el-container{
    width: 100%;
  }
  .el-footer{
    width: 100%;
    background-color: #111111;
  }
</style>

