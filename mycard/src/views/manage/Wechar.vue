<template>
  <div class="login">
    <div class="form-login">
      <el-form ref="form" :model="data.form" >
        <el-form-item label="账户">
          <el-input v-model="data.form.name" class="input-login"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="data.form.pass" type="password" class="input-login"></el-input>
        </el-form-item>
        <el-button type="primary"  @click="Wechatlogin">微信授权</el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
import {getCurrentInstance, reactive} from "vue";
import qs from "qs";
import router from "../../router";
import { ElLoading } from 'element-plus'

export default {
  name: "Wechar",
  setup(){
    const {proxy}=getCurrentInstance();
    const qs=require('qs');
    const data=reactive({
      form: {
        name: '',
        pass: '',
      },
    })
    const Wechatlogin = () => {
      const loading = ElLoading.service({
        lock: true,
        text: 'Loading',
        background: 'rgba(0, 0, 0, 0.7)',
      })
      proxy.$axios.post('user/login',qs.stringify({ "userId":data.form.name,"userPass":data.form.pass,"code":"wechat"})).then(res=>{
        if ("允许登录"==res.data){
          window.location.href="http://www.cyc292.top:8089/wechat/oauth?id="+data.form.name
          loading.close()
        }else{
          loading.close()
          alert(res.data);
        }
      });

    }
    return{
      data,Wechatlogin,
    }
  }
}
</script>

<style scoped>

</style>