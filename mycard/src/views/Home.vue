<template>
  <div class="home">
    <Select :msgforselect="welcomeMessage"/>
  </div>
</template>

<script>
import Select from "./home/Select";
import { ref } from 'vue';

export default {
  name: 'Home',
  components: {
    Select
  },
  setup() {
    const welcomeMessage = ref("Welcome to George's Card");
    return { welcomeMessage };
  }
}
</script>

<style>
.home {
  min-height: 100vh;
  background: linear-gradient(135deg,rgb(184, 196, 211) 0%,rgb(92, 115, 173) 100%);
  color: #ffffff;
}
</style>