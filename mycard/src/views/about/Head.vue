<template>
  <div class="about-head">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>关于站长</span>
        </div>
      </template>
      <el-descriptions :column="ismoblie==true?1:3">
        <el-descriptions-item label="姓名：">陈益财</el-descriptions-item>
        <el-descriptions-item label="联系电话：">15500932013</el-descriptions-item>
        <el-descriptions-item label="城市：">广西桂林</el-descriptions-item>
        <el-descriptions-item label="标签:">
          <el-tag size="small" v-for="tag in tagList" style="margin-right: 5px;margin-top: 3px">{{tag.value}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="联系地址：">广西壮族自治区-桂林市-桂林电子科技大学-花江校区</el-descriptions-item>
      </el-descriptions>
    </el-card>
  </div>
</template>

<script>
import {reactive, toRefs} from "vue";

export default {
  name: "Head",
  setup(){
    const data=reactive({
      audioUrl:"../../assets/music.mp3",
      tagList:[
          {value:"Java"},
        {value:"JavaScript"},
        {value:"HTML"},
        {value:"Vue"},
        {value:"Linux"}],
      ismoblie:false,
    })

    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      if (flag) {
        data.ismoblie=true
      } else {
        data.ismoblie=false
      }
    }


    isMobile()
    return{
      ...toRefs(data),isMobile,
    }
  }
}
</script>

<style scoped>
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


</style>