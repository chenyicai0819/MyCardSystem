/*
 * Copyright (C) 2016 eKing Technology, Inc. All Rights Reserved.
 */
package com.chen.mycardsystembackstage.common.responsecode;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回的错误代码, 返回前端用 int, 后端用枚举, 可以减少数据传输
 * 可以基于 code 的 name 进行国际化
 * 这里只列出系统默认的 code, 其他的 code, 应该按 CodePerson 这样进行区分
 * 建议划分方法, 使用 9 位数字 (一个 int 可以表示完全), 第一位固定是 1, 接下来 4 位划分模块, 最后四位划分具体的 errorCode;
 * 如: 10002xxxx, 表示用户相关模块错误
 */
@AllArgsConstructor
@Getter
public enum CodeCommonCheck implements CodeEnum {
   /**
    * 通用检查枚举值
    */
   CAN_NOT_LARGER_THAN_THE_MAX_LENGTH(100020001, "不能超过最大长度"),
   CAN_NOT_LARGER_THAN_THE_MAX_SIZE(100020002, "不能超过集合最大大小"),
   OBJECT_CAN_NOT_BE_NULL_OR_EMPTY(100020003, "对象不能为空"),
   CAN_NOT_MATCH_DATE_FORMAT_PATTERN(100020004, "无法匹配限制的时间格式"),
   STRING_CAN_NOT_BE_NULL_OR_EMPTY(100020005, "字符串参数不能为空(空串)"),
   LONG_CAN_NOT_BE_NULL_OR_EMPTY(100020008, "整型不能为空"),
   LIST_CAN_NOT_BE_NULL_OR_EMPTY(100020006, "集合不能为空"),
   TYPE_IS_NOT_SUPPORT(100020007, "不支持的类型"),
   CHECKTEMPLATE_IS_NOT_SUPPORT(100020008, "不支持的检查模版"),
   COMPARETYPE_IS_NOT_SUPPORT(100020009, "不支持的比较类型"),
   ;

   /**
    * 返回客户端的编码
    */
   private final long code;

   /**
    * 默认消息
    */
   private final String defaultMessage;

}