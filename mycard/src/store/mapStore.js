import { reactive } from "vue"
const mapStore ={
    citys:'',
    setCitys(value){
        this.citys=value
    },
}
export default reactive(mapStore)