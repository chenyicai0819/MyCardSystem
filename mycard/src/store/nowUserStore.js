import { reactive } from "vue"

const nowUserStore = {
    userid:'',
    username:'',

    setUsername(value){
        this.username = value
    },
    setUserid(value){
        this.userid = value
    }
}

export default reactive(nowUserStore)