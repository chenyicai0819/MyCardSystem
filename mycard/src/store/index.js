// src>store>index.js
import appStore from "./appStore";
import mapStore from "./mapStore";
import nowUserStore from "./nowUserStore";

const store = createStore({
    appStore,mapStore,nowUserStore
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
