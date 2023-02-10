<template>
  <!--  mycard管理端新首页-->
  <div class="index">
    <div class="top">
      <!--      顶部导航栏-->
    </div>
    <div class="center">
      <div class="index-left">
        <!--        侧边导航栏-->
        <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            :collapse="isCollapse"
            @open="handleOpen"
            @close="handleClose"
        >
          <el-menu-item v-for="menus in menuList.data" :index="menus.id" v-show="menus.children.length === 0">
            <el-icon><icon-menu/></el-icon>
            <template #title>{{ menus.name }}</template>
          </el-menu-item>
          <el-sub-menu v-for="menus in menuList.data" :index="menus.id" v-show="menus.children.length>0">
            <template #title>
              <el-icon><location/></el-icon>
              {{ menus.name }}
            </template>
            <el-menu-item v-for="children in menus.children" :index="children.id">
              {{children.name}}
            </el-menu-item>
          </el-sub-menu>


        </el-menu>
      </div>
      <div class="index-body">
        <!--        主要显示区域-->
      </div>
    </div>
    <div class="footer">
      <!--      底部区域-->
    </div>
  </div>
</template>

<script>
import {getCurrentInstance, reactive, toRefs} from "vue";
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'

export default {
  name: "index",
  setup() {
    const {proxy} = getCurrentInstance();
    const data = reactive({
      isCollapse: false,
      menuList: {},

    })

    /**
     * 获取菜单
     */
    const getMenus = () => {
      proxy.$axios.get("index/getIndexMenu", {params: {}}).then(res => {
        data.menuList = res

        // console.log(data.menuList.data[1].children.length)
      });

    }

    const handleOpen = (key, keyPath) => {

    }
    const handleClose = (key, keyPath) => {

    }


    getMenus()

    return {
      ...toRefs(data), handleOpen, handleClose, getMenus,
    }
  }

}
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>