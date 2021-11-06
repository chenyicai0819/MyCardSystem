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
    })
    const getAd = () => {
      proxy.$axios.get('ad/show',{}).then(res=>{
        data.ad=res.data;
        console.log(res.data);
        data.name=data.ad[0].adName
        data.text=data.ad[0].adText
        data.img=data.ad[0].adImg
        data.upDate=data.ad[0].adUpDate
        data.downDate=data.ad[0].adDownDate
        checkDate()
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
      checkDate,
    }
  }
}
</script>

<style scoped>

</style>