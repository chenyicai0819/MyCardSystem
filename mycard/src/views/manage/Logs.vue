<template>
  <div class="logs-container">
    <!-- 顶部导航 -->
    <header class="logs-header">
      <div class="header-left">
        <h2>操作日志查看</h2>
        <el-tag type="info" effect="plain" class="version-tag">
          系统日志
        </el-tag>
      </div>
      <div class="header-right">
        <!-- 将导出按钮移到这里，与返回按钮并排 -->
        <el-button 
          type="success" 
          @click="DownloadDialogVisible = true"
          icon="Download"
        >
          导出日志
        </el-button>
        <el-button 
          type="primary" 
          @click="toMana"
          icon="Back"
        >
          返回管理页
        </el-button>
      </div>
    </header>

    <!-- 主要内容区域 -->
    <main class="logs-content">
      <el-card class="content-card">
        <el-tabs 
          v-model="activeName" 
          @tab-click="handleClick"
          class="custom-tabs"
        >
          <el-tab-pane 
            v-for="logs in logName" 
            :key="logs.logId" 
            :label="logs.logName" 
            :name="logs.logId"
          >
            <!-- 工具栏 -->
            <div class="toolbar">
              <div class="toolbar-left">
                <el-date-picker
                  v-model="dataTime"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始时间"
                  end-placeholder="结束时间"
                  :shortcuts="shortcuts"
                  value-format="YYYY-MM-DD HH:mm:ss"
                  class="date-picker"
                />
                <el-input
                  v-model="search"
                  placeholder="搜索用户ID..."
                  @change="searchName"
                  clearable
                  class="search-input"
                >
                  <template #prefix>
                    <el-icon><Search /></el-icon>
                  </template>
                </el-input>
              </div>
              <div class="toolbar-right">
                <el-button-group>
                  <el-button 
                    type="primary" 
                    @click="timeSea"
                    icon="Search"
                  >
                    筛选
                  </el-button>
                  <el-button 
                    @click="reLoad"
                    icon="Refresh"
                  >
                    重置
                  </el-button>
                </el-button-group>
              </div>
            </div>

            <!-- 表格区域 -->
            <div class="table-container">
              <el-table 
                :data="tableData" 
                stripe 
                border
                v-loading="loading"
                class="custom-table"
                height="calc(100vh - 400px)"
              >
                <el-table-column 
                  v-for="(thead, i) in tableHead"
                  :key="i"
                  :prop="thead.name"
                  :label="formatLabel(thead.name)"
                  sortable
                  show-overflow-tooltip
                >
                  <template #default="scope">
                    <template v-if="thead.name === 'type'">
                      <el-tag 
                        :type="getTypeTagType(scope.row.type)"
                        size="small"
                      >
                        {{ scope.row.type }}
                      </el-tag>
                    </template>
                    <template v-else-if="thead.name === 'time'">
                      <span class="time-cell">{{ formatTime(scope.row.time) }}</span>
                    </template>
                    <template v-else>
                      {{ scope.row[thead.name] }}
                    </template>
                  </template>
                </el-table-column>
              </el-table>
            </div>

            <!-- 分页器 -->
            <div class="pagination-container">
              <el-pagination
                v-model:currentPage="currentPage"
                :page-sizes="[20, 30, 50, 100]"
                :page-size="pageSize"
                background
                layout="total, sizes, prev, pager, next, jumper"
                :total="allTotal"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
              />
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </main>

    <!-- 导出日志对话框 -->
    <el-dialog
      v-model="DownloadDialogVisible"
      title="导出日志"
      width="500px"
      class="export-dialog"
      destroy-on-close
    >
      <div class="export-content">
        <el-alert
          title="请选择要导出的日志时间范围"
          type="info"
          :closable="false"
          class="export-alert"
        />
        
        <el-date-picker
          v-model="dataTime"
          type="daterange"
          range-separator="至"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          :shortcuts="shortcuts"
          value-format="YYYY-MM-DD HH:mm:ss"
          class="export-date-picker"
        />
      </div>
      
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="DownloadDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="downLogs">
            确认导出
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {getCurrentInstance, inject, reactive, ref, toRefs, watchEffect} from "vue";
import router from "@/router";
import qs from "qs";
import {ElMessage} from "element-plus";
import moment from "moment";
import { Search, Download, Back, Refresh } from '@element-plus/icons-vue'

export default {
  name: "Logs",

  setup() {
    const appStore = inject('appStore')
    const qs = require('qs');
    const {proxy} = getCurrentInstance();
    const RefChilde = ref();
    const count = ref(0)
    const data = reactive({
      logName: [{"logName": "全部日志", "logId": 1}, {"logName": "操作日志", "logId": 2}, {
        "logName": "登录日志",
        "logId": 3
      },], //tab名
      tableData: [], //表格数据
      dataTime: [], //日期时间
      activeName: 1,//当前界面的编号
      tableHead: [
        {"name": "id"},
        {"name": "type"},
        {"name": "user"},
        {"name": "ip"},
        {"name": "classMethod"},
        {"name": "args"},
        {"name": "path"},
        {"name": "time"}
      ],//表头
      search: '', //搜索框内容
      uri: '',//查找的uri
      currentPage: 1, //当前页
      allTotal: '', //全部页
      pageSize: 30,//每页数量
      DownloadDialogVisible:false,//下载弹窗
      downloadLogsIp:"http://localhost:8089/download/logs?",
      shortcuts: [
        {
          text: '前一周',
          value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            return [start, end]
          },
        },
        {
          text: '前一个月',
          value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            return [start, end]
          },
        },
        {
          text: '前三个月',
          value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            return [start, end]
          },
        }],
      filtrate:{ //筛选
        startDate:'',
        endTime:'',
      },
      loading: ref(false)
    })

    //导出备份
    const downLogs = () => {
      let source="beginTime="+data.dataTime[0]+"&endTime="+data.dataTime[1]+"&type="+data.activeName
      // console.log(data.downloadLogsIp+source)
      window.location.href=data.downloadLogsIp+source
      // ElMessage.error('功能正在开发中')
    }
    const toMana = () => {
      router.push("/manages");
    }
    // 切换Tab时
    const handleClick = (tab, event) => {
      // console.log(data.activeName);
      // getHead();
      getCount();
      getData();
    }

    // 根据时间查找数据
    const timeSea = () => {
      if (data.dataTime[0] == null || data.dataTime[1] == null || data.dataTime===[]){
        ElMessage.error("请选择日期")
      }else {
        data.filtrate.startDate=data.dataTime[0]
        data.filtrate.endTime=data.dataTime[1]

        let uri = 'logs/time';
        let active = data.activeName;
        proxy.$axios.get(uri, {params: {page: data.currentPage, size: data.pageSize,
            beginTime:data.dataTime[0], endTime:data.dataTime[1], type: active}}).then(res => {
          data.tableData = res.data;
          for (let i = 0; i < res.data.length; i++) {
            data.tableData[i].time=moment(res.data[i].time).format("yyyy-MM-DD HH:mm:ss");
          }
        });
      }
    }

    //点击重置按钮
    const reLoad = () => {
      getData()
      ElMessage.success("重置成功")
    }

    //查找
    const searchName = () => {
      let search = data.search;
      proxy.$axios.post('/logs/sea', qs.stringify({"user": search})).then(res => {
        const getdata = res.data;
        // console.log(getdata);
        data.tableData = getdata;
      });
    }

    // 每页条数改变
    const handleSizeChange = (val) => {
      data.pageSize = val
      data.currentPage = 1
      getData();
    }
    // 当前页改变
    const handleCurrentChange = (val) => {
      data.currentPage = val
      getData();
    }

    const chooseNum = () => {

    }

    //获取总的数量
    const getCount = () => {
      let active = data.activeName;
      proxy.$axios.get("logs/count", {params: {type: active}}).then(res => {
        data.allTotal = res.data;
      });
    }

    //获取数据
    const getData = () => {
      let uri = 'logs/get';
      let active = data.activeName;
      proxy.$axios.get(uri, {params: {page: data.currentPage, size: data.pageSize, type: active}}).then(res => {
        const getdata = res.data;
        // console.log(getdata)
        data.tableData = getdata;
        for (let i = 0; i < getdata.length; i++) {
          data.tableData[i].time=moment(getdata[i].time).format("yyyy-MM-DD HH:mm:ss");
        }
      });
    }

    //获取表头
    const getHead = () => {

    }

    // 格式化表头标签
    const formatLabel = (label) => {
      const labelMap = {
        'id': 'ID',
        'type': '操作类型',
        'user': '用户',
        'ip': 'IP地址',
        'classMethod': '方法',
        'args': '参数',
        'path': '路径',
        'time': '时间'
      }
      return labelMap[label] || label
    }

    // 获取标签类型
    const getTypeTagType = (type) => {
      const typeMap = {
        '登录': 'success',
        '新增': 'primary',
        '修改': 'warning',
        '删除': 'danger'
      }
      return typeMap[type] || 'info'
    }

    // 格式化时间
    const formatTime = (time) => {
      return moment(time).format('YYYY-MM-DD HH:mm:ss')
    }

    getCount();
    getData();
    return {
      ...toRefs(data), toMana, handleClick, reLoad, searchName, handleSizeChange, handleCurrentChange,
      chooseNum, getCount, getData, downLogs,timeSea,
      formatLabel, getTypeTagType, formatTime
    }
  }
}
</script>

<style scoped>
.logs-container {
  min-height: 100vh;
  background: #f6f8fa;
  padding: 24px;
}

.logs-header {
  background: white;
  padding: 16px 24px;
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
  margin-bottom: 24px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 12px;
}

.header-right {
  display: flex;
  gap: 12px;
  align-items: center;
}

.header-left h2 {
  margin: 0;
  font-size: 1.5rem;
  font-weight: 600;
  color: #1f2937;
}

.version-tag {
  font-weight: 500;
}

.content-card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
  height: calc(100vh - 140px);
  display: flex;
  flex-direction: column;
}

.toolbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 0;
  gap: 16px;
  border-bottom: 1px solid #e5e7eb;
  margin-bottom: 16px;
}

.toolbar-left {
  display: flex;
  align-items: center;
  gap: 12px;
  flex: 1;
}

.date-picker {
  width: 360px;
}

.search-input {
  width: 240px;
}

.toolbar-right {
  display: flex;
  align-items: center;
}

.action-group {
  display: flex;
  gap: 8px;
}

.table-container {
  flex: 1;
  overflow: hidden;
  margin-bottom: 16px;
  position: relative;
}

.custom-table {
  --el-table-border-color: #e5e7eb;
  --el-table-header-bg-color: #f8fafc;
}

:deep(.el-table th) {
  background: var(--el-table-header-bg-color);
  font-weight: 600;
  color: #374151;
  padding: 12px 8px;
  border-bottom: 2px solid #e5e7eb;
}

:deep(.el-table td) {
  padding: 12px 8px;
  color: #4b5563;
}

.time-cell {
  color: #6b7280;
  font-size: 0.9rem;
}

.pagination-container {
  flex-shrink: 0;
  padding: 12px 0;
  background: white;
  border-top: 1px solid #e5e7eb;
}

/* 导出对话框样式 */
.export-dialog {
  --el-dialog-padding-primary: 24px;
}

.export-content {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.export-alert {
  margin: 0;
}

.export-date-picker {
  width: 100%;
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  margin-top: 24px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .logs-container {
    padding: 16px;
  }

  .content-card {
    height: calc(100vh - 100px);
  }

  .logs-header {
    flex-direction: column;
    gap: 12px;
    padding: 12px;
  }

  .header-right {
    width: 100%;
    justify-content: space-between;
  }

  .header-right .el-button {
    flex: 1;
  }

  .toolbar {
    flex-direction: column;
    gap: 12px;
  }

  .toolbar-left {
    flex-direction: column;
    width: 100%;
  }

  .date-picker,
  .search-input {
    width: 100%;
  }

  .toolbar-right {
    width: 100%;
  }

  .toolbar-right .el-button-group {
    width: 100%;
    display: flex;
  }

  .toolbar-right .el-button {
    flex: 1;
  }

  .custom-table {
    height: calc(100vh - 280px);
  }
}

/* Element Plus 组件样式优化 */
:deep(.el-button--success) {
  --el-button-hover-bg-color: #34d399;
  --el-button-hover-border-color: #34d399;
}

:deep(.el-button--primary) {
  --el-button-hover-bg-color: #4096ff;
  --el-button-hover-border-color: #4096ff;
}

:deep(.el-tabs__item) {
  font-size: 14px;
  height: 40px;
  line-height: 40px;
}

:deep(.el-tabs__item.is-active) {
  font-weight: 600;
}

:deep(.el-input__wrapper) {
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
}

:deep(.el-tag) {
  font-weight: 500;
}

.custom-tabs {
  height: 100%;
  display: flex;
  flex-direction: column;
}

:deep(.el-tabs__content) {
  flex: 1;
  overflow: hidden;
  padding: 20px;
}

:deep(.el-tab-pane) {
  height: 100%;
  display: flex;
  flex-direction: column;
}

/* 美化表格滚动条 */
:deep(.el-table__body-wrapper::-webkit-scrollbar) {
  width: 6px;
  height: 6px;
}

:deep(.el-table__body-wrapper::-webkit-scrollbar-thumb) {
  background: #d1d5db;
  border-radius: 3px;
}

:deep(.el-table__body-wrapper::-webkit-scrollbar-track) {
  background: #f3f4f6;
}

/* 固定列样式优化 */
:deep(.el-table__fixed) {
  height: 100% !important;
}

:deep(.el-table__fixed-right) {
  height: 100% !important;
  right: 0;
}
</style>