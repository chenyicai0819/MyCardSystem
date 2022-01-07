import _axios from "../axios/config";
import axios from "axios";
import qs from "qs";


let apihead="https://gwpre.sina.cn/interface/news/wap/historydata.d.json?province="
let apiout="&callback=_aProvinceFunction&_=1585398242301"

let citys='';
export const apis = (city) => {
  citys=city;
  return axios.get("/mapapi?province="+city,{})
}

