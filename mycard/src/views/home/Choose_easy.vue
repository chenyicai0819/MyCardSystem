<template>
  <div class="easy-container">
    <div class="search-section">
      <h2 class="easy-title">快捷搜索</h2>
      <div class="search-wrapper">
        <QuiteSearch :class="{'search-mobile': ismobile}" />
      </div>
    </div>
  </div>
</template>

<script>
import QuiteSearch from "./QuiteSearch";
import { ref, onMounted } from 'vue';

export default {
  name: "Choose_easy",
  components: { 
    QuiteSearch
  },
  setup() {
    const ismobile = ref(false);

    const checkMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i);
      ismobile.value = !!flag;
    };

    onMounted(() => {
      checkMobile();
      window.addEventListener('resize', checkMobile);
    });

    return {
      ismobile
    }
  }
}
</script>

<style scoped>
.easy-container {
  min-height: calc(100vh - 80px);
  background: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(10px);
}

.search-section {
  max-width: 1200px;
  margin: 0 auto;
  padding: 3rem 2rem;
}

.easy-title {
  text-align: center;
  color: #1e293b;
  font-size: 2rem;
  font-weight: 600;
  margin-bottom: 3rem;
}

.search-wrapper {
  max-width: 800px;
  margin: 0 auto;
  background: rgba(255, 255, 255, 0.9);
  padding: 2rem;
  border-radius: 16px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  border: 1px solid rgba(0, 0, 0, 0.1);
}

.search-mobile {
  width: 100%;
}

@media (max-width: 768px) {
  .search-section {
    padding: 2rem 1rem;
  }

  .easy-title {
    font-size: 1.5rem;
    margin-bottom: 2rem;
  }

  .search-wrapper {
    padding: 1rem;
  }
}
</style>