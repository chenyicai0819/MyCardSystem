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
          <el-form-item label="验证码">
            <el-input v-model="data.form.code" class="input-code"></el-input>
            <el-button type="primary" :disabled="data.isdisabled" style="width: 100px" @click="getAuthCode">{{data.getCode}}</el-button>
          </el-form-item>
          <!--<el-form-item label="验证码">-->
          <!--  <div @click="refreshCode()" class="code" style="cursor:pointer;" title="点击切换验证码">-->
          <!--    <Verify/>-->
          <!--  </div>-->
          <!--</el-form-item>-->
          <el-button type="primary" @click="onSubmit">登录</el-button>
          <!--<el-button @click="Wechatlogin">微信授权</el-button>-->
          <el-button @click="toAl">访问al网址</el-button>
          <div v-if="data.isdisabled === true">
            验证码已发送至邮箱：{{data.form.email}}
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import {getCurrentInstance, reactive} from "vue";
import router from "../../router";
import qs from "qs";
import Verify from "./Verify";
import moment from "moment/moment";
import {ElMessage} from "element-plus";
export default {
  name: "Login",
  components: {Verify},
  setup(){
    const {proxy}=getCurrentInstance();
    const qs=require('qs');
    const data=reactive({
      form: {
        name: '',
        pass: '',
        code: '',
        email:'',
      },
      authCodeTime:300, //短信验证码过期时间，默认300秒(5分钟)
      isCountDown:false, //是否开始倒计时
      getCode: '获取验证码', //获取验证码的按钮
      authCodeCountDownModel:60,//验证码倒计时
      authCodeCountDown:60, // 验证码倒计时，显示出来的
      isdisabled:false, // 获取验证码按钮是否禁用
      ismoblie:false,
      identifyCode: "",
      identifyCodes: ['0','1','2','3','4','5','6','7','8','9','a','b','c','d'], //根据实际需求加入自己想要的字符
    })
    const onSubmit = () => {
      // console.log(data.form.name);
      // console.log(data.form.pass);
      proxy.$axios.post('user/login',qs.stringify({ "userId":data.form.name,"userPass":data.form.pass,"code":data.form.code })).then(res=>{
        if ("允许登录"==res.data){
          localStorage.setItem("loginToken",data.form.name)
          router.push("/manages");
        }else{
          alert(res.data);
        }
      });
    }

    // 获取用户邮箱
    const getEmail = () => {
      proxy.$axios.get('user/getEmail', {params: {"id":data.form.name}}).then(res => {
        data.form.email = res.data
      });
    }
    // 获取短信验证码
    const getAuthCode = () => {
      proxy.$axios.get('user/getAuthCode', {params: {"id":data.form.name,"time":data.authCodeTime}}).then(res => {
        if (res.data == 1){
          getEmail()
          data.authCodeCountDown =data.authCodeCountDownModel;
          ElMessage.success("验证码邮件发送成功，请注意查收！")
          data.isCountDown = true;
          data.isdisabled = true;
          isCountDown()
        }else {
          ElMessage.error("出现错误，请重试！")
        }
      });
    }
    // 是否开始倒计时
    const isCountDown = () => {
      if (data.isCountDown === true){
        data.getCode = data.authCodeCountDown
        countDown()
      }
    }
    // 验证码失效倒计时
    const countDown = () => {
      data.authCodeCountDown = data.authCodeCountDown -1;
      if (data.authCodeCountDown < 0){
        data.isCountDown = false
        data.getCode = '获取验证码'
        data.isdisabled = false
      }
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
    // 生成随机数
    const randomNum = (min, max) => {
      max = max + 1
      return Math.floor(Math.random() * (max - min) + min);
    }
    // 更新验证码
    const refreshCode = () => {
      data.identifyCode = "";
      makeCode(data.identifyCodes, 4);
      console.log('当前验证码:',data.identifyCode);
    }
    // 随机生成验证码字符串
    const makeCode = (data, len) => {
      console.log('data, len:', data, len)
      for (let i = 0; i < len; i++) {
        data.identifyCode += data.identifyCodes[randomNum(0, data.identifyCodes.length-1)]
      }
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
    isCountDown()
    setInterval(isCountDown,"1000");
    // refreshCode()
    return{
      data,
      onSubmit,countDown,getAuthCode,isCountDown,getEmail,
      toLoad,toAl,Wechatlogin,isMobile,refreshCode,makeCode,randomNum,
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
  height: 350px;
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
  width: 210px;
}
.input-code{
  width: 100px;

}
</style>