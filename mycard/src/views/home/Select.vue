<template>
  <div class="select-father">
    <div id="nav">
      <router-link to="/home">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/home/easy">Easy</router-link>
    </div>
    <div class="select">
      <h2>{{msgforselect}}</h2>
    </div>
    <div id="selectbody">
      <div id="left" class="couplet" v-if="isMob==false">
        <!--<h1>广<br/>告<br/>位<br/>招<br/>租</h1>-->
        <Ad/>
      </div>
      <router-view/>
      <div id="right" class="couplet" v-if="isMob==false">
        <!--<h1>广<br/>告<br/>位<br/>招<br/>租</h1>-->
        <Ad/>
      </div>
    </div>
  </div>


</template>

<script>
import Choose from "./Choose";
import Ad from "../ad/Ad";
import {reactive, ref, toRefs} from "vue";
import Add from "../manage/Add";
export default {
  name: "Select",
  components: {Add, Choose,Ad},
  props:{
    msgforselect:''
  },
  setup(){
    const RefChilde=ref();

    const data=reactive({
      isMob:false,
    })

    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      if (flag) {
        data.isMob=true
      }else{
        data.isMob=false
      }
    }

    isMobile()
    return{
      isMobile,...toRefs(data),
    }
  }
}
</script>

<style scoped>
*{
  margin: 0;
  padding: 0;
}
.select-father{
}
#nav {

}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}
#nav a.router-link-exact-active {
  color: #FFFFFF;
}
#selectbody{
  display: flex;
}
.select{
  margin: 0 auto;
}
.couplet{
  width: 10%;

}
</style>