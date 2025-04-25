<template>
  <div class="add-form">
    <!-- 表单容器 -->
    <div class="form-container">
      <!-- 名称输入 -->
      <el-input 
        v-model="data.name"
        placeholder="请输入名字"
        class="form-item"
      />

      <!-- 链接地址 -->
      <el-input 
        v-if="activeName == 1 || activeName == 2 || activeName == 5"
        v-model="data.link"
        placeholder="请输入链接地址"
        class="form-item"
      >
        <template #prefix>
          <el-icon><Link /></el-icon>
        </template>
      </el-input>

      <!-- 图片链接 -->
      <el-input 
        v-if="activeName != 5"
        v-model="data.img"
        placeholder="请输入图片链接"
        class="form-item"
      >
        <template #prefix>
          <el-icon><Picture /></el-icon>
        </template>
      </el-input>

      <!-- 图片预览 -->
      <div v-if="data.img && activeName != 5" class="image-preview">
        <el-image
          :src="data.img"
          fit="cover"
          :preview-src-list="[data.img]"
        >
          <template #error>
            <div class="image-error">
              <el-icon><ImageClose /></el-icon>
              <span>加载失败</span>
            </div>
          </template>
        </el-image>
      </div>

      <!-- 类型选择 -->
      <el-select
        v-if="activeName == 2"
        v-model="data.type"
        placeholder="请选择类型"
        class="form-item"
      >
        <el-option
          v-for="item in data.collName"
          :key="item.collName"
          :label="item.collName"
          :value="item.collName"
        />
      </el-select>

      <!-- 介绍内容 -->
      <el-input
        v-if="activeName == 2 || activeName == 3 || activeName == 4 || activeName == 5"
        v-model="data.text"
        type="textarea"
        :rows="4"
        placeholder="请输入介绍内容"
        resize="none"
        class="form-item"
      />

      <!-- 时间选择器 -->
      <div v-if="activeName == 4" class="block form-item">
        <el-date-picker
          v-model="data.value2"
          type="daterange"
          unlink-panels
          range-separator="⏩"
          start-placeholder="上架时间"
          end-placeholder="下架时间"
          :shortcuts="data.shortcuts"
          format="YYYY/MM/DD"
          value-format="YYYY-MM-DD HH:mm:ss"
          class="date-picker"
        />
      </div>
    </div>

    <!-- 操作按钮 -->
    <div class="form-actions">
      <el-button 
        type="primary" 
        @click="add"
        :loading="loading"
      >
        {{ data.manatype }}
      </el-button>
    </div>
  </div>
</template>

<script>
import { ref, reactive, onMounted, getCurrentInstance, inject } from 'vue'
import { ElMessage } from 'element-plus'
import { Link, Picture, ImageClose } from '@element-plus/icons-vue'
import qs from 'qs'

export default {
  name: "Add",
  props: {
    numlength: Array,
    manatype: String,
    manaData: Array,
    activeName: Number,
    drawer: Boolean,
  },
  setup(props, context) {
    const { proxy } = getCurrentInstance()
    const appStore = inject('appStore')
    const loading = ref(false)
    
    const data = reactive({
      manatype: props.manatype,
      uri: '',
      posts: '',
      id: '',
      name: '',
      link: '',
      img: '',
      type: '',
      text: '',
      upDate: '',
      downDate: '',
      getReData: '',
      maData: [],
      collName: [],
      value2: '',
      shortcuts: [
        {
          text: '未来一周',
          value: () => {
            const end = new Date()
            const start = new Date()
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 7)
            return [start, end]
          },
        },
        {
          text: '未来一个月',
          value: () => {
            const end = new Date()
            const start = new Date()
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 30)
            return [start, end]
          },
        },
        {
          text: '未来三个月',
          value: () => {
            const end = new Date()
            const start = new Date()
            end.setTime(end.getTime() + 3600 * 1000 * 24 * 90)
            return [start, end]
          },
        },
      ],
    })

    // 添加或修改
    const add = () => {
      loading.value = true
      let active = props.activeName
      data.methods="add";
      if (props.manatype=="修改"){
        data.methods="up"
      }
      if (active == 1) {
        data.uri = 'card/'
      } else if (active == 2) {
        data.uri = 'mork/'
      } else if (active == 3) {
        data.uri = 'coll/'
      } else if (active == 4) {
        data.uri = 'ad/'
      } else if (active == 5) {
        data.uri = 'imgs/'
      }

      if (props.manatype == "修改") {
        data.posts = qs.stringify({
          "id": data.id,
          "name": data.name,
          "link": data.link,
          "img": data.img,
          "type": data.type,
          "text": data.text,
          "upDate": data.value2 ? data.value2[0] : '',
          "downDate": data.value2 ? data.value2[1] : ''
        })
      } else {
        data.posts = qs.stringify({
          "name": data.name,
          "link": data.link,
          "img": data.img,
          "type": data.type,
          "text": data.text,
          "upDate": data.value2 ? data.value2[0] : '',
          "downDate": data.value2 ? data.value2[1] : ''
        })
      }

      proxy.$axios.post(data.uri + data.methods, data.posts).then(res => {
        data.getReData = res.data
        checkSucces()
        loading.value = false
      })
    }

    // 检查是否成功
    const checkSucces = () => {
      if (data.getReData == 1) {
        appStore.setgetIntoSucc(data.getReData)
        context.emit('checkSucces')
        ElMessage({
          message: data.methods == "修改" ? "修改成功" : "添加成功",
          type: 'success',
        })
        data.id = ''
        data.name = ''
        data.link = ''
        data.img = ''
        data.type = ''
        data.text = ''
        data.upDate = ''
        data.downDate = ''
      } else {
        ElMessage.error('操作失败')
      }
    }

    // 获取父组件数据
    const create = () => {
      let active = props.activeName
      let manadata = props.manaData

      if (active == 1) {
        if (props.manatype == "修改") {
          data.id = props.manaData.cardId
          data.name = props.manaData.cardName
          data.link = props.manaData.cardLink
          data.img = props.manaData.cardImg
        }
        data.uri = 'card/'
      } else if (active == 2) {
        if (props.manatype == "修改") {
          data.id = props.manaData.morkId
          data.name = props.manaData.morkName
          data.link = props.manaData.morkLink
          data.img = props.manaData.morkImg
          data.type = props.manaData.morkType
          data.text = props.manaData.morkText
        }
        data.uri = 'mork/'
      } else if (active == 3) {
        if (props.manatype == "修改") {
          data.id = props.manaData.collId
          data.name = props.manaData.collName
          data.img = props.manaData.collImg
          data.text = props.manaData.collText
        }
        data.uri = 'coll/'
      } else if (active == 4) {
        if (props.manatype == "修改") {
          data.id = props.manaData.adId
          data.name = props.manaData.adName
          data.img = props.manaData.adImg
          data.text = props.manaData.adText
          data.upDate = props.manaData.adUpDate
          data.downDate = props.manaData.adDownDate
        }
        data.uri = 'ad/'
      } else if (active == 5) {
        if (props.manatype == "修改") {
          data.id = props.manaData.imgsId
          data.name = props.manaData.imgsName
          data.link = props.manaData.imgsLink
          data.text = props.manaData.imgsText
        }
        data.uri = 'imgs/'
      }
    }

    // 获取类型列表
    const getType = () => {
      let uri = 'coll/show'
      proxy.$axios.get(uri, {}).then(res => {
        data.collName = res.data
      })
    }

    // 从父组件获取数据
    const getFather = () => {
      create()
      data.manatype = props.manatype
    }

    onMounted(() => {
      getType()
    })

    return {
      data,
      loading,
      create,
      add,
      checkSucces,
      getFather,
      getType,
    }
  }
}
</script>

<style scoped>
.add-form {
  display: flex;
  flex-direction: column;
  height: 100%;
  padding: 24px;
  background: #fff;
}

.form-container {
  flex: 1;
  overflow-y: auto;
  padding-right: 10px;
}

.form-item {
  margin-bottom: 16px;
  width: 100%;
}

/* 输入框样式 */
:deep(.el-input__wrapper) {
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
  border: 1px solid #e5e7eb;
  padding: 8px 12px;
}

:deep(.el-input__wrapper:hover) {
  border-color: #60a5fa;
}

:deep(.el-input__wrapper.is-focus) {
  border-color: #3b82f6;
  box-shadow: 0 0 0 2px rgba(59, 130, 246, 0.1);
}

/* 图片预览样式 */
.image-preview {
  margin: 12px 0;
  border-radius: 8px;
  overflow: hidden;
  width: 200px;
  height: 120px;
  background: #f3f4f6;
}

:deep(.el-image) {
  width: 100%;
  height: 100%;
  transition: transform 0.3s;
}

:deep(.el-image:hover) {
  transform: scale(1.02);
}

.image-error {
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: #9ca3af;
  font-size: 14px;
}

/* 日期选择器样式 */
.date-picker {
  width: 100%;
}

/* 操作按钮容器 */
.form-actions {
  display: flex;
  justify-content: flex-end;
  padding-top: 20px;
  border-top: 1px solid #e5e7eb;
  margin-top: 20px;
}

:deep(.el-button) {
  padding: 8px 24px;
  font-weight: 500;
}

:deep(.el-button--primary) {
  background: #3b82f6;
  border-color: #3b82f6;
}

:deep(.el-button--primary:hover) {
  background: #60a5fa;
  border-color: #60a5fa;
}

/* 滚动条样式 */
.form-container::-webkit-scrollbar {
  width: 6px;
}

.form-container::-webkit-scrollbar-thumb {
  background: #d1d5db;
  border-radius: 3px;
}

.form-container::-webkit-scrollbar-track {
  background: #f3f4f6;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .add-form {
    padding: 16px;
  }

  .image-preview {
    width: 100%;
    height: 180px;
  }
}
</style>