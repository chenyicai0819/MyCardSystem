package com.chen.mycardsystembackstage.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.entity
 * @date 2021/10/21 21:52
 * @since 1.0
 */
@Data
public class Mork implements Serializable {
    private int morkId;
    private String morkName;
    private String morkLink;
    private String morkType;
    private String morkImg;
    private String morkText;
}
