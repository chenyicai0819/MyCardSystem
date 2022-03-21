<template>
  <h3>{{name}}</h3>
  <p>{{text}}</p>
  <div class="demo-image">
    <div :key="fit" class="block">
      <el-image
          style="width: 100px; height: 100px"
          :src="img"
          :fit="fit"
      ></el-image>
    </div>
  </div>
  <div class="Formail" v-if="isAd==0">
    <!--<a href="mailto:chenyc2021@qq.com?subject=广告招标&body=广告商家：初步报价：其他联系方式：">联系管理员</a>-->
    <el-button type="success" @click="dialogVisible = true">联系管理员</el-button>
  </div>
  <div>
    <el-dialog
        v-model="dialogVisible"
        title="您的广告内容"
        width="30%"
    >
      <div>
        <div>
          <span style="size: 18px;font-weight: bold">广告标题：</span>
          <el-input v-model="input.title" placeholder="广告标题" style="width: 80%"></el-input>
        </div>
        <div style="padding-top: 5px">
          <span style="size: 18px;font-weight: bold">广告内容：</span>
          <el-input v-model="input.text" autosize type="textarea" placeholder="广告内容" style="width: 80%"></el-input>
        </div>
        <div style="padding-top: 5px">
          <span style="size: 18px;font-weight: bold">广告报价：</span>
          <el-input v-model="input.money" placeholder="广告报价" style="width: 80%"></el-input>
        </div>
        <div style="padding-top: 5px">
          <span style="size: 18px;font-weight: bold">显示日期：</span>
          <el-input v-model="input.times" placeholder="显示日期" style="width: 80%"></el-input>
        </div>
        <div style="padding-top: 5px">
          <span style="size: 18px;font-weight: bold">联系方式：</span>
          <el-input v-model="input.phone" placeholder="联系方式" style="width: 80%"></el-input>
        </div>
        <div style="padding-top: 5px">
          <span style="size: 18px;font-weight: bold">您的邮箱：</span>
          <el-input v-model="input.email" placeholder="您的邮箱" style="width: 80%"></el-input>
        </div>
      </div>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="buyEmail">发送</el-button
        >
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {getCurrentInstance, defineComponent, reactive, toRefs} from "vue";
import {ElMessage} from "element-plus";


export default {
  name: "Ad",
  setup(){
    const {proxy}=getCurrentInstance();
    const data=reactive({
      fit:'fill',
      ad:[],
      show:true,
      name:'',
      text:'',
      img:'',
      upDate:'',
      downDate:'',
      isAd:1,
      dialogVisible:false,
      input:{
        title:'',
        text:'',
        money:'',
        phone:'',
        email:'',
        times:'',
      }
    })
    const buyEmail = () => {
      proxy.$axios.get('ad/buy',{params:{"title":data.input.title,
          "text":data.input.text,
          "money":data.input.money,
          "time":data.input.times,
          "phone":data.input.phone,
          "buyemail":data.input.email}
      }).then((res)=>{
        ElMessage({
          message: '邮件发送成功，请等待工作人员回复.',
          type: 'success',
        })
      })
      data.dialogVisible = false
    }
    const getAd = () => {
      proxy.$axios.get('ad/show',{}).then(res=>{
        data.ad=res.data;
        // console.log(res.data);
        if (res.data.length==0){
          data.name="广告位招租"
          data.isAd=0
        }
        data.name=data.ad[0].adName
        data.text=data.ad[0].adText
        data.img=data.ad[0].adImg
        data.upDate=data.ad[0].adUpDate
        data.downDate=data.ad[0].adDownDate
      });
    }
    const checkDate = () => {
      let day = new Date();
      let date=new Date();
      date.setTime(day*1000)
      // console.log(date);
      if (date-data.downDate>=0){
        // console.log("显示时间内")
      }else {
        // console.log("不在显示时间")
      }
    }
    
    getAd();
    return{
      ...toRefs(data),
      getAd,
      checkDate,buyEmail
    }
  }
}
</script>

<style scoped>

</style>