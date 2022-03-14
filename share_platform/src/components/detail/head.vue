<template>
  <div class="detail-head">
    <el-container>
      <el-aside width="500px" style="margin-left: 100px">
        <el-breadcrumb separator-class="el-icon-arrow-right" class="searchBreadcrumb">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{path: '/search'}">搜索结果</el-breadcrumb-item>
          <el-breadcrumb-item>详情</el-breadcrumb-item>
        </el-breadcrumb>
        <span style="width: 100%">
          <img :src="getImgUrl(this.detail.goodsCoverImg)" class="goodsImg">
        </span>
      </el-aside>
      <el-main>
        <div class="goodsTitle" style="margin-bottom: 10px">{{this.detail.goodsName}}</div>
        <div class="titleIcon" style="margin-bottom: 50px">
          <i class="el-icon-date" style="margin-right: 50px">30天起租</i>
          <i class="el-icon-finished">可免押金</i>
        </div>
        <div class="rentTime">
          <div style="margin-left: -300px;font-weight: 700">租期：</div>
          <el-radio-group size="medium" v-model="totalDay" @change="chooseDay">
            <el-radio-button label="30"></el-radio-button>
            <el-radio-button label="60"></el-radio-button>
            <el-radio-button label="90"></el-radio-button>
            <el-radio-button label="180"></el-radio-button>
            <el-radio-button label="365"></el-radio-button>
          </el-radio-group>
          <span style="margin-left: 10px">天</span>
          <div style="margin-left: -300px;font-weight: 700;margin-top: 30px">自定义租期：</div>
          <div class="datePick">
            <el-date-picker
                v-model="dayPick"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                @change="dayChange"
            >
            </el-date-picker>
          </div>
        </div>
        <div class="rentPrice">
          <div style="margin-left: -300px;font-weight: 700;margin-top: 30px">租金单价：</div>
          <div class="rentSinglePrice">
            <span class="rentSinglePrice">单价：¥{{this.detail.goodsPrice}}/天</span>
          </div>
        </div>
        <div class="total">
          <div style="margin-left: -300px;font-weight: 700;margin-top: 30px">总计：</div>
          <div class="totalDay">
            <span class="totalDayShow">总共：{{totalDay}}天</span>
          </div>
          <div class="totalPrice">
            <span class="totalPrice">免押金总计：¥{{totalPrice}}</span>
          </div>
        </div>
        <el-button type="success" style="width: 300px;font-size: 30px" @click="rent">点击租赁</el-button>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import {goodsDetail} from '@/api/categoryAndGoods'
import {addOrder,addOrderItem} from "@/api/orderAndOrderItems"
export default {
name: "head",
  data(){
    return{
      totalDay:"",
      dayPick:"",
      singlePrice:233,
      totalPrice:"",
      detail:"",
      startDate:"",
      endDate:"",
    }
  },
  methods:{
    dayChange(){
      let mmSec=(this.dayPick[1].getTime()-this.dayPick[0].getTime());
      let day=mmSec/3600000/24
      this.totalDay=day
      this.totalPrice=this.singlePrice*day
      let tempDate=Date.now()
      let startDate=new Date().format("yyyy-MM-dd");
      let resultDate=new Date((tempDate/1000+(86400*this.totalDay))*1000).format("yyyy-MM-dd");//增加n天后的日期
      this.startDate=startDate
      this.endDate=resultDate
      console.log(this.startDate)
      console.log(this.endDate)
      console.log(this.totalPrice)
    },
    chooseDay(){
      let tempDate=Date.now()
      let startDate=new Date().format("yyyy-MM-dd");
      let resultDate=new Date((tempDate/1000+(86400*this.totalDay))*1000).format("yyyy-MM-dd");//增加n天后的日期
      this.startDate=startDate
      this.endDate=resultDate
      console.log(this.startDate)
      console.log(this.endDate)
      this.totalPrice=this.singlePrice*this.totalDay
    },
    async getDetail(){
      try{
        await goodsDetail({
          "key":this.$store.state.detailGoodsId
        }).then((data)=>{
          this.detail=data
          console.log(this.detail)
        })

      }catch (e) {
        console.log(e)
      }
    },
    getImgUrl(imgId){
      return this.$store.state.baseUrl+"/img/"+imgId+".jpg"
    },
    async rent(){
      if(this.$store.state.isLogin){
        try{
          await addOrder({
            "totalPrice":this.totalPrice,
            "payStatus":1,
            "payTime":new Date().format("yyyy-MM-dd"),
            "orderStatus":1,
            "createTime":this.startDate,
            "endTime":this.endDate,
          }).then((orderId)=>{
            console.log(orderId)
            addOrderItem({
              "orderId":orderId,
              "goodsId":this.detail.goodsId,
              "goodsName":this.detail.goodsName,
              "goodsCoverImg":this.detail.goodsCoverImg,
              "price":this.totalPrice,
              "goodsCount":1,
              "createTime": this.startDate,
              "endTime":this.endDate,
            }).then((orderItemId)=>{
              console.log(orderItemId)
              alert("租赁请求成功!")
              this.$router.push({
                name:'home',
                query:{
                  t: Date.now(),
                }})
            })

          })

        }catch (e) {
          console.log(e)
        }
      }else {
        alert("请先登录！")
      }
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
.detail-head{
  margin-top: 30px;
}
.goodsImg{
  max-height: 600px;
  max-width: 500px;
  width: auto;
  height: auto;
  border-radius: 5px;
}
.totalDayShow{
  font-weight: 700;
  font-size: 30px;
  margin-top: 10px;
}
.datePick{
  margin-bottom: 30px;
}
.rentSinglePrice{
  font-size: 30px;
  font-weight: 700;
}
.totalPrice{
  font-weight: 700;
  font-size: 50px;
  margin-top: 10px;
}
.total{
  margin-bottom: 20px;
}
.searchBreadcrumb{
  /*margin-top: 15px;*/
  margin-bottom: 15px;
}
</style>
