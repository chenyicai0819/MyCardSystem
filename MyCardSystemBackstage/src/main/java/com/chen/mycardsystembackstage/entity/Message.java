package com.chen.mycardsystembackstage.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Project : MyCardSystem - Message
 * Powered by GeorgeChen On 2022-12-24 23:14:49
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
public class Message implements Serializable {
    private int id;
    private String key;
    private int type;
    private int isRead;
    private String title;
    private String text;
    private String money;
    private Timestamp showdate;
    private String phone;
    private String email;
    private String link;
    private String name;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Timestamp getShowdate() {
        return showdate;
    }

    public void setShowdate(Timestamp showdate) {
        this.showdate = showdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
