package com.chen.mycardsystembackstage.oauth;

import com.alibaba.fastjson.JSONObject;
import com.chen.mycardsystembackstage.entity.User;
import com.chen.mycardsystembackstage.service.UserService;
import com.chen.mycardsystembackstage.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.oauth
 * @date 2021/12/7 17:16
 * @since 1.0
 */

@RestController
@RequestMapping("wechat")
public class WechatOAuth {

    @Autowired
    private AuthUtil authUtil;
    @Resource
    private UserService userService;

    // 用户同意授权，获取code
    @RequestMapping("/oauth")
    public void oauth(HttpServletResponse response, String id) throws IOException {

        // System.out.println("开始询问用户是否同统一授权");
        String appid = "wxacc93d31c5dbd26f";
        String path = "http://10.34.155.160:8089/wechat/invoke";
        path = URLEncoder.encode(path, "UTF-8");

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid
                + "&response_type=code&scope=snsapi_userinfo&redirect_uri=" + path
                + "&state=" + id
                + "&connect_redirect=1#wechat_redirect";
        response.sendRedirect(url);


    }

    // 用户同意授权
    @RequestMapping("/invoke")
    public String oauthInvoke(HttpServletRequest request, HttpServletResponse response) {
        // 获得code
        System.out.println("用户同意授权，开始获取openid");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        System.out.println(code);
        System.out.println(state);

        // 判断是否绑定过openid
        String isOpenid=userService.selUser(state).getOpenid();
        if (isOpenid!=null){
            return "您已经绑定过微信了,如需修改请联系管理员";
        }
        // 通过token获取access_token
        String appid = "wxacc93d31c5dbd26f";
        String secret = "f8c9e82b590ac71eb12e96f77cf65740";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid
                + "&secret=" + secret
                + "&code=" + code
                + "&grant_type=authorization_code";
        try {
            JSONObject jsonObject = authUtil.doGetJson(url);
            String openid= (String) jsonObject.get("openid");
            System.out.println(openid);
            // 将openid插入到user表中
            userService.addOpenid(state,openid);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "授权成功,绑定微信成功";
    }
}
