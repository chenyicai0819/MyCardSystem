package com.chen.mycardsystembackstage;

import com.chen.mycardsystembackstage.utils.components.CountdownUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Project : MyCardSystem - TestComponent
 * Powered by GeorgeChen On 2023-03-10 15:37:51
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@SpringBootTest
public class TestComponent {
    @Autowired
    private CountdownUtil countdownUtil;

    @Test
    public void testHoliday(){
        countdownUtil.holidays();
    }

    @Test
    public void testOffwork(){
        countdownUtil.offWork();
    }
}
