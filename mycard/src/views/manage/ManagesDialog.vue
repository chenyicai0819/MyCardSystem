<template>
  <div class="managesdialog">
    <el-dialog
      v-model="dialogTableVisible"
      title="消息管理"
      width="70%"
      class="message-dialog"
      destroy-on-close
    >
      <div class="message-table-wrapper">
        <el-table 
          :data="messageTable" 
          style="width: 100%"
          height="450px"
          border
          stripe
          class="message-table"
        >
          <el-table-column 
            type="index" 
            label="序号" 
            width="80" 
            align="center"
          />
          <el-table-column 
            property="type" 
            label="类型" 
            width="120"
            align="center"
          >
            <template #default="{ row }">
              <el-tag 
                :type="row.type === '广告' ? 'warning' : row.type === '网站' ? 'primary' : 'info'"
                effect="light"
                class="type-tag"
              >
                {{ row.type }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column 
            property="title" 
            label="标题"
            show-overflow-tooltip
          >
            <template #default="{ row }">
              <div class="message-title">
                <el-badge v-if="!row.isRead" is-dot class="unread-badge" />
                {{ row.title }}
              </div>
            </template>
          </el-table-column>
          <el-table-column 
            fixed="right"
            label="操作" 
            width="120"
            align="center"
          >
            <template #default="scope">
              <el-button
                type="primary"
                link
                @click="showColumn(scope.$index, scope.row)"
              >
                查看详情
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-dialog>

    <el-drawer
      v-model="drawerShow"
      :title="drawerTitle"
      :before-close="drawerHandleClose"
      direction="rtl"
      size="45%"
      class="message-detail-drawer"
      destroy-on-close
    >
      <div class="drawer-content">
        <el-descriptions 
          :column="1" 
          border
          class="message-details"
        >
          <el-descriptions-item label="ID">
            {{ messages.id }}
          </el-descriptions-item>
          
          <el-descriptions-item label="KEY">
            {{ messages.keyid }}
          </el-descriptions-item>
          
          <el-descriptions-item label="类型">
            <el-tag 
              :type="messages.type === '广告' ? 'warning' : messages.type === '网站' ? 'primary' : 'info'"
              class="type-tag"
            >
              {{ messages.type }}
            </el-tag>
          </el-descriptions-item>
          
          <el-descriptions-item label="标题">
            {{ messages.title }}
          </el-descriptions-item>
          
          <el-descriptions-item label="内容">
            <div class="message-content">{{ messages.text }}</div>
          </el-descriptions-item>

          <template v-if="messages.type === '广告'">
            <el-descriptions-item label="金额">
              {{ messages.money }}
            </el-descriptions-item>
            
            <el-descriptions-item label="日期">
              {{ messages.showdate }}
            </el-descriptions-item>
            
            <el-descriptions-item label="电话">
              {{ messages.phone }}
            </el-descriptions-item>
          </template>

          <el-descriptions-item label="邮箱">
            {{ messages.email }}
          </el-descriptions-item>

          <template v-if="messages.type === '网站'">
            <el-descriptions-item label="链接">
              <el-link 
                type="primary" 
                :href="messages.link" 
                target="_blank"
              >
                {{ messages.link }}
              </el-link>
            </el-descriptions-item>
            
            <el-descriptions-item label="名称">
              {{ messages.name }}
            </el-descriptions-item>
            
            <el-descriptions-item label="分类">
              <el-select 
                v-model="messages.Linktype" 
                placeholder="请选择类型"
                class="type-select"
              >
                <el-option
                  v-for="item in collName"
                  :key="item.collName"
                  :label="item.collName"
                  :value="item.collName"
                />
              </el-select>
            </el-descriptions-item>
          </template>

          <el-descriptions-item label="状态">
            <el-tag 
              :type="messages.isPass === '通过' ? 'success' : messages.isPass === '不通过' ? 'danger' : 'info'"
            >
              {{ messages.isPass }}
            </el-tag>
          </el-descriptions-item>
        </el-descriptions>

        <div class="operation-buttons">
          <el-button 
            type="success" 
            @click="passMessage(1)"
            class="operation-btn"
          >
            通过
          </el-button>
          <el-button 
            type="danger" 
            @click="passMessage(2)"
            class="operation-btn"
          >
            拒绝
          </el-button>
          <el-button 
            @click="drawerShow = false"
            class="operation-btn"
          >
            关闭
          </el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import {getCurrentInstance, reactive, toRefs} from "vue";
import {ElMessage} from "element-plus";
import qs from "qs";
import { Check, Close } from '@element-plus/icons-vue'

export default {
  name: "ManagesDialog",
  // props: {
  //   dialogTableVisible:false
  // },

  setup(){
    const {proxy} = getCurrentInstance();
    const data=reactive({
      dialogTableVisible:false, //当前消息列表是否显示
      messageTable:[], // 消息列表
      isRead:0, //是否已读，0未读，1已读
      drawerShow:false, //抽屉显示
      drawerTitle:'',//抽屉表头
      //消息详细信息
      messages:{
        id:'',
        keyid:'',
        type:'',
        isRead:'',
        title:'',
        text:'',
        money:'',
        showdate:'',
        phone:'',
        email:'',
        link:'',
        name:'',
        checkdate:'',
        isPass:'',
        Linktype:'',
      },
      collName:[],
    })

    /**
     * 处理消息
     */
    const passMessage = (ispass) => {
      let uri='/message/pass'
      proxy.$axios.get(uri, {params: {ispass:ispass,keyid:data.messages.keyid}}).then(res => {
        if (res.data===1){
          ElMessage.success("操作成功")
          getMessage()
          data.drawerShow=false
          // 选择提交且类型为网站时
          if (ispass === 1 && data.messages.type === '网站'){
            proxy.$axios.post('mork/add',qs.stringify({"id":data.messages.id,"name":data.messages.title,"link":data.messages.link,
              "type":data.messages.Linktype,"img":'',"text":data.messages.text})).then(res=>{
                if (res === 1){
                  ElMessage.success("网站添加成功")
                }
            });
          }
        }else {
          ElMessage.error("错误！请联系管理员！")
        }

      });
    }

    /**
     * 关闭抽屉触发地方法
     */
    const drawerHandleClose = () => {
      getMessage()
    }

    /**
     * 打开当前界面
     */
    const openThis = () => {
      data.dialogTableVisible=true
    }

    /**
     * 显示日志详情
     */
    const showColumn = (index, row) => {
      data.drawerTitle=row.type+":"+row.title
      data.messages=row
      if (row.type==='网站'){
        getType()
      }
      data.drawerShow=true
    }

    /**
     * 获取消息列表
     */
    const getMessage = () => {
      let uri="/message/isread"
      proxy.$axios.get(uri, {params: {isread: data.isRead}}).then(res => {
        data.messageTable = res.data;
        for (let i = 0; i < res.data.length; i++) {
          if (res.data[i].type === 1){
            data.messageTable[i].type='广告'
          }else if (res.data[i].type === 2){
            data.messageTable[i].type='网站'
          }
          // data.messageTable[i].type=res.data[i].type = 1 ? "广告" : "消息"
          if (res.data[i].isPass === 0){
            data.messageTable[i].isPass='未处理'
          }else if (res.data[i].isPass === 1){
            data.messageTable[i].isPass='通过'
          }else if (res.data[i].isPass === 2){
            data.messageTable[i].isPass='不通过'
          }
        }

      });
    }
    /**
     * 获取网站类型
     */
    const getType = () => {
      let uri='coll/show';
      proxy.$axios.get(uri,{}).then(res=>{
        data.collName=res.data;
      });
    }
    getMessage() //显示该弹框获取数据
    return{
      ...toRefs(data),openThis,showColumn,getMessage,drawerHandleClose,passMessage,getType, Check, Close
    }
  }
}
</script>

<style scoped>
/* 消息列表弹窗样式 */
.message-dialog {
  --el-dialog-padding-primary: 20px;
  --el-dialog-bg-color: var(--el-bg-color);
  --el-dialog-border-radius: 8px;
}

.message-dialog :deep(.el-dialog__header) {
  margin: 0;
  padding: 20px 24px;
  border-bottom: 1px solid var(--el-border-color-lighter);
}

.message-dialog :deep(.el-dialog__title) {
  font-size: 18px;
  font-weight: 600;
  color: var(--el-text-color-primary);
}

.message-dialog :deep(.el-dialog__body) {
  padding: 20px;
}

.message-table-wrapper {
  border-radius: 4px;
  overflow: hidden;
}

/* 表格样式优化 */
.message-table {
  --el-table-border-color: var(--el-border-color-lighter);
  --el-table-header-bg-color: var(--el-fill-color-light);
  --el-table-row-hover-bg-color: var(--el-fill-color-lighter);
}

.message-table :deep(.el-table__header) {
  font-weight: 600;
}

.message-table :deep(.el-table__row) {
  cursor: pointer;
}

.message-table :deep(.el-table__row:hover) {
  background-color: var(--el-fill-color-lighter);
}

/* 消息标题样式 */
.message-title {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 4px 0;
}

/* 未读消息标记 */
.unread-badge :deep(.el-badge__content) {
  background-color: var(--el-color-danger);
}

/* 类型标签样式 */
.type-tag {
  font-size: 12px;
  padding: 0 8px;
  height: 24px;
  line-height: 22px;
  border-radius: 4px;
}

/* 操作按钮样式 */
.message-table :deep(.el-button--link) {
  font-size: 14px;
  padding: 4px 0;
}

.message-table :deep(.el-button--link:hover) {
  opacity: 0.8;
}

/* 滚动条样式 */
.message-table :deep(.el-table__body-wrapper::-webkit-scrollbar) {
  width: 6px;
  height: 6px;
}

.message-table :deep(.el-table__body-wrapper::-webkit-scrollbar-thumb) {
  background: var(--el-border-color);
  border-radius: 3px;
}

.message-table :deep(.el-table__body-wrapper::-webkit-scrollbar-track) {
  background: var(--el-fill-color-lighter);
}

/* 响应式调整 */
@media screen and (max-width: 768px) {
  .message-dialog {
    width: 95% !important;
    margin: 0 auto;
  }

  .message-dialog :deep(.el-dialog__body) {
    padding: 16px;
  }

  .message-table :deep(.el-table__header-wrapper) {
    display: none;
  }
}

/* 消息详情抽屉样式 */
.message-detail-drawer {
  --el-drawer-padding-primary: 0;
}

.drawer-content {
  padding: 24px;
  height: 100%;
  overflow-y: auto;
}

/* 描述列表样式 */
.message-details {
  --el-descriptions-table-border: 1px solid var(--el-border-color-lighter);
  --el-descriptions-item-bordered-label-background: var(--el-fill-color-light);
}

.message-details :deep(.el-descriptions__label) {
  width: 100px;
  font-weight: 500;
  color: var(--el-text-color-regular);
  padding: 16px 24px;
}

.message-details :deep(.el-descriptions__content) {
  padding: 16px 24px;
}

/* 消息内容样式 */
.message-content {
  white-space: pre-wrap;
  line-height: 1.6;
  max-height: 200px;
  overflow-y: auto;
  padding: 12px;
  background-color: var(--el-fill-color-lighter);
  border-radius: 4px;
}

/* 类型选择器样式 */
.type-select {
  width: 100%;
}

/* 标签样式 */
.type-tag {
  font-size: 13px;
  padding: 0 10px;
  height: 26px;
  line-height: 24px;
}

/* 操作按钮区域样式 */
.operation-buttons {
  margin-top: 24px;
  padding: 20px 0;
  border-top: 1px solid var(--el-border-color-lighter);
  display: flex;
  justify-content: center;
  gap: 12px;
}

.operation-btn {
  min-width: 90px;
  font-size: 14px;
}

/* 滚动条样式 */
.drawer-content::-webkit-scrollbar,
.message-content::-webkit-scrollbar {
  width: 6px;
}

.drawer-content::-webkit-scrollbar-thumb,
.message-content::-webkit-scrollbar-thumb {
  background: var(--el-border-color);
  border-radius: 3px;
}

.drawer-content::-webkit-scrollbar-track,
.message-content::-webkit-scrollbar-track {
  background: var(--el-fill-color-lighter);
}

/* 响应式调整 */
@media screen and (max-width: 768px) {
  .message-detail-drawer {
    width: 90% !important;
  }

  .drawer-content {
    padding: 16px;
  }

  .message-details :deep(.el-descriptions__label) {
    padding: 12px 16px;
  }

  .message-details :deep(.el-descriptions__content) {
    padding: 12px 16px;
  }

  .operation-buttons {
    margin-top: 20px;
    padding: 16px 0;
    flex-wrap: wrap;
  }

  .operation-btn {
    flex: 1;
    min-width: 0;
  }
}
</style>