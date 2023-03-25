<template>
  <div>

  </div>
</template>

<script>
import {apis} from "../../api/citymap";
import mapStore from "../../store/mapStore";
import {onBeforeMount, onMounted, reactive, toRefs} from "vue";
import AMapLoader from '@amap/amap-jsapi-loader';
import { shallowRef } from '@vue/reactivity'

export default {
  name: "Maps",
  setup(){
    const map = shallowRef(null);
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
      showCard:false,
      lng:'',
      lat:'',
    })
    const getAllProvince = () => {
      for (let i = 0; i < data.provinceList.length; i++) {
        // console.log(data.provinceList[i].key);
        apis(data.provinceList[i].key).then((res)=>{
          // console.log(res.data);
          data.allProvince[i]=res.data.data
          // console.log(data.allProvince);
        })
      }

    }
    const getMap = (province) => {
      apis(province).then((res)=>{
        // console.log(res.data.data);
        data.citys=res.data.data.city
        console.log(data.citys);
      })
    }
    /**
     * 获取当前IP所在的城市
     */
    const getIpCity = () => {

      AMapLoader.load({
        key: 'd6c8f397b0da87524290b6622de498f5',
        version: '1.4.15',
        plugins: ['AMap.Geolocation','AMap.Geocoder'],

      }).then(AMap => {
        // const map = new AMap.Map('container')
        // 这里也可以写实例，这边我用不到，如果用别忘了写 html
        // <div id="container" width="100" height="100" ></div>
        const geolocation = new AMap.Geolocation({
          enableHighAccuracy: true, // 高精度开启
          radius: 10000,
          extensions: 'all'
        })

        geolocation.getCurrentPosition()
        AMap.event.addListener(geolocation, 'complete', onComplete)
        AMap.event.addListener(geolocation, 'error', onError)

        function onComplete (data) {
          // console.log(data.position)
          // console.log('data是具体的定位信息', data)
          console.log(data.position.lng);
          console.log(data.position.lat);
          data.lng=data.position.lng
          data.lat=data.position.lat
          // onLoad(lng, lat) 从这里调用你的接口
        }
        function onError (err) {
          // 定位出错
          console.log('定位失败', err)
        }
        var geocoder = new AMap.Geocoder({
          radius: 1000,
          extensions: "all"
        });
        geocoder.getAddress([110.410421,25.309698], function(status, result) {
          if (status === 'complete' && result.info === 'OK') {
            console.log(result);
          }else {
            // alert('地址获取失败')
          }
        });
      }).catch((e) => {
        console.log(e)
      })
    }
    // getMap()
    // getIpCity()
    onBeforeMount(()=>{
      getAllProvince()
      mapStore.setMaplist(data.allProvince)
    })
    return{
      getMap,getAllProvince,map,
      ...toRefs(data),getIpCity,
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