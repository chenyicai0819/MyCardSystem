<template>
  <dark-layout>
    <div class="about-container">
      <div class="about-header">
        <h1>站长资料</h1>
      </div>
      <div class="about-content">
        <Head />
      </div>
    </div>
  </dark-layout>
</template>

<script>
import DarkLayout from '@/components/layouts/DarkLayout.vue'
import Head from "./about/Head"

export default {
  components: {
    DarkLayout,
    Head
  }
}
</script>

<style scoped>
.about-container {
  min-height: calc(100vh - 80px); /* 减去导航栏的高度 */
  background: linear-gradient(135deg,rgb(184, 196, 211) 0%,rgb(92, 115, 173) 100%);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.1);
}

.about-header {
  text-align: center;
  padding: 2rem 0;
  background: linear-gradient(135deg,rgb(184, 196, 211) 0%,rgb(92, 115, 173) 100%);
}

.about-header h1 {
  color: #ffffff;
  font-size: 2.5rem;
  font-weight: 600;
  margin: 0;
  background: linear-gradient(135deg, #ffffff 0%, #a8b2c1 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.about-content {
  padding: 2rem;
  max-width: 1200px;
  margin: 0 auto;
}

@media (max-width: 768px) {
  .about-header {
    padding: 1.5rem 0;
  }

  .about-header h1 {
    font-size: 2rem;
  }

  .about-content {
    padding: 1rem;
  }
}
</style>
