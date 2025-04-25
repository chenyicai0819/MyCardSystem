<template>
  <div class="bookmarks-container" :class="{ 'mobile': ismobile }">
    <Marks />
  </div>
</template>

<script>
import Marks from "./Marks";
import { ref, onMounted } from 'vue';

export default {
  name: "Bookmarks",
  components: { Marks },
  setup() {
    const ismobile = ref(false);

    const checkMobile = () => {
      ismobile.value = !!navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i);
    };

    onMounted(() => {
      checkMobile();
      window.addEventListener('resize', checkMobile);
    });

    return {
      ismobile
    };
  }
}
</script>

<style scoped>
.bookmarks-container {
  width: 90%;
  max-width: 1400px;
  margin: 0 auto;
  padding: 2rem;
}

.bookmarks-container.mobile {
  width: 100%;
  padding: 1rem;
}
</style>