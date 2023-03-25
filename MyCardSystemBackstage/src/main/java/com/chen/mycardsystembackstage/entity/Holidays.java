package com.chen.mycardsystembackstage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;

/**
 * Project : MyCardSystem - Holidays
 * Powered by GeorgeChen On 2023-03-10 15:14:33
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Data
public class Holidays {
    private String year;
    private String name;
    private Date startDate;
    private Date endDate;
    private int isRest;
}
