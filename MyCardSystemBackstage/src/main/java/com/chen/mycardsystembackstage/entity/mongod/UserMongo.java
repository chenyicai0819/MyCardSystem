package com.chen.mycardsystembackstage.entity.mongod;

import lombok.Data;

import java.io.Serializable;

/**
 * Project : MyCardSystem - user
 * Powered by GeorgeChen On 2023-05-13 00:16:51
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Data
public class UserMongo implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    private Long id;
    private String name;
    private String age;
}
