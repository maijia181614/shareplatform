<template>
  <transition name="el-fade-in-linear">
    <div class="app-myInfo">
      <div class="app-header">
        <appHeader></appHeader>
        <el-divider></el-divider>
      </div>
      <div class="myInfo-body">
        <el-card class="box-card editCard">
          <div slot="header" class="clearfix">
            <span>个人信息修改</span>
          </div>
          <el-form ref="editForm" :model="editForm" label-width="80px" :rules="editRules" hide-required-asterisk="true" >
            <el-form-item label="昵称" prop="name">
              <el-input type="text"  autocomplete="off"  size="small" placeholder="昵称" maxlength="30" show-word-limit v-model="editForm.name"></el-input>
            </el-form-item>
            <el-form-item label="收货地址" prop="address">
              <el-input type="text"  autocomplete="off"  size="small" placeholder="收货地址" maxlength="50" show-word-limit v-model="editForm.address"></el-input>
            </el-form-item>
            <el-form-item label="电话号码" prop="phone">
              <el-input type="text"  autocomplete="off"  size="small" placeholder="电话号码" suffix-icon="el-icon-mobile-phone" v-model="editForm.phone"></el-input>
            </el-form-item>
            <el-form-item label="性别" size="medium" prop="sex">
              <el-radio-group v-model="editForm.sex">
                <el-radio border label="男"></el-radio>
                <el-radio border label="女"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item class="editButtonItem">
              <el-button type="primary" @click="submitEdit('editForm')" class="editButton">提交</el-button>
              <el-button @click="resetForm('editForm')" class="resetButton">重置</el-button>
            </el-form-item>
          </el-form>
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
import appHeader from "@/components/header/header";
import appFooter from "@/components/footer/footer";
import {editInfo} from "@/api/user";

export default {
name: "myInfo",
  components: {
    appHeader,
    appFooter,
  },

  data(){
    return{
      editForm: {
        sex:'',
        phone:'',
        name:'',
        address:'',
      },

      editRules:{
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
      }

    }
  },

  methods:{
    //提交编辑
    submitEdit(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.onSubmit()
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    //提交编辑请求
    async onSubmit(){
      const { data} = await editInfo({
        "name": this.editForm.userName,
        "sex": this.editForm.sex,
        "phone": this.editForm.phone,
        "address": this.editForm.address,
      })
      window.location.href = '/'
      console.log(data)
      this.openSuccess()
    },

    openSuccess() {
      this.$message({
        message: '恭喜你，编辑个人信息成功!',
        type: 'success'
      });
    },

    //重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }


}
</script>

<style scoped>
.el-divider--horizontal {
  margin: 0;
}
div.app-myInfo{
  display: flex;
  min-height: 100vh;
  flex-direction: column;
  /*justify-content: center;*/
}
.myInfo-body{
  flex:1;
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-top: 20px;
}
.editCard{
  width: 391px;
  height: 520px;
  padding: 10px;
}
.editButtonItem{
  margin-top: 10px;
}
.editButton{
  width: 100px;
  margin-right: 60px;
}
.resetButton{
  width: 100px;
}
</style>
