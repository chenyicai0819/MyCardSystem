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
    <el-card class="box-card" >
      <template #header>
        <div class="card-header">
          <span>地图位置&我的词云</span>
        </div>
      </template>
      <div class="card-header-div">
        <div id="container" :class="ismoblie==true?'map1':'map2'" />
        <div>
          <WordCloud/>
        </div>
      </div>
    </el-card>
    <el-card class="box-card" >
      <template #header>
        <div class="card-header">
          <span>我的相册</span>
        </div>
      </template>
      <div class="card-header-images">
        <div v-for="url in urls" style="width: 30%;height: 30%;display: inline;">
          <el-image
              style="width: 30%;height: 30%"
              :preview-src-list="urls"
              :key="url"
              :src="url"
              lazy />
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import {onMounted, reactive, toRefs} from "vue";
import {Discount} from "@element-plus/icons";
import WordCloud from "./WordCloud";

export default {
  name: "Head",
  components: {WordCloud, Discount},
  setup(){
    const data=reactive({
      audioUrl:"../../assets/music.mp3",
      tagList:[
          {value:"Java"},
        {value:"JavaScript"},
        {value:"HTML"},
        {value:"Vue"},
        {value:"Linux"}
      ],
      ismoblie:false,
      urls:['https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
          'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
          'https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg',
          'https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg',
          'https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg',
          'https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg',
          'https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg',
        ],
    })

    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      if (flag) {
        data.ismoblie=true
      } else {
        data.ismoblie=false
      }
    }

    onMounted(()=>{
      //页面加载完,开始异步引入高德地图
      //创建了一个回调函数,高德地图加载完毕会调用
      window.onLoad = function () {
        // 所有关于地图的逻辑全部都要写在这个回调里面
        // 保证高德地图加载完毕
        // var map = new AMap.Map("container");
        var map = new AMap.Map("container", {
          zoom: 16, //级别
          center: [110.417813,25.31373], //中心点坐标
          viewMode: "3D", //使用3D视图
        });

      };
      // key是申请的值
      var url =
          "https://webapi.amap.com/maps?v=1.4.15&key=d6c8f397b0da87524290b6622de498f5&callback=onLoad";
      //创建一个 script dom元素
      // doc需要补全document
      var jsapi = document.createElement("script");
      //设定script标签属性
      jsapi.charset = "utf-8";
      jsapi.src = url;
      //将API文件引入页面中,加载完毕以后会调用函数
      document.head.appendChild(jsapi);
    })
    isMobile()
    return{
      ...toRefs(data),isMobile,
    }
  }
}
</script>
<style scoped>

.about-head{
  width: 90%;
  margin: 0 auto;
}
.box-card{
  width: 100%;
  margin-bottom: 10px;
}
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
#container {

}
.card-header-div{
  display: flex;
}
.map1{
  width:100%; height: 200px;
}
.map2{
  width:600px; height: 300px;
}


</style>