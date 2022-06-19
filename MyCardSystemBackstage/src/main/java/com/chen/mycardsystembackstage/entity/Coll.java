package com.chen.mycardsystembackstage.entity;

import java.io.Serializable;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.entity
 * @date 2021/10/21 21:51
 * @since 1.0
 */
public class Coll implements Serializable {
    private String collId;
    private String collName;
    private String collImg;
    private String collText;

    public Coll() {
    }

    public Coll(String collId, String collName, String collImg, String collText) {
        this.collId = collId;
        this.collName = collName;
        this.collImg = collImg;
        this.collText = collText;
    }

    public String getCollId() {
        return collId;
    }

    public void setCollId(String collId) {
        this.collId = collId;
    }

    public String getCollName() {
        return collName;
    }

    public void setCollName(String collName) {
        this.collName = collName;
    }

    public String getCollImg() {
        return collImg;
    }

    public void setCollImg(String collImg) {
        this.collImg = collImg;
    }

    public String getCollText() {
        return collText;
    }

    public void setCollText(String collText) {
        this.collText = collText;
    }

    @Override
    public String toString() {
        return "Coll{" +
                "collId='" + collId + '\'' +
                ", collName='" + collName + '\'' +
                ", collImg='" + collImg + '\'' +
                ", collText='" + collText + '\'' +
                '}';
    }
}
