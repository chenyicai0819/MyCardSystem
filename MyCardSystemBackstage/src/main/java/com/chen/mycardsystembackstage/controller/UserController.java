package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.User;
import com.chen.mycardsystembackstage.oauth.WechatOAuth;
import com.chen.mycardsystembackstage.service.UserService;
import com.chen.mycardsystembackstage.utils.WeChatNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.controller
 * @date 2021/10/22 15:39
 * @since 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    //公众号的唯一标识
    static String APPID = "";
    //公众号的secret
    static String SECRET = "";

    Map<String,Object> map=new HashMap<>();

    @Resource
    private UserService userService;
    @Autowired
    private WeChatNotify wcn;

    InetAddress addr;

    {
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    Date date = new Date();
    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

    @PostMapping("/login")
    public String login(User user){
        System.out.println(user);
        int num= userService.login(user);
        // 往模板消息里添加内容
        map.put("ip",addr.getHostAddress());
        map.put("id",user.getUserId());
        map.put("time", dateFormat.format(date));
        if (num==0){
            // 通知登录错误结果
            map.put("isYes","否");
            wcn.push(map);
            return "没有这个用户，或者密码错误";
        }else{
            // 通知登录成功结果
            map.put("isYes","是");
            wcn.push(map);
            return "允许登录";
        }
    }
}
