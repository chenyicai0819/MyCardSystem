<template>
  <div class="managesdialog">
    <el-dialog v-model="dialogTableVisible"
               :show-close="false"
               title="消息列表"
    >
      <el-table :data="messageTable" height="300px">
        <el-table-column property="id" label="id" width="100" />
        <el-table-column property="type" label="type" width="100" />
        <el-table-column property="title" label="title" width="300" />
        <el-table-column align="right">
          <template #default="scope">
            <el-button type="primary" size="mini" @click="showColumn(scope.$index, scope.row)"
            >查看详情</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogTableVisible = false" type="warning">关 闭</el-button>
        </div>
      </template>
    </el-dialog>

    <el-drawer
        v-model="drawerShow"
        :title=drawerTitle
        :before-close="drawerHandleClose"
        direction="rtl"
        custom-class="demo-drawer"
        :show-close=false
        :close-on-press-escape=true
    >
      <div class="drawer-body">
        <div><div class="drawer-body-key">ID:</div><el-input class="drawer-body-value" v-model="messages.id" disabled/></div>
        <div><div class="drawer-body-key">KEY:</div><el-input class="drawer-body-value" v-model="messages.keyid" disabled/></div>
        <div><div class="drawer-body-key">类型:</div><el-input class="drawer-body-value" v-model="messages.type" disabled/></div>
        <div><div class="drawer-body-key">标题:</div><el-input class="drawer-body-value" v-model="messages.title" disabled/></div>
        <div><div class="drawer-body-key">内容:</div><el-input class="drawer-body-value" v-model="messages.text" disabled/></div>
        <div v-if="messages.type==='广告'"><div class="drawer-body-key">金额:</div><el-input class="drawer-body-value" v-model="messages.money" disabled/></div>
        <div v-if="messages.type==='广告'"><div class="drawer-body-key">日期:</div><el-input class="drawer-body-value" v-model="messages.showdate" disabled/></div>
        <div v-if="messages.type==='广告'"><div class="drawer-body-key">电话:</div><el-input class="drawer-body-value" v-model="messages.phone" disabled/></div>
        <div><div class="drawer-body-key">邮箱:</div><el-input class="drawer-body-value" v-model="messages.email" disabled/></div>
        <div v-if="messages.type==='网站'"><div class="drawer-body-key">链接:</div><el-input class="drawer-body-value" v-model="messages.link" disabled/></div>
        <div v-if="messages.type==='网站'"><div class="drawer-body-key">名称:</div><el-input class="drawer-body-value" v-model="messages.name" disabled/></div>
      </div>
      <div class="drawer-footer">
        <el-button @click="drawerShow = false" type="success">提 交</el-button>
        <el-button @click="drawerShow = false" type="warning">关 闭</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import {reactive, toRefs, ref, getCurrentInstance} from "vue";
import moment from "moment/moment";

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
      }
    })

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
          data.messageTable[i].type=res.data[i].type = 1 ? "广告" : "消息"
        }

      });
    }
    getMessage() //显示该弹框获取数据
    return{
      ...toRefs(data),openThis,showColumn,getMessage,drawerHandleClose
    }
  }
}
</script>

<style scoped>
.my-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.drawer-body >div{
  margin-bottom: 5px;
  display: flex;
}
.drawer-body > div > .drawer-body-key{
  width: 10%;
}
.drawer-body > div > .drawer-body-value{
  width: 80%;
}
</style>