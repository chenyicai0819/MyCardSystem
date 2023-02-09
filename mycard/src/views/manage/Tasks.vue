<template>
  <div class="tasks">
    <el-dialog v-model="dialogTableVisible"
               :show-close="false"
               title="定时任务"
    >
      <div class="tasks-collapse">
        <el-collapse
            v-model="activeNames"
            @change="handleChange"
            accordion="true"
        >
          <el-collapse-item
              v-for="(item,i) in taskLists"
              :title="item.jobName"
              :name="item.id">
            <div>
              <el-descriptions
                  :title="taskLists[i].jobName"
                  column="2"
              >
                <el-descriptions-item label="任务名称">{{ taskLists[i].jobName }}</el-descriptions-item>
                <el-descriptions-item label="任务组">{{ taskLists[i].jobGroup }}</el-descriptions-item>
                <el-descriptions-item label="任务路径">{{ taskLists[i].jobClass+taskLists[i].jobMethod }}</el-descriptions-item>
                <el-descriptions-item label="任务参数">
                  <el-tag size="small">{{ taskLists[i].jobParams }}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="任务Cron">
                  <el-tag size="small">{{ taskLists[i].cron }}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="任务描述">{{ taskLists[i].jobDesc }}</el-descriptions-item>
                <el-descriptions-item label="任务触发器">{{ taskLists[i].triggerName }}</el-descriptions-item>
                <el-descriptions-item label="触发器组">{{ taskLists[i].triggerGroup }}</el-descriptions-item>
                <el-descriptions-item label="触发器描述">{{ taskLists[i].triggerDesc }}</el-descriptions-item>
                <el-descriptions-item label="是否允许并发">{{ taskLists[i].concurrent==1?"禁止":"允许" }}</el-descriptions-item>
                <el-descriptions-item label="状态">{{ taskLists[i].status==1?"启用":"禁用" }}</el-descriptions-item>
                <el-descriptions-item label="任务添加人">{{ taskLists[i].account }}</el-descriptions-item>
                <el-descriptions-item label="添加时间">{{ taskLists[i].createTime }}</el-descriptions-item>
                <el-descriptions-item label="操作">
                  <el-button type="warning" @click="edit(i)">编辑</el-button>
                  <el-popconfirm
                      width="220"
                      confirm-button-text="确认"
                      cancel-button-text="取消"
                      title="确定要删除该条定时任务吗?"
                      @confirm="cherkDel"
                  >
                    <template #reference>
                      <el-button type="danger" @click="del(i)">删除</el-button>
                    </template>
                  </el-popconfirm>
                </el-descriptions-item>

              </el-descriptions>
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>
    </el-dialog>
    <el-drawer v-model="drawerShow" :direction="direction">
      <template #header>
        <h4>编辑定时任务{{drawerData.jobName}}</h4>
      </template>
      <template #default>
        <div>
          {{drawerData}}
        </div>
        <div>
          <el-button @click="cancelClick">取消</el-button>
          <el-button type="primary" @click="confirmClick">修改</el-button>
        </div>
      </template>

    </el-drawer>

  </div>
</template>

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
      activeNames: 1, //定时任务序号
      dialogTableVisible: false, //是否显示当前定时任务弹窗
      taskLists:[], // 定时任务存储列表
      drawerShow:false, //抽屉是否显示
      direction:"rtl", //弹框出现位置
      drawerData:{}, //抽屉数据
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
      data.dialogTableVisible=true
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
            "id":data.drawerData.id,
            "jobName":data.drawerData.jobName,
            "jobGroup":data.drawerData.jobGroup,
            "jobClass":data.drawerData.jobClass,
            "jobMethod":data.drawerData.jobMethod,
            "jobParams":data.drawerData.jobParams,
            "jobDesc":data.drawerData.jobDesc,
            "cron":data.drawerData.cron,
            "triggerName":data.drawerData.triggerName,
            "triggerGroup":data.drawerData.triggerGroup,
            "triggerDesc":data.drawerData.triggerDesc,
            "concurrent":data.drawerData.concurrent,
            "status":data.drawerData.status,
            "account":data.drawerData.account,
          })).then(res=>{
        if (200===res.data.code){
          // console.log(res.data)
          ElMessage.success("修改内容成功")
        }else{
          // console.log(res.data)
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
          // console.log(res.data)
          ElMessage.success("删除内容成功")
        }else{
          // console.log(res.data)
          ElMessage.error("删除内容失败")
        }
      });
    }
    getTaskLists()

    return{
      ...toRefs(data),handleChange,openThis,getTaskLists,edit,del,
      cancelClick,confirmClick,cherkDel,
    }
  }

}
</script>

<style scoped>

</style>