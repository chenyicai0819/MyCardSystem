/*
 * Copyright (C) 2016 eKing Technology, Inc. All Rights Reserved.
 */
package com.chen.mycardsystembackstage.common.responsecode;


import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodeSecurity implements CodeEnum {
   /**
    * 安全检查异常枚举
    */
   LLEGAL_KEYWORDS(100090001, "请求包含非法字符"),
   AUTH_FAIL(100090002, "权限校验失败"),
   NOT_LOGIN(100090003, "未登录"),
   CHECK_FAIL(100090004, "验证失败，请重新登录后使用"),
   TOKEN_EXPIRE(100090005, "TOKEN已过期"),
   SESSION_EXPIRE(100090006, "会话超时，请重新登录"),
   ;

   // 返回客户端的编码
   private final long code;

   // 默认消息
   private final String defaultMessage;

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this)
              .add("code", code)
              .add("defaultMessage", defaultMessage)
              .toString();
   }

}