<template>
  <h3>{{data.name}}</h3>
  <p>{{data.text}}</p>
  <div class="demo-image">
    <div :key="data.fit" class="block">
      <el-image
          style="width: 100px; height: 100px"
          :src="data.img"
          :fit="data.fit"
      ></el-image>
    </div>
  </div>
  <div class="Formail" v-if="data.isAd==0">
    <a href="mailto:chenyc2021@qq.com?subject=广告招标&body=广告商家：初步报价：其他联系方式：">联系管理员</a>
  </div>
</template>

<script>
import {getCurrentInstance, defineComponent, reactive, toRefs} from "vue";

export default {
  name: "Ad",
  setup(){
    const {proxy}=getCurrentInstance();
    const data=reactive({
      fits:'fill',
      ad:[],
      show:true,
      name:'',
      text:'',
      img:'',
      upDate:'',
      downDate:'',
      isAd:1,
    })
    const getAd = () => {
      proxy.$axios.get('ad/show',{}).then(res=>{
        data.ad=res.data;
        console.log(res.data);
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
      console.log(date);
      if (date-data.downDate>=0){
        console.log("显示时间内")
      }else {
        console.log("不在显示时间")
      }
    }
    
    getAd();
    return{
      data,
      getAd,
      checkDate
    }
  }
}
</script>

<style scoped>

</style>