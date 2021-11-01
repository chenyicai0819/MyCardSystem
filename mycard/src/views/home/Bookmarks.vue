<template>
  <div id="bookmarks">
    <Marks/>
  </div>
</template>

<script>
import Marks from "./Marks";
export default {
  name: "Bookmarks",
  components: {Marks}
}
</script>

<style scoped>
*{
  margin:0;
  padding: 0;
}
#bookmarks{
  width: 80%;
  background-color: #BDBDBD;
  display: flex;
  justify-content: center;
  flex-flow: wrap;
}
</style>