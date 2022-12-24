<template>
  <div class="managesdialog">
    <el-dialog v-model="dialogTableVisible"
               :show-close="false"
               title="消息列表"
    >
      <el-table :data="messageTable">
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
          <el-button @click="dialogTableVisible = false">关 闭</el-button>

        </div>
      </template>
    </el-dialog>
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
    })

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

    }

    /**
     * 获取消息列表
     */
    const getMessage = () => {
      let uri="/message/isread"
      proxy.$axios.get(uri, {params: {isread: data.isRead}}).then(res => {
        data.messageTable = res.data;

      });
    }
    getMessage() //显示该弹框获取数据
    return{
      ...toRefs(data),openThis,showColumn,getMessage,
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
</style>