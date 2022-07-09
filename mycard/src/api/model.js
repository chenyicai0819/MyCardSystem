// post 和 get 请求的模板
import {getCurrentInstance} from "vue/dist/vue";
import qs from "qs";
import router from "../router";

const {proxy}=getCurrentInstance();

const testGet = (uri,data) => {
    proxy.$axios.get(uri,data).then(res=>{
        console.log(res)
    }).catch(() => {

    })
}

const testPost = (uri,data) => {
    proxy.$axios.post(uri,qs.stringify(data)).then(res=>{
        console.log(res)
    }).catch(() => {

    })
}