<template>
  <div class="managesdialog">
    <el-dialog v-model="dialogTableVisible"
               :show-close="false">
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogTableVisible = false">关 闭</el-button>

        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {reactive, toRefs, ref} from "vue";

export default {
  name: "ManagesDialog",
  // props: {
  //   dialogTableVisible:false
  // },
  setup(){
    const data=reactive({
      dialogTableVisible:false,

    })

    const openThis = () => {
      data.dialogTableVisible=true
    }
    return{
      ...toRefs(data),openThis
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