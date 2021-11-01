<template>
  <div id="choose">
    <div class="choosecard" :class="{choosecardmoblie:data.ismoblie}" v-for="name in data.cardname" >
      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>{{ name.cardName }}</span>
            <a :href="name.cardLink" style="text-decoration: none;font-weight: bold">前往链接</a>
          </div>
        </template>
        <div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>
      </el-card>
    </div>
  </div>
</template>

<script>
import {reactive,getCurrentInstance} from "vue";
export default {
  name: "Choose",
  setup(){
    const {proxy}=getCurrentInstance();
    const data=reactive({
      cardname:[

      ],
      ismoblie:false,
    })
    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      if (flag) {
        console.log('移动端打开')
        data.ismoblie=true
      } else {
        data.ismoblie=false
        console.log('PC端打开')
      }
    }
    const getCard = () =>{
      proxy.$axios.get('card/show',{}).then(res=>{
        const getdata=res.data;
        data.cardname=getdata;
      });
    }
    isMobile();
    getCard();
    return{
      data,
      isMobile,
      getCard,
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
  width: 80%;
  background-color: #BDBDBD;
  display: flex;
  justify-content: center;
  flex-flow: wrap;
}
.choosecard{
  margin: 5px;
  width: 30%;
}
.choosecardmoblie{
  margin: 5px;
  width: 90%;
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

.box-card {
  width: 100%;
}
</style>