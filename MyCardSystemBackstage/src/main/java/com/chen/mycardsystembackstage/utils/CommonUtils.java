package com.chen.mycardsystembackstage.utils;

import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author George
 * @project Cyc-Sequence
 * @package com.chen.sequence.utils
 * @date 2022/10/10 20:58
 * @since 1.0
 * 将指定文件读取到 InputStream 中
 */
@Component
public class CommonUtils {

    /**
     *
     * @param fileName 要导入的文件名称
     * @return inputStream 流
     */
    public static InputStream readResourceFile(String fileName) {
        return CommonUtils.class.getClassLoader().getResourceAsStream(fileName);
    }
}
