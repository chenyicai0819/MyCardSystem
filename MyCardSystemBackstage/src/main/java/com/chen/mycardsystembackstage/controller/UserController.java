package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.User;
import com.chen.mycardsystembackstage.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
