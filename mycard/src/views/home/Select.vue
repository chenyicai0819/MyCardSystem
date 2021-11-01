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
      <h1>广<br/>告<br/>位<br/>招<br/>租</h1>
    </div>
    <router-view/>
    <div id="right" class="couplet">
      <h1>广<br/>告<br/>位<br/>招<br/>租</h1>
    </div>
  </div>

</template>

<script>
import Choose from "./Choose";
import {reactive} from "vue";
export default {
  name: "Select",
  components: {Choose},
  props:{
    msgforselect:''
  },
  setup(){

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