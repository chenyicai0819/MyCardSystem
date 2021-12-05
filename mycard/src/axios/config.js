import axios from "axios";
/*接口请求 自定义交互配置*/
let  config={
    // baseURL:'http://10.34.56.145:8089/',/*请求项目接口 一级域名前缀*/
    // baseURL:'http://localhost:8089/',/*请求项目接口 一级域名前缀*/
    baseURL:'http://8.129.212.155:8089/',/*请求项目接口 一级域名前缀，服务器地址*/
    timeout:8000 /*异步交互 请求时间段*/
}
/*localStorage 存储token的前端缓存时间 默认三天*/
const  EXPIRESTIME=86400000*3;
const _axios = axios.create(config);

// http request 请求拦截器
_axios.interceptors.request.use(config => { // 3、添加拦截器
    // console.log('异步交互之前触发--拦截11111');
    if(localStorage.getItem('token')){
        // console.log(localStorage.getItem('token')+"????");
        config.headers.token=localStorage.getItem('token');
    }
    return config;
}, e => {

})

// http response 响应拦截器
_axios.interceptors.response.use(res => { // 4、添加拦截器
    // console.log('异步交互之后触发--拦截22222');
    // console.log(res.headers.authorization);
    if(res.headers.authorization){
        // console.log("页面存储token成功！"+res.headers.authorization);
        /*交互完毕后 交互相应头如果返回token 就及时的在前端缓存中 存储token手令值 并设定三天的缓存时间
        * 方便下一次axios请求交互时 装载进交互请求头 通过后端接口安全性检查*/
        window.localStorage.setItem('token',JSON.stringify({data:res.headers.authorization,time:EXPIRESTIME}));
        /*window.localStorage.setItem('token',res.headers.authorization);*/
    }
    return res;
}, e => {

})

export  default  _axios;