<template>
  <transition name="el-fade-in-linear">
  <div class="app-home">
      <el-backtop target=".app-home" :bottom="100" :visibility-height="100" :right="20"></el-backtop>
      <div class="app-header">
        <appHeader></appHeader>
        <el-divider></el-divider>
      </div>
      <span class="carousel-title" style="margin-top: 50px">今日推荐Today</span>
      <home-carousel style="margin-bottom: 50px"></home-carousel>
      <home-discount style="margin-bottom: 50px"></home-discount>
      <home-recommend style="margin-bottom: 50px"></home-recommend>
      <div class="app-footer">
        <el-divider></el-divider>
        <appFooter class="app-footer"></appFooter>
      </div>
  </div>
  </transition>
</template>

<script>
export default {
  name: "home",
  components: {
    appHeader,
    appFooter,
    homeCarousel,
    homeDiscount,
    homeRecommend,
  }
}
import appHeader from "../../components/header/header";
import appFooter from "@/components/footer/footer";
import homeCarousel from "@/components/home/carousel"
import homeDiscount from "@/components/home/discount"
import homeRecommend from "@/components/home/recommend"
</script>

<style scoped>
.el-divider--horizontal {
  margin: 0;
}
div.app-home{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  overflow-y: scroll;
}
.app-footer{

}
span.carousel-title{
  font-weight: 700;
  color: #111111;
  font-size: 37px;
}
div.app-header{
  position: -webkit-sticky;
  position: sticky;
  top: 0;
  z-index: 99;
}

</style>
