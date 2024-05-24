package com.chen.mycardsystembackstage.repository;

import com.chen.mycardsystembackstage.entity.mongod.UserMongo;
import org.springframework.stereotype.Service;

/**
 * Project : MyCardSystem - UserRepository
 * Powered by GeorgeChen On 2023-05-13 00:21:36
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
public interface UserRepository {

    public void saveUser(UserMongo userMongo);
    public UserMongo findUserByUserName(String name);
}
