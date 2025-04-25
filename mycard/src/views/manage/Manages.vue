<template>
  <div class="manages-container">
    <!-- 顶部导航栏 -->
    <header class="manages-header">
      <div class="header-left">
        <h2>网站内容管理</h2>
      </div>
      
      <div class="header-actions">
        <el-button-group>
          <el-button 
            type="primary" 
            @click="openTasks"
            icon="el-icon-timer"
          >
            定时任务
          </el-button>
          <el-button 
            type="primary" 
            @click="toLogs"
            icon="el-icon-document"
          >
            查看日志
          </el-button>
        </el-button-group>

        <el-badge 
          :value="data.messageNotReadCount" 
          :max="99" 
          class="message-badge"
        >
          <el-button
            type="primary"
            circle
            @click="openMd"
          >
            <el-icon><Message /></el-icon>
          </el-button>
        </el-badge>

        <el-button 
          type="default" 
          @click="toLoad"
          icon="el-icon-back"
        >
          返回首页
        </el-button>
      </div>
    </header>

    <!-- 主要内容区域 -->
    <main class="manages-content">
      <el-card class="content-card">
        <el-tabs 
          v-model="data.activeName" 
          @tab-click="handleClick"
          class="custom-tabs"
        >
          <el-tab-pane 
            v-for="mana in data.manaName" 
            :key="mana.manaId"
            :label="mana.manaName" 
            :name="mana.manaId"
          >
            <!-- 操作按钮组 -->
            <div class="action-toolbar">
              <div class="toolbar-left">
                <el-button-group>
                  <el-button 
                    type="primary" 
                    icon="el-icon-plus"
                    @click="openDrawer('增加')"
                  >
                    新增
                  </el-button>
                  <el-button 
                    type="primary" 
                    icon="el-icon-upload"
                    @click="data.uploaddialog=true"
                  >
                    批量导入
                  </el-button>
                  <el-button 
                    type="primary" 
                    icon="el-icon-download"
                    plain
                    @click="outExcel"
                  >
                    导出备份
                  </el-button>
                </el-button-group>
              </div>

              <div class="toolbar-right">
                <el-input
                  v-model="data.search"
                  placeholder="搜索..."
                  prefix-icon="el-icon-search"
                  clearable
                  @change="searchName"
                  class="search-input"
                />
              </div>
            </div>

            <!-- 表格区域 -->
            <div class="table-container">
              <el-table 
                :data="data.tableData" 
                stripe 
                class="custom-table"
                v-loading="loading"
                height="calc(100vh - 380px)"
              >
                <el-table-column
                  v-for="(thead, i) in data.tableHead"
                  :key="i"
                  :prop="thead"
                  :label="thead"
                  sortable
                />
                
                <el-table-column align="right" width="180">
                  <template #default="scope">
                    <el-button-group>
                      <el-button
                        size="small"
                        type="primary"
                        plain
                        @click="handleEdit(scope.$index, scope.row)"
                        icon="el-icon-edit"
                      >
                        编辑
                      </el-button>
                      
                      <el-popconfirm
                        title="确定要删除这条记录吗？"
                        @confirm="confirmEvent"
                        @cancel="cancelEvent"
                      >
                        <template #reference>
                          <el-button
                            size="small"
                            type="danger"
                            plain
                            icon="el-icon-delete"
                            @click="handleDelete(scope.$index, scope.row)"
                          >
                            删除
                          </el-button>
                        </template>
                      </el-popconfirm>
                    </el-button-group>
                  </template>
                </el-table-column>
              </el-table>
            </div>

            <!-- 分页器 -->
            <div class="pagination-container">
              <el-pagination
                v-model:currentPage="data.currentPage"
                :page-sizes="[10, 20, 30, 40, 50]"
                :page-size="data.pageSize"
                background
                layout="total, sizes, prev, pager, next, jumper"
                :total="data.allTotal"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
              />
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </main>

    <!-- 用户信息 -->
    <footer class="manages-footer">
      <div class="user-info">
        <el-avatar 
          :size="32"
          icon="el-icon-user"
        />
        <span>{{ data.nowUser.userName }}</span>
      </div>
    </footer>

    <!-- 抽屉组件 -->
    <el-drawer
      v-model="data.drawer"
      :title="data.buttenType"
      :direction="data.direction"
      size="50%"
      destroy-on-close
      class="custom-drawer"
    >
      <Add 
        :numlength="data.tableHead"
        :manatype="data.buttenType"
        :mana-data="data.editData"
        :active-name="data.activeName"
        :drawer="data.drawer"
        @checkSucces="downAdd"
        ref="RefChilde"
      />
    </el-drawer>

    <!-- 批量上传对话框 -->
    <el-dialog 
      v-model="data.uploaddialog" 
      :title="data.uploadTiele[data.activeName-1]"
      width="500px"
      class="custom-dialog"
    >
      <div class="upload-content">
        <el-alert
          type="info"
          :closable="false"
        >
          <template #default>
            <el-link 
              :href="data.downloadModelIp + data.activeName" 
              target="_blank" 
              type="primary"
            >
              下载导入模板
            </el-link>
          </template>
        </el-alert>

        <el-upload
          v-model:file-list="data.fileList"
          class="upload-area"
          :action="data.uploadFile"
          :limit="1"
          :on-exceed="onExceed"
          :on-remove="onRemove"
          :on-error="onError"
          :on-success="onSuccess"
        >
          <el-button type="primary" icon="el-icon-upload">
            选择文件
          </el-button>
          <template #tip>
            <div class="upload-tip">
              仅支持 .xls 或 .xlsx 格式的 Excel 文件
            </div>
          </template>
        </el-upload>
      </div>
    </el-dialog>

    <!-- 其他组件 -->
    <ManagesDialog ref="RefManagesDialog"/>
    <Tasks ref="RefTasks"/>
  </div>
</template>

<script>
import { Message } from '@element-plus/icons-vue'
import {getCurrentInstance, reactive, defineComponent, ref, computed, inject} from "vue";
import { ElMessageBox } from 'element-plus'
import qs from "qs";
import Add from "./Add";
import router from "../../router";
import { ElMessage } from 'element-plus'
import { UploadFilled } from '@element-plus/icons-vue'
import moment from 'moment'
import _axios from "@/axios/config";
import ManagesDialog from "@/views/manage/ManagesDialog";
import Tasks from "@/views/manage/Tasks";

export default {
  name: "Manages",
  components: {Tasks, ManagesDialog, Add},
  setup(){
    const appStore=inject('appStore')
    const qs=require('qs');
    const {proxy}=getCurrentInstance();
    const RefChilde=ref();
    const RefManagesDialog=ref(null);
    const RefTasks=ref(null);
    const count = ref(0)
    const data=reactive({
      nowUser:{},//当前登录用户
      drawer:false,
      direction:'rtl',
      buttenType:'',
      activeName: appStore.activeName, //当前界面的编号
      search: '',
      deleteId:'',
      editId:'',
      editData:[],
      manaName:[],
      tableData:[],
      tableHead:[],
      maData:[],
      upDate:[],
      fileList:[], //文件上传文件夹
      id:'',
      uri:'',
      currentPage:1,
      allTotal:'',
      pageSize:20,
      uploaddialog:false,
      uploadTiele:["批量上传-页面","批量上传-书签内容","批量上传-书签分类","批量上传-广告"],
      imgs:false,
      downloadModelIp:"http://8.129.212.155:8089/download/model?id=",
      uploadFile:"http://8.129.212.155:8089/file/upload/?id="+appStore.activeName,
      manages_dialog_dialog:false,//消息弹窗是否显示
      messageNotReadCount:0,//未读消息的数量
    })

    // 获取到用户信息
    const getUserMessageFromStore = () => {
      proxy.$axios.post('user/getUserMessage').then(res=>{
        data.nowUser = res.data
      });
    }

    /**
     * 打开消息子组件
     */
    const openMd = () => {
      RefManagesDialog.value.openThis()
    }

    /**
     * 打开定时任务组件
     */
    const openTasks = () => {
      RefTasks.value.openThis()
    }
    /**
     * 获取未读消息的数量
     */
    const getMessageNotReadCount = () => {
      proxy.$axios.get('message/count',qs.stringify({})).then(res=>{
        data.messageNotReadCount=res.data;
      });
    }

    /**
     * 前往查看日志
     */
    const toLogs = () => {
      router.push("/logs");
    }

    /**
     * 上传文件时候各种回调
     * @param mes
     */
    const onExceed = () => {
      ElMessage.warning("一次只能上传一个文件，请先删除上传成功的文件")
    }
    const onSuccess = () => {
      ElMessage.success("上传成功")
    }
    const onError = () => {
      ElMessage.error("上传失败")
    }
    const onRemove = () => {
      ElMessage.info("文件删除成功")
    }

    /**
     * 上传文件，暂时不用到
     * @param e
     */
    const updataFile =(e)=>{
      let file = e.target.files[0];//拿到上传的file
      let param = new FormData();//创建form对象
      param.append("multipartFile", file);//为创建的form对象增加上传的文件
      param.append("id", data.activeName);//如果需要上传其他字段，在这里增加
      let headers= { 'content-type': 'multipart/form-data' }//修改请求头
      proxy.$axios.get('file/upload',param, headers).then(res=>{
        console.log(res, "res");
      });
    }
    // 时间格式化
    const dateFormat = (row,column) => {

    }
    const load = () => {
      count.value += 2
    }
    // 切换Tab时
    const handleClick = (tab, event) => {
      console.log(data.activeName);
      getHead();
      getCount();
      getData();
    }

    // 打开子组件界面
    const openDrawer = (buttenType) => {
      data.drawer=true;
      data.buttenType=buttenType;
      // 使用子组件方法
      setTimeout(() => {
        RefChilde.value.getFather();
      }, 500);
    }

    // 点击编辑按钮
    const handleEdit = (index, row) => {
      chooseNum();
      let active=data.activeName;
      if (active==1){
        data.editId=row.cardId;
      }else if(active==2){
        data.editId=row.morkId;
      }else if(active==3){
        data.editId=row.collId;
      }else if (active==4){
        data.editId=row.adId;
      }else if (active==5){
        data.editId=row.imgsId;
      }
      data.editData=row
      data.buttenType='修改'
      // console.log(data.editData);
      // RefChilde.value.getFather();
      openDrawer('修改');

    }
    /**
     * 导出备份
     */
    const outExcel = () => {
      let active=data.activeName;
      window.location.href="http://8.129.212.155:8089/download/message?id="+active
      // window.location.href="http://localhost:8089/download/message?id="+active
    }
    /**
     * 批量上传
     */
    const upExcel = () => {
      ElMessage.error('功能正在开发中')
    }

    // 点击删除按钮
    const handleDelete = (index, row) => {
      // console.log(index, row)
      chooseNum();
      let active=data.activeName;
      if (active==1){
        data.deleteId=row.cardId;
      }else if(active==2){
        data.deleteId=row.morkId;
      }else if(active==3){
        data.deleteId=row.collId;
      }else if (active==4){
        data.deleteId=row.adId;
      }else if (active==5){
        data.deleteId=row.imgsId;
      }
    }

    // 确认添加
    const downAdd = () => {
      data.drawer=false
      getData()
    }

    // 获取标签
    const getMana= () =>{
      proxy.$axios.get('mana/get',{}).then(res=>{
        const getdata=res.data;
        data.manaName=getdata;
      });
    }
    // 获取表格的字段头
    const getHead= () =>{
      proxy.$axios.post('mana/head',qs.stringify({"head":data.activeName})).then(res=>{
        const getdata=res.data;
        console.log(getdata);
        data.tableHead=getdata;
      });
    }

    // 获取主要数据
    const getData= () =>{
      let uri;
      let active=data.activeName;
      if (active===1){
        uri='card/get';
      }else if(active===2){
        uri='mork/get';
      }else if(active===3){
        uri='coll/get';
      }else if (active===4){
        uri='ad/get';
      }else if (active===5){
        uri='imgs/get';
      }
      proxy.$axios.get(uri,{params:{page:data.currentPage,size:data.pageSize}}).then(res=>{
        const getdata=res.data;
        console.log(getdata)
        data.tableData=getdata;
      });
    }

    // 确认删除
    const confirmEvent = () => {
      proxy.$axios.post(data.uri+'del',qs.stringify({"id":data.deleteId})).then(res=>{
        const getdata=res.data;
        // console.log(getdata);
        getData();
      });
    }
    const cancelEvent = () => {
      // console.log('cancel!')
    }

    // 搜索
    const searchName = () => {
      chooseNum();
      let search=data.search;
      proxy.$axios.post(data.uri+'sea',qs.stringify({"name":search})).then(res=>{
        const getdata=res.data;
        // console.log(getdata);
        data.tableData=getdata;
      });
    }

    // 登出
    const toLoad = () => {
      router.push("/home");
      localStorage.removeItem("loginToken");

    }

    // 显示数据
    const chooseNum = () => {
      let active=data.activeName;
      let manadata=data.editData;
      data.maData.splice(0,data.maData.length)
      data.upDate.splice(0,data.maData.length)
      if (active==1){
        data.id='cardId';
        data.uri='card/';
        data.maData.push({"text": manadata.cardId}),
        data.maData.push({"text": manadata.cardName}),
        data.maData.push({"text": manadata.cardLink}),
        data.maData.push({"text": manadata.cardImg})
        data.upDate.push({"cardId":manadata.cardId,"cardName": manadata.cardName,"cardLink": manadata.cardLink,"cardImg": manadata.cardImg})
      }else if(active==2){
        data.id='morkId';
        data.uri='mork/';
        data.maData.push({"text": manadata.morkId}),
        data.maData.push({"text": manadata.morkImg}),
        data.maData.push({"text": manadata.morkLink}),
        data.maData.push({"text": manadata.morkName}),
        data.maData.push({"text": manadata.morkType}),
        data.maData.push({"text": manadata.morkText})
      }else if(active==3){
        data.id='collId';
        data.uri='coll/';
        data.maData.push({"text": manadata.collId}),
        data.maData.push({"text": manadata.collImg}),
        data.maData.push({"text": manadata.collName}),
        data.maData.push({"text": manadata.collText})
      }else if (active==4){
        data.id='adId';
        data.uri='ad/';
        data.maData.push({"text": manadata.adId}),
        data.maData.push({"text": manadata.adImg}),
        data.maData.push({"text": manadata.adName}),
        data.maData.push({"text": manadata.adText}),
        data.maData.push({"text": manadata.adUpDate}),
        data.maData.push({"text": manadata.adDownDate})
      }else if (active==5){
        data.id='imgsId';
        data.uri='imgs/';
        data.maData.push({"text": manadata.imgsId}),
            data.maData.push({"text": manadata.imgsName}),
            data.maData.push({"text": manadata.imgsText}),
            data.maData.push({"text": manadata.imgsLink})
      }
    }

    // 获取数据数量
    const getCount = () => {
      chooseNum();
      proxy.$axios.get(data.uri+"count",{}).then(res=>{
        data.allTotal=res.data;
      });
    }
    // 每页条数改变
    const handleSizeChange = (val) => {
      data.pageSize=val
      data.currentPage=1
      getData();
    }
    // 当前页改变
    const handleCurrentChange = (val) => {
      data.currentPage=val
      getData();
    }

    // 以下是进入界面后执行的方法
    getMana();
    getUserMessageFromStore();
    getHead();
    getCount();
    getData();
    chooseNum();
    getMessageNotReadCount();


    return{
      data, dateFormat, handleSizeChange, handleCurrentChange, toLoad, handleClick, getMana,
      getHead, getData, openDrawer, handleEdit, handleDelete, confirmEvent, cancelEvent, chooseNum,
      searchName, getCount, downAdd, RefChilde, count, load, outExcel,upExcel,updataFile,onSuccess,
      onExceed,onRemove,onError,toLogs,RefManagesDialog,openMd,getMessageNotReadCount,getUserMessageFromStore,
      openTasks,RefTasks,
    }

  }
}
</script>

<style scoped>
.manages-container {
  min-height: 100vh;
  background: #f6f8fa;
  display: flex;
  flex-direction: column;
}

.manages-header {
  background: white;
  padding: 1rem 2rem;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: sticky;
  top: 0;
  z-index: 10;
}

.header-left h2 {
  margin: 0;
  color: #1a1a1a;
  font-size: 1.5rem;
  font-weight: 600;
}

.header-actions {
  display: flex;
  gap: 1rem;
  align-items: center;
}

.manages-content {
  flex: 1;
  padding: 2rem;
  overflow: hidden; /* 防止内容溢出 */
}

.content-card {
  height: calc(100vh - 140px); /* 减去头部和padding的高度 */
  background: white;
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
  display: flex;
  flex-direction: column;
}

.custom-tabs {
  height: 100%;
  display: flex;
  flex-direction: column;
}

:deep(.el-tabs__content) {
  flex: 1;
  overflow: hidden;
}

:deep(.el-tab-pane) {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.action-toolbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
  padding: 8px 0;
}

.search-input {
  width: 300px;
}

.table-container {
  flex: 1;
  overflow: hidden;
  margin-bottom: 8px;
}

.custom-table {
  height: calc(100vh - 240px);
  margin-bottom: 1.5rem;
}

.pagination-container {
  display: flex;
  justify-content: center;
  padding: 8px 0;
}

.manages-footer {
  background: white;
  padding: 1rem 2rem;
  border-top: 1px solid #eee;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  color: #666;
}

.custom-drawer :deep(.el-drawer__header) {
  margin-bottom: 0;
  padding: 1rem 1.5rem;
  border-bottom: 1px solid #eee;
}

.upload-content {
  padding: 1rem;
}

.upload-area {
  margin-top: 1.5rem;
}

.upload-tip {
  color: #666;
  font-size: 0.9rem;
  margin-top: 0.5rem;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .manages-header {
    padding: 1rem;
    flex-direction: column;
    gap: 1rem;
  }

  .manages-content {
    padding: 1rem;
  }

  .action-toolbar {
    flex-direction: column;
    gap: 8px;
  }

  .toolbar-right {
    width: 100%;
  }

  .search-input {
    width: 100%;
  }
}

/* Element Plus 组件样式优化 */
:deep(.el-table) {
  --el-table-border-color: #eee;
  --el-table-header-bg-color: #f8f9fa;
}

:deep(.el-button--primary) {
  --el-button-hover-bg-color: #4096ff;
  --el-button-hover-border-color: #4096ff;
}

:deep(.el-tabs__header) {
  margin-bottom: 12px;
}

:deep(.el-tabs__item) {
  height: 32px;
  line-height: 32px;
  padding: 0 12px;
  font-size: 13px;
}

:deep(.el-button) {
  padding: 8px 12px;
  font-size: 13px;
  height: 32px;
}

:deep(.el-input) {
  height: 32px;
}

:deep(.el-input__inner) {
  height: 32px;
  line-height: 32px;
  font-size: 13px;
}

:deep(.el-input__icon) {
  line-height: 32px;
}

:deep(.el-pagination) {
  height: 28px;
  line-height: 28px;
}

:deep(.el-pagination .el-select .el-input) {
  width: 100px;
}

:deep(.el-pagination button) {
  height: 28px;
  min-width: 28px;
}

:deep(.el-pagination .el-input__inner) {
  height: 28px;
}

:deep(.el-pager li) {
  height: 28px;
  line-height: 28px;
  min-width: 28px;
}

:deep(.el-table) {
  font-size: 13px;
}

:deep(.el-table .cell) {
  line-height: 20px;
  padding: 8px;
}
</style>