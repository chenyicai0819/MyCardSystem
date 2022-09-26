<template>
  <div class="message-verify">
    <canvas id="s-canvas" :width="contentWidth" :height="contentHeight"></canvas>
  </div>
</template>

<script>
import {reactive, toRefs} from "vue";

export default {
  name: "Verify",
  props: {
    identifyCode: {
      type: String,
      default: '1234'
    },
    fontSizeMin: {
      type: Number,
      default: 18
    },
    fontSizeMax: {
      type: Number,
      default: 40
    },
    backgroundColorMin: {
      type: Number,
      default: 180
    },
    backgroundColorMax: {
      type: Number,
      default: 240
    },
    colorMin: {
      type: Number,
      default: 50
    },
    colorMax: {
      type: Number,
      default: 160
    },
    lineColorMin: {
      type: Number,
      default: 40
    },
    lineColorMax: {
      type: Number,
      default: 180
    },
    dotColorMin: {
      type: Number,
      default: 0
    },
    dotColorMax: {
      type: Number,
      default: 255
    },
    contentWidth: {
      type: Number,
      default: 111
    },
    contentHeight: {
      type: Number,
      default: 38
    }
  },
  setup(){
    const data = reactive({
      url:"localhost:8089/user/verify", //验证码图片获取地址
    })
    // 生成一个随机数
    const randomNum = (min, max) => {
      return Math.floor(Math.random() * (max - min) + min)
    }
    // 生成一个随机的颜色
    const randomColor = (min, max) => {
      let r = randomNum(min, max)
      let g = randomNum(min, max)
      let b = randomNum(min, max)
      return 'rgb(' + r + ',' + g + ',' + b + ')'
    }

    const drawPic = () => {
      let canvas = document.getElementById('s-canvas')
      let ctx = canvas.getContext('2d')
      ctx.textBaseline = 'bottom'
      // 绘制背景
      ctx.fillStyle = randomColor(this.props.backgroundColorMin, this.props.backgroundColorMax)
      ctx.fillRect(0, 0, this.props.contentWidth, this.props.contentHeight)
      // 绘制文字
      for (let i = 0; i < this.props.identifyCode.length; i++) {
        drawText(ctx, this.props.identifyCode[i], i)
      }
      // drawLine(ctx) // 绘制干扰线
      // drawDot(ctx) // 绘制干扰点
    }
    // 绘制文本
    const drawText = (ctx, txt, i) => {
      ctx.fillStyle = randomColor(this.props.colorMin, this.props.colorMax)
      ctx.font = randomNum(this.props.fontSizeMin, this.props.fontSizeMax) + 'px SimHei'
      let x = (i + 1) * (this.props.contentWidth / (this.props.identifyCode.length + 1))
      let y = randomNum(this.props.fontSizeMax, this.props.contentHeight - 5)
      var deg = randomNum(-30, 30) // 字符旋转角度(不超过45度比较好)
      // 修改坐标原点和旋转角度
      ctx.translate(x, y)
      ctx.rotate(deg * Math.PI / 180)
      ctx.fillText(txt, 0, 0)
      // 恢复坐标原点和旋转角度
      ctx.rotate(-deg * Math.PI / 180)
      ctx.translate(-x, -y)
    }
    const drawLine = (ctx) => {
      // 绘制干扰线
      for (let i = 0; i < 8; i++) {
        ctx.strokeStyle = randomColor(this.props.lineColorMin, this.props.lineColorMax)
        ctx.beginPath()
        ctx.moveTo(randomNum(0, this.props.contentWidth), randomNum(0, this.props.contentHeight))
        ctx.lineTo(randomNum(0, this.props.contentWidth), randomNum(0, this.props.contentHeight))
        ctx.stroke()
      }
    }
    const drawDot = (ctx) => {
      // 绘制干扰点
      for (let i = 0; i < 100; i++) {
        ctx.fillStyle = randomColor(0, 255)
        ctx.beginPath()
        ctx.arc(randomNum(0, this.props.contentWidth), randomNum(0, this.props.contentHeight), 1, 0, 2 * Math.PI)
        ctx.fill()
      }
    }
    drawPic()
    return{
      ...toRefs(data),randomNum,drawDot,drawLine,drawText,drawPic,randomColor

    }
  }
}
</script>

<style scoped>

</style>