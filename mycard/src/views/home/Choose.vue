<template>
  <div class="choose-container" v-loading="data.loading">
    <!-- 快捷搜索区域 -->
    <section class="search-section">
      <el-card class="search-card">
        <QuiteSearch :class="data.ismoblie ? 'search-mobile' : 'search-desktop'"/>
      </el-card>
    </section>

    <!-- 功能区域 -->
    <section class="features-section">
      <!-- 时间卡片 -->
      <el-card class="feature-card time-card">
        <div class="time-display">
          <div class="time" :class="{'time-mobile': data.ismoblie}">
            {{data.nowTime}}
          </div>
          <div class="date" :class="{'date-mobile': data.ismoblie}">
            {{data.nowDate}} {{data.nowDay}}
          </div>
        </div>
      </el-card>

      <!-- 天气卡片 -->
      <div class="weather-card">
        <div id="he-plugin-standard"></div>
      </div>
    </section>

    <!-- 卡片网格区域 -->
    <section class="cards-grid">
      <el-card 
        v-for="card in data.cardname" 
        :key="card.cardName"
        class="grid-card"
        :class="{'card-mobile': data.ismoblie}"
      >
        <template #header>
          <div class="card-header">
            <span class="card-title">{{ card.cardName }}</span>
            <a :href="card.cardLink" class="card-link" @click="toLink(card.cardLink)">
              访问
              <el-icon><ArrowRight /></el-icon>
            </a>
          </div>
        </template>
        <el-image
          :src="card.cardImg"
          class="card-image"
          fit="cover"
        >
          <template #error>
            <div class="image-placeholder">
              <el-icon><Picture /></el-icon>
            </div>
          </template>
        </el-image>
      </el-card>
    </section>

    <!-- 页脚 -->
    <footer class="site-footer" :class="{'footer-mobile': data.ismoblie}">
      <el-divider />
      <div class="footer-content">
        <span>Copyright © 2020-{{ new Date().getFullYear() }} cyc292.top</span>
        <a href="https://beian.miit.gov.cn/#/Integrated/index" target="_blank" class="beian-link">
          <el-image
            src="https://z3.ax1x.com/2021/10/08/59OMgU.png"
            class="beian-icon"
          />
          琼ICP备2021006962号
        </a>
      </div>
    </footer>
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
.choose-container {
  width: 100%;
  min-height: 100vh;
  padding: 1.5rem;
}

.search-section {
  margin-bottom: 2rem;
}

.search-card {
  background: rgba(255, 255, 255, 0.9) !important;
  backdrop-filter: blur(10px);
  border: none;
  border-radius: 12px;
}

.search-desktop {
  width: 60%;
  margin: 0 auto;
}

.search-mobile {
  width: 100%;
}

.features-section {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 1.5rem;
  margin-bottom: 2rem;
}

.feature-card {
  background: rgba(255, 255, 255, 0.9) !important;
  backdrop-filter: blur(10px);
  border: none;
  border-radius: 12px;
  overflow: hidden;
}

.time-display {
  text-align: center;
  padding: 1.5rem;
}

.time {
  font-size: 3.5rem;
  font-weight: 700;
  color: #1e293b;
  line-height: 1.2;
  margin-bottom: 0.5rem;
}

.date {
  font-size: 1.2rem;
  color: #64748b;
}

.time-mobile {
  font-size: 2.5rem;
}

.date-mobile {
  font-size: 1rem;
}

.cards-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 1.5rem;
  margin-bottom: 2rem;
}

.grid-card {
  background: rgba(255, 255, 255, 0.9) !important;
  backdrop-filter: blur(10px);
  border: none;
  border-radius: 12px;
  transition: transform 0.3s ease;
}

.grid-card:hover {
  transform: translateY(-5px);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
}

.card-title {
  font-size: 1.2rem;
  font-weight: 600;
  color: #1e293b;
}

.card-link {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  text-decoration: none;
  color: #3b82f6;
  font-weight: 500;
  transition: color 0.3s ease;
}

.card-link:hover {
  color: #2563eb;
}

.card-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
  border-radius: 8px;
}

.image-placeholder {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 200px;
  background: #f1f5f9;
  color: #94a3b8;
  font-size: 2rem;
}

.site-footer {
  text-align: center;
  padding: 2rem 0;
  color: #64748b;
}

.footer-content {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  align-items: center;
}

.beian-link {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  text-decoration: none;
  color: #64748b;
}

.beian-icon {
  width: 15px;
  height: 15px;
}

@media (max-width: 768px) {
  .choose-container {
    padding: 1rem;
  }

  .cards-grid {
    grid-template-columns: 1fr;
  }

  .card-mobile {
    width: 100%;
  }

  .footer-mobile {
    padding: 1rem 0;
  }
}
</style>