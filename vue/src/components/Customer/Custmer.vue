<template>
    <div>
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
  import HeaderLy from '../Header'
  import FooterLy from '../Footer'
    export default {
        data() {
            return {}
        },

        methods: {},

      components: {
        HeaderLy,
        FooterLy
      }
    }
</script>

<style>



</style>
