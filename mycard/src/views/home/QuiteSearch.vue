<template>
  <div class="quitesearch">
    <div>
      <el-input v-model="input1" placeholder="快捷访问">
        <template #prepend>
          <el-select v-model="select1" placeholder="Select" style="width: 110px">
            <el-option label="Https://" value="Https://"></el-option>
            <el-option label="Http://" value="Http://"></el-option>
          </el-select>
        </template>
        <template #append>
          <el-button
              @click="link1"
          >
            <svg t="1640424282593" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3110" width="20" height="20"><path d="M512 989.866667C248.081067 989.866667 34.133333 775.918933 34.133333 512S248.081067 34.133333 512 34.133333s477.866667 213.947733 477.866667 477.866667-213.947733 477.866667-477.866667 477.866667z m0-68.266667c226.2016 0 409.6-183.3984 409.6-409.6S738.2016 102.4 512 102.4 102.4 285.7984 102.4 512s183.3984 409.6 409.6 409.6z" fill="#2c2c2c" p-id="3111"></path><path d="M439.6032 439.6032l-72.430933 217.224533 217.224533-72.430933 48.298667 48.298667L270.6432 753.322667l120.661333-362.052267 48.298667 48.298667z m0 0l-48.298667-48.298667L753.3568 270.677333l-120.661333 362.052267-48.298667-48.298667 72.430933-217.224533-217.224533 72.430933z" fill="#2c2c2c" p-id="3112"></path><path d="M512 512m-34.133333 0a34.133333 34.133333 0 1 0 68.266666 0 34.133333 34.133333 0 1 0-68.266666 0Z" fill="#2c2c2c" p-id="3113"></path>
            </svg>
          </el-button>

        </template>
      </el-input>
    </div>
    <div style="margin-top: 15px">
      <el-input
          v-model="input2"
          placeholder="快捷搜索"
          class="input-with-select"
      >
        <template #prepend>
          <el-select v-model="select2" placeholder="Select" style="width: 110px">
            <el-option label="Baidu" value="1"></el-option>
            <el-option label="Google" value="2"></el-option>
            <el-option label="Bing" value="3"></el-option>
          </el-select>
        </template>
        <template #append>
          <el-button
              @click="link2"
          >
            <svg t="1640424135532" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1250" width="20" height="20"><path d="M953.474215 908.234504l-152.576516-163.241391c61.92508-74.48211 95.81186-167.36973 95.81186-265.073744 0-229.294809-186.63531-415.930119-416.102133-415.930119-229.294809 0-415.930119 186.63531-415.930119 415.930119s186.63531 415.930119 415.930119 415.930119c60.032925 0 118.00168-12.55703 172.186125-37.327062 16.169326-7.396607 23.221905-26.318159 15.825298-42.315471-7.396607-16.169326-26.318159-23.221905-42.315471-15.825298-45.927768 20.813707-94.951789 31.478582-145.695952 31.478582-194.031917 0-351.94087-157.908953-351.94087-351.94087 0-194.031917 157.908953-351.94087 351.94087-351.94087 194.031917 0 351.94087 157.908953 351.94087 351.94087 0 91.339493-34.918864 177.86259-98.048043 243.743995-12.213002 12.729044-11.868974 33.026709 0.860071 45.239711 1.032085 0.860071 2.236183 1.204099 3.268268 2.064169 0.860071 1.204099 1.376113 2.752226 2.408198 3.956325l165.477574 177.00252c6.192508 6.70855 14.793214 10.148833 23.393919 10.148833 7.912649 0 15.653284-2.92424 21.845792-8.600706C964.827146 941.433227 965.515202 921.135562 953.474215 908.234504z" p-id="1251" fill="#2c2c2c"></path>
            </svg>
          </el-button>

        </template>
      </el-input>
    </div>
  </div>
</template>

<script>
import {reactive, toRefs} from "vue";

export default {
  name: "QuiteSearch",
  setup() {
    const data = reactive({
      input1: '',
      input2: '',
      select1: 'Https://',
      select2: '1',
      ismoblie:false,
    })
    const link1 = () => {
      if (data.input1!=''){
        window.open(data.select1+data.input1, '_blank');
      }
    }
    const link2 = () => {
      if (data.input2!=''){
        if (data.select2=="1"){
          window.open("https://www.baidu.com/s?wd="+data.input2, '_blank');
        }else if(data.select2=="2"){
          window.open("https://www.google.com/search?q="+data.input2, '_blank');
        }else if(data.select2=="3"){
          window.open("https://cn.bing.com/search?q="+data.input2, '_blank');
        }
      }
    }

    return {
      ...toRefs(data),link1,link2
    }
  }
}
</script>

<style scoped>
.quitesearch{
  margin: 5px auto;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
</style>