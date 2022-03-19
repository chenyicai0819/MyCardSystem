<template>
  <div id="choose" v-loading="data.loading">
    <!--快捷搜索-->
    <div style="width: 100%;margin: 5px;">
      <el-card class="sea-card" style="width: 91.5%;margin: 0 auto ;background: rgba(255, 255, 255, 0.46);">
        <QuiteSearch :class="data.ismoblie==true?'qsForMobile':'qsForPC'"/>
      </el-card>
    </div>

    <!--功能插件-->
    <div :class="data.ismoblie==true?'function1':'function2'">
      <el-card id="timecard" class="sea-card" style="width: 100%;margin: 0 auto ;background: rgba(255, 255, 255, 0.46);">
        <!--时间插件-->
        <div id="mycard-time" style="">
          <div class="time" :class="data.ismoblie==true?'function-time1':'function-time2'">
            {{data.nowTime}}
          </div>
          <div class="date" :class="data.ismoblie==true?'function-date1':'function-date2'">
            {{data.nowDate}} {{data.nowDay}}
          </div>
        </div>
      </el-card>
    </div>
    <div id="mycard-weather-box" :class="data.ismoblie==true?'function1':'function2'" style="margin-left: 20px">
      <div id="he-plugin-standard" style="margin-left: 20px;"></div>
      <!--<el-card class="sea-card" style="width: 91.5%;margin: 0 auto ;background: rgba(255, 255, 255, 0.46);">-->
      <!--  &lt;!&ndash;天气插件&ndash;&gt;-->

      <!--</el-card>-->
    </div>
    <!--入口-->
    <div class="choosecard" :class="{choosecardmoblie:data.ismoblie}" v-for="name in data.cardname" >
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ name.cardName }}</span>
            <!--<el-button type="text" id="choose-link" @click="toLink(name.cardLink)">前往链接</el-button>-->
            <a :href="name.cardLink" id="choose-link" @click="toLink(name.cardLink)">前往链接</a>
          </div>
        </template>
        <el-image
            :src="name.cardImg"
            class="image"
            style="width: 100px; height: 100px"
        >
          <template #error>
            <div class="image-slot">
              <el-icon><icon-picture /></el-icon>
            </div>
          </template>
        </el-image>
        <!--<div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>-->
      </el-card>
    </div>
  </div>
</template>

<script>
import {reactive,getCurrentInstance} from "vue";
import QuiteSearch from "./QuiteSearch";
import router from "@/router";
import {ElMessage} from "element-plus";
export default {
  name: "Choose",
  components: {QuiteSearch},
  setup(){
    const {proxy}=getCurrentInstance();
    const data=reactive({
      cardname:[],
      ismoblie:false,
      loading: false,
      nowTime:'',
      nowDate:'',
      nowDay:'',
    })
    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      if (flag) {
        data.ismoblie=true
      } else {
        data.ismoblie=false
      }
    }
    const getCard = () =>{
      data.loading=true
      proxy.$axios.get('card/show',{}).then(res=>{
        const getdata=res.data;
        data.cardname=getdata;
        data.loading=false
      });
    }
    /**
     * 获取当前时间
     */
    const mygetTime = () => {
      let mytime=new Date();
      let hou=mytime.getHours();
      if (hou<10){hou='0'+hou}
      let min=mytime.getMinutes();
      if (min<10){min='0'+min}
      let sec=mytime.getSeconds();
      if (sec<10){sec='0'+sec}
      data.nowTime=hou+":"+min+":"+sec

    }

    /**
     * 前往链接
     */
    const toLink = (link) => {
      if (link=="/#/"){
        ElMessage.error('功能正在开发中')
      }
    }

    /**
     * 获取今天日期
     */
    const mygetDate = () => {
      let mydate=new Date();
      let yea=mydate.getFullYear();
      let mon=mydate.getMonth()+1;
      let dat=mydate.getDate();
      let days=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"]
      data.nowDate=yea+"年"+mon+"月"+dat+"日"
      data.nowDay=days[mydate.getDay()]
    }
    setInterval(mygetDate,"1000")
    setInterval(mygetTime,"1000");
    isMobile();
    getCard();
    return{
      data,
      isMobile,
      getCard,mygetTime,mygetDate,toLink
    }
  },
  created() {
    const widths = window.innerWidth;
    let weatherWidth;
    // let tar=document.getElementById('mycard-weather-box');
    // // 根据设备，切换天气的大小
    // let weatherWidth=tar.offsetWidth
    // let weatherHeight=tar.offsetHeight
    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      if (flag) {
        weatherWidth=widths*0.90;
        // weatherWidth=widths*0.80;
      } else {
        weatherWidth=widths*0.37;
      }
    }
    isMobile()
    window.WIDGET = {
      "CONFIG": {
        "layout": "1",
        "width": weatherWidth,
        "height": "239.2",
        "background": "1",
        "dataColor": "FFFFFF",
        "borderRadius": "5",
        "key": "94096c96a79b4e3d9bf421612639f9e9"
      }
    };
    (function (d) {
      var c = d.createElement('link')
      c.rel = 'stylesheet'
      c.href = 'https://widget.heweather.net/standard/static/css/he-standard.css?v=1.4.0'
      var s = d.createElement('script')
      s.src = 'https://widget.heweather.net/standard/static/js/he-standard.js?v=1.4.0'
      var sn = d.getElementsByTagName('script')[0]
      sn.parentNode.insertBefore(c, sn)
      sn.parentNode.insertBefore(s, sn)
    })(document)
  }
}
</script>

<style scoped>
*{
  margin:0;
  padding: 0;
}
#choose{
  width: 100%;
  /*height: 93vh;*/
  /*background-color: #BDBDBD;*/
  /*background-image: url("https://cdn.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220308133544.png");*/
  display: flex;
  justify-content: center;
  flex-flow: wrap;
}
.choosecard{
  margin: 5px;
  width: 30%;
}
.choosecardmoblie{
  margin: 5px;
  width: 90%;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 100%;
  background: rgba(255, 255, 255, 0.46);
}
.qsForPC{
  width: 50%;
}
.qsForMobile{
  width: 90%;
}
.function1{
  width: 100%;
  margin: 5px;
}
.function2{
  width: 45%;
}
.function-time1{
  color: rgb(255, 255, 255);
  /*text-shadow: 5px 5px 5px #42b983;*/
  font-size: 70px;

}
.function-time2{
  color: rgb(255, 255, 255);
  font-size: 126px;
  /*text-shadow: 5px 5px 5px #42b983;*/
  margin-top: 10px
}
.function-date1{
  color: rgb(255, 255, 255);
  /*text-shadow: 5px 5px 5px #42b983;*/
  font-size: 20px;
}
.function-date2{
  color: rgb(255, 255, 255);
  /*text-shadow: 5px 5px 5px #42b983;*/
  font-size: 26px;
  margin-bottom: 10px
}
#choose-link{
  font-size: 16px;
  font-family: 微软雅黑;
  font-weight: bolder;
  text-decoration: none;
  color: #2c3e50
}
#choose-link:hover{
  color: #1FA2FF;
}
</style>