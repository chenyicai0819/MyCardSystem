<template>
  <div class="tasks">
    <!-- 定时任务列表弹窗 -->
    <el-dialog 
      v-model="dialogTableVisible"
      title="定时任务管理"
      width="80%"
      class="task-dialog"
    >
      <div class="tasks-container">
        <el-collapse
          v-model="activeNames"
          @change="handleChange"
          accordion
          class="custom-collapse"
        >
          <el-collapse-item
            v-for="(item, i) in taskLists"
            :key="item.id"
            :title="item.jobName"
            :name="item.id"
          >
            <el-descriptions
              :title="taskLists[i].jobName"
              :column="2"
              border
              class="task-descriptions"
            >
              <el-descriptions-item label="任务名称" label-class-name="label-bold">
                {{ taskLists[i].jobName }}
              </el-descriptions-item>
              
              <el-descriptions-item label="任务组" label-class-name="label-bold">
                {{ taskLists[i].jobGroup }}
              </el-descriptions-item>
              
              <el-descriptions-item label="任务路径" label-class-name="label-bold">
                <el-tag size="small" type="info">
                  {{ taskLists[i].jobClass + taskLists[i].jobMethod }}
                </el-tag>
              </el-descriptions-item>
              
              <el-descriptions-item label="任务参数" label-class-name="label-bold">
                <el-tag size="small" type="success">
                  {{ taskLists[i].jobParams }}
                </el-tag>
              </el-descriptions-item>
              
              <el-descriptions-item label="任务Cron" label-class-name="label-bold">
                <el-tag size="small" type="warning">
                  {{ taskLists[i].cron }}
                </el-tag>
              </el-descriptions-item>
              
              <el-descriptions-item label="任务描述" label-class-name="label-bold">
                {{ taskLists[i].jobDesc }}
              </el-descriptions-item>
              
              <el-descriptions-item label="任务触发器" label-class-name="label-bold">
                {{ taskLists[i].triggerName }}
              </el-descriptions-item>
              
              <el-descriptions-item label="触发器组" label-class-name="label-bold">
                {{ taskLists[i].triggerGroup }}
              </el-descriptions-item>
              
              <el-descriptions-item label="触发器描述" label-class-name="label-bold">
                {{ taskLists[i].triggerDesc }}
              </el-descriptions-item>
              
              <el-descriptions-item label="是否允许并发" label-class-name="label-bold">
                <el-tag :type="taskLists[i].concurrent == 1 ? 'danger' : 'success'" size="small">
                  {{ taskLists[i].concurrent == 1 ? "禁止" : "允许" }}
                </el-tag>
              </el-descriptions-item>
              
              <el-descriptions-item label="状态" label-class-name="label-bold">
                <el-tag :type="taskLists[i].status == 1 ? 'success' : 'danger'" size="small">
                  {{ taskLists[i].status == 1 ? "启用" : "禁用" }}
                </el-tag>
              </el-descriptions-item>
              
              <el-descriptions-item label="任务添加人" label-class-name="label-bold">
                {{ taskLists[i].account }}
              </el-descriptions-item>
              
              <el-descriptions-item label="添加时间" label-class-name="label-bold">
                {{ taskLists[i].createTime }}
              </el-descriptions-item>
              
              <el-descriptions-item label="操作" label-class-name="label-bold">
                <div class="action-buttons">
                  <el-button 
                    type="primary" 
                    size="small" 
                    @click="edit(i)"
                    icon="Edit"
                  >
                    编辑
                  </el-button>
                  
                  <el-popconfirm
                    width="220"
                    confirm-button-text="确认"
                    cancel-button-text="取消"
                    title="确定要删除该条定时任务吗?"
                    @confirm="cherkDel"
                  >
                    <template #reference>
                      <el-button 
                        type="danger" 
                        size="small" 
                        @click="del(i)"
                        icon="Delete"
                      >
                        删除
                      </el-button>
                    </template>
                  </el-popconfirm>
                </div>
              </el-descriptions-item>
            </el-descriptions>
          </el-collapse-item>
        </el-collapse>
      </div>
    </el-dialog>

    <!-- 编辑任务抽屉 -->
    <el-drawer
      v-model="drawerShow"
      :direction="direction"
      size="45%"
      class="task-drawer"
    >
      <template #header>
        <h4 class="drawer-title">
          编辑定时任务：{{ drawerData.jobName }}
        </h4>
      </template>
      
      <template #default>
        <div class="drawer-content">
          <el-form 
            :model="drawerData"
            label-position="top"
            class="edit-form"
          >
            <el-form-item label="ID">
              <el-input v-model="drawerData.id" disabled />
            </el-form-item>

            <el-form-item label="名称">
              <el-input v-model="drawerData.jobName" />
            </el-form-item>

            <el-form-item label="参数">
              <el-input v-model="drawerData.jobParams" />
            </el-form-item>

            <el-form-item label="备注">
              <el-input 
                v-model="drawerData.jobDesc"
                type="textarea"
                :rows="3"
              />
            </el-form-item>

            <el-form-item label="定时时间">
              <el-input v-model="drawerData.cron">
                <template #append>
                  <el-tooltip 
                    content="Cron表达式格式：秒 分 时 日 月 周"
                    placement="top"
                  >
                    <el-icon><QuestionFilled /></el-icon>
                  </el-tooltip>
                </template>
              </el-input>
            </el-form-item>

            <el-form-item label="并发设置">
              <el-radio-group v-model="drawerData.concurrent">
                <el-radio :label="0">允许</el-radio>
                <el-radio :label="1">禁止</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="状态">
              <el-radio-group v-model="drawerData.status">
                <el-radio :label="1">启用</el-radio>
                <el-radio :label="0">禁用</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="添加人">
              <el-input v-model="drawerData.account" disabled />
            </el-form-item>

            <el-form-item label="添加时间">
              <el-input v-model="drawerData.createTime" disabled />
            </el-form-item>
          </el-form>
        </div>
      </template>

      <template #footer>
        <div class="drawer-footer">
          <el-button @click="cancelClick">取消</el-button>
          <el-button type="primary" @click="confirmClick">保存修改</el-button>
        </div>
      </template>
    </el-drawer>
  </div>
</template>

<style scoped>
.tasks-container {
  padding: 0 10px;
}

.custom-collapse {
  border: none;
  background: transparent;
}

:deep(.el-collapse-item__header) {
  font-size: 16px;
  font-weight: 500;
  color: #1f2937;
  background: #f8fafc;
  border-radius: 6px;
  padding: 0 16px;
  height: 48px;
  transition: all 0.3s ease;
}

:deep(.el-collapse-item__header:hover) {
  background: #f1f5f9;
}

:deep(.el-collapse-item__content) {
  padding: 20px;
}

.task-descriptions {
  margin-bottom: 0;
}

:deep(.el-descriptions__header) {
  margin-bottom: 20px;
}

:deep(.el-descriptions__title) {
  font-size: 18px;
  font-weight: 600;
  color: #1f2937;
}

:deep(.el-descriptions__label) {
  color: #4b5563;
  font-weight: 500;
}

:deep(.label-bold) {
  font-weight: 600 !important;
}

.action-buttons {
  display: flex;
  gap: 8px;
}

/* 抽屉样式 */
.task-drawer {
  --el-drawer-padding-primary: 0;
}

.drawer-title {
  margin: 0;
  font-size: 16px;
  font-weight: 600;
  color: #1f2937;
}

.drawer-content {
  padding: 20px;
  height: calc(100% - 120px);
  overflow-y: auto;
}

.edit-form {
  max-width: 600px;
  margin: 0 auto;
}

:deep(.el-form-item__label) {
  font-weight: 500;
  color: #374151;
  padding-bottom: 4px;
}

.drawer-footer {
  padding: 16px 20px;
  border-top: 1px solid #e5e7eb;
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  background: white;
}

/* 滚动条样式 */
.drawer-content::-webkit-scrollbar {
  width: 6px;
}

.drawer-content::-webkit-scrollbar-thumb {
  background: #d1d5db;
  border-radius: 3px;
}

.drawer-content::-webkit-scrollbar-track {
  background: #f3f4f6;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .task-dialog {
    --el-dialog-width: 95% !important;
  }

  .task-drawer {
    width: 90% !important;
  }

  :deep(.el-descriptions__cell) {
    padding: 12px !important;
  }
}

/* 标签样式优化 */
:deep(.el-tag) {
  border-radius: 4px;
  font-weight: 500;
}

/* 按钮样式优化 */
:deep(.el-button--small) {
  padding: 6px 12px;
  font-size: 13px;
}

:deep(.el-button--primary) {
  --el-button-hover-bg-color: #4096ff;
  --el-button-hover-border-color: #4096ff;
}

:deep(.el-button--danger) {
  --el-button-hover-bg-color: #f43f5e;
  --el-button-hover-border-color: #f43f5e;
}
</style>

<script>
import {getCurrentInstance, reactive, toRefs} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import qs from "qs";
import router from "@/router";

export default {
  name: "Tasks",
  setup() {
    const {proxy}=getCurrentInstance();
    const data = reactive({
      activeNames: 1,
      dialogTableVisible: false,
      taskLists: [],
      drawerShow: false,
      direction: "rtl",
      drawerData: {},
    })

    /**
     * 点击编辑
     */
    const edit = (index) => {
      data.drawerData = data.taskLists[index]
      data.drawerShow = true
    }

    /**
     * 点击删除
     */
    const del = (index) => {
      data.drawerData = data.taskLists[index]
    }
    
    const handleChange = (val) => {
      console.log(val)
    }

    /**
     * 打开当前界面
     */
    const openThis = () => {
      data.dialogTableVisible = true
    }

    /**
     * 获取定时任务列表
     */
    const getTaskLists = () => {
      proxy.$axios.get('schedule/allSchedulerTrigger',{}).then(res=>{
        data.taskLists = res.data
      });
    }

    /**
     * 抽屉取消修改
     */
    const cancelClick = () => {
      data.drawerShow = false
    }

    /**
     * 抽屉提交修改
     */
    const confirmClick = () => {
      proxy.$axios.post('schedule/updateScheduleTrigger',
          qs.stringify({
            "id": data.drawerData.id,
            "jobName": data.drawerData.jobName,
            "jobGroup": data.drawerData.jobGroup,
            "jobClass": data.drawerData.jobClass,
            "jobMethod": data.drawerData.jobMethod,
            "jobParams": data.drawerData.jobParams,
            "jobDesc": data.drawerData.jobDesc,
            "cron": data.drawerData.cron,
            "triggerName": data.drawerData.triggerName,
            "triggerGroup": data.drawerData.triggerGroup,
            "triggerDesc": data.drawerData.triggerDesc,
            "concurrent": data.drawerData.concurrent,
            "status": data.drawerData.status,
            "account": data.drawerData.account,
          })).then(res=>{
        if (200===res.data.code){
          ElMessage.success("修改内容成功")
        }else{
          ElMessage.error("修改内容失败")
        }
      });

      data.drawerShow = false
    }

    /**
     * 确认删除
     */
    const cherkDel = () => {
      proxy.$axios.get('schedule/deleteScheduleTrigger',
          {params: {"id":data.drawerData.id}}).then(res => {
        if (200===res.data.code){
          ElMessage.success("删除内容成功")
        }else{
          ElMessage.error("删除内容失败")
        }
      });
    }
    getTaskLists()

    return{
      ...toRefs(data),
      handleChange,
      openThis,
      getTaskLists,
      edit,
      del,
      cancelClick,
      confirmClick,
      cherkDel,
    }
  }
}
</script>