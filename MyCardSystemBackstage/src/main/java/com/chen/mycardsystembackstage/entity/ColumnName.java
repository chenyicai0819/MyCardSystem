package com.chen.mycardsystembackstage.entity;

import java.io.Serializable;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.entity
 * @date 2021/10/22 17:19
 * @since 1.0
 */
public class ColumnName implements Serializable {
    private String COLUMN_NAME;

    public String getName() {
        return COLUMN_NAME;
    }

    public void setName(String COLUMN_NAME) {
        this.COLUMN_NAME = COLUMN_NAME;
    }
}
