<template>

</template>

<script>
  export default {
    mounted() {
      console.log(this.$route.query)
      const div = document.createElement('div');
      div.innerHTML = this.$route.query.htmls;
      document.body.appendChild(div);
      document.forms[0].submit();
    }
  }

</script>

<style scoped>

</style>
