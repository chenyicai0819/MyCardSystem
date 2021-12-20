<template>
  <div class="about-music">
    <audio autoplay="autoplay"
           controls="controls"
           preload="auto" :src="audioUrl"></audio>
  </div>
</template>

<script>
import {reactive, toRefs} from "vue";

export default {
  name: "Music",
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