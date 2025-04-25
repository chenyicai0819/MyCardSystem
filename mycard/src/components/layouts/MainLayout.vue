<template>
  <div class="main-layout">
    <nav class="main-nav">
      <router-link to="/home">Home</router-link>
      <router-link to="/about">About</router-link>
      <router-link to="/home/easy">Easy</router-link>
    </nav>
    <main class="main-content">
      <slot></slot>
    </main>
  </div>
</template>

<style scoped>
.main-layout {
  min-height: 100vh;
  background: linear-gradient(135deg, #f6f9fc 0%, #eef3f8 100%);
}

.main-nav {
  height: 60px;
  position: sticky;
  top: 0;
  z-index: 100;
  background: rgba(255, 255, 255, 0.9);
  backdrop-filter: blur(10px);
  padding: 0 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 2rem;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.main-nav a {
  text-decoration: none;
  color: #64748b;
  font-weight: 500;
  padding: 0.5rem 1.5rem;
  border-radius: 8px;
  transition: all 0.3s ease;
}

.main-nav a:hover {
  color: #3b82f6;
  background: rgba(59, 130, 246, 0.08);
}

.main-nav a.router-link-exact-active {
  color: #3b82f6;
  background: rgba(59, 130, 246, 0.12);
}

.main-content {
  min-height: calc(100vh - 60px);
}

@media (max-width: 768px) {
  .main-nav {
    padding: 0 1rem;
    height: 50px;
  }

  .main-nav a {
    padding: 0.4rem 1rem;
    font-size: 0.9rem;
  }
}
</style> 