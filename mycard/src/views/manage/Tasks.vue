<template>
  <div class="tasks">
    <el-dialog v-model="dialogTableVisible"
               :show-close="false"
               title="定时任务"
    >
      <div class="tasks-collapse">
        <el-collapse v-model="activeNames" @change="handleChange">
          <el-collapse-item title="Consistency" name="1">
            <div>
              Consistent with real life: in line with the process and logic of real
              life, and comply with languages and habits that the users are used to;
            </div>
            <div>
              Consistent within interface: all elements should be consistent, such
              as: design style, icons and texts, position of elements, etc.
            </div>
          </el-collapse-item>
          <el-collapse-item title="Feedback" name="2">
            <div>
              Operation feedback: enable the users to clearly perceive their
              operations by style updates and interactive effects;
            </div>
            <div>
              Visual feedback: reflect current state by updating or rearranging
              elements of the page.
            </div>
          </el-collapse-item>
          <el-collapse-item title="Efficiency" name="3">
            <div>
              Simplify the process: keep operating process simple and intuitive;
            </div>
            <div>
              Definite and clear: enunciate your intentions clearly so that the
              users can quickly understand and make decisions;
            </div>
            <div>
              Easy to identify: the interface should be straightforward, which helps
              the users to identify and frees them from memorizing and recalling.
            </div>
          </el-collapse-item>
          <el-collapse-item title="Controllability" name="4">
            <div>
              Decision making: giving advices about operations is acceptable, but do
              not make decisions for the users;
            </div>
            <div>
              Controlled consequences: users should be granted the freedom to
              operate, including canceling, aborting or terminating current
              operation.
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {reactive, toRefs} from "vue";

export default {
  name: "Tasks",
  setup() {
    const data = reactive({
      activeNames: 1,
      dialogTableVisible: false,

    })
    
    const handleChange = (val) => {
      console.log(val)
    }

    /**
     * 打开当前界面
     */
    const openThis = () => {
      data.dialogTableVisible=true
    }

    return{
      ...toRefs(data),handleChange,openThis,
    }
  }

}
</script>

<style scoped>

</style>