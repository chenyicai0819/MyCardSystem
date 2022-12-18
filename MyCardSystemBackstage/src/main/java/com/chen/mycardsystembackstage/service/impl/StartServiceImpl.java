package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.mapper.StartMapper;
import com.chen.mycardsystembackstage.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project : MyCardSystem - StartServiceImpl
 * Powered by GeorgeChen On 2022-12-16 23:37:32
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/

@Service
public class StartServiceImpl implements StartService {
    @Autowired
    private StartMapper startMapper;
    @Override
    public int addStart(String ip, String username) {
        return startMapper.addStart(ip,username);
    }

    @Override
    public int upStart(String ip, String username) {
        return startMapper.upStart(ip, username);
    }

    @Override
    public String selStart(String ip) {
        return startMapper.selStart(ip);
    }

    @Override
    public int countStart(String ip) {
        return startMapper.countStart(ip);
    }
}
