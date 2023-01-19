package com.chen.mycardsystembackstage.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.entity
 * @date 2021/10/22 15:31
 * @since 1.0
 */
@Data
public class User implements Serializable {
    private int userId;
    private String userPass;
    private String openid;
    private String email;
    private String userName;

    public User() {
    }
}
