<template>
  <div>
    测试
    <el-button @click="getAllProvince">获取数据</el-button>
  </div>



</template>

<script>
import {apis} from "../../api/citymap";
import mapStore from "../../store/mapStore";
import {onBeforeMount, onMounted, reactive, toRefs} from "vue";

export default {
  name: "Index",
  setup(){

    const data=reactive({
      provinceList:[{'key': 'beijing'}, {'key': 'tianjin'}, {'key': 'shanghai'}, {'key': 'chongqing'},
        {'key': 'neimenggu'}, {'key': 'guangxi'}, {'key': 'xizang'}, {'key': 'ningxia'}, {'key': 'xinjiang'},
        {'key': 'xianggang'}, {'key': 'aomen'}, {'key': 'hebei'}, {'key': 'shanxi'}, {'key': 'liaoning'},
        {'key': 'jilin'}, {'key': 'heilongjiang'}, {'key': 'jiangsu'}, {'key': 'zhejiang'}, {'key': 'anhui'},
        {'key': 'fujian'}, {'key': 'jiangxi'}, {'key': 'shandong'}, {'key': 'henan'}, {'key': 'hubei'},
        {'key': 'hunan'}, {'key': 'guangdong'}, {'key': 'hainan'}, {'key': 'sichuan'}, {'key': 'guizhou'},
        {'key': 'yunnan'}, {'key': 'shanxis'}, {'key': 'gansu'}, {'key': 'qinghai'}, {'key': 'taiwan'}],
      allProvince: [],
      citys:{},
      showCard:false
    })
    const getAllProvince = () => {
      for (let i = 0; i < data.provinceList.length; i++) {
        // console.log(data.provinceList[i].key);
        apis(data.provinceList[i].key).then((res)=>{
          // console.log(res.data);
          data.allProvince[i]=res.data.data
          console.log(data.allProvince[i]);
        })
      }

    }
    const getMap = (province) => {
      apis(province).then((res)=>{
        console.log(res.data.data);
        data.citys=res.data.data.city
      })
    }


    onBeforeMount(()=>{
      getAllProvince()
      mapStore.setMaplist(data.allProvince)
    })
    // getAllProvince()

    return{
      getMap,getAllProvince,
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

.box-card {
  width: 480px;
  margin-top: 5px;
}
</style>