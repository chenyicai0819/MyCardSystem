package com.chen.mycardsystembackstage.common.responsecode;


import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 微信小程序枚举类
 * @author xumj
 * @date 2021-10-08
 */
@AllArgsConstructor
@Getter
public enum CodeWxApp implements CodeEnum {

   error(100100000, "小程序请求异常"),
   error_qr(100100001, "获取小程序二维码接口请求异常"),
   code_is_empty(100100002, "登录时获取的code不存在"),
   code_error(100100003, "获取openID接口请求异常"),
   id_is_empty(100100004, "志愿者ID不存在"),
   encrypted_data_is_empty(100100005, "敏感数据不存在"),
   iv_is_empty(100100006, "加密初始向量不存在"),
   openid_is_empty(100100007, "openId不存在"),
   session_key_is_empty(100100008, "登录的会话密钥不存在"),
   eecrypt_error(100100009, "解密信息异常"),
   sys_is_empty(100100010, "学校信息不存在"),
   user_str_error(100100011, "用户信息不存在"),
   child_id_is_empty(100100012, "儿童ID不存在"),
   child_info_is_empty(100100013, "儿童信息不存在"),
   phone_is_empty(100100014, "手机号码不存在"),
   child_id_card_is_existence(100100015, "儿童身份证已存在"),
   child_name_is_existence(100100016, "儿童姓名已存在"),
   openid_verify_error(100100017, "access_token校验失败"),
   wx_app_token_is_empty(100100018, "wx-app-token不存在"),
   wx_phone_is_not_empty(100100019, "微信手机号已存在"),
   wx_id_card_is_not_empty(100100020, "(父/母)身份证号码已存在"),
   wx_id_card_is_empty(100100021, "该学生暂时不在调查的范围内，请联系老师核对学生的身份证号"),
   ;


   private final long code;
   private final String defaultMessage;

}