<template>
  <div :id="ismoblie==true?'bookmarks-moblic':'bookmarks-PC'">
    <Marks/>
  </div>
</template>

<script>
import Marks from "./Marks";
import {reactive, toRefs} from "vue";
export default {
  name: "Bookmarks",
  components: {Marks},
  setup(){
    const data=reactive({
      ismoblie:false,
    })
    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      if (flag) {
        data.ismoblie=true
      } else {
        data.ismoblie=false
      }
    }

    isMobile();
    return{
      ...toRefs(data),isMobile,
    }
  }
}
</script>

<style scoped>
*{
  margin:0;
  padding: 0;
}
#bookmarks-PC{
  width: 80%;
  background-color: #BDBDBD;
  display: flex;
  justify-content: center;
  flex-flow: wrap;
}
#bookmarks-moblic{
  width: 100%;
  background-color: #BDBDBD;
  display: flex;
  justify-content: center;
  flex-flow: wrap;
}
</style>