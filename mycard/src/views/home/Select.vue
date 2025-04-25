<template>
  <div class="select-father">
    <nav class="modern-nav">
      <router-link to="/home">Home</router-link>
      <router-link to="/about">About</router-link>
      <router-link to="/home/easy">Easy</router-link>
    </nav>
    
    <header class="welcome-header">
      <h1>{{ msgforselect }}</h1>
    </header>

    <div class="content-container">
      <router-view/>
    </div>
  </div>
</template>

<script>
import {reactive, toRefs} from "vue";

export default {
  name: "Select",
  props: {
    msgforselect: String
  },
  setup() {
    const data = reactive({
      isMob: false,
    })

    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      data.isMob = !!flag;
    }

    isMobile()
    return {
      isMobile,
      ...toRefs(data),
    }
  }
}
</script>

<style scoped>
.select-father {
  max-width: 1400px;
  margin: 0 auto;
  padding: 2rem;
}

.modern-nav {
  background: white;
  padding: 1rem;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  margin-bottom: 2rem;
  display: flex;
  justify-content: center;
  gap: 2rem;
  backdrop-filter: blur(10px);
  position: sticky;
  top: 1rem;
  z-index: 100;
}

.modern-nav a {
  text-decoration: none;
  color: #64748b;
  font-weight: 500;
  padding: 0.75rem 1.5rem;
  border-radius: 8px;
  transition: all 0.3s ease;
  font-size: 1.1rem;
}

.modern-nav a:hover {
  background: #f8fafc;
  color: #3b82f6;
  transform: translateY(-1px);
}

.modern-nav a.router-link-exact-active {
  color: #3b82f6;
  background: #eff6ff;
  font-weight: 600;
}

.welcome-header {
  text-align: center;
  padding: 3rem 0;
}

.welcome-header h1 {
  font-size: 3rem;
  color: #1e293b;
  font-weight: 700;
  margin: 0;
  line-height: 1.2;
  letter-spacing: -0.5px;
  background: linear-gradient(135deg, #1e293b 0%, #3b82f6 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.content-container {
  display: grid;
  gap: 2rem;
  padding: 2rem;
}

@media (max-width: 768px) {
  .select-father {
    padding: 1rem;
  }
  
  .modern-nav {
    padding: 0.75rem;
    gap: 1rem;
    flex-wrap: wrap;
  }
  
  .modern-nav a {
    padding: 0.5rem 1rem;
    font-size: 1rem;
  }
  
  .welcome-header {
    padding: 2rem 0;
  }
  
  .welcome-header h1 {
    font-size: 2rem;
  }
}
</style>