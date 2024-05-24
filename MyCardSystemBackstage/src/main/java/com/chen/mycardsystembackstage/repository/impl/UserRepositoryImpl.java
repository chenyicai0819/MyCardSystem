package com.chen.mycardsystembackstage.repository.impl;

import com.chen.mycardsystembackstage.entity.mongod.UserMongo;
import com.chen.mycardsystembackstage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


/**
 * Project : MyCardSystem - UserRepositoryImpl
 * Powered by GeorgeChen On 2023-05-13 00:22:26
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Component
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(UserMongo userMongo) {
        mongoTemplate.save(userMongo);
    }

    @Override
    public UserMongo findUserByUserName(String name) {
        Query query=new Query(Criteria.where("name").is(name));
        UserMongo userMongo =  mongoTemplate.findOne(query , UserMongo.class);
        return userMongo;
    }
}
