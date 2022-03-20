<template>

</template>

<script>
import {watch} from "vue";
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const indexTip = false;  // 首页是否开启时间消息提示，默认 false。因为首页大图模块已经内置时间消息提示，所以我不需要开启。
export default {
  name: "GlobalTip",
  
  setup() {
    const $router = useRouter()
    const bgTimeColor = () => {
      var hours = new Date().getHours();
      var minutes = new Date().getMinutes();
      var seconds = new Date().getSeconds();
      hours = hours < 10 ? "0" + hours : hours;
      minutes = minutes < 10 ? "0" + minutes : minutes;
      seconds = seconds < 10 ? "0" + seconds : seconds;

      if (hours >= 6 && hours < 11) {
        let mess="早上好呀~~，现在是"+hours+":"+minutes+":"+seconds+"，吃早餐了吗？😊🤭"
        ElMessage({
          message: mess,
          type: 'success',
        })

      } else if (hours >= 12 && hours <= 16) {
        let mess="下午好呀~~，现在是"+hours+":"+minutes+":"+seconds+"，繁忙的下午也要适当休息哦🥤🏀~~"
        ElMessage({
          message: mess,
          type: 'success',
        })
      } else if (hours >= 16 && hours <= 19) {
        let mess="到黄昏了~~，现在是"+hours+":"+minutes+":"+seconds+"，该准备吃饭啦🥗🍖~~"
        ElMessage({
          message: mess,
          type: 'success',
        })
      } else if (hours >= 19 && hours < 24) {
        let mess="晚上好呀~~，现在是"+hours+":"+minutes+":"+seconds+"，该准备洗漱睡觉啦🥱😪~~"
        ElMessage({
          message: mess,
          type: 'success',
        })
      } else if (hours >= 0 && hours < 6) {
        let mess="别再熬夜了~~，现在是"+hours+":"+minutes+":"+seconds+"，早点睡吧，让我们一起欣赏早上的太阳~~😇🛏"
        ElMessage({
          message: mess,
          type: 'success',
        })
      }
    }
    const nextAllTipElement = (elem) => {
      var r = [];
      var n = elem;
      for (; n; n = n.nextSibling) {
        if (n.nodeType === 1 && n !== elem) {
          r.push(n);
        }
      }
      return r;
    }

    onMounted(()=>{
      if ($router.currentRoute.value.path=="/home") {
        bgTimeColor();
      }
    })

    watch(()=>$router.currentRoute.value.path,()=>{
      // console.log("地址改为："+$router.currentRoute.value.path)
      bgTimeColor()
    })

    return {
      bgTimeColor,nextAllTipElement,
    }
  },


}
</script>

<style scoped>
/* 提示框元素 */
.global-tip {
  position: fixed;
  display: flex;
  top: -10px;
  left: 50%;
  opacity: 0;
  min-width: 320px;
  transform: translateX(-50%);
  transition: opacity 0.3s linear, top 0.4s, transform 0.4s;
  z-index: 99999;
  padding: 15px 15px 15px 20px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  grid-row: 1;
  line-height: 17px;
}

.global-tip p {
  line-height: 17px;
  margin: 0;
  font-size: 14px;
}

.icon {
  margin-right: 10px;
  line-height: 17px;
}

.tip-success {
  color: #67c23a;
  background-color: #f0f9eb;
  border-color: #e1f3d8;
}

.tip-success .tip-success-content {
  color: #67c23a;
}

.tip-danger {
  color: #f56c6c;
  background-color: #fef0f0;
  border-color: #fde2e2;
}

.tip-danger .tip-danger-content {
  color: #f56c6c;
}

.tip-info {
  background-color: #edf2fc;
  border-color: #ebeef5;
}

.tip-info .tip-info-content {
  color: #909399;
}

.tip-warning {
  color: #e6a23c;
  background-color: #fdf6ec;
  border-color: #faecd8;
}

.tip-warning .tip-warning-content {
  margin: 0;
  color: #e6a23c;
  line-height: 21px;
  font-size: 14px;
}
</style>