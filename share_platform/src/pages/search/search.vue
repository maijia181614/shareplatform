<template>
  <transition name="el-fade-in-linear">
    <div class="app-search">
      <div class="app-header">
        <appHeader></appHeader>
        <el-divider></el-divider>
      </div>
      <div class="search-main">
        <el-breadcrumb separator-class="el-icon-arrow-right" class="searchBreadcrumb">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>搜索结果</el-breadcrumb-item>
        </el-breadcrumb>
        <div style="width: 90%;margin: 0 auto">
          <el-divider content-position="center" >搜索</el-divider>
        </div>
        <div class="searchKey">
          <span class="searchKeyTitle" v-html="searchKeyTitle">手机相关</span>
        </div>
        <div class="orderOption">
          <span>排序方式：</span>
          <el-radio-group v-model="orderOption" @change="searchByOrder">
            <el-radio-button label="默认" ></el-radio-button>
            <el-radio-button label="价格" ></el-radio-button>
            <el-radio-button label="评价" ></el-radio-button>
          </el-radio-group>
        </div>
        <el-card class="box-card searchCard" >
          <div class="searchDiv">
            <el-card :body-style="{ padding: '0px' }" class="searchItem" v-for="(item) in searchData" v-bind:key="item.goodsId"  shadow="hover">
              <img :src="getImgUrl(item.goodsCoverImg)" class="image" @click="toDetail(item.goodsId)" style="width: 230px;height: 250px">
              <div style="padding: 9px;">
                <span style="font-weight: bold;cursor:pointer" @click="toDetail(item.goodsId)">{{item.goodsName}}</span>
                <div class="bottom clearfix">
                  <div style="font-weight: bold;font-size: 20px">¥{{item.goodsPrice}} /天 </div>
                  <el-rate
                      v-bind:value="item.goodsScore"
                      disabled
                      show-score
                      text-color="#ff9900"
                      >
                  </el-rate>
                  <el-button type="primary" style="margin-top: 10px" @click="addGoodsInCart(item.goodsId)" icon="el-icon-shopping-cart-1">加入购物车</el-button>
                </div>
              </div>
            </el-card>
          </div>
        </el-card>
        <el-pagination
            background
            layout="prev, pager, next"
            :total="searchList.length"
            :current-page.sync  ="currentPage"
            :page-size="pageSize"
            :current-change="handleCurrentChange"
        style="margin-top: 20px;margin-bottom: 20px">
        </el-pagination>
      </div>
      <div class="app-footer">
        <el-divider></el-divider>
        <appFooter class="app-footer"></appFooter>
      </div>
    </div>
  </transition>

</template>

<script>
import appHeader from "../../components/header/header";
import appFooter from "@/components/footer/footer";
import { listGoods,listGoodsByOrder} from '@/api/categoryAndGoods'
import {addCart} from "@/api/cart";
export default {
name: "search",
  components: {
    appHeader,
    appFooter,
  },
  data(){
    return{
      currentPage:1,
      pageSize:12,
      score:3,
      searchList:[
        {
          "goodsId":1,
          "goodsName":"iphone",
          "goodsCoverImg":"url",
          "goodsPrice":100,
        },
        {
          "goodsId":2,
          "goodsName":"iphone",
          "goodsCoverImg":"url",
          "goodsPrice":100,
        },
        {
          "goodsId":2,
          "goodsName":"iphone",
          "goodsCoverImg":"url",
          "goodsPrice":100,
        },
        {
          "goodsId":2,
          "goodsName":"iphone",
          "goodsCoverImg":"url",
          "goodsPrice":100,
        },
        {
          "goodsId":2,
          "goodsName":"iphone",
          "goodsCoverImg":"url",
          "goodsPrice":100,
        },
        {
          "goodsId":2,
          "goodsName":"iphone",
          "goodsCoverImg":"url",
          "goodsPrice":100,
        },
      ],
      searchKeyTitle:"",
      orderOption:"",
    }
  },
  computed:{
    searchData:function () {
      let result= this.searchList.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize);
      console.log(result)
      return result
    },
  },


  methods:{
    //处理当前页
    handleCurrentChange(currentPage){
      this.currentPage = currentPage;
      console.log(this.currentPage)  //点击第几页
    },
    //搜索
    async search(){
      try{
        await listGoods({
          "key":this.$store.state.searchKey
        }).then((data)=>{
          this.searchList=data
          this.searchKeyTitle=this.$store.state.searchKey
        })

      }catch (e) {
        console.log(e)
      }
    },
    //前往物品详情页面
    toDetail(key){
      this.$store.commit("commitDetailGoodsId",key)
      this.$router.push({
        name:'detail',
        query:{
          t: Date.now(),
        }})
    },
    //获取图片地址
    getImgUrl(imgId){
      return this.$store.state.baseUrl+"/img/"+imgId+".jpg"
    },

    //添加物品到购物车
    async addGoodsInCart(goodsId){
      try{
        await addCart({
          "goodsId":goodsId
        }).then((data)=>{
          console.log(data);
          this.openSuccess()
        })

      }catch (e) {
        console.log(e)
      }
    },

    //根据条件对物品进行排序
    async searchByOrder(){
      console.log("data")
      let option="";
      if(this.orderOption==="价格"){
          option="goods_price"
      }if(this.orderOption==="评价"){
        option="goods_score"
      }
      try{
        await listGoodsByOrder({
          "key":this.$store.state.searchKey,
          "order":option,
        }).then((data)=>{
          this.searchList=data
          this.searchKeyTitle=this.$store.state.searchKey
          console.log(data)
        })

      }catch (e) {
        console.log(e)
      }
    },

    openSuccess() {
      this.$message({
        message: '恭喜你，添加成功!',
        type: 'success'
      });
    }
  },

  //创建时调用
  mounted() {
    this.search()
    this.searchKeyTitle=this.$store.state.searchKey
  },

  //相同路由刷新时调用
  beforeRouteUpdate(){
    this.search()
    this.searchKeyTitle=this.$store.state.searchKey
  },
}

</script>

<style scoped>
div.app-search{
  display: flex;
  flex-direction: column;
  /*justify-content: center;*/
  min-height: 100vh;
}
div.search-main{
  flex: 1;
  background-size: 100% 100%;
}
.searchBreadcrumb{
  margin-left: 70px;
  margin-top: 20px;
  margin-bottom: 20px;
}
span.searchKeyTitle{
  font-weight: 700;
  color: #111111;
  font-size: 37px;
}
div.searchKey{
  /*position: absolute;*/
  left: 200px;
  margin-top: 30px;
  margin-left: -1200px;
}
.searchCard{
  width: 90%;
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
  padding: 0;
}
.searchItem{
  margin-left: 50px;
  margin-top: 30px;
  margin-bottom: 20px;
}
.searchDiv{
  display: flex;
  flex-direction: row;
  justify-content: left;
  flex-wrap: wrap;
}
</style>

<style>
.el-menu.el-menu--horizontal{
  border: 0;
}

.el-divider--horizontal {
  margin: 0;
}
div.app-header{
  position: -webkit-sticky;
  position: sticky;
  top: 0;
  z-index: 99;
}
div.orderOption{
  margin-bottom: 10px;
}
</style>
