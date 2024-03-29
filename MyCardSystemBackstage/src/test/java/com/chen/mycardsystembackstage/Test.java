package com.chen.mycardsystembackstage;

import com.chen.mycardsystembackstage.utils.AuthCodeUtil;
import com.chen.mycardsystembackstage.utils.QuartUtils.PutChatsLogsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Project : MyCardSystem - Test
 * Powered by GeorgeChen On 2023-01-18 14:38:14
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@SpringBootTest
public class Test {
    @Autowired
    private AuthCodeUtil authCodeUtil;
    @Autowired
    private PutChatsLogsUtils putChatsLogsUtils;

    @org.junit.jupiter.api.Test
    public void testRedis(){
        System.out.println(authCodeUtil.create(String.valueOf(1), 60));
    }

    @org.junit.jupiter.api.Test
    public void testPutChatLog(){
        System.out.println(putChatsLogsUtils.getTrigge("test").getId());

    }


}
