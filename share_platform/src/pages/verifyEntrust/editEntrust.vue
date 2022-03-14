<template>
  <transition name="el-fade-in-linear">
    <div class="app-editEntrust">
      <div class="app-header">
        <appHeader></appHeader>
        <el-divider></el-divider>
      </div>
      <div class="editEntrust-body">
        <el-form ref="form" label-width="150px" class="formTable">
          <el-form-item label="委托物品图片">
            <img :src="getImgUrl(entrustDetail.eGoodsCoverImg)" style="width: 150px;height: 150px;">
          </el-form-item>
          <el-form-item label="委托物品名称">
            <el-input v-model="entrustDetail.eGoodsName" placeholder="请输入物品名称"></el-input>
          </el-form-item>
          <el-form-item label="委托物品单价 ">
            <el-input v-model="entrustDetail.eGoodsPrice" placeholder="请输入物品单价"></el-input>
          </el-form-item>
          <el-form-item label="委托物品数量 ">
            <el-input-number :min="1" :max="100" label="物品数量" v-model="entrustDetail.eStockNum"></el-input-number>
          </el-form-item>
          <el-form-item label="委托物品简介 ">
            <el-input v-model="entrustDetail.eGoodsIntro" placeholder="请输入物品简介"></el-input>
          </el-form-item>
          <el-form-item label="选择分类">
            <el-select v-model="entrustDetail.eGoodsCategory" placeholder="请选择分类">
              <el-option :label="item.categoryName" :value="item.categoryId"
                         v-for="item in childrenCategoryList"
                         v-bind:key="item.categoryId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="物品描述">
            <el-input type="textarea" v-model="entrustDetail.eGoodsDetailContent"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="publishEntrust">发布委托</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
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
import {listEntrustById} from '@/api/entrust'
import {editAndPublishEntrust} from '@/api/entrust'
import {listChildrenCategory} from "@/api/categoryAndGoods";
export default {
name: "editEntrust",
  components: {
    appHeader,
    appFooter,
  },
  data(){
    return{
      entrustDetail:"",
      childrenCategoryList:"",
    }
  },

  methods:{
    //获取图片地址
    getImgUrl(imgId){
      return this.$store.state.baseUrl+"/img/"+imgId+".jpg"
    },

    //获取委托详情
    async listEntrustDetail(){
      let entrustId=this.$store.state.editEntrustId
      try{
        await listEntrustById({entrustId}).then((data)=>{
          this.entrustDetail=data
          console.log(data);
        })

      }catch (e) {
        console.log(e)
      }
    },


    //获取分类
    async listChildren(){
      try{
        await listChildrenCategory({
        }).then((data)=>{
          this.childrenCategoryList=data
          console.log(data);
        })

      }catch (e) {
        console.log(e)
      }
    },

    //发布委托
    async publishEntrust(){
      try{
        await editAndPublishEntrust({
          "entrust":{
            "goodsName":this.entrustDetail.eGoodsName,
            "goodsIntro":this.entrustDetail.eGoodsIntro,
            "goodsCategoryId":this.entrustDetail.eGoodsCategory,
            "goodsCoverImg":this.entrustDetail.eGoodsCoverImg,
            "goodsCarousel":this.entrustDetail.eGoodsCarousel,
            "goodsDetailContent":this.entrustDetail.eGoodsDetailContent,
            "stockNum":this.entrustDetail.eStockNum,
            "goodsStatus":1,
            "goodsScore":3.7,
          },
          "itPrice":this.entrustDetail.eGoodsPrice,
          "entrustId":this.entrustDetail.entrustId
        }).then((data)=>{
          console.log(data);
          alert("发布成功！")
          this.$router.push({
            name:'verifyEntrust',
            query:{
              t: Date.now(),
            }})
        })

      }catch (e) {
        console.log(e)
      }
    }


  },

  //创建时调用
  mounted() {
    this.listEntrustDetail()
    this.listChildren()
  },

  //相同路由刷新时调用
  beforeRouteUpdate(){
    this.listEntrustDetail()
    this.listChildren()
  },


}
</script>

<style scoped>
.el-divider--horizontal {
  margin: 0;
}
.app-editEntrust{
  display: flex;
  min-height: 100vh;
  flex-direction: column;
  /*justify-content: center;*/
}
.editEntrust-body{
  flex:1;
  display: flex;
  margin-top: 50px;
}
.formTable{
  margin: 0 auto;
}
div.app-header{
  position: -webkit-sticky;
  position: sticky;
  top: 0;
  z-index: 99;
}
</style>
