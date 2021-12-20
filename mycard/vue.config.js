module.exports = {
    publicPath: './',
    outputDir: "dist", // 输出文件目录
    lintOnSave: false, // eslint 是否在保存时检查
    assetsDir: 'static', // 配置js、css静态资源二级目录的位置
    devServer: {
        proxy: {
            '/connect/oauth2/authorize': {
                target: 'https://open.weixin.qq.com/', //对应自己的接口
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/connect/oauth2/authorize': ''
                }
            }
        }
    }
}
