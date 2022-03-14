<template>
  <transition name="el-fade-in-linear">
    <div class="app-cart">
      <div class="app-header">
        <appHeader></appHeader>
        <el-divider></el-divider>
      </div>
      <div class="cart-body">
        <div class="tableCardDiv">
          <el-card class="box-card tableCard" >
            <div slot="header" class="clearfix orderTitle">
              <span >购物车</span>
            </div>
            <table border="0" class="cart-table" cellspacing = 50px>
              <tr>
                <th>物品图片</th>
                <th>物品名称</th>
                <th>租赁价格</th>
                <th>租赁数量</th>
                <th>租赁起始时间</th>
                <th>租赁结束时间</th>
                <th>删除</th>
              </tr>
              <tr v-for="item in cartItemList"
                  v-bind:key="item.cartItemId"
              >
                <td><img :src="getImgUrl(item.goodsCoverImg)" class="goodsImg" style="width: 70px;height: 70px"></td>
                <td>{{item.goodsName}}</td>
                <td>
                  {{item.price}}
                </td>
                <td>
                  <el-input-number v-model="item.goodsCount" :min="1" :max="100" label="物品数量" @change="calSingleTotalPrice(item)"></el-input-number></td>
                <td>
                  <el-date-picker
                    v-model="item.createTime"
                    type="date"
                    placeholder="选择租赁起始日期"
                    @change="calTotalDay(item)">
                  </el-date-picker>
                </td>
                <td>
                  <el-date-picker
                      v-model="item.endTime"
                      type="date"
                      placeholder="选择租赁结束日期"
                      @change="calTotalDay(item)"
                  >
                  </el-date-picker>
                </td>
                <td>
                  <el-dialog
                      title="删除"
                      :visible.sync="dialogVisible"
                      width="30%"
                      >
                    <span>确认是否删除？</span>
                    <span slot="footer" class="dialog-footer">
                      <el-button @click="dialogVisible = false">取 消</el-button>
                      <el-button type="primary" @click="deleteCartItem(item.cartItemId)">确 定</el-button>
                    </span>
                  </el-dialog>
                  <el-button type="danger" slot="reference" @click="dialogVisible = true">删除</el-button>

                </td>
              </tr>
            </table>
            <el-divider></el-divider>
            <div class="totalPriceDiv">
              <span class="totalPrice">
                总计：{{this.totalPrice}}
              </span>
            </div>
            <div class="createOrder">
              <el-button type="success" class="createOrderButton" @click="addOrder">确认下单</el-button>
            </div>
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
import {listCartItem,deleteItem,addOrderByCart} from "@/api/cart";
export default {
name: "cart",
  components: {
    appHeader,
    appFooter,
  },

  data(){
    return{
      cartItemList:"",
      dialogVisible: false,
      totalPrice:"",
    }
  },

  methods:{
    //获取购物车物品列表
    async getList(){
      try{
        await listCartItem({
        }).then((data)=>{
          this.cartItemList=data
          for (let index = 0; index < this.cartItemList.length; index++) {
            this.cartItemList[index]['totalDay']=[]
            this.cartItemList[index]['singleTotalPrice']=this.cartItemList[index].price
          }
          console.log(this.cartItemList)
        })

      }catch (e) {
        console.log(e)
      }
    },

    //删除购物车物品项
    async deleteCartItem(cartItemId){
      try{
        await deleteItem({
          "cartItemId":cartItemId,
        }).then((data)=>{
          console.log(data)
        })

      }catch (e) {
        console.log(e)
      }
      this.dialogVisible = false
      alert("删除成功")
      await this.$router.push({
        name:'cart',
        query:{
          t: Date.now(),
        }})
    },

    //获取图片地址
    getImgUrl(imgId){
      return this.$store.state.baseUrl+"/img/"+imgId+".jpg"
    },

    date(createDate){
      console.log(createDate);
    },
    //计算总价
    calTotalPrice(){
      let total=0
      for (let i = 0; i < this.cartItemList.length; i++){
        total=total+this.cartItemList[i].singleTotalPrice
      }
      this.totalPrice=total.toString()
      console.log(this.totalPrice)
    },
    //计算单项物品总价
    calSingleTotalPrice(item){
      item.singleTotalPrice=item.price*item.goodsCount*item.totalDay
        this.calTotalPrice()
        console.log(item.totalDay)
        console.log(item.singleTotalPrice);
    },
    //计算租赁天数
    calTotalDay(item){
      let mmSec=(item.endTime-item.createTime);
      let day=mmSec/3600000/24
      item.totalDay=day
      console.log(day)
      console.log(item.createTime)
      this.calSingleTotalPrice(item)
      this.calTotalPrice()
    },
    //提交订单
    async addOrder(){
      try{
        await addOrderByCart({
          "cartItemList":this.cartItemList,
          "totalPrice":this.totalPrice,
        }).then((data)=>{
          console.log(data)
          this.openSuccess();
          this.$router.push({
            name:'home',
            query:{
              t: Date.now(),
            }})
        })

      }catch (e) {
        console.log(e)
      }
    },
    openSuccess() {
      this.$message({
        message: '恭喜你，下单成功!',
        type: 'success'
      });
    }


  },
  //创建时调用
  mounted() {
    this.getList()
  },

  //相同路由刷新时调用
  beforeRouteUpdate(){
    this.getList()
  },


}


</script>

<style scoped>
.el-divider--horizontal {
  margin: 0;
}
div.app-cart{
  display: flex;
  min-height: 100vh;
  flex-direction: column;
  /*justify-content: center;*/
}
.cart-body{
  flex:1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-top: 20px;
}
.tableCardDiv{
  margin: 0 auto;
}
.tableCard{
  margin-bottom: 30px;
}
div.totalPriceDiv{
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  margin-top: 20px;
}
.totalPrice{
  font-weight: 700;
  font-size: 37px;
  margin-right: 10px;
}
div.createOrder{
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  margin-top: 20px;
}
div.app-header{
  position: -webkit-sticky;
  position: sticky;
  top: 0;
  z-index: 99;
}
.createOrderButton{
  width: 200px;
  font-weight: 700;
  font-size: 20px;
  margin-right: 10px;
  margin-bottom: 10px;
}
</style>
