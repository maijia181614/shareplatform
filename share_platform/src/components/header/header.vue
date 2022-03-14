<template>
  <div class="app-header">

    <router-link to="./" class="header-icon-link">
      <img src="../../static/images/DOGE.png" class="header-icon">
    </router-link>
    <span style="font-size: 20px">数码共享平台</span>
    <el-menu mode="horizontal" class="header-nav"
             text-color="#111111"
    >
      <el-submenu index="1">
        <template slot="title">所有商品</template>
        <el-container>
        <el-aside width="200px">
        <el-menu
            default-active="2">
          <el-menu-item index="item.categoryId"
                      v-for="item in categoryData"
                      v-bind:key="item.categoryId"
                        @mouseover.native="getChildrenCategory(item.children)"
                      >
            <template slot="title">
              <span >{{item.categoryName}}</span>
            </template>
          </el-menu-item>
        </el-menu>
        </el-aside>
          <el-main style="width: 500px">
            <div class="childrenCategoryPanel">
                <div class="childrenCategoryItem"
                     v-for="item in childrenCategoryData"
                     :key="item.categoryId"
                >
                  <router-link to="">
<!--                    <div>-->
<!--                      <img class="childrenCategoryItemImg" :src="getImgUrl(item.categoryImg)">-->
<!--                    </div>-->
                  </router-link>
                  <el-link active-class="childrenCategoryItemTitle-active" class="childrenCategoryItemTitle">
                    <span class="childrenCategoryItemTitle" @click="catergorySearch(item.categoryName)">{{item.categoryName}}</span>
                  </el-link>
                </div>
            </div>
          </el-main>
        </el-container>
      </el-submenu>
      <el-menu-item index="2" @click="toPublishEntrust">发布委托</el-menu-item>
      <el-menu-item index="3" @click="toMyOrder">订单管理</el-menu-item>
    </el-menu>
    <el-input placeholder="请输入内容"  class=" searchInput" v-model="searchValue" @keyup.enter.native="commitSearch(searchValue)">
      <el-button slot="append" icon="el-icon-search" @click="commitSearch(searchValue)" ></el-button>
    </el-input>
    <div v-if="isLogin" class="header-myName">
      <el-button round class="cartButton"><router-link to="./cart" tag="span">购物车</router-link></el-button>
<!--      <el-link type="primary" underline="false" style="margin-right: 10px" v-if="ifAdmin"><router-link to="./verifyEntrust" tag="span">审核委托</router-link></el-link>-->
<!--      <el-button round class="editInfoButton" v-if="isLogin" ><router-link to="./myInfo" tag="span" >个人信息</router-link></el-button>-->
      <el-dropdown v-if="isLogin" >
        <el-button round class="cartButton">
          个人信息<i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item><router-link to="./myInfo" tag="span" >个人信息</router-link></el-dropdown-item>
          <el-dropdown-item><router-link to="./verifyEntrust" tag="span" v-if="ifAdmin">审核委托</router-link></el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-link type="primary" underline="false" @click="logout">登出</el-link>
    </div>
    <div v-else>
      <el-button round class="loginButton"><router-link to="./login" tag="span">登录</router-link></el-button>
      <el-button round class="registerButton"><router-link to="./register" tag="span">注册</router-link></el-button>
    </div>
  </div>
</template>

<script>
import { userInfo,logout,isAdmin} from '@/api/user'
import { listCategory} from '@/api/categoryAndGoods'
export default {
name: "header",
  data(){
    return{
      categoryData: [
      ],
      childrenCategoryData:[],
      isLogin:false,
      name:"",
      categoryList:[],
      categoryChildrenList:[],
      searchValue:"",
      ifAdmin:false,
    }
  },

  methods:{
    //获取子分类
    getChildrenCategory(childrenCategory){
      this.childrenCategoryData=childrenCategory
    },
    //获取用户信息
    async getUserInfo(){
      try{
        await userInfo({}).then((data)=>{
          console.log(data)
          this.name=data.name
          console.log(data.name)
          if(typeof(this.name) !== "undefined"){
            this.isLogin=true
            this.$store.commit("commitIsLogin",true)
          }else {
            this.isLogin=false
            this.$store.commit("commitIsLogin",false)
          }
        })

      }catch (e) {
        console.log(e)
      }
    },
    //登出
    async logout(){
      try{
        await logout({}).then(()=>{
          this.$router.go(0);
        })

      }catch (e) {
        console.log(e)
      }
    },
    //获取物品分类
    async listCategory(){
      try{
        await listCategory({}).then((data)=>{
          // console.log(data)
          this.categoryList=data.categoryParentList
          this.categoryChildrenList=data.categoryChildrenList
          for (let index = 0; index < this.categoryList.length; index++) {
            this.categoryList[index]['children']=[]
          }
          for (let index = 0; index < this.categoryChildrenList.length; index++) {
            let parentId=this.categoryChildrenList[index].parentId
            this.categoryList.find(item => item.categoryId === parentId).children.push(this.categoryChildrenList[index])
            console.log(this.categoryList)
          }
          this.categoryData=this.categoryList
        })

      }catch (e) {
        console.log(e)
      }
    },


    //判断是否管理员
    async admin(){
      try{
        await isAdmin({}).then((res)=>{
          this.ifAdmin=res
          console.log("是否管理员"+this.ifAdmin)
        })

      }catch (e) {
        console.log(e)
      }
    },


    //获取图片地址
    getImgUrl(imgId){
      return this.baseUrl+"/img/"+imgId+".jpg"
    },
    //进行物品搜索
    commitSearch(key){
      this.$store.commit("commitSearchKey",key)
      this.$router.push({
        name:'search',
        query:{
          t: Date.now(),
        }})
    },
    //根据分类进行搜索
    catergorySearch(key){
      this.$store.commit("commitSearchKey",key)
      this.$router.push({
        name:'search',
        query:{
          t: Date.now(),
        }})
    },
    //前往发布委托页面
    toPublishEntrust(){
      if(this.isLogin){
        this.$router.push({
          name:'publishEntrust',
          query:{
            t: Date.now(),
          }})
      }else {
        this.openWarning();
      }
    },
    //前往我的订单页面
    toMyOrder(){
      if(this.isLogin){
        this.$router.push({
          name:'myOrder',
          query:{
            t: Date.now(),
          }})
      }else {
        this.openWarning();
      }
    },
    openWarning() {
      this.$message({
        message: '请先登录！',
        type: 'warning'
      });
    },
  },

  mounted() {
    this.getUserInfo()
    this.listCategory()
    this.admin()
  },

  //相同路由刷新时调用
  beforeRouteUpdate(){
    this.getUserInfo()
    this.listCategory()
    this.admin()
  },

  watch:{
    '$router': 'getUserInfo'
  },

  computed: {
    baseUrl () {
      return this.$store.state.baseUrl
    }
  },
}
</script>

<style scoped>
  div.app-header{
    width: 100%;
    height: 80px;
    background-color: #FFFFFF;
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
  }
  img.header-icon{
    width: 60px;
    height: auto;
    margin-left: 50px;
    margin-right: 20px;
  }
  .header-nav{
    margin-left: 30px;
    font-weight: 700;

  }
  .goods-menu{
    width: 500px;
    height: auto;
  }
  div.childrenCategoryPanel{
    display: flex;
    flex-direction: row;
    justify-content: left;
    align-items: flex-start;
  }
  div.childrenCategoryItem{
    width: 90px;
    height: 90px;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  div.childrenCategoryItem img{
    width: 30px;
    height: 30px;
  }
  .childrenCategoryItemTitle-active{
    text-decoration: none;
  }
  .childrenCategoryItemTitle{
    color: cornflowerblue;
  }
  .loginButton{
    margin-left: 80px;
    margin-right: 20px;
  }
  .loginButton,.registerButton{
    font-weight: 700;
    color: #111111;
  }
  .searchInput{
    width: 500px;
    margin-left: 50px;

  }
  .header-myName{
    margin-left: 30px;
  }
  .cartButton{
    margin-right: 10px;
  }
  .editInfoButton{
    margin-right: 15px;
  }

</style>



<style>
.searchInput .el-input__inner{
  background-color: #f5f5f5;
  border-color:#f5f5f5 ;
}
.searchInput .el-button.el-button--default{
  background-color: #f5f5f5;
  border-color:#f5f5f5 ;
}
.searchInput .el-input-group__append{
  background-color: #f5f5f5;
  border-color:#f5f5f5 ;
}
.el-menu.el-menu--horizontal{
  border: 0;
}
div.childrenCategoryPanel{
  max-width: 700px;
  display: flex;
  flex-direction: row;
  justify-content: left;
  flex-wrap: wrap;
}
</style>
