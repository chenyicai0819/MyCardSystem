<template>
  <div class="messages">
    <div class="mana-head">
      <h2 class="mana-h2">网站内容管理界面</h2>
      <el-badge :value="data.messageNotReadCount" :max="99" class="item" style="right: 15px">
        <el-button type="warning" circle @click="openMd"><el-icon><Message /></el-icon></el-button>
      </el-badge>
      <el-button type="danger" @click="toLogs">查看日志</el-button>
      <el-button type="danger" @click="toLoad">返回首页</el-button>
    </div>
    <div class="manages">
      <el-tabs v-model="data.activeName" @tab-click="handleClick">
        <el-tab-pane v-for="mana in data.manaName" :label="mana.manaName" :name="mana.manaId">
          <div class="buttons">
            <el-button type="primary" @click="openDrawer('增加')">增加</el-button>
            <el-button type="success" @click="data.uploaddialog=true">批量上传</el-button>
            <el-button type="success" plain @click="outExcel">导出备份</el-button>
          </div>
          <div class="table">
            <el-table :data="data.tableData" stripe style="width: 100%">
              <el-table-column :prop="thead.name"
                               :label="thead.name"
                               sortable
                               v-for="(thead,i) in data.tableHead"
                               :key =i
              />
              <el-table-column align="right">
                <template #header>
                  <el-input v-model="data.search" size="mini" placeholder="输入想找的名字" @change="searchName"/>
                </template>
                <template #default="scope">
                  <el-button size="mini" @click="handleEdit(scope.$index, scope.row)"
                  >Edit</el-button
                  >
                  <el-popconfirm
                      confirm-button-text="Yes"
                      cancel-button-text="No"
                      icon="el-icon-info"
                      icon-color="red"
                      :title="'确定要删除'+data.deleteId+'吗'"
                      @confirm="confirmEvent"
                      @cancel="cancelEvent"
                  >
                    <template #reference>
                      <el-button
                          size="mini"
                          type="danger"
                          @click="handleDelete(scope.$index, scope.row)"
                      >Delete</el-button
                      >
                    </template>
                  </el-popconfirm>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div class="demo-pagination-block">
      <el-pagination
          v-model:currentPage="data.currentPage"
          :page-sizes="[10, 20, 30, 40, 50]"
          :page-size=data.pageSize
          background="true"
          layout="total, sizes, prev, pager, next, jumper"
          :total="data.allTotal"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
    <div class="drawers">
      <el-drawer
          v-model="data.drawer"
          :title="data.buttenType"
          :direction="data.direction"
          size="50%"
      >
        <Add :numlength="data.tableHead"
             :manatype="data.buttenType"
             :mana-data="data.editData"
             :active-name="data.activeName"
             :drawer="data.drawer"
             @checkSucces="downAdd"
             ref="RefChilde"
        ></Add>
      </el-drawer>
    </div>
<!--    批量上传弹框-->
    <el-dialog v-model="data.uploaddialog" :title=data.uploadTiele[data.activeName-1]>
      <el-link :href=data.downloadModelIp+data.activeName target="_blank" type="danger">点击此处下载对应的模板</el-link>
<!--      原生上传模块/-->
<!--      <input type="file" @change="updataFile" />-->
      <div style="margin-top: 5px">
        <el-upload
            v-model:file-list="data.fileList"
            class="manages-upload"
            :action=data.uploadFile
            multiple
            name="multipartFile"
            limit="1"
            :on-exceed="onExceed"
            :on-remove="onRemove"
            :on-error="onError"
            :on-success="onSuccess"

        >
          <el-button type="primary">点击上传</el-button>
          <template #tip>
            <div class="el-upload__tip">
              只能上传excel(后缀为.xls或.xlsx)文件，一次只能上传一个文件，请先删除已经上传的文件
            </div>
          </template>
        </el-upload>
      </div>
    </el-dialog>

    <ManagesDialog ref="RefManagesDialog"/>
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
export default {
  name: "Manages",
  components: {ManagesDialog, Add},
  setup(){

    const appStore=inject('appStore')
    const qs=require('qs');
    const {proxy}=getCurrentInstance();
    const RefChilde=ref();
    const RefManagesDialog=ref(null);
    const count = ref(0)
    const data=reactive({
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

    /**
     * 打开消息子组件
     */
    const openMd = () => {
      RefManagesDialog.value.openThis()
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
      // console.log(data.activeName);
      getHead();
      getCount();
      getData();
    }
    const openDrawer = (buttenType) => {
      data.drawer=true;
      data.buttenType=buttenType;
      // 使用子组件方法
      setTimeout(() => {
        RefChilde.value.getFather();
      }, 500);
    }
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
    const downAdd = () => {
      data.drawer=false
      getData()
    }
    const getMana= () =>{
      proxy.$axios.get('mana/get',{}).then(res=>{
        const getdata=res.data;
        data.manaName=getdata;
      });
    }
    const getHead= () =>{
      proxy.$axios.post('mana/head',qs.stringify({"head":data.activeName})).then(res=>{
        const getdata=res.data;
        // console.log(getdata);
        data.tableHead=getdata;
      });
    }
    const getData= () =>{
      let uri;
      let active=data.activeName;
      if (active==1){
        uri='card/get';
      }else if(active==2){
        uri='mork/get';
      }else if(active==3){
        uri='coll/get';
      }else if (active==4){
        uri='ad/get';
      }else if (active==5){
        uri='imgs/get';
      }
      proxy.$axios.get(uri,{params:{page:data.currentPage,size:data.pageSize}}).then(res=>{
        const getdata=res.data;
        console.log(getdata)
        data.tableData=getdata;
      });

    }
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
    const searchName = () => {
      chooseNum();
      let search=data.search;
      proxy.$axios.post(data.uri+'sea',qs.stringify({"name":search})).then(res=>{
        const getdata=res.data;
        // console.log(getdata);
        data.tableData=getdata;
      });
    }
    const toLoad = () => {
      router.push("/home");
      localStorage.removeItem("loginToken");
    }
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

    getMana();
    getHead();
    getCount();
    getData();
    chooseNum();
    getMessageNotReadCount()
    return{
      data, dateFormat, handleSizeChange, handleCurrentChange, toLoad, handleClick, getMana,
      getHead, getData, openDrawer, handleEdit, handleDelete, confirmEvent, cancelEvent, chooseNum,
      searchName, getCount, downAdd, RefChilde, count, load, outExcel,upExcel,updataFile,onSuccess,
      onExceed,onRemove,onError,toLogs,RefManagesDialog,openMd,getMessageNotReadCount,
    }
  }
}
</script>

<style scoped>
.messages{
  height: 100vh;
}
.mana-head{
  display: flex;
  position: fixed;
  top: 10px;
  width: 100%;
  z-index: 1;
  background-color: #FFFFFF;
}
.mana-h2{
  width: 90%;
}
.manages{
  width: 95%;
  margin:30px auto;
  z-index: 2;
}
.buttons{
  display: flex;
}
.demo-pagination-block {
  position: fixed;
  bottom: 0px;
  background-color: #FFFFFF;
}
.table{

}
</style>