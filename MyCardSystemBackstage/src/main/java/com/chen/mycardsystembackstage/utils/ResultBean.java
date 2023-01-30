package com.chen.mycardsystembackstage.utils;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Project : MyCardSystem - ResultBean
 * Powered by GeorgeChen On 2023-01-30 16:29:01
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Component
public class ResultBean implements Serializable {

    //返回信息常量
    private static final String SUCCESS_MESSAGE = "success";
    private static final String FAIL_MESSAGE = "error";
    //返回状态码常量
    private static final int SUCCESS_CODE = 200;
    private static final int FAIL_CODE = 500;

    //状态码
    private Integer code;
    //返回信息
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    //私有化构造
    private ResultBean() {
    }

    //全参构造
    private ResultBean(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 默认成功响应
     *
     * @return
     */
    public static ResultBean success() {
        return new ResultBean(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    /**
     * 默认失败相应
     *
     * @return
     */
    public static ResultBean error() {
        return new ResultBean(FAIL_CODE, FAIL_MESSAGE);
    }

    /**
     * 自定义相应信息，成功响应
     *
     * @return
     */
    public static ResultBean success(String msg) {
        return new ResultBean(SUCCESS_CODE, msg);
    }

    /**
     * 自定义信息，失败响应
     *
     * @return
     */
    public static ResultBean error(String msg) {
        return new ResultBean(FAIL_CODE, msg);
    }

    /**
     * 自定义状态吗和信息，成功响应
     *
     * @return
     */
    public static ResultBean success(Integer code, String msg) {
        return new ResultBean(code, msg);
    }

    /**
     * 自定义状态码和信息，失败响应
     *
     * @return
     */
    public static ResultBean error(Integer code, String msg) {
        return new ResultBean(code, msg);
    }

    /**
     * 自定义状态码和信息响应
     * 推荐使用
     * @return
     */
    public static ResultBean getInstance(Integer code, String msg) {
        return new ResultBean(code, msg);
    }
}
