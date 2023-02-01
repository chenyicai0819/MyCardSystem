package com.chen.mycardsystembackstage.utils.exception;


import com.chen.mycardsystembackstage.common.responsecode.CodeEnum;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 全局 exception, 本系统中的所有自定义 exception 都应该由此派生, 以便 GlobalExceptionHandler 进行捕获
 *
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalException extends RuntimeException {

    private CodeEnum codeEnum;

    private Map<String, Object> extraInfo = Maps.newHashMap();

    private GlobalException() {
        super();
    }

    /**
     * 不允许使用不带 Throwable cause 的构造函数，如果没有 case，就传入 null
     * 这样可以避免在有 Throwable cause 时，也漏传的情况
     *
     * @param codeEnum
     */
    private GlobalException(CodeEnum codeEnum) {
        super(codeEnum.toString());
        this.codeEnum = codeEnum;
    }

    /**
     * 使用 codeEnum 的 defaultMessage 异常信息
     * defaultMessage 用来输出国际化信息
     * 一定要带上 cause，才能 log 出最原始的堆栈
     *
     * @param codeEnum
     */
    public GlobalException(CodeEnum codeEnum, Throwable cause) {
        super(codeEnum.toString(), cause);
        this.codeEnum = codeEnum;
    }

    /**
     * 使用 defaultMessage + extraInfo 作为异常信息
     * defaultMessage 用来输出国际化信息
     * 一定要带上 cause，才能 log 出最原始的堆栈
     *
     * @param codeEnum
     * @param extraInfo
     */
    public GlobalException(CodeEnum codeEnum, Map<String, Object> extraInfo, Throwable cause) {
        super(String.format("%s\t%s", codeEnum.toString(), extraInfo == null ? "" : extraInfo.toString()), cause);
        this.codeEnum = codeEnum;
        if (null != extraInfo) {
            this.extraInfo.putAll(extraInfo);
        }
    }
}

