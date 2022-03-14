<template>
  <div class="home-carousel">
    <el-card class="carousel-card" shadow="hover">
      <el-carousel indicator-position="outside" height="700px">
        <el-carousel-item v-for="item in carouselList" v-bind:key="item.configId">
          <img :src="getImgUrl(item.goodsCoverImg)" style="height: auto" @click="toSearch(item.configName)">
        </el-carousel-item>
      </el-carousel>
    </el-card>
  </div>
</template>

<script>
import {indexList} from "@/api/index"
export default {
name: "carousel",
  methods:{
    //进行物品搜索
    toSearch(itemName){
      this.$store.commit("commitSearchKey",itemName)
      this.$router.push({
        name:'search',
        query:{
          t: Date.now(),
        }})
    },

    //获取首页轮播配置
    async getIndexList(){
      try{
        await indexList({
          "indexType":1
        }).then((data)=>{
          this.carouselList=data
          console.log(this.carouselList)
        })

      }catch (e) {
        console.log(e)
      }
    },

    //获取图片地址
    getImgUrl(imgId){
      return this.$store.state.baseUrl+"/img/"+imgId+".jpg"
    },

  },
  data(){
    return{
      carouselList:"",
    }
  },

  //创建时调用
  mounted() {
    this.getIndexList()
  },

  //相同路由刷新时调用
  beforeRouteUpdate(){
    this.getIndexList()
  }

}
</script>

<style scoped>
.carousel-card{
  width: 100%;
  height: 700px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.carousel-card img{
  width: 100%;
  height: auto;
}
div.home-carousel{
  display: flex;
  flex-direction: row;
  justify-content: center;
}
div.carousel-Title{

}

</style>
<style>
.el-card__body{
  padding: 0;
}
</style>
