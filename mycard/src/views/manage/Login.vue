<template>
  <div class="login-container">
    <!-- 背景动画效果 -->
    <div class="background-animation">
      <div class="gradient-bg"></div>
    </div>

    <!-- 主要内容 -->
    <div class="content-wrapper">
      <!-- 顶部导航 -->
      <nav class="login-nav">
        <h2>身份验证</h2>
        <el-button 
          class="back-button" 
          @click="toLoad"
          type="warning"
          plain
        >
          <i class="el-icon-arrow-left"></i>
          返回首页
        </el-button>
      </nav>

      <!-- 登录表单 -->
      <div class="login-form-container" :class="{ 'mobile': data.ismoblie }">
        <div class="form-wrapper">
          <h3 class="form-title">管理员登录</h3>
          
          <el-form 
            ref="form" 
            :model="data.form"
            class="login-form"
          >
            <el-form-item>
              <el-input
                v-model="data.form.name"
                placeholder="请输入账户"
                prefix-icon="el-icon-user"
              />
            </el-form-item>

            <el-form-item>
              <el-input
                v-model="data.form.pass"
                type="password"
                placeholder="请输入密码"
                prefix-icon="el-icon-lock"
                show-password
              />
            </el-form-item>

            <el-form-item>
              <div class="verification-code">
                <el-input
                  v-model="data.form.code"
                  placeholder="验证码"
                  prefix-icon="el-icon-key"
                />
                <el-button 
                  type="primary" 
                  :disabled="data.isdisabled"
                  @click="getAuthCode"
                  class="code-button"
                >
                  {{ data.getCode }}
                </el-button>
              </div>
            </el-form-item>

            <div class="form-footer">
              <el-button 
                type="primary" 
                class="submit-button"
                @click="onSubmit"
              >
                登录
              </el-button>
            </div>

            <!-- 验证码发送提示 -->
            <div v-if="data.isdisabled" class="email-hint">
              <i class="el-icon-message"></i>
              验证码已发送至：{{ data.form.email }}
            </div>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getCurrentInstance, inject, reactive} from "vue";
import router from "../../router";
import qs from "qs";
import Verify from "./Verify";
import moment from "moment/moment";
import {ElMessage} from "element-plus";
import Cron from "@/views/manage/Cron";
import md5 from 'js-md5';

export default {
  name: "Login",
  components: {Cron, Verify},

  setup(){
    const {proxy}=getCurrentInstance();
    const qs=require('qs');
    const formData = reactive({  // 表单数据
      name: "", // 名称
      logicConfig: "* * * * * ? *", // cron表达式
    });
    const data=reactive({
      form: {
        name: '',
        pass: '',
        code: '',
        email:'',
        logicConfig: "* * * * * ? *", // cron表达式
      },
      authCodeTime:300, //短信验证码过期时间，默认300秒(5分钟)
      isCountDown:false, //是否开始倒计时
      getCode: '获取验证码', //获取验证码的按钮
      authCodeCountDownModel:60,//验证码倒计时
      authCodeCountDown:60, // 验证码倒计时，显示出来的
      isdisabled:false, // 获取验证码按钮是否禁用
      ismoblie:false,
      isShowCronCore:true, //是否打开cron表达式配置狂
      identifyCode: "",
      identifyCodes: ['0','1','2','3','4','5','6','7','8','9','a','b','c','d'], //根据实际需求加入自己想要的字符
    })

    const changeCron = (val) => {
      if (typeof val !== "string") return false;
      formData.logicConfig = val;
    };

    // 登录
    const onSubmit = () => {
      // 密码md5加密
      let secret = "george";
      let md5Pass = md5(md5(data.form.pass)+secret);
      // console.log(data.form.pass)
      // console.log(md5Pass)
      proxy.$axios.post('user/login',qs.stringify({ "userId":data.form.name,"userPass":md5Pass,"code":data.form.code })).then(res=>{
        if ("允许登录"==res.data){
          // 登录成功后将token存储到store，然后再进入界面
          localStorage.setItem("loginToken",data.form.name)
          // 缓冲500毫秒，等待加载数据
          setTimeout(router.push("/manages"),500)

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
      data,changeCron,
      onSubmit,countDown,getAuthCode,isCountDown,getEmail,
      toLoad,toAl,Wechatlogin,isMobile,refreshCode,makeCode,randomNum,
    }
  }
}
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  position: relative;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* 背景动画 */
.background-animation {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 0;
}

.gradient-bg {
  position: absolute;
  width: 200%;
  height: 200%;
  background: linear-gradient(
    45deg,
    #12c2e9,
    #c471ed,
    #f64f59,
    #12c2e9
  );
  animation: gradient 15s ease infinite;
  background-size: 400% 400%;
  opacity: 0.8;
}

@keyframes gradient {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}

/* 主要内容 */
.content-wrapper {
  position: relative;
  z-index: 1;
  width: 100%;
  max-width: 1200px;
  margin: 0 2rem;
}

/* 导航栏 */
.login-nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2rem;
  padding: 1rem 2rem;
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border-radius: 12px;
}

.login-nav h2 {
  color: white;
  margin: 0;
  font-weight: 500;
}

.back-button {
  color: #fff !important;
  background-color: rgba(230, 162, 60, 0.9) !important;
  border-color: rgba(230, 162, 60, 0.9) !important;
}

.back-button:hover {
  background-color: rgba(230, 162, 60, 1) !important;
  border-color: rgba(230, 162, 60, 1) !important;
  color: #fff !important;
}

/* 登录表单容器 */
.login-form-container {
  max-width: 420px;
  margin: 0 auto;
  padding: 2rem;
}

.form-wrapper {
  background: rgba(255, 255, 255, 0.95);
  padding: 2rem;
  border-radius: 16px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
  backdrop-filter: blur(10px);
}

.form-title {
  text-align: center;
  color: #333;
  font-size: 1.5rem;
  margin-bottom: 2rem;
  font-weight: 500;
}

/* 表单样式 */
.login-form :deep(.el-input__inner) {
  height: 45px;
  border-radius: 8px;
  border: 1px solid #e0e0e0;
}

.login-form :deep(.el-input__prefix) {
  left: 10px;
}

.login-form :deep(.el-input__inner:focus) {
  border-color: #409EFF;
  box-shadow: 0 0 0 2px rgba(64, 158, 255, 0.1);
}

/* 验证码区域 */
.verification-code {
  display: flex;
  align-items: center;
  gap: 10px;
}

.verification-code :deep(.el-input) {
  flex: 1;
}

.code-button {
  height: 45px;
  padding: 0 20px;
  white-space: nowrap;
  border-radius: 8px;
  flex-shrink: 0; /* 防止按钮被压缩 */
}

/* 提交按钮 */
.form-footer {
  margin-top: 2rem;
}

.submit-button {
  width: 100%;
  height: 45px;
  border-radius: 8px;
  font-size: 1rem;
}

/* 邮箱提示 */
.email-hint {
  margin-top: 1rem;
  padding: 0.75rem;
  background: rgba(64, 158, 255, 0.1);
  border-radius: 8px;
  color: #409EFF;
  font-size: 0.9rem;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

/* 移动端适配 */
@media (max-width: 768px) {
  .content-wrapper {
    margin: 0 1rem;
  }

  .login-nav {
    padding: 1rem;
    margin-bottom: 1rem;
  }

  .login-form-container {
    padding: 1rem;
  }

  .form-wrapper {
    padding: 1.5rem;
  }

  .verification-code {
    gap: 8px; /* 移动端稍微减小间距 */
  }

  .code-button {
    padding: 0 12px;
    font-size: 0.9rem;
  }
}
</style>