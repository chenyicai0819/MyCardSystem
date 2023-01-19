/*
 * Copyright (C) 2016 eKing Technology, Inc. All Rights Reserved.
 */
package com.chen.mycardsystembackstage.common.responsecode;


import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodeRest implements CodeEnum {
   /**
    * http请求异常枚举
    */

   HTTP_REST_CLIENT_ERROR(100080001, "http请求client端异常"),

   HTTP_REST_SERVER_ERROR(100080002, "http请求server端异常");

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