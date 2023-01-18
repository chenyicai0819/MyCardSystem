package com.chen.mycardsystembackstage.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.chen.mycardsystembackstage.entity.User;
import com.chen.mycardsystembackstage.oauth.WechatOAuth;
import com.chen.mycardsystembackstage.service.StartService;
import com.chen.mycardsystembackstage.service.UserService;
import com.chen.mycardsystembackstage.utils.*;
import com.sun.mail.util.MailSSLSocketFactory;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    @Autowired
    private StartService startService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private GetIpUtil getIpUtil;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AuthCodeUtil authCodeUtil;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    // InetAddress addr;
    //
    // {
    //     try {
    //         addr = InetAddress.getLocalHost();
    //     } catch (UnknownHostException e) {
    //         e.printStackTrace();
    //     }
    // }

    @PostMapping("/login")
    public String login(User user,String code){
        // System.out.println(user);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int num= userService.login(user);
        // 往模板消息里添加内容
        map.put("ip",getIpUtil.getIpAddr(request));
        map.put("id",user.getUserId());
        map.put("time", dateFormat.format(date));
        if (num==0){
            // 通知登录错误结果
            map.put("isYes","否");
            wcn.pushLogin(map);
            return "没有这个用户，或者密码错误";
        }else{
            ValueOperations<Object, Object> vo = redisTemplate.opsForValue();

            if (!Objects.equals(vo.get("CODE:" + user.getUserId()), code)){
                return "验证码有误";
            }
            // 通知登录成功结果
            map.put("isYes","是");
            String ip = getIpUtil.getIpAddr(request);
            // 登陆成功的时候往数据库添加登陆状态
            if (1 == startService.countStart(ip)){
                startService.upStart(ip, String.valueOf(user.getUserId()));
            }else {
                startService.addStart(ip, String.valueOf(user.getUserId()));
            }
            wcn.pushLogin(map);
            // System.out.println("允许登录");
            return "允许登录";
        }
    }

    @GetMapping("/verify")
    public void getVerifyCode() throws IOException {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(200,100,4,5);
        String code = circleCaptcha.getCode();
        session.setAttribute("code",code);
        circleCaptcha.write(response.getOutputStream());
    }
    // @ApiOperation(value = "用户登录", notes = "登录--不进行拦截")
    // @PostMapping("/login")
    // public Msg login(@RequestParam("username") String username,
    //                  @RequestParam("password") String password) {
    //     String realPassword = userService.getPassword(username);
    //     if (realPassword == null) {
    //         return Msg.fail().add("info","用户名错误");
    //     } else if (!realPassword.equals(password)) {
    //         return Msg.fail().add("info","密码错误");
    //     } else {
    //         return Msg.success().add("token",JwtUtils.createToken(username));
    //     }
    // }

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

    /**
     * 获取到验证码
     * @param id 用户id，
     * @param time 过期时间（秒）
     *
     */
    @GetMapping("/getAuthCode")
    public int getAuthCode(String id,int time) {
        String email = userService.selUser(id).getEmail();
        String authCode = authCodeUtil.create(id,time);
        String message = "您正在登录MyCardSystem管理端，您的所有行为都将收到监控！\r\n您的验证码为："
                +authCode +
                "\r\n请注意不要将此验证码泄露给其他人！\r\n该验证码有效时间为："
                +time+
                "秒！\r\n如果本操作不是您本人所为，请注意您的账号安全！";
        String put = MailUtil.send(email,"MyCardSystem-您的验证码",message,false);
        if (authCode != null && put.length()>0){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 获取用户邮箱
     * @param id 用户id
     * @return 用户邮箱
     */
    @GetMapping("/getEmail")
    public String getEmail(String id){
        return userService.selUser(id).getEmail();
    }
}
