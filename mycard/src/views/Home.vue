<template>
  <div class="home">
    <Select msgforselect="欢迎来到George的Card"/>
  </div>
  <div id="block">
    <p> </p>
    <el-carousel trigger="click" height="50px" indicator-position="none" arrow="never">
      <el-carousel-item v-for="item in data.backtop" :key="item">
        <p class="small" style="font-size: 36px">{{ item.text }}</p>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import Select from "./home/Select";
import {inject, reactive} from "vue";
export default {
  name: 'Home',
  components: {
    Select
  },
  setup(){
    const appStore=inject('appStore')

    const data=reactive({
      // nameAndAge:appStore.name
      ip:'',
      backtop:[
        {text: '富强 民主 文明 和谐'},
        {text: '自由 平等 公正 法制'},
        {text: '爱国 敬业 诚信 友善'},
      ],
    })
    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      if (flag) {
        console.log('移动端打开')
      } else {
        console.log('PC端打开')
      }
    }

    return{
      isMobile,
      data,
    }
  }
}
</script>
<style>
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