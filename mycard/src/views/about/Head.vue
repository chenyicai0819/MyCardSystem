<template>
  <div class="about-head">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>关于站长</span>
        </div>
      </template>
      <el-descriptions>
        <el-descriptions-item label="姓名：">陈益财</el-descriptions-item>
        <el-descriptions-item label="联系电话：">15500932013</el-descriptions-item>
        <el-descriptions-item label="城市：">广西桂林</el-descriptions-item>
        <el-descriptions-item label="标签:">
          <el-tag size="small" v-for="tag in tagList" style="margin-right: 5px">{{tag.value}}</el-tag>
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
        {value:"React"},
        {value:"Linux"}]
    })

    return{
      ...toRefs(data)
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