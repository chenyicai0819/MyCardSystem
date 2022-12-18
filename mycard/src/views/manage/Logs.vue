<template>
  <div class="mana-logs">
    <div class="mana-logs-head">
      <h2 class="mana-logs-h2">查看操作日志</h2>
      <el-button type="danger" @click="toMana">返回管理页</el-button>
    </div>
    <div class="mana-body">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane v-for="logs in logName" :label="logs.logName" :name="logs.logId">
          <div class="buttons">
            <el-button type="success" plain @click="reLoad">重置</el-button>
          </div>
          <div class="table">
            <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column :prop="thead.name"
                               :label="thead.name"
                               sortable

                               show-overflow-tooltip="true"
                               v-for="(thead,i) in tableHead"
                               :key =i
              />
              <el-table-column align="right">
                <template #header>
                  <el-input v-model="search" size="mini" placeholder="输入想找的名字" @change="searchName"/>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div class="demo-pagination-block">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[20, 30, 50, 100]"
          :page-size=pageSize
          background="true"
          layout="total, sizes, prev, pager, next, jumper"
          :total="allTotal"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {getCurrentInstance, inject, reactive, ref, toRefs} from "vue";
import router from "@/router";
import qs from "qs";

export default {
  name: "Logs",
  setup(){
    const appStore=inject('appStore')
    const qs=require('qs');
    const {proxy}=getCurrentInstance();
    const RefChilde=ref();
    const count = ref(0)
    const data=reactive({
      logName:[{"logName":"全部日志","logId":1},{"logName":"操作日志","logId":2},{"logName":"登录日志","logId":3},], //tab名
      tableData:[], //表格数据
      activeName:1,//当前界面的编号
      tableHead:[
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
      uri:'',//查找的uri
      currentPage:1, //当前页
      allTotal:'', //全部页
      pageSize:30,//每页数量
    })
    
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

    //点击重置按钮
    const reLoad = () => {

    }

    //查找
    const searchName = () => {
      chooseNum();
      let search=data.search;
      proxy.$axios.post(data.uri+'sea',qs.stringify({"name":search})).then(res=>{
        const getdata=res.data;
        // console.log(getdata);
        data.tableData=getdata;
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

    const chooseNum = () => {

    }

    //获取总的数量
    const getCount = () => {
      let active=data.activeName;
      proxy.$axios.get("logs/count",{params:{type:active}}).then(res=>{
        data.allTotal=res.data;
      });
    }

    //获取数据
    const getData= () =>{
      let uri='logs/get';
      let active=data.activeName;
      proxy.$axios.get(uri,{params:{page:data.currentPage,size:data.pageSize,type:active}}).then(res=>{
        const getdata=res.data;
        console.log(getdata)
        data.tableData=getdata;
      });


    }

    //获取表头
    const getHead= () =>{

    }


    getCount();
    getData();
    return{
      ...toRefs(data),toMana,handleClick,reLoad,searchName,handleSizeChange,handleCurrentChange,
      chooseNum,getCount,getData
    }
  }
}
</script>

<style scoped>
.mana-logs{
  height: 100vh;
}
.mana-logs-head{
  display: flex;
  position: fixed;
  top: 0px;
  width: 100%;
  z-index: 1;
  background-color: #FFFFFF;
}
.mana-logs-h2{
  width: 90%;
}
.mana-body{
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
</style>