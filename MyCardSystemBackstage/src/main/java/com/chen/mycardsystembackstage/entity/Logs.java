package com.chen.mycardsystembackstage.entity;

import java.io.Serializable;

/**
 * Project : MyCardSystem - Logs
 * Powered by GeorgeChen On 2022-12-16 23:04:06
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
public class Logs implements Serializable {

    private int id;
    private String type;
    private String user;
    private String ip;
    private String classMethod;
    private String args;
    private String path;
    private String time;

    public Logs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
