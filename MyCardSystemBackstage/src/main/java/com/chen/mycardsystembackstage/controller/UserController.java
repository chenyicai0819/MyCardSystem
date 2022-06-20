package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.User;
import com.chen.mycardsystembackstage.oauth.WechatOAuth;
import com.chen.mycardsystembackstage.service.UserService;
import com.chen.mycardsystembackstage.utils.Msg;
import com.chen.mycardsystembackstage.utils.WeChatNotify;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
import com.chen.mycardsystembackstage.utils.JwtUtils;

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




    @PostMapping("/login")
    public String login(User user){
        // System.out.println(user);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int num= userService.login(user);
        // 往模板消息里添加内容
        map.put("ip",addr.getHostAddress());
        map.put("id",user.getUserId());
        map.put("time", dateFormat.format(date));
        if (num==0){
            // 通知登录错误结果
            map.put("isYes","否");
            wcn.pushLogin(map);
            // System.out.println("没有这个用户，或者密码错误");
            return "没有这个用户，或者密码错误";
        }else{
            // 通知登录成功结果
            map.put("isYes","是");
            wcn.pushLogin(map);
            // System.out.println("允许登录");
            return "允许登录";
        }
    }
    // @ApiOperation(value = "用户登录", notes = "登录--不进行拦截")
    @PostMapping("/login")
    public Msg login(@RequestParam("username") String username,
                     @RequestParam("password") String password) {
        String realPassword = userService.getPassword(username);
        if (realPassword == null) {
            return Msg.fail().add("info","用户名错误");
        } else if (!realPassword.equals(password)) {
            return Msg.fail().add("info","密码错误");
        } else {
            return Msg.success().add("token",JwtUtils.createToken(username));
        }
    }

    // @ApiOperation(value = "无权限", notes = "无权限跳转的接口")
    @RequestMapping(path = "/unauthorized/{message}")
    public Msg unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return Msg.fail().add("info",message);
    }

    // @ApiOperation(value = "特定用户访问", notes = "拥有 user, admin 角色的用户可以访问下面的页面")
    @PostMapping("/getMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public Msg getMessage() {
        return Msg.success().add("info","成功获得信息！");
    }

    // @ApiOperation(value = "Vip用户访问", notes = "拥有 vip 权限可以访问该页面")
    @PostMapping("/getVipMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequiresPermissions("vip")
    public Msg getVipMessage() {
        return Msg.success().add("info","成功获得 vip 信息！");
    }


}
