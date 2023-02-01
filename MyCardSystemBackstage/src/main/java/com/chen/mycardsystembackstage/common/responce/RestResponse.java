/*
 */
package com.chen.mycardsystembackstage.common.responce;

import com.chen.mycardsystembackstage.common.responsecode.CodeDefault;
import com.chen.mycardsystembackstage.common.responsecode.CodeEnum;
import com.dtb.hwcmc.queue.common.responsecode.CodeDefault;
import com.dtb.hwcmc.queue.common.responsecode.CodeEnum;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Rest 请求返回值
 *
 * @author zengfan
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@JsonInclude(Include.NON_NULL)
@JsonSerialize
@JsonDeserialize
@JsonClassDescription("API 返回对象")
public class RestResponse<BEAN_CLASS> implements Serializable {

   @JsonProperty
   @JsonPropertyDescription("执行状态码, 执行成功返回 0, 其他的都是发生错误")
   private long code;

   @JsonProperty
   @JsonPropertyDescription("消息, OK 的时候, message 一般不填")
   private String message = null;

   @JsonProperty
   @JsonPropertyDescription("具体的数据, 发生错误的时候, data一般不填")
   private BEAN_CLASS data = null;

   @JsonProperty
   @JsonPropertyDescription("时间戳, 只在 exception 时有用")
   private Date timestamp = null;

   @JsonProperty
   @JsonPropertyDescription("放一些辅助信息 key->value, 一般用来进一步描述异常信息, OK 时暂未用到")
   private Map<String, Object> extraInfo = null;


   /**
    * 表示调用成功
    *
    * @return 结果
    */
   public static <BEAN_CLASS> RestResponse<BEAN_CLASS> ok() {
      RestResponse<BEAN_CLASS> restResponse = new RestResponse<>();
      restResponse.code = CodeDefault.OK.getCode();
      return restResponse;
   }

   /**
    * 表示调用成功
    *
    * @param data 数据
    * @return 结果
    */
   public static <BEAN_CLASS> RestResponse<BEAN_CLASS> ok(BEAN_CLASS data) {
      RestResponse<BEAN_CLASS> restResponse = new RestResponse<>();
      restResponse.code = CodeDefault.OK.getCode();
      restResponse.data = data;
      return restResponse;
   }

   /**
    * 调用发生 异常
    *
    * @param errorMessage 代码枚举
    * @return 结果
    */
   public static <BEAN_CLASS> RestResponse<BEAN_CLASS> exception(String errorMessage) {
      RestResponse<BEAN_CLASS> restResponse = new RestResponse<>();
      restResponse.code = -1;
      restResponse.message = errorMessage;
      restResponse.timestamp = new Date();
      return restResponse;
   }

   /**
    * 调用发生 异常
    *
    * @param codeEnum 代码枚举
    * @return 结果
    */
   public static <BEAN_CLASS> RestResponse<BEAN_CLASS> exception(CodeEnum codeEnum) {
      return exception(codeEnum, null);
   }

   /**
    * 调用发生 异常
    *
    * @param codeEnum      代码枚举
    * @param exceptionInfo 详细信息
    * @return 结果
    */
   public static <BEAN_CLASS> RestResponse<BEAN_CLASS> exception(CodeEnum codeEnum, Map<String, Object> exceptionInfo) {
      RestResponse<BEAN_CLASS> restResponse = new RestResponse<>();
      restResponse.code = codeEnum.getCode();
      restResponse.message = codeEnum.getDefaultMessage();
      if(exceptionInfo != null){
         Set<String> keys = exceptionInfo.keySet();
         for (String key : keys) {
            restResponse.message += " " + key + ":" + exceptionInfo.get(key);
         }
      }
      restResponse.timestamp = new Date();
      restResponse.extraInfo = exceptionInfo;
      return restResponse;
   }
}
