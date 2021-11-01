package com.chen.mycardsystembackstage.entity;

import java.sql.Timestamp;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.entity
 * @date 2021/10/21 21:49
 * @since 1.0
 */
public class Ad {
    private String adId;
    private String adName;
    private String adText;
    private String adImg;
    private Timestamp adUpDate;
    private Timestamp adDownDate;

    public Ad() {
    }

    public Ad(String adId, String adName, String adText, String adImg, Timestamp adUpDate, Timestamp adDownDate) {
        this.adId = adId;
        this.adName = adName;
        this.adText = adText;
        this.adImg = adImg;
        this.adUpDate = adUpDate;
        this.adDownDate = adDownDate;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdText() {
        return adText;
    }

    public void setAdText(String adText) {
        this.adText = adText;
    }

    public String getAdImg() {
        return adImg;
    }

    public void setAdImg(String adImg) {
        this.adImg = adImg;
    }

    public Timestamp getAdUpDate() {
        return adUpDate;
    }

    public void setAdUpDate(Timestamp adUpDate) {
        this.adUpDate = adUpDate;
    }

    public Timestamp getAdDownDate() {
        return adDownDate;
    }

    public void setAdDownDate(Timestamp adDownDate) {
        this.adDownDate = adDownDate;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "adId='" + adId + '\'' +
                ", adName='" + adName + '\'' +
                ", adText='" + adText + '\'' +
                ", adImg='" + adImg + '\'' +
                ", adUpDate=" + adUpDate +
                ", adDownDate=" + adDownDate +
                '}';
    }
}
