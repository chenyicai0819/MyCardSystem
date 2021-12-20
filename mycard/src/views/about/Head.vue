<template>
  <div class="about-head">
    <music/>
  </div>
  <div class="about-body">

  </div>
</template>

<script>
import {reactive, toRefs} from "vue";
import Music from "./Music";

export default {
  name: "Head",
  components: {Music},
  setup(){
    const data=reactive({
      audioUrl:"../../assets/music.mp3"
    })

    return{
      ...toRefs(data)
    }
  }
}
</script>

<style scoped>

</style>