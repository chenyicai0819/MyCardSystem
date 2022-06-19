package com.chen.mycardsystembackstage.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.entity
 * @date 2022/5/27 15:47
 * @since 1.0
 */

@Data
public class Imgs implements Serializable {
    private int imgsId;
    private String imgsName;
    private String imgsText;
    private String imgsLink;
}
