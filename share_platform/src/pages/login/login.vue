<template>
  <transition name="el-fade-in-linear">
    <div class="app-login">
      <div class="app-header">
        <appHeader></appHeader>
        <el-divider></el-divider>
      </div>
      <div class="login-title">登录</div>
      <div class="login-main" >
        <login-left></login-left>
        <login-right></login-right>
      </div>
      <div class="app-footer">
        <el-divider></el-divider>
        <appFooter class="app-footer"></appFooter>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
name: "login",
  components:{
    loginLeft,
    appHeader,
    appFooter,
    loginRight,
  }
}
import loginLeft from "@/components/login/login-left";
import loginRight from "@/components/login/login-right";
import appHeader from "../../components/header/header";
import appFooter from "@/components/footer/footer";
</script>

<style scoped>
div.app-login{
  display: flex;
  flex-direction:column;
  overflow-y: scroll;
  min-height: 100vh;
}
div.login-main{
  display: flex;
  flex-direction: row;
  justify-content: center;
  flex: 1;
  margin-top: -50px;
}
.el-divider--horizontal {
  margin: 0;
}
.login-title{
  font-weight: 700;
  color: #111111;
  font-size: 50px;
  margin-top: 30px;
}
div.app-header{
  position: -webkit-sticky;
  position: sticky;
  top: 0;
  z-index: 99;
}
</style>
