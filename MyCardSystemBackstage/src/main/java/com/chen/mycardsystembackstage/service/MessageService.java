package com.chen.mycardsystembackstage.service;

import com.chen.mycardsystembackstage.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project : MyCardSystem - MessageService
 * Powered by GeorgeChen On 2022-12-24 23:26:25
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Service
public interface MessageService {

    public List<Message> getMessage(int isRead);
    public int addMessage(Message message);
    public int countMessageNotRead();
    public int psaaMessage(int ispass,String keyid);
}
