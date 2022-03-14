<template>
  <transition name="el-fade-in-linear">
    <div class="app-register">
      <div class="app-header">
        <appHeader></appHeader>
        <el-divider></el-divider>
      </div>
      <div class="register-main">
        <el-card class="register-card">
          <div slot="header" class="clearfix">
            <span class="register-title">注册</span>
          </div>
          <el-form  ref="registerForm" label-width="70px" class="" :rules="registerRules" :model="registerForm" hide-required-asterisk="true">
            <el-form-item label="用户名" prop="userName">
              <el-input type="text" autocomplete="off"  size="small" class="userNameInput" placeholder="用户名" suffix-icon="el-icon-user" v-model="registerForm.userName"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="passWord">
              <el-input type="password"  autocomplete="off"  size="small" placeholder="密码" suffix-icon="el-icon-key" v-model="registerForm.passWord"></el-input>
            </el-form-item>
            <el-form-item label="性别" size="medium" prop="sex">
              <el-radio-group v-model="registerForm.sex">
                <el-radio border label="男"></el-radio>
                <el-radio border label="女"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="电话号码" prop="phone">
              <el-input type="text"  autocomplete="off"  size="small" placeholder="电话号码" suffix-icon="el-icon-mobile-phone" v-model="registerForm.phone"></el-input>
            </el-form-item>
            <el-form-item label="昵称" prop="name">
              <el-input type="text"  autocomplete="off"  size="small" placeholder="昵称" maxlength="30" show-word-limit v-model="registerForm.name"></el-input>
            </el-form-item>
            <el-form-item label="收货地址" prop="address">
              <el-input type="text"  autocomplete="off"  size="small" placeholder="收货地址" maxlength="50" show-word-limit v-model="registerForm.address"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitRegister('registerForm')" class="registerButton">提交</el-button>
              <el-button @click="resetForm('registerForm')">重置</el-button>
            </el-form-item>
          </el-form>
          <vCode
              :show="isShow"
              @success="vCodeSuccess"
              @close="vCodeClose"
              :imgs="[vCodeImg1]"
          />
        </el-card>
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
  name: "register",
  components: {
    appHeader,
    appFooter,
    vCode,
  },
  data() {
    return {
      registerForm: {
        userName: '',
        passWord: '',
        sex:'',
        phone:'',
        name:'',
        address:'',
      },
      registerRules:{
        userName:[
          {required:true,message:'请输入用户名',trigger:'blur'},
          {min:3,max:20,message:'用户名长度在3到20个字符',trigger: 'blur'}
        ],
        passWord:[
          {required:true,message:'请输入密码',trigger:'blur'},
          {min:3,max:30,trigger: 'blur'}
        ],
        sex:[
          {required:true,message:'请选择性别',trigger:'blur'},
        ],
        phone:[
          {required:true,message:'请输入电话号码',trigger:'blur'},
          {min:11,max:11,trigger: 'blur'}
        ],
        name:[
          {required:true,message:'请输入您的昵称',trigger:'blur'},
          {min:3,max:30,trigger: 'blur'}
        ],
        address:[
          {required:true,message:'请输入您的收货地址',trigger:'blur'},
          {min:3,max:50,trigger: 'blur'}
        ],
      },
      isShow:false,
      vCodeImg1,
    }
  },
  methods:{
    //提交注册请求
    submitRegister(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.isShow=true;
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    //重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    //验证图通过
    vCodeSuccess(){
        this.isShow=false;
        this.onSubmit();
    },
    //验证图关闭
    vCodeClose(){
      this.isShow=false;
    },
    //提交注册请求
    async onSubmit(){
      const { data} = await register({
        "name": this.registerForm.userName,
        "sex": this.registerForm.sex,
        "phone": this.registerForm.phone,
        "userName": this.registerForm.userName,
        "passWord": this.registerForm.passWord,
        "address": this.registerForm.address,
      })
      window.location.href = '/'
      console.log(data)
      this.openSuccess()
    },
    openSuccess() {
      this.$message({
        message: '恭喜你，注册成功!',
        type: 'success'
      });
    }


  },

}
import appHeader from "../../components/header/header";
import appFooter from "@/components/footer/footer";
import vCode from "vue-puzzle-vcode";
import vCodeImg1 from '@/static/images/vcodeimg1.jpeg'
import {register} from '@/api/user'
</script>

<style scoped>
.el-divider--horizontal {
  margin: 0;
}
div.app-register{
  display: flex;
  flex-direction: column;
  justify-content: center;
  min-height: 100vh;

}
div.register-main{
  flex: 1;
  background-image: url("../../static/images/background.jpg");
  background-size: 100% 100%;
}
.register-card{
  width: 391px;
  height: 520px;
  margin-left: 60%;
  margin-top: 30px;
  padding: 10px;
}
span.register-title{
  font-weight: 700;
  color: #111111;
  font-size: 30px;
}
.registerButton{
  margin-right: 100px;
}
div.app-header{
  position: -webkit-sticky;
  position: sticky;
  top: 0;
  z-index: 99;
}
</style>
