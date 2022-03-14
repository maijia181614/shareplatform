<template>
<div class="footer">
  <span class="footerTitle">© 2021 sharePlatform</span>
  <span class="footerSpan">帮助中心</span>
  <span class="footerSpan">联络我们</span>
  <span class="footerSpan">媒体</span>
  <span class="footerSpan">公司职位</span>
  <span class="footerSpan">营销中心</span>
</div>
</template>

<script>
export default {
name: "footer"
}
</script>

<style scoped>
div.footer{
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  padding-bottom: 10px;
  padding-top: 10px;
}
span.footerTitle{
  color: rgb(94,94,96);
  margin-left: 100px;
  margin-right: 700px;
}
span.footerSpan{
  color: rgb(94,94,96);
  margin-left: 20px;
  font-size: 10px;
}
</style>
