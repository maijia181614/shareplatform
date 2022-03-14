<template>
  <div class="home-recommend">
    <span class="recommend-title">为您推荐Recommend</span>
    <div class="home-recommend-body">
      <div class="recommend-card-div">
        <el-card class="recommend-card" style="width: 100%;height: auto" shadow="hover">
          <div class="recommend-row" style="width: 100%;height:auto">
            <el-carousel :interval="3000" type="card" class="recommend-row-carousel">
              <el-carousel-item v-for="item in recommendList" v-bind:key="item.configId">
                <img class="recommend-row-item-img" :src="getImgUrl(item.goodsCoverImg)" @click="toSearch(item.configName)">
              </el-carousel-item>
            </el-carousel>
          </div>
        </el-card>

      </div>
    </div>


  </div>
</template>

<script>
import {indexList} from "@/api";

export default {
name: "recommend",
  //进行物品搜索
  methods:{
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
          "indexType":3
        }).then((data)=>{
          this.recommendList=data
          console.log(this.recommendList)
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
      recommendList:"",
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
div.home-recommend-body{
  display: flex;
  flex-direction: row;
  justify-content: center;
}
div.recommend-card-div{
  width: 91%;
  height: auto;

}
span.recommend-title{
  font-weight: 700;
  color: #111111;
  font-size: 37px;
}
.recommend-row{
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top: 30px;
  padding: 20px;
}
.recommend-row-item{
  /*display: flex;*/
  /*flex-direction: column;*/
  /*justify-content: center;*/
  /*width: 600px;*/
  /*height: 400px;*/
  /*margin-left: 30px;*/
  padding: 0;
}
img.recommend-row-item-img{
  width: 100%;
  height: 300px;
  border-radius: 5px;
}
.recommend-row-carousel{
  width: 100%;
}
</style>
<style>
.recommend-row-item{
  /*padding: 30px;*/
}

</style>
