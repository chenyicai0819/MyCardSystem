/*
 * Copyright (C) 2016 eKing Technology, Inc. All Rights Reserved.
 */
package com.chen.mycardsystembackstage.utils.exception;

import com.chen.mycardsystembackstage.common.responce.RestResponse;
import com.chen.mycardsystembackstage.common.responsecode.CodeDefault;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 */
@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 系统自定义全局异常
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public RestResponse exception(HttpServletRequest req, GlobalException e) {
        RestResponse exception;
        if (e.getExtraInfo().isEmpty()) {
            exception = RestResponse.exception(e.getCodeEnum());
        } else {
            exception = RestResponse.exception(e.getCodeEnum(), e.getExtraInfo());
        }
        log.error("GlobalException : {}", exception);

        return exception;
    }

    /**
     * controller 参数转化时, 主要从这里捕获错误信息
     * @param request 请求
     * @param e 错误信息
     * @return 请求结果
     */
    @ExceptionHandler(value = ServletRequestBindingException.class)
    @ResponseBody
    public RestResponse<?> exception(HttpServletRequest request, ServletRequestBindingException e) {
        RestResponse<?> exception = RestResponse.exception(CodeDefault.ILLEGAL_ARGUMENT);
        log.error("请求参数错误: {}", exception, e);
        return exception;
    }

    /**
     * NPE处理
     * @param request 请求
     * @param npe 错误信息
     * @return 请求结果
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public RestResponse<?> exception(HttpServletRequest request, NullPointerException npe) {
        RestResponse<?> exception = RestResponse.exception(CodeDefault.NULL_POINT_ERROR);
        if(!Strings.isNullOrEmpty(npe.getMessage())){
            exception.setMessage(npe.getMessage());
        }
        Map<String,Object> extras = new HashMap<>(0);
        StackTraceElement stackTraceElement = npe.getStackTrace()[0];
        extras.put("class", stackTraceElement.getClassName());
        extras.put("method", stackTraceElement.getMethodName());
        extras.put("lineNumber", stackTraceElement.getLineNumber());
        exception.setExtraInfo(extras);
        log.error("RuntimeException: {}", exception, npe);
        return exception;
    }

    /**
     * 这个兜底
     * @param request 请求
     * @param e 错误信息
     * @return 请求结果
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public RestResponse<?> exception(HttpServletRequest request, RuntimeException e) {
        RestResponse<?> exception;
        if(Strings.isNullOrEmpty(e.getMessage())){
            exception = RestResponse.exception(CodeDefault.INTERNAL_SERVER_ERROR);
        }else{
            exception = RestResponse.exception(e.getMessage());
        }
        log.error("RuntimeException: {}", exception, e);
        return exception;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResponse<?> exception(HttpServletRequest request, Exception e) {
        RestResponse<?> exception = RestResponse.exception(CodeDefault.INTERNAL_SERVER_ERROR);
        log.error("RuntimeException: {}", exception, e);
        return exception;
    }
    /**
     * ValidationException
     */
    @ExceptionHandler(value = ValidationException.class)
    public RestResponse<?> handleValidationException(ValidationException e) {
        RestResponse<?> exception = RestResponse.exception(e.getCause().getMessage());
        log.error(e.getMessage());
        return exception;
    }

    /**
     * 方法参数校验
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RestResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        RestResponse<?> exception = RestResponse.exception(e.getBindingResult().getFieldError().getDefaultMessage());
        log.error(exception.getMessage());
        return exception;
    }

    // /**
    //  * ConstraintViolationException
    //  */
    // @ExceptionHandler(value = ConstraintViolationException.class)
    // public RestResponse<?> handleConstraintViolationException(ConstraintViolationException e) {
    //     RestResponse<?> exception = RestResponse.exception(e.getMessage());
    //     log.error(e.getMessage());
    //     return exception;
    // }

}
