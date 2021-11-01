<template>
  <div class="add">
    <el-input placeholder="请输入名字" v-model="data.name"></el-input>
    <el-input placeholder="请输入链接地址" v-model="data.link" v-show="activeName==1||activeName==2"></el-input>
    <el-input placeholder="请输入图片链接" v-model="data.img"></el-input>
    <el-select v-model="data.type" placeholder="请选择类型" v-show="activeName==2">
      <el-option
          v-for="item in data.collName"
          :key="item.collName"
          :label="item.collName"
          :value="item.collName"

      >
      </el-option>
    </el-select>
    <el-input placeholder="请输入介绍内容" v-model="data.text" v-show="activeName==3||activeName==4"></el-input>
    <el-input placeholder="请输入上架时间" v-model="data.upDate" v-show="activeName==4"></el-input>
    <el-input placeholder="请输入下架时间" v-model="data.downDate" v-show="activeName==4"></el-input>
  </div>
  <div>
    <el-button type="primary" @click="add">{{data.manatype}}</el-button>

  </div>

</template>

<script>
import {getCurrentInstance, inject, onMounted, reactive} from "vue";
import { ElMessage } from 'element-plus'
import qs from "qs";
export default {
  name: "Add",
  props:{
    numlength:Array,
    manatype:String,
    manaData:Array,
    activeName:Number,
    drawer:Boolean,
  },
  setup(props,context){
    const {proxy}=getCurrentInstance();
    const appStore=inject('appStore')
    const data=reactive({
      manatype:props.manatype,
      uri:'',
      posts:'',
      id:'',
      name:'',
      link:'',
      img:'',
      type:'',
      text:'',
      upDate:'',
      downDate:'',
      getReData:'',
      maData:[],
      collName:[],
    })
    const add = () => {
      let active=props.activeName;
      data.posts="add";
      if (props.manatype=="修改"){
        data.posts="up"
      }
      if (active==1){
        proxy.$axios.post('card/'+data.posts,qs.stringify({"id":data.id,"name":data.name,"link":data.link,"img":data.img})).then(res=>{
          data.getReData=res.data;
          checkSucces();
        });
      }else if(active==2){
        proxy.$axios.post('mork/'+data.posts,qs.stringify({"id":data.id,"name":data.name,"link":data.link,"type":data.type,"img":data.img})).then(res=>{
          data.getReData=res.data;
          checkSucces();
        });
      }else if(active==3){
        proxy.$axios.post('coll/'+data.posts,qs.stringify({"id":data.id,"name":data.name,"img":data.img,"text":data.text})).then(res=>{
          data.getReData=res.data;
          checkSucces();
        });
      }else if (active==4){
        proxy.$axios.post('ad/'+data.posts,qs.stringify({"id":data.id,"name":data.name,"text":data.text,"img":data.img,"upDate":data.upDate,"downDate":data.downDate})).then(res=>{
          data.getReData=res.data;
          checkSucces();
        });
      }
    }
    const checkSucces = () => {
      if (data.getReData==1){
        appStore.setgetIntoSucc(data.getReData);
        context.emit('checkSucces')
        ElMessage({
          message: props.manatype=="修改"?"修改成功":"添加成功",
          type: 'success',
        })
        data.id='',data.name='',data.link='',data.img='',
            data.type='',data.text='',data.upDate='',data.downDate=''
      }else {
        ElMessage.error('添加失败');
      }
    }
    const create = () => {
      let active=props.activeName;
      let manadata=props.manaData;

      console.log(manadata);
      if (active==1){
        if (props.manatype=="修改"){
          data.id=props.manaData.cardId
          data.name=props.manaData.cardName
          data.link=props.manaData.cardLink
          data.img=props.manaData.cardImg
        }
        data.uri='card/'
      }else if(active==2){
        if (props.manatype=="修改"){
          data.id=props.manaData.morkId
          data.name=props.manaData.morkName
          data.link=props.manaData.morkLink
          data.img=props.manaData.morkImg
          data.type=props.manaData.morkType
        }
        data.uri='mork/'
      }else if(active==3){
        if (props.manatype=="修改"){
          data.id=props.manaData.collId
          data.name=props.manaData.collName
          data.img=props.manaData.collImg
          data.text=props.manaData.collText
        }
        data.uri='coll/'
      }else if (active==4){
        if (props.manatype=="修改"){
          data.id=props.manaData.adId
          data.name=props.manaData.adName
          data.img=props.manaData.adImg
          data.text=props.manaData.adText
          data.upDate=props.manaData.adUpDate
          data.downDate=props.manaData.adDownDate
        }
        data.uri='ad/'
      }
    }
    const getFather = () => {
      create()
    }
    const getType = () => {
      let uri='coll/show';
      proxy.$axios.get(uri,{}).then(res=>{
        const getdata=res.data;
        data.collName=getdata;
      });
    }
    onMounted(() => {
      getType();
    })
    return{
      data,
      create,
      add,
      checkSucces,
      getFather,
      getType,
    }
  }
}
</script>

<style scoped>
.add{
  margin: 0 auto;
  width: 60%;
}

</style>