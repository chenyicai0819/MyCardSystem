<template>
  <div class="demo-collapse" v-for="coll in data.collname">
    <el-collapse >
      <el-collapse-item>
        <template #title>
          {{coll.collName}}
        </template>
        <div id="morks">
          <div class="morkscard" :class="{morkscardmoblie:data.ismoblie}" v-for="mork in data.morkname" v-show="mork.morkType==coll.collName">
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
          </div>
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>

</template>

<script>
import {getCurrentInstance, reactive} from "vue";
export default {
  name: "Marks",
  setup(){
    const {proxy}=getCurrentInstance();
    const data=reactive({
      ismoblie:false,
      morkname:[

      ],
      collname:[

      ]
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
    isMobile();
    getColl();
    getMork();
    return{
      data,
      getColl,
      isMobile,
      getMork,
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
</style>