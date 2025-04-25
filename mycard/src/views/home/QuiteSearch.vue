<template>
  <div class="quitesearch">
    <!-- 快捷访问输入框 -->
    <div class="search-box">
      <el-input 
        v-model="input1"
        placeholder="输入网址进行快捷访问"
        class="glassmorphism-input"
        @keyup.enter="link1"
      >
        <template #prepend>
          <el-select 
            v-model="select1" 
            class="protocol-select"
            popper-class="dark-select-dropdown"
          >
            <el-option label="Https://" value="Https://"></el-option>
            <el-option label="Http://" value="Http://"></el-option>
          </el-select>
        </template>
        <template #append>
          <el-button class="search-button" @click="link1">
            <i class="el-icon-position"></i>
          </el-button>
        </template>
      </el-input>
    </div>

    <!-- 搜索引擎输入框 -->
    <div class="search-box">
      <el-input
        v-model="input2"
        placeholder="输入关键词搜索"
        class="glassmorphism-input"
        @keyup.enter="link2"
      >
        <template #prepend>
          <el-select 
            v-model="select2" 
            class="engine-select"
            popper-class="dark-select-dropdown"
          >
            <el-option label="Baidu" value="1">
              <img src="path-to-baidu-icon" class="search-engine-icon" alt="baidu"/>
              Baidu
            </el-option>
            <el-option label="Google" value="2">
              <img src="path-to-google-icon" class="search-engine-icon" alt="google"/>
              Google
            </el-option>
            <el-option label="Bing" value="3">
              <img src="path-to-bing-icon" class="search-engine-icon" alt="bing"/>
              Bing
            </el-option>
          </el-select>
        </template>
        <template #append>
          <el-button class="search-button" @click="link2">
            <i class="el-icon-search"></i>
          </el-button>
        </template>
      </el-input>
    </div>
  </div>
</template>

<script>
import {reactive, toRefs} from "vue";

export default {
  name: "QuiteSearch",
  setup() {
    const data = reactive({
      input1: '',
      input2: '',
      select1: 'Https://',
      select2: '1',
      ismoblie:false,
    })
    const enter = () => {
      alert("ceshi")
    }
    const link1 = () => {
      if (data.input1!=''){
        window.open(data.select1+data.input1, '_blank');
      }
    }
    const link2 = () => {
      if (data.input2!=''){
        if (data.select2=="1"){
          window.open("https://www.baidu.com/s?wd="+data.input2, '_blank');
        }else if(data.select2=="2"){
          window.open("https://www.google.com/search?q="+data.input2, '_blank');
        }else if(data.select2=="3"){
          window.open("https://cn.bing.com/search?q="+data.input2, '_blank');
        }
      }
    }

    return {
      ...toRefs(data),link1,link2,enter
    }
  }
}
</script>

<style scoped>
.quitesearch {
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
}

.search-box {
  margin-bottom: 20px;
  background: rgba(255, 255, 255, 0.9);
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  backdrop-filter: blur(10px);
}

.glassmorphism-input {
  --el-input-bg-color: rgba(255, 255, 255, 0.1) !important;
  --el-input-border-color: rgba(255, 255, 255, 0.2) !important;
  --el-input-hover-border-color: rgba(255, 255, 255, 0.3) !important;
  --el-input-focus-border-color: rgba(255, 255, 255, 0.4) !important;
}

:deep(.el-input__inner) {
  background: rgba(255, 255, 255, 0.9) !important;
  border: 1px solid rgba(0, 0, 0, 0.1);
  color: #1e293b;
  height: 50px;
  font-size: 16px;
  transition: all 0.3s ease;
}

:deep(.el-input__inner):hover {
  border-color: #3b82f6;
}

:deep(.el-input__inner)::placeholder {
  color: #94a3b8;
}

.protocol-select,
.engine-select {
  width: 120px !important;
}

:deep(.el-select .el-input__inner) {
  border-right: 1px solid rgba(0, 0, 0, 0.1);
}

.search-button {
  background: #ffffff !important;
  border: 1px solid rgba(0, 0, 0, 0.1);
  color: #3b82f6;
  transition: all 0.3s ease;
}

.search-button:hover {
  background: #3b82f6 !important;
  color: #ffffff;
}

.search-engine-icon {
  width: 20px;
  height: 20px;
  margin-right: 8px;
  vertical-align: middle;
}

/* 下拉菜单样式 */
:deep(.dark-select-dropdown) {
  background: rgba(28, 32, 38, 0.95) !important;
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.1);
}

:deep(.dark-select-dropdown .el-select-dropdown__item) {
  color: #ffffff;
}

:deep(.dark-select-dropdown .el-select-dropdown__item.hover),
:deep(.dark-select-dropdown .el-select-dropdown__item:hover) {
  background: rgba(255, 255, 255, 0.1);
}
</style>