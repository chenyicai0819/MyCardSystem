<template>
  <div>
    测试
    <el-button @click="shanxis">获取数据</el-button>
    <el-input>

    </el-input>
  </div>
</template>

<script>
import {apis} from "../../api/citymap";
import mapStore from "../../store/mapStore";

export default {
  name: "Index",
  setup(){
    const shanxis = () => {
      mapStore.setCitys("hainan")
      apis("shanxis").then((res)=>{
        console.log(res.data.data);
      })
    }

    return{
      shanxis,
    }
  }
}
</script>

<style scoped>

</style>