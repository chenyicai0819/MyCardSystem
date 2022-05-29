<template>
  <div id="wordCloud" style="height: 300px; width: 600px;"></div>
</template>

<script>
import {onMounted, reactive, toRef} from "vue";
import * as echarts from "echarts";
import 'echarts-wordcloud'

export default {
  name: "WordCloud",
  setup(){
    const data=reactive({
      echartsData: [
        { value: '30', name: 'Java' },
        { value: '29', name: 'Vue' },
        { value: '28', name: 'Linux' },
        { value: '27', name: 'Spring' },
        { value: '26', name: 'JavaScript' },
        { value: '25', name: 'HTML' },
        { value: '24', name: 'CSS' },
        { value: '23', name: 'MI' },
        { value: '22', name: 'GUET' },
        { value: '21', name: 'IDEA' },
        { value: '20', name: 'WebStorm' },
        { value: '16', name: 'Pytharm' },
        { value: '15', name: 'React' },
        { value: '14', name: 'Element' },
        { value: '13', name: 'Apache' },
        { value: '12', name: 'Nginx' },
        { value: '11', name: 'Tomcat' },
        { value: '10', name: 'Hexo' },
        { value: '9', name: 'CentOS' },
        { value: '8', name: 'Aliyun' },
      ],
    })
    
    const initChart = () => {
      let myChart = echarts.init(document.getElementById('wordCloud'));
      const option = {
        title: {
          text: '',
          x: "center"
        },
        backgroundColor: "#fff",
        // tooltip: {
        //   pointFormat: "{series.name}: <b>{point.percentage:.1f}%</b>"
        // },
        series: [
          {
            type: "wordCloud",
            //用来调整词之间的距离
            gridSize: 10,
            //用来调整字的大小范围
            // Text size range which the value in data will be mapped to.
            // Default to have minimum 12px and maximum 60px size.
            sizeRange: [14, 40],
            // Text rotation range and step in degree. Text will be rotated randomly in range [-90,                                                                             90] by rotationStep 45
            //用来调整词的旋转方向，，[0,0]--代表着没有角度，也就是词为水平方向，需要设置角度参考注释内容
            // rotationRange: [-45, 0, 45, 90],
            // rotationRange: [ 0,90],
            rotationRange: [0, 0],
            //随机生成字体颜色
            // maskImage: maskImage,
            textStyle: {
              color: function () {
                return (
                    "rgb(" +
                    Math.round(Math.random() * 255) +
                    ", " +
                    Math.round(Math.random() * 255) +
                    ", " +
                    Math.round(Math.random() * 255) +
                    ")"
                );
              }
            },
            //位置相关设置
            // Folllowing left/top/width/height/right/bottom are used for positioning the word cloud
            // Default to be put in the center and has 75% x 80% size.
            left: "center",
            top: "center",
            right: null,
            bottom: null,
            width: "100%",
            height: "100%",
            //数据
            data: data.echartsData
          }
        ]
      };
      myChart.setOption(option);
      // 点击某个字
      myChart.on('click', function (params) {
        console.log('myChart----click---:', params, '------', params.data)
      });
    }

    onMounted(()=>{
      initChart()
    })

    return{
      ...toRef(data),initChart,
    }
  }

}
</script>

<style scoped>

</style>