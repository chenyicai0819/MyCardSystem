<!--书签-->
<template>
  <div class="demo-collapse" v-for="coll in collname">
    <el-collapse >
      <el-collapse-item>
        <template #title>
          {{coll.collName}}
        </template>
        <div id="morks">
          <div class="morkscard" :class="{morkscardmoblie:ismoblie}" v-for="mork in morkname" v-show="mork.morkType==coll.collName">
            <el-popover
                placement="top-start"
                title="网站介绍"
                :width="200"
                effect="dark"
                trigger="hover"
                :content="mork.morkText!=null?mork.morkText:'暂无介绍内容'"
            >
              <template #reference>
                <el-card class="box-card">
                  <template #header>
                    <div class="card-header">
                      <span>{{ mork.morkName }}</span>
                      <a :href="mork.morkLink" style="text-decoration: none;font-weight: bold">前往链接</a>
                    </div>
                  </template>
                  <div class="block">
                    <el-image
                        style="width: 100px; height: 100px"
                        :src="mork.morkImg"
                        :fit="mork.morkName"
                    ></el-image>
                  </div>
                </el-card>
              </template>
            </el-popover>

          </div>
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
  <div class="marks-affix">
    <div class="marks-affix-input" v-show="visible">
      <div style="width: 80%;margin-top: 5px;margin-left: 30px">
        <h3>在这里写下你想推荐给我们的有趣的网站吧</h3>
      </div>
      <el-divider />
      <el-input :class="net.name==''?'marks-affix-input-input-null':'marks-affix-input-input'" v-model="net.name" placeholder="网站的名称" />
      <el-input :class="net.link==''?'marks-affix-input-input-null':'marks-affix-input-input'" v-model="net.link" placeholder="网站的网址" />
      <el-input :class="net.text==''?'marks-affix-input-input-null':'marks-affix-input-input'" v-model="net.text" placeholder="网站的介绍" />
      <el-input :class="net.user==''?'marks-affix-input-input-null':'marks-affix-input-input'" v-model="net.user" placeholder="您的名字" />
      <el-input :class="net.email==''?'marks-affix-input-input-null':'marks-affix-input-input'" v-model="net.email" placeholder="您的邮箱" />
      <el-divider style="margin-bottom: 10px"/>
      <el-button type="primary" style="margin-top: -10px;width: 100px" @click="recommendPush">推  荐</el-button>
    </div>
    <el-tooltip
        popper-class="atooltip"
        effect="dark"
        content="点击按钮来给我们推荐你收藏的有趣的网站吧！"
        placement="left-start"
    >
      <el-button id="marks-affix-button"
                 @click="checkDiv"
                 style="position: fixed;left: 95%;top:87%;"
                 circle
      >
        <el-icon size="20" v-show="visible==false"><chat-line-square /></el-icon>
        <el-icon size="20" v-show="visible==true"><close-bold /></el-icon>
      </el-button>
    </el-tooltip>
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
.demo-collapse{
  border-radius: 10px;
  margin-top: 5px;
  width: 98%;
}
.mark-collapse{
  background-color: #475669;
}
#morks{
  /*width: 80%;*/
  /*background-color: #BDBDBD;*/
  display: flex;
  justify-content: left;
  flex-flow: wrap;
}
.morkscard{
  margin: 5px;
  width: 20%;
}
.morkscardmoblie{
  margin: 5px;
  width: 46%;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 100%;
}
#marks-affix-button:hover{
  background-color: #97d9bb;
}
.marks-affix-input {
  background-color: #99a9bf;
  width: 300px;
  height: 400px;
  position: fixed;
  left: 80%;
  top: 30%;
  border-radius: 15px;
  animation: marks-affix-input 1s 1;
}

@keyframes marks-affix-input{

  0%{opacity: 0;}

  100%{opacity: 1;}

}
.marks-affix-input-input{
  margin-bottom: 3px;
  width: 90%;
  margin-left: 7.5px;
}
.marks-affix-input-input-null{
  border: 1px solid #F56C6C;
  border-radius: 5px;
  margin-bottom: 3px;
  width: 90%;
  margin-left: 7.5px;
}
</style>