<template>
  <div class="message-login">
    <div class="head-login">
      <h2 class="head-h2">在前往网站内容设置前请验证您的身份</h2>
      <el-button type="danger" @click="toLoad">返回首页</el-button>
    </div>
    <div :class="data.ismoblie==true?'login-mobile':'login-pc'">
      <div :class="data.ismoblie==true?'form-login-mobile':'form-login-pc'">
        <el-form ref="form" :model="data.form" >
          <el-form-item label="账户">
            <el-input v-model="data.form.name" class="input-login"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="data.form.pass" type="password" class="input-login"></el-input>
          </el-form-item>
          <el-button type="primary" @click="onSubmit">登录</el-button>
          <!--<el-button @click="Wechatlogin">微信授权</el-button>-->
          <el-button @click="toAl">访问al网址</el-button>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import {getCurrentInstance, reactive} from "vue";
import router from "../../router";
import qs from "qs";
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
      ismoblie:false,

    })
    const onSubmit = () => {
      // console.log(data.form.name);
      // console.log(data.form.pass);
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
    const toAl = () => {
      window.open("http://al.cyc0819.top")
    }
    const Wechatlogin = () => {
      router.push("/wechar");
    }
    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      if (flag) {
        // console.log('移动端打开')
        data.ismoblie=true
      } else {
        data.ismoblie=false
        // console.log('PC端打开')
      }
    }

    isMobile()
    return{
      data,
      onSubmit,
      toLoad,toAl,Wechatlogin,isMobile,
    }
  }
}
</script>

<style scoped>
.message-login{
  height: 100vh;
  background: #1FA2FF;  /* fallback for old browsers */
  background: -webkit-linear-gradient(to right, #A6FFCB, #12D8FA, #1FA2FF);  /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to right, #A6FFCB, #12D8FA, #1FA2FF); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

}
.head-login{
  display: flex;
}
.head-h2{
  width: 90%;
}
.login-pc{
  margin: 10px auto;
  width: 500px;
  height: 300px;
  background-color: #42b983;
}
.login-mobile{
  margin: 10px auto;
  width: 100%;
  height: 300px;
  background-color: #42b983;
}
.form-login-pc{
  padding-top: 50px;
  margin: auto;
  width: 300px;
}
.form-login-mobile{
  padding-top: 50px;
  margin: auto;
  width: 90%;
}
.input-login{
  width: 200px;
}
</style>