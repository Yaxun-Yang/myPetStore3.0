<template>
  <div class="all">
    <div class="all-else">
      &nbsp;
    </div>
  <router-view></router-view>
 </div>
</template>
<script>
  export default {
    data() {
      return {
      };
    },
    methods: {

    }
  }
</script>
<style>
  html{
    min-height:100%;
    position: relative;
    background-color: #e5e8ef;
  }
  body{
    margin-bottom: 60px;
    background-color: #e5e8ef;
  }

  .all-else{
    width: 100%;
    background-color: #447387;
    height: 90px;
  }
</style>
