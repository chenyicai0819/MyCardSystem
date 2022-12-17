package com.chen.mycardsystembackstage.service;

import org.springframework.stereotype.Service;

/**
 * Project : MyCardSystem - StartService
 * Powered by GeorgeChen On 2022-12-16 23:36:52
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/

@Service
public interface StartService {
    public int addStart(String ip,String username);
    public int upStart(String ip,String username);
    public String selStart(String ip);
    public int countStart(String ip);
}
