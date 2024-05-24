package com.chen.mycardsystembackstage;


import com.chen.mycardsystembackstage.entity.mongod.UserMongo;
import com.chen.mycardsystembackstage.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Project : MyCardSystem - TestMongodb
 * Powered by GeorgeChen On 2023-05-13 00:27:06
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMongodb {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() throws Exception {
        UserMongo userMongo =new UserMongo();
        userMongo.setId(29188989892932l);
        userMongo.setName("小明");
        userMongo.setAge("20");
        userRepository.saveUser(userMongo);
    }

    @Test
    public void findUserByUserName(){
        UserMongo userMongo = userRepository.findUserByUserName("小黑");
        System.out.println("user is "+ userMongo);
    }

}
