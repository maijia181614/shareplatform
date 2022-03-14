<template>
<div class="login-right-div">
  <el-form  ref="loginForm" label-width="100px" class="" :rules="rules" :model="ruleForm">
    <el-form-item label="用户名" prop="userName">
      <el-input type="text" autocomplete="off" v-model="ruleForm.userName" size="small" class="userNameInput" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="passWord">
      <el-input type="password"  autocomplete="off" v-model="ruleForm.passWord" size="small" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitLogin('loginForm')" class="loginButton">提交</el-button>
      <el-button @click="resetForm('loginForm')">重置</el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script>
import {login} from '@/api/user'
export default {
name: "login-right",
  data(){
    return{
      ruleForm:{
        userName:'',
        passWord:'',
      },
      rules:{
        userName:[
          {required:true,message:'请输入用户名',trigger:'blur'},
          {min:3,max:20,message:'用户名长度在3到20个字符',trigger: 'blur'}
        ],
        passWord:[
          {required:true,message:'请输入密码',trigger:'blur'},
          {min:3,max:30,trigger: 'blur'}
        ],
      }
    }
  },
  methods:{
  //提交登录
  submitLogin(formName){
    this.$refs[formName].validate((valid) => {
      if (valid) {
        this.onSubmit();
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
    //提交登录请求
    async onSubmit(){
      try{
        await login({
          "userName": this.ruleForm.userName,
          "passWord": this.ruleForm.passWord,
        }).then((data)=>{
          localStorage.setItem('token', data.toString())
          console.log(data)
          console.log(localStorage.getItem('token'))
          this.openSuccess();
          this.$router.push({name:'home'})
        })

      }catch (e) {
        console.log(e)
      }
    },
    openSuccess() {
      this.$message({
        message: '恭喜你，登录成功!',
        type: 'success'
      });
    }

  }




}
</script>

<style scoped>
.userNameInput{
  width: 230px;
}
.loginButton{
  margin-right: 50px;
}
div.login-right-div{
  display: flex;
  flex-direction: column;
  justify-content: center;
}
</style>
