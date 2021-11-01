import { reactive } from "vue"
const appStore ={
    activeName:1,
    setActiveName(value){
        this.activeName=value
    },
    getIntoSucc:0,
    setgetIntoSucc(value){
        this.getIntoSucc=value
    },
}
export default reactive(appStore)
