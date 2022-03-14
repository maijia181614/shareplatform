<template>
  <div class="detail-body">
    <el-card class="box-card detailCard">
      <div slot="header" class="clearfix">
        <span style="font-size: 30px">物品详情</span>
      </div>
      <div style="margin-top: 20px;margin-bottom: 20px">{{this.detail.goodsDetailContent}}</div>
      <img :src="getImgUrl(this.detail.goodsCarousel)">
    </el-card>
  </div>
</template>

<script>
import {goodsDetail} from '@/api/categoryAndGoods'

export default {
name: "body",
  data(){
    return{
      detail:"",
    }
  },
  methods:{
    //获取物品详情
    async getDetail(){
      try{
        await goodsDetail({
          "key":this.$store.state.detailGoodsId
        }).then((data)=>{
          this.detail=data
          console.log(data)
        })

      }catch (e) {
        console.log(e)
      }
    },
    //获取图片地址
    getImgUrl(imgId){
      return this.$store.state.baseUrl+"/img/"+imgId+".jpg"
    }
  },

  //创建时调用
  mounted() {
    this.getDetail()
  },

  //相同路由刷新时调用
  beforeRouteUpdate(){
    this.getDetail()
  }
}
</script>

<style scoped>
.detail-body{
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
.detailCard{
  width: 95%;
}
.el-divider--horizontal {
  margin: 0;
}
</style>
