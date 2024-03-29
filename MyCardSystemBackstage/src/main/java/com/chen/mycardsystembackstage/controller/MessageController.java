package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.Message;
import com.chen.mycardsystembackstage.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Project : MyCardSystem - MessageController
 * Powered by GeorgeChen On 2022-12-24 23:24:26
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 获取未读或者已读的消息
     * @return
     */
    @GetMapping("/isread")
    public List<Message> getMessageNoRead(int isread){
        return messageService.getMessage(isread);
    }

    /**
     * 添加新消息
     * @param message
     * @return
     */
    @GetMapping("/add")
    public int addMessage(Message message){
        return messageService.addMessage(message);
    }

    /**
     * 统计未读消息的数量
     * @return
     */
    @GetMapping("/count")
    public int countMessageNotRead(){
        return messageService.countMessageNotRead();
    }

    /**
     * 处理消息是否通过
     * @return
     */
    @GetMapping("/pass")
    public int psaaMessage(int ispass,String keyid){
        return messageService.psaaMessage(ispass, keyid);
    }
}
