// src>store>index.js
import appStore from "./appStore";
import mapStore from "./mapStore";

const store = createStore({
    appStore,mapStore
})

function createStore(parmas) {
    return {
        install(app) {
            this.provide(app)
        },
        provide(app) {
            const keys = parmas&&Object.keys(parmas)
            keys&&keys.forEach((item) => {
                // 注册
                app.provide(item,parmas[item])
            })
        }
    }
}
export default store
