<template>
  <div id="choose" v-loading="data.loading">
    <div style="width: 100%;">
      <el-card class="sea-card" style="width: 91.5%;margin: 0 auto ;background: rgba(255, 255, 255, 0.46);">
        <QuiteSearch :class="data.ismoblie==true?'qsForMobile':'qsForPC'"/>
      </el-card>
    </div>
    <div style="width: 100%">
      <el-card class="sea-card" style="width: 91.5%;margin: 0 auto ;background: rgba(255, 255, 255, 0.46);">
        <div id="he-plugin-standard"></div>
      </el-card>
    </div>
    <div class="choosecard" :class="{choosecardmoblie:data.ismoblie}" v-for="name in data.cardname" >
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ name.cardName }}</span>
            <a :href="name.cardLink" style="text-decoration: none;font-weight: bold">前往链接</a>
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
export default {
  name: "Choose",
  components: {QuiteSearch},
  setup(){
    const {proxy}=getCurrentInstance();
    const data=reactive({
      cardname:[],
      ismoblie:false,
      loading: false,
    })
    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      if (flag) {
        console.log('移动端打开')
        data.ismoblie=true
      } else {
        data.ismoblie=false
        console.log('PC端打开')
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

    isMobile();
    getCard();
    return{
      data,
      isMobile,
      getCard,
    }
  },
  created() {
    window.WIDGET = {
      "CONFIG": {
        "layout": "1",
        "width": "500",
        "height": "200",
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
  /*background-color: #BDBDBD;*/
  background-image: url("https://cdn.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220308133544.png");
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
</style>