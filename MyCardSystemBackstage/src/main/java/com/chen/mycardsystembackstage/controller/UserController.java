package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.User;
import com.chen.mycardsystembackstage.oauth.WechatOAuth;
import com.chen.mycardsystembackstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public String login(User user){
        System.out.println(user);
        int num= userService.login(user);
        if (num==0){
            return "没有这个用户，或者密码错误";
        }else{
            return "允许登录";
        }
    }
}
