<template>
  <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </div>
  <div class="select">
    <h2>{{msgforselect}}</h2>
  </div>
  <div id="selectbody">
    <div id="left" class="couplet">
      <!--<h1>广<br/>告<br/>位<br/>招<br/>租</h1>-->
      <Ad/>
    </div>
    <router-view/>
    <div id="right" class="couplet">
      <!--<h1>广<br/>告<br/>位<br/>招<br/>租</h1>-->
      <Ad/>
    </div>
  </div>

</template>

<script>
import Choose from "./Choose";
import Ad from "../ad/Ad";
import {reactive, ref} from "vue";
import Add from "../manage/Add";
export default {
  name: "Select",
  components: {Add, Choose,Ad},
  props:{
    msgforselect:''
  },
  setup(){
    const RefChilde=ref();
  }
}
</script>

<style scoped>
*{
  margin: 0;
  padding: 0;
}
#nav {
  background-color: #42b983;
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
  background-color: #42b983;
}
.couplet{
  width: 10%;
  height: 700px;

}
</style>