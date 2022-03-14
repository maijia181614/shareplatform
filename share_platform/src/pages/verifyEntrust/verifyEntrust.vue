<template>
  <transition name="el-fade-in-linear">
    <div class="app-verifyEntrust">
      <div class="app-header">
        <appHeader></appHeader>
        <el-divider></el-divider>
      </div>
      <div class="verifyEntrust-body">
        <div class="tableCardDiv">
          <el-card class="box-card tableCard" >
            <div slot="header" class="clearfix orderTitle">
              <span >待审核委托列表</span>
            </div>
            <table border="0" class="verifyEntrust-table" cellspacing = 70px>
              <tr>
                <th>委托物品图片</th>
                <th>委托物品名称</th>
                <th>委托租赁价格</th>
                <th>委托租赁数量</th>
                <th>确认审核</th>
              </tr>
              <tr v-for="item in this.entrustList"
                  v-bind:key="item.entrustId"
              >
                <td><img :src="getImgUrl(item.eGoodsCoverImg)" class="goodsImg" style="width: 70px;height: 70px"></td>
                <td>{{item.eGoodsName}}</td>
                <td>{{item.eGoodsPrice}}</td>
                <td>{{item.eStockNum}}</td>
                <td><el-button type="success" @click="toEditEntrust(item.entrustId)">审核</el-button></td>
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
import {listEntrust} from '@/api/entrust'
export default {
name: "verifyEntrust",
  components: {
    appHeader,
    appFooter,
  },
  data(){
    return{
      entrustList:"",
    }
  },
  methods:{
    //获取委托列表
    async getEntrustList(){
      try{
        await listEntrust({
        }).then((data)=>{
          this.entrustList=data
          console.log(data);
        })

      }catch (e) {
        console.log(e)
      }
    },

    //获取图片地址
    getImgUrl(imgId){
      return this.$store.state.baseUrl+"/img/"+imgId+".jpg"
    },

    //前往编辑委托页面
    toEditEntrust(entrustId){
      this.$store.commit("commitEditEntrustId",entrustId)
      console.log(this.$store.state.editEntrustId)
      this.$router.push({
        name:'editEntrust',
        query:{
          t: Date.now(),
        }})
    }

  },

  //创建时调用
  mounted() {
    this.getEntrustList()
  },

  //相同路由刷新时调用
  beforeRouteUpdate(){
    this.getEntrustList()
  },

}


</script>

<style scoped>
.el-divider--horizontal {
  margin: 0;
}
div.app-verifyEntrust{
  display: flex;
  min-height: 100vh;
  flex-direction: column;
  /*justify-content: center;*/
}
.verifyEntrust-body{
  flex:1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-top: 20px;
}


.verifyEntrust-table th{
  margin-left: 10px;
}
.tableCardDiv{
  margin: 0 auto;
}
.tableCard{
  margin-bottom: 30px;
}
div.app-header{
  position: -webkit-sticky;
  position: sticky;
  top: 0;
  z-index: 99;
}
</style>
