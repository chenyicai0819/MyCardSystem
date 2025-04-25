<template>
  <div class="dark-layout">
    <nav class="dark-nav">
      <router-link to="/home">Home</router-link>
      <router-link to="/about">About</router-link>
      <router-link to="/home/easy">Easy</router-link>
    </nav>
    <main class="dark-content">
      <slot></slot>
    </main>
  </div>
</template>

<style scoped>
.dark-layout {
  min-height: 100vh;
  background: linear-gradient(135deg, #1a1f25 0%, #242832 100%);
  color: #ffffff;
}

.dark-nav {
  height: 60px;
  position: sticky;
  top: 0;
  z-index: 100;
  background: rgba(28, 32, 38, 0.95);
  backdrop-filter: blur(10px);
  padding: 0 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 2rem;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.dark-nav a {
  text-decoration: none;
  color: rgba(255, 255, 255, 0.7);
  font-weight: 500;
  padding: 0.5rem 1.5rem;
  border-radius: 8px;
  transition: all 0.3s ease;
}

.dark-nav a:hover {
  color: #ffffff;
  background: rgba(255, 255, 255, 0.1);
}

.dark-nav a.router-link-exact-active {
  color: #ffffff;
  background: rgba(255, 255, 255, 0.15);
}

.dark-content {
  min-height: calc(100vh - 60px);
}

@media (max-width: 768px) {
  .dark-nav {
    padding: 0 1rem;
    height: 50px;
  }

  .dark-nav a {
    padding: 0.4rem 1rem;
    font-size: 0.9rem;
  }
}
</style> 