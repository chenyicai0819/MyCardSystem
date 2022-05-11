<template>
  <div class="messages">
    <div class="mana-head">
      <h2 class="mana-h2">网站内容管理界面</h2>
      <el-button type="danger" @click="toLoad">返回首页</el-button>
    </div>
    <div class="manages">
      <el-tabs v-model="data.activeName" @tab-click="handleClick">
        <el-tab-pane v-for="mana in data.manaName" :label="mana.manaName" :name="mana.manaId">
          <div class="buttons">
            <el-button type="primary" @click="openDrawer('增加')">增加</el-button>
            <el-button type="success" @click="upExcel">批量上传</el-button>
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
  </div>
</template>

<script>
import {getCurrentInstance, reactive, defineComponent, ref, computed, inject} from "vue";
import { ElMessageBox } from 'element-plus'
import qs from "qs";
import Add from "./Add";
import router from "../../router";
import { ElMessage } from 'element-plus'
import moment from 'moment'
import _axios from "@/axios/config";
export default {
  name: "Manages",
  components: {Add},
  setup(){

    const appStore=inject('appStore')
    const qs=require('qs');
    const {proxy}=getCurrentInstance();
    const RefChilde=ref();
    const count = ref(0)
    const data=reactive({
      drawer:false,
      direction:'rtl',
      buttenType:'',
      activeName: appStore.activeName,
      search: '',
      deleteId:'',
      editId:'',
      editData:[],
      manaName:[],
      tableData:[],
      tableHead:[],
      maData:[],
      upDate:[],
      id:'',
      uri:'',
      currentPage:1,
      allTotal:'',
      pageSize:20,
    })
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

      }
      proxy.$axios.get(uri,{params:{page:data.currentPage,size:data.pageSize}}).then(res=>{
        const getdata=res.data;
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
    return{
      data, dateFormat, handleSizeChange, handleCurrentChange, toLoad, handleClick, getMana,
      getHead, getData, openDrawer, handleEdit, handleDelete, confirmEvent, cancelEvent, chooseNum,
      searchName, getCount, downAdd, RefChilde, count, load, outExcel,upExcel,
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
  top: 0px;
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