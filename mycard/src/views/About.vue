<template>
  <div class="home-about">
    <div id="nav">
      <router-link to="/home">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/home/easy">Easy</router-link>
    </div>
    <div class="about">
      <h2>站长资料</h2>
      <Head class="about-head-div"/>
    </div>
  </div>

</template>

<script>
import Head from "./about/Head";
export default {
  components: {Head}
}
</script>

<style scoped>

.home-about{
  height: 100vh;
}
#nav {
  background: #1FA2FF;  /* fallback for old browsers */
  background: -webkit-linear-gradient(to right, #A6FFCB, #12D8FA, #1FA2FF);  /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to right, #A6FFCB, #12D8FA, #1FA2FF); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}
#nav a.router-link-exact-active {
  color: #FFFFFF;
}
.about{
  margin: 0 auto;
  background: #1FA2FF;  /* fallback for old browsers */
  background: -webkit-linear-gradient(to right, #A6FFCB, #12D8FA, #1FA2FF);  /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to right, #A6FFCB, #12D8FA, #1FA2FF); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
.about-head-div{


}
</style>
