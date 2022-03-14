<template>
  <transition name="el-fade-in-linear">
    <div class="app-detail">
      <div class="app-header">
        <appHeader></appHeader>
        <el-divider></el-divider>
      </div>
      <detailHead></detailHead>
      <detailBody></detailBody>
      <div class="app-footer">
        <el-divider></el-divider>
        <appFooter class="app-footer"></appFooter>
      </div>
    </div>
  </transition>
</template>

<script>
import appHeader from "@/components/header/header";
import appFooter from "@/components/footer/footer";
import detailHead from "@/components/detail/head"
import detailBody from "@/components/detail/body"




export default {
name: "detail",
  components: {
    appHeader,
    appFooter,
    detailHead,
    detailBody,
  }
}
</script>

<style scoped>
.el-divider--horizontal {
  margin: 0;
}
div.app-header{
  position: -webkit-sticky;
  position: sticky;
  top: 0;
  z-index: 99;
}
</style>
