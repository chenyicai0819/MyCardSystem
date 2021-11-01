<template>
  <div class="head-login">
    <h2 class="head-h2">在前往网站内容设置前请验证您的身份</h2>
    <el-button type="danger" @click="toLoad">返回首页</el-button>
  </div>
  <div class="login">
    <div class="form-login">
      <el-form ref="form" :model="data.form" >
        <el-form-item label="账户">
          <el-input v-model="data.form.name" class="input-login"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="data.form.pass" type="password" class="input-login"></el-input>
        </el-form-item>
        <el-button type="primary" @click="onSubmit">登录</el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
import {getCurrentInstance, reactive} from "vue";
import router from "../../router";
export default {
  name: "Login",
  setup(){
    const {proxy}=getCurrentInstance();
    const qs=require('qs');
    const data=reactive({
      form: {
        name: '',
        pass: '',
      },
    })
    const onSubmit = () => {
      console.log(data.form.name);
      console.log(data.form.pass);
      proxy.$axios.post('user/login',qs.stringify({ "userId":data.form.name,"userPass":data.form.pass })).then(res=>{
        if ("允许登录"==res.data){
          localStorage.setItem("loginToken",data.form.name)
          router.push("/manages");
        }else{
          alert(res.data);
        }
      });
    }
    const toLoad = () => {
      router.push("/home");
    }
    return{
      data,
      onSubmit,
      toLoad
    }
  }
}
</script>

<style scoped>
.head-login{
  display: flex;
}
.head-h2{
  width: 90%;
}
.login{
  margin: 10px auto;
  width: 500px;
  height: 300px;
  background-color: #42b983;
}
.form-login{
  padding-top: 50px;
  margin: auto;
  width: 300px;
}
.input-login{
  width: 200px;
}
</style>