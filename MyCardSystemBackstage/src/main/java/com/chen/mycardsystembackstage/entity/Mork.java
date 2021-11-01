package com.chen.mycardsystembackstage.entity;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.entity
 * @date 2021/10/21 21:52
 * @since 1.0
 */
public class Mork {
    private int morkId;
    private String morkName;
    private String morkLink;
    private String morkType;
    private String morkImg;

    public Mork() {
    }

    public Mork(int morkId, String morkName, String morkLink, String morkType, String morkImg) {
        this.morkId = morkId;
        this.morkName = morkName;
        this.morkLink = morkLink;
        this.morkType = morkType;
        this.morkImg = morkImg;
    }

    public int getMorkId() {
        return morkId;
    }

    public void setMorkId(int morkId) {
        this.morkId = morkId;
    }

    public String getMorkName() {
        return morkName;
    }

    public void setMorkName(String morkName) {
        this.morkName = morkName;
    }

    public String getMorkLink() {
        return morkLink;
    }

    public void setMorkLink(String morkLink) {
        this.morkLink = morkLink;
    }

    public String getMorkType() {
        return morkType;
    }

    public void setMorkType(String morkType) {
        this.morkType = morkType;
    }

    public String getMorkImg() {
        return morkImg;
    }

    public void setMorkImg(String morkImg) {
        this.morkImg = morkImg;
    }

    @Override
    public String toString() {
        return "Mork{" +
                "morkId='" + morkId + '\'' +
                ", morkName='" + morkName + '\'' +
                ", morkLink='" + morkLink + '\'' +
                ", morkType='" + morkType + '\'' +
                ", morkImg='" + morkImg + '\'' +
                '}';
    }
}
