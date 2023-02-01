package com.chen.mycardsystembackstage.utils.exception;

import com.chen.mycardsystembackstage.common.responsecode.CodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 全局校验 exception, 如客户端输入参数错误，这些是属于可控范围的 exception； 无需 log
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalVerifyException extends RuntimeException {
   private CodeEnum codeEnum;

   public GlobalVerifyException() {
      super();
   }

   /**
    * 使用 codeEnum 的 defaultMessage 异常信息
    *
    * @param codeEnum
    */
   public GlobalVerifyException(CodeEnum codeEnum) {
      super(codeEnum.toString());
      this.codeEnum = codeEnum;
   }

   /**
    * 使用 defaultMessage + message 作为异常信息
    *
    * @param codeEnum
    * @param message
    */
   public GlobalVerifyException(CodeEnum codeEnum, String message) {
      super(String.format("%s，%s", codeEnum.getDefaultMessage(), message));
      this.codeEnum = codeEnum;
   }

   /**
    * 使用 defaultMessage + message 作为异常信息
    *
    * @param codeEnum
    * @param message
    */
   public GlobalVerifyException(CodeEnum codeEnum, String message, Throwable cause) {
      super(String.format("%s\t%s", codeEnum.toString(), message), cause);
      this.codeEnum = codeEnum;
   }

   public GlobalVerifyException(String message) {
      super(message);
   }
}

