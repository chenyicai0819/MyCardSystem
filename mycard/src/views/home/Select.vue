<template>
  <div class="select-father">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
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
  background: #4A569D;  /* fallback for old browsers */
  background: -webkit-linear-gradient(to right, #4A569D, #DC2424);  /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to right, #4A569D, #DC2424); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */


}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}
#nav a.router-link-exact-active {
  color: #42b983;
}
#selectbody{
  display: flex;
}
.select{
  margin: 0 auto;
  background: #4A569D;  /* fallback for old browsers */
  background: -webkit-linear-gradient(to right, #4A569D, #DC2424);  /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to right, #4A569D, #DC2424); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

}
.couplet{
  width: 10%;

}
</style>