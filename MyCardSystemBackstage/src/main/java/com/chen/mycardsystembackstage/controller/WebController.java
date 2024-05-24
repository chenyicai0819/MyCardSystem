package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.mongod.UserMongo;
import com.chen.mycardsystembackstage.repository.UserRepository;
import com.chen.mycardsystembackstage.utils.GetCityByIp;
import com.chen.mycardsystembackstage.utils.GetIpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.controller
 * @date 2022/1/7 21:30
 * @since 1.0
 */
@RestController
public class WebController {

    @Autowired
    private GetCityByIp getCityByIp;

    @Autowired
    private GetIpUtil getIpUtil;

    @Autowired
    private UserRepository userRepository;
    // InetAddress addr;
    //
    // {
    //     try {
    //         addr = InetAddress.getLocalHost();
    //     } catch (UnknownHostException e) {
    //         e.printStackTrace();
    //     }
    // }

    public void getMap(){

    }

    @GetMapping("/getCity")
    public String getTest(HttpServletRequest request){
        String ip=getIpUtil.getIpAddr(request);
        System.out.println(ip);
        try {
            return getCityByIp.getAlibaba(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getUser")
    public UserMongo getUser(){
        UserMongo userMongo = userRepository.findUserByUserName("小黑");
        return userMongo;
    }
}
