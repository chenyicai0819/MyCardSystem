<template>
  <div class="c404">
    <div class="c404h1div">
      <h1 class="c404h1">出错了！</h1>
      <h1 class="c404h1">404错误！</h1>
      <h1 class="c404h1">找不到界面</h1>
    </div>
    <div class="c404Buttons">
      <el-button size="large" type="success" plain @click="outHome(1)">返回首页</el-button>
      <el-button size="large" type="success" @click="outHome(2)">返回上一页</el-button>
    </div>
    <div>
      <div class="logo" id="logo">
        <el-image
            style="width: 100px; height: 100px; border-radius: 50%"
            src="https://cdn.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20211206122142.png"
            fit="fill"
            @click="play"
            id="img"
        ></el-image>
        <div style="width:200px;position: absolute;left: 110px;top: 20px;font-weight: bold">
          <p style="font-size: 30px;color: #FFFFFF">👈来听首歌吧</p>
        </div>
      </div>

    </div>
    <audio loop id="music">
      <source src="../assets/music.mp3" type="audio/mpeg">
      该浏览器不支持 audio 属性~
    </audio>
  </div>

</template>

<script>

import {onBeforeMount, onMounted, reactive, toRefs} from "vue";
import router from "@/router";
import mapStore from "@/store/mapStore";
export default {
  name: "404",
  setup(){

    const data=reactive({
      // nameAndAge:appStore.name
      ip:'',
      test:"返回首页",
      maxheight:0,
      audio: {
        // 旋转角度
        rotateVal:0,
        InterVal:0,
      }
    })

    const outHome = (id) => {
      if (id==1){
        router.push("/home");
      }else if (id==2){
        router.go(-1);
      }
    }

    /**
     * 点击头像播放音乐
     */
    const play = () => {
      const audio=window.document.getElementById("music")
      if (audio.paused) {
        audio.play();    // 播放
        rotate();
      }
      else {
        audio.pause();  // 暂停
        clearInterval(data.audio.InterVal)
      }
    }

    /**
     * 设置定时器
     */
    const rotate = () => {
      data.audio.InterVal=setInterval(function (){
        const img=window.document.getElementById("img")
        data.audio.rotateVal+=5;
        img.style.transform="rotate(" + data.audio.rotateVal + "deg)"
        img.style.transition="0.1s linear"

        if (data.audio.rotateVal == 36000) {
          img.style.transform=""
          img.style.transition=""
          data.audio.rotateVal = 0;
        }
      },100)
    }
    onBeforeMount(()=>{
      data.maxheight=window.screen.height

    })
    return{
      ...toRefs(data),outHome,play,rotate,
    }
  }
}
</script>

<style scoped>
.c404{
  height:100vh;
  background-color:#2F3242;
}
.c404h1div{
  padding-top: 200px;
  margin: auto;
  width: 250px;
}
.c404Buttons{
  padding-top: 10px;
  margin: auto;
  width: 250px;
}
.c404h1{
  color: white;
  margin: 0 auto;
  font-size: 40px;
  /*background-color: #42b983;*/
}
#logo{
  position: absolute;
  left: 20px;
  top: 20px;
}
</style>