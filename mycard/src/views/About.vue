<template>
  <div id="nav">
    <router-link to="/home">Home</router-link> |
    <router-link to="/about">About</router-link>
  </div>
  <div class="about">
    <h2>站长资料</h2>
  </div>
</template>

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
  color: #FFFFFF;
}
.about{
  margin: 0 auto;
  background-color: #42b983;
}
</style>
