package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.Message;
import com.chen.mycardsystembackstage.mapper.MessageMapper;
import com.chen.mycardsystembackstage.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project : MyCardSystem - MessageServiceImpl
 * Powered by GeorgeChen On 2022-12-24 23:27:45
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public List<Message> getMessage(int isRead) {
        return messageMapper.getMessage(isRead);
    }
}
