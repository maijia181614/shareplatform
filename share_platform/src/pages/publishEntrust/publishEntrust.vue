<template>
  <transition name="el-fade-in-linear">
  <div class="app-publishEntrust">
    <div class="app-header">
      <appHeader></appHeader>
      <el-divider></el-divider>
    </div>
    <div class="publishEntrust-body">
      <el-form ref="form" label-width="150px" class="formTable" :model="entrustList">
        <el-form-item label="委托物品名称">
          <el-input v-model="entrustList.goodsName" placeholder="请输入物品名称"></el-input>
        </el-form-item>
        <el-form-item label="委托物品单价 ">
          <el-input v-model="entrustList.goodsPrice" placeholder="请输入物品单价"></el-input>
        </el-form-item>
        <el-form-item label="委托物品数量 ">
          <el-input-number :min="1" :max="100" label="物品数量" v-model="entrustList.stockNum"></el-input-number>
        </el-form-item>
        <el-form-item label="委托物品简介 ">
          <el-input v-model="entrustList.goodsIntro" placeholder="请输入物品简介"></el-input>
        </el-form-item>
        <el-form-item label="选择分类">
          <el-select v-model="entrustList.goodsCategory" placeholder="请选择分类">
            <el-option :label="item.categoryName" :value="item.categoryId"
                       v-for="item in childrenCategoryList"
                        v-bind:key="item.categoryId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="委托物品主图片 ">
          <el-upload
              class="upload-demo"
              :action="uploadUrl"
              :on-success="getCoverImg"
              :headers="headers"
              name="img"
              :limit="1"
              accept=".jpg"
            >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="委托物品详细图片 ">
          <el-upload
              class="upload-demo"
              drag
              :action="uploadUrl"
              :on-success="getCarouselImg"
              :headers="headers"
              :limit="1"
              name="img"
              accept=".jpg"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="物品描述">
          <el-input type="textarea" v-model="entrustList.goodsDetailContent"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addEntrust">申请发布委托</el-button>
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
import {listChildrenCategory} from '@/api/categoryAndGoods'
import {publishEntrust} from '@/api/entrust'
export default {
name: "publishEntrust",
  components: {
    appHeader,
    appFooter,
  },
  data(){
    return{
      entrustList:{
        category:"",
        goodsName:"",
        goodsIntro:"",
        goodsCategory:"",
        goodsCoverImg:"",
        goodsCarousel:"",
        goodsDetailContent:"",
        goodsPrice:"",
        stockNum:"",
      },
      uploadUrl:this.$store.state.baseUrl+"/file/upload",
      headers:{
        "authToken":localStorage.getItem('token')
      },
      childrenCategoryList:"",
    }
  },
  methods:{
    getUploadUrl(){
      return this.$store.state.baseUrl+"/file/upload";
    },
    //获取封面图片
    getCoverImg(responese){
      this.entrustList.goodsCoverImg=responese
      console.log(this.entrustList.goodsCoverImg);
    },
    //获取详细图片
    getCarouselImg(responese){
      this.entrustList.goodsCarousel=responese
      console.log(this.entrustList.goodsCarousel);
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

    //添加委托
    async addEntrust(){
      console.log(this.goodsName);
      console.log(this.goodsIntro);
      try{
        await publishEntrust({
          "eGoodsName":this.entrustList.goodsName,
          "eGoodsIntro":this.entrustList.goodsIntro,
          "eGoodsCategory":this.entrustList.goodsCategory,
          "eGoodsCoverImg":this.entrustList.goodsCoverImg,
          "eGoodsCarousel":this.entrustList.goodsCarousel,
          "eGoodsDetailContent":this.entrustList.goodsDetailContent,
          "eGoodsPrice":this.entrustList.goodsPrice,
          "eStockNum":this.entrustList.stockNum,
        }).then((data)=>{
          this.childrenCategoryList=data
          console.log(data);
          alert("已提交")
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


  },


  //创建时调用
  mounted() {
    this.listChildren()
  },

  //相同路由刷新时调用
  beforeRouteUpdate(){
    this.listChildren()
  },






}
</script>

<style scoped>
.el-divider--horizontal {
  margin: 0;
}
.app-publishEntrust{
  display: flex;
  min-height: 100vh;
  flex-direction: column;
  /*justify-content: center;*/
}
.publishEntrust-body{
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
