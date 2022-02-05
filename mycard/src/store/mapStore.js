import { reactive } from "vue"
const mapStore ={
    citys:'',
    maplist:[],
    setCitys(value){
        this.citys=value
    },
    setMaplist(value){
        this.maplist=value
    },
}
export default reactive(mapStore)