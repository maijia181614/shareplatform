<template>
  <transition name="el-fade-in-linear">
  <div class="app-myOrder">
    <el-backtop target=".app-myOrder" :bottom="100" :visibility-height="100" :right="20"></el-backtop>
    <div class="app-header">
      <appHeader></appHeader>
      <el-divider></el-divider>
    </div>
  <div class="myOrder-body">
      <div class="tableCardDiv" v-for="item in orderList" v-bind:key="item.orderId">
      <el-card class="box-card tableCard" shadow="hover">
        <div slot="header" class="clearfix orderTitle">
          <span >订单号：{{ item.orderNum }}</span>
          <span >创建时间：{{ item.createTime }}</span>
        </div>
        <table border="0" class="myOrder-table" cellspacing = 50px>
          <tr>
            <th>物品图片</th>
            <th>物品名称</th>
            <th>租赁价格</th>
            <th>租赁数量</th>
            <th>租赁起始时间</th>
            <th>租赁结束时间</th>
            <th>确认</th>
          </tr>
          <tr v-for="itemDetail in item.orderItemList"
              v-bind:key="itemDetail.orderItemId"
          >
            <td><img :src="getImgUrl(itemDetail.goodsCoverImg)" class="goodsImg" style="width: 70px;height: 70px"></td>
            <td>{{itemDetail.goodsName}}</td>
            <td>{{itemDetail.price}}</td>
            <td>{{itemDetail.goodsCount}}</td>
            <td>{{itemDetail.createTime}}</td>
            <td>{{itemDetail.endTime}}</td>
            <td><el-button type="success">确认租赁</el-button></td>
          </tr>
        </table>
      </el-card>
      </div>

  </div>
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
import {listOrderByUser} from '@/api/orderAndOrderItems'
export default {
name: "myOrder",
  data(){
    return{
      orderItemList:[],
      orderList:[],
      imgIndex:"",
    }
  },
  methods:{
    //获取订单列表
    async getList(){
      try{
        await listOrderByUser({
        }).then((data)=>{
          this.orderList=data
          console.log(this.orderList)
        })

      }catch (e) {
        console.log(e)
      }
    },
    getImgUrl(imgId){
      return this.$store.state.baseUrl+"/img/"+imgId+".jpg"
    }
  },

  //创建时调用
  mounted() {
  let user=localStorage.getItem("token")
    if(null!==user){
      this.getList()
    }else {
      alert("请先登录")
    }

  },

  //相同路由刷新时调用
  beforeRouteUpdate(){
    let user=localStorage.getItem("token")
    if(null!==user){
      this.getList()
    }else {
      alert("请先登录")
    }
  },
  components: {
    appHeader,
    appFooter,
  }
}
</script>

<style scoped>
.el-divider--horizontal {
  margin: 0;
}
div.app-myOrder{
  display: flex;
  min-height: 100vh;
  flex-direction: column;
  /*justify-content: center;*/
  overflow-y: scroll;
}
.myOrder-body{
  flex:1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-top: 20px;
}
.myOrder-table th{
  margin-left: 10px;
}
.tableCardDiv{
  margin: 0 auto;
}
.tableCard{
  margin-bottom: 30px;
}
.orderTitle{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
div.app-header{
  position: -webkit-sticky;
  position: sticky;
  top: 0;
  z-index: 99;
}
</style>
