<template>
  <div id="choose" v-loading="loading">
    <!--快捷搜索-->
    <!--简易模式只保存搜索-->
    <div style="width: 100%;margin: 100px;">
      <el-card class="sea-card">
        <QuiteSearch :class="ismoblie==true?'qsForMobile':'qsForPC'"/>
      </el-card>
    </div>
  </div>
</template>

<script>
import QuiteSearch from "./QuiteSearch";
import {reactive, toRefs} from "vue";

export default {
  name: "Choose_easy",
  components: {QuiteSearch},
  setup(){
    const data=reactive({
      ismoblie:false,
      loading: false,
    })

    return{
      ...toRefs(data),
    }
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
  height: 93vh;
  /*background-color: #BDBDBD;*/
  /*background-image: url("https://cdn.jsdelivr.net/gh/chenyicai0819/MyImage/Imgs/20220308133544.png");*/
  display: flex;
  justify-content: center;
  flex-flow: wrap;
}
.sea-card{
  width: 91.5%;
  margin: 0 auto ;
  background: rgba(255, 255, 255, 0.46);
}
.qsForPC{
  width: 80%;
}
.qsForMobile{
  width: 90%;
}
</style>