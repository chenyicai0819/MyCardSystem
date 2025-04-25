<!--书签-->
<template>
  <div class="marks-container">
    <!-- 书签分类列表 -->
    <div class="demo-collapse" v-for="coll in collname">
      <el-collapse class="custom-collapse">
        <el-collapse-item>
          <template #title>
            <div class="collapse-title">
              {{ coll.collName }}
            </div>
          </template>
          <div class="morks-container">
            <div 
              class="mork-card" 
              :class="{ 'mork-card-mobile': ismoblie }" 
              v-for="mork in morkname" 
              v-show="mork.morkType==coll.collName"
            >
              <el-popover
                placement="top-start"
                title="网站介绍"
                :width="200"
                trigger="hover"
                :content="mork.morkText || '暂无介绍内容'"
                popper-class="custom-popover"
              >
                <template #reference>
                  <el-card class="site-card" shadow="hover">
                    <template #header>
                      <div class="card-header">
                        <span>{{ mork.morkName }}</span>
                        <a :href="mork.morkLink" class="site-link">前往链接</a>
                      </div>
                    </template>
                    <div class="site-image">
                      <el-image
                        :src="mork.morkImg"
                        :fit="mork.morkName"
                      />
                    </div>
                  </el-card>
                </template>
              </el-popover>
            </div>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>

    <!-- 推荐按钮和表单 -->
    <div class="recommend-section">
      <transition name="slide-fade">
        <div v-show="visible" class="recommend-form">
          <div class="recommend-header">
            <h3>推荐网站</h3>
            <p>在这里写下你想推荐给我们的有趣的网站</p>
          </div>
          
          <el-form :model="net" class="recommendation-form">
            <el-form-item>
              <el-input v-model="net.name" placeholder="网站名称" />
            </el-form-item>
            <el-form-item>
              <el-input v-model="net.link" placeholder="网站网址" />
            </el-form-item>
            <el-form-item>
              <el-input v-model="net.text" type="textarea" placeholder="网站介绍" />
            </el-form-item>
            <el-form-item>
              <el-input v-model="net.user" placeholder="您的名字" />
            </el-form-item>
            <el-form-item>
              <el-input v-model="net.email" placeholder="您的邮箱" />
            </el-form-item>
            
            <el-button type="primary" @click="recommendPush" class="submit-button">
              提交推荐
            </el-button>
          </el-form>
        </div>
      </transition>

      <el-tooltip
        effect="light"
        content="推荐新网站"
        placement="left"
      >
        <el-button
          class="floating-button"
          @click="checkDiv"
          :icon="visible ? 'el-icon-close' : 'el-icon-plus'"
          circle
        />
      </el-tooltip>
    </div>
  </div>
</template>

<script>
import { ElNotification } from 'element-plus'
import { h } from 'vue'
import {getCurrentInstance, onMounted, reactive, toRef, toRefs} from "vue";
import {ChatLineRound} from '@element-plus/icons-vue';
import {recommend} from "@/api/mork";
import {recommendEmail, replyEmail} from "../../utils/emailModel";

export default {
  name: "Marks",
  setup(){
    const {proxy}=getCurrentInstance();
    const data=reactive({
      ismoblie:false,
      morkname:[

      ],
      collname:[

      ],
      visible:false,
      net:{
        name:'',
        link:'',
        text:'',
        user:'',
        email:'',
        type:'推荐网站',
        out:'我们已经收到了您的推荐！我们会尽快审核,审核通过后尽快上架！感谢！'
      }
    })

    const isMobile = () => {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      if (flag) {
        data.ismoblie=true
      } else {
        data.ismoblie=false
      }
    }
    const getColl= () =>{
      proxy.$axios.get('coll/show',{}).then(res=>{
        const getdata=res.data;
        // console.log(getdata);
        data.collname=getdata;
      });
    }
    const getMork= () =>{
      proxy.$axios.get('mork/show',{}).then(res=>{
        const getdata=res.data;
        // console.log(getdata);
        data.morkname=getdata;
      });
    }
    /**
     * 切换div显示情况
     */
    const checkDiv = () => {
      if (data.visible==false){
        data.visible=true
      }else {
        data.visible=false
      }
    }
    /**
     * 发送推荐
     */
    const recommendPush = () => {
      if (data.net.name==''||data.net.link==''||data.net.text==''||data.net.name==''||data.net.email==''){
        ElNotification({
          title: '提示',
          message: '您有未填的内容，请填完！',
          type: 'error',
        })
      }else {
        proxy.$axios.get("/mork/recommend",{params:
              {"name":data.net.name,
                "link":data.net.link,
                "text":data.net.text,
                "user":data.net.user,
                "email":data.net.email,
                "model":recommendEmail(data.net),
              }
        }).then((res)=>{
          res=res.data
          if (res==1){
            proxy.$axios.get("/mork/reply",{params:
                  {"email":data.net.email,
                    "model":replyEmail(data.net),
                  }
            })
            ElNotification({
              title: '推荐成功',
              message: h('i', { style: 'color: teal' }, '感谢您的推荐！'),
            })
          }
        })
      }
    }
    // onMounted(()=>{
    //   document.addEventListener('click', (e)=> {
    //     if (e.target.className != 'marks-affix') {
    //       console.log("aaa")
    //       data.visible= false;
    //     }
    //   })
    // })
    isMobile();
    getColl();
    getMork();
    return{
      ...toRefs(data),getColl,recommendPush, isMobile, getMork,checkDiv,
    }
  }
}
</script>

<style scoped>
.marks-container {
  position: relative;
  min-height: 100vh;
}

.demo-collapse {
  margin: 1rem 0;
}

.custom-collapse {
  border: none;
  border-radius: 12px;
  overflow: hidden;
}

:deep(.el-collapse-item__header) {
  background: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(0, 0, 0, 0.05);
  height: 60px;
  line-height: 60px;
  padding: 0 20px;
  font-size: 1.1rem;
  color: #333;
  transition: all 0.3s ease;
}

:deep(.el-collapse-item__header:hover) {
  background: rgba(255, 255, 255, 0.9);
}

:deep(.el-collapse-item__wrap) {
  background: rgba(255, 255, 255, 0.6);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(0, 0, 0, 0.05);
  border-top: none;
}

:deep(.el-collapse-item__content) {
  padding: 20px;
}

.collapse-title {
  font-weight: 500;
}

.morks-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  padding: 20px;
  justify-content: center;
}

.mork-card {
  width: 260px;
  flex-shrink: 0;
  transition: transform 0.3s ease;
}

.mork-card-mobile {
  width: 260px;
}

.site-card {
  background: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(0, 0, 0, 0.05);
  transition: all 0.3s ease;
  height: 200px;
  overflow: hidden;
}

.site-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 20px;
  padding: 0 5px;
  overflow: hidden;
}

.card-header span {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  max-width: 150px;
}

.site-link {
  color: #3b82f6;
  text-decoration: none;
  font-size: 0.9rem;
  transition: color 0.3s ease;
}

.site-link:hover {
  color: #2563eb;
}

.site-image {
  height: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
}

:deep(.el-image) {
  width: 100px;
  height: 100px;
  border-radius: 8px;
  object-fit: cover;
}

.custom-popover {
  backdrop-filter: blur(10px);
  background: rgba(255, 255, 255, 0.95);
  border: 1px solid rgba(0, 0, 0, 0.05);
}

.recommend-section {
  position: fixed;
  right: 2rem;
  bottom: 2rem;
  z-index: 100;
}

.recommend-form {
  position: absolute;
  right: 0;
  bottom: 5rem;
  width: 320px;
  background: white;
  border-radius: 16px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  padding: 1.5rem;
}

.recommend-header {
  text-align: center;
  margin-bottom: 1.5rem;
}

.recommend-header h3 {
  margin: 0;
  color: #1a1a1a;
  font-size: 1.25rem;
}

.recommend-header p {
  margin: 0.5rem 0 0;
  color: #666;
  font-size: 0.9rem;
}

.recommendation-form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.floating-button {
  width: 50px;
  height: 50px;
  background: #3b82f6;
  color: white;
  box-shadow: 0 2px 4px rgba(59, 130, 246, 0.5);
}

.floating-button:hover {
  background: #2563eb;
  transform: scale(1.05);
}

/* 动画 */
.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: all 0.3s ease;
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(20px);
  opacity: 0;
}

/* 移动端适配 */
@media (max-width: 768px) {
  .morks-container {
    gap: 15px;
    padding: 15px;
  }

  .mork-card,
  .mork-card-mobile {
    width: 260px;
  }

  :deep(.el-collapse-item__header) {
    height: 50px;
    line-height: 50px;
    padding: 0 15px;
    font-size: 1rem;
  }

  .recommend-form {
    width: 280px;
  }

  .recommend-section {
    right: 1rem;
    bottom: 1rem;
  }
}
</style>