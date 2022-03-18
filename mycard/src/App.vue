<template>
  <!--<div id="nav">-->
  <!--  <router-link to="/">Home</router-link> |-->
  <!--  <router-link to="/about">About</router-link>-->
  <!--</div>-->
  <router-view/>
  <el-backtop :bottom="100">
    <div
        style="
        height: 100%;
        width: 100%;
        background-color: #42b983;
        box-shadow: 0 0 6px rgba(0, 0, 0, 0.12);
        text-align: center;
        line-height: 40px;
        color: white;
      "
    >
      UP
    </div>
  </el-backtop>

</template>

<script>
import {inject, reactive, watch} from "vue";
export default {
  name: "App",
  setup(){
    const appStore=inject('appStore')
    const data=reactive({
      isMoblie:appStore.isMoblie,
      backtop:[
        {text: '富强 民主 文明 和谐'},
        {text: '自由 平等 公正 法制'},
        {text: '爱国 敬业 诚信 友善'},
      ],
    })

    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      if (flag) {
        appStore.setIsMoblie(true)
      } else {
        appStore.setIsMoblie(false)
      }
    }
    isMobile()
    return{
      data,isMobile,
    }
  }
}
</script>

<style>
*{
  margin: 0;
  padding: 0;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#block{
  position: fixed;
  bottom: 0px;
  width: 100%;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
