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
            <el-date-picker
                v-model="dataTime"
                type="daterange"
                range-separator="至"
                start-placeholder="开始时间"
                end-placeholder="结束时间"
                :shortcuts="shortcuts"
                size="default"
                style="margin-right: 5px"
                value-format="YYYY-MM-DD HH:mm:ss"
            />
            <el-button type="success" @click="timeSea" >筛选</el-button>
            <el-button type="success" plain @click="reLoad">重置</el-button>
            <el-button type="info" plain @click="DownloadDialogVisible = true">导出</el-button>
          </div>
          <div class="table">
            <el-table :data="tableData" stripe style="width: 100%">
              <el-table-column :prop="thead.name"
                               :label="thead.name"
                               sortable

                               show-overflow-tooltip="true"
                               v-for="(thead,i) in tableHead"
                               :key=i
              />
              <el-table-column align="right">
                <template #header>
                  <el-input v-model="search" size="mini" placeholder="输入想找的用户ID" @change="searchName"/>
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

    <el-dialog v-model="DownloadDialogVisible" title="备份日志" width="30%" center>
      <el-date-picker
          v-model="dataTime"
          type="daterange"
          range-separator="至"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          :shortcuts="shortcuts"
          size="default"
          style="margin-right: 5px"
          value-format="YYYY-MM-DD HH:mm:ss"
      />
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="DownloadDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="downLogs">
          备份
        </el-button>
      </span>
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
        {"name": "userMongo"},
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
      }
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
      proxy.$axios.post('/logs/sea', qs.stringify({"userMongo": search})).then(res => {
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


    getCount();
    getData();
    return {
      ...toRefs(data), toMana, handleClick, reLoad, searchName, handleSizeChange, handleCurrentChange,
      chooseNum, getCount, getData, downLogs,timeSea,
    }
  }
}
</script>

<style scoped>
.mana-logs {
  height: 100vh;
}

.mana-logs-head {
  display: flex;
  position: fixed;
  top: 0px;
  width: 100%;
  z-index: 1;
  background-color: #FFFFFF;
}

.mana-logs-h2 {
  width: 90%;
}

.mana-body {
  width: 95%;
  margin: 30px auto;
  z-index: 2;
}

.buttons {
  display: flex;
}

.demo-pagination-block {
  position: fixed;
  bottom: 0px;
  background-color: #FFFFFF;
}
</style>