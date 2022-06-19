package com.chen.mycardsystembackstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author swagg
 */
@SpringBootApplication
@EnableCaching
public class MyCardSystemBackstageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyCardSystemBackstageApplication.class, args);
    }

}
