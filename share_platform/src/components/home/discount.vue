<template>
  <div class="home-discount">
    <span class="discount-title">今日优惠Discount</span>
    <div class="discount-card-div">
      <el-card class="discount-card" style="margin-right: 100px" v-for="item in discountList" v-bind:key="item.configId" shadow="hover">
        <img :src="getImgUrl(item.goodsCoverImg)" style="width: 100%;height: auto" @click="toSearch(item.configName)">
      </el-card>
    </div>
  </div>
</template>

<script>
import {indexList} from "@/api";

export default {
  name: "discount",
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
          "indexType":2
        }).then((data)=>{
          this.discountList=data
          console.log(this.discountList)
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
      discountList:"",
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
div.home-discount{

}
div.discount-card-div{
  display: flex;
  flex-direction: row;
  justify-content: center;
  padding-left: 80px;
}
.discount-card{
  width: 600px;
  height: 350px;
  /*display: flex;*/
  /*flex-direction: row;*/
  /*justify-content: center;*/
  margin: 0 auto;
}

span.discount-title{
  font-weight: 700;
  color: #111111;
  font-size: 37px;
}
</style>
