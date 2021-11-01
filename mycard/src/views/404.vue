<template>
  <div class="c404h1div">
    <h1 class="c404h1">出错了！</h1>
    <h1 class="c404h1">404错误！</h1>
    <h1 class="c404h1">找不到界面</h1>
    <!--<p>{{data.nameAndAge}}</p>-->
  </div>

</template>

<script>

import {inject, reactive, ref, watchEffect} from "vue";

export default {
  name: "404",
  setup(){
    const appStore=inject('appStore')

    const data=reactive({
      // nameAndAge:appStore.name
    })
    return{
      data
    }
  }
}
</script>

<style scoped>
*{
  margin: 0;
  padding: 0;
}
.c404h1div{

}
.c404h1{
  color: white;
  margin: 0 auto;
  width: 250px;
  height: 60px;
  font-size: 40px;
  background-color: #42b983;
}
</style>