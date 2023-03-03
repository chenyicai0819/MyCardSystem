package com.chen.mycardsystembackstage.entity.VO;

import lombok.Data;

import java.io.File;
import java.util.List;

/**
 * Project : MyCardSystem - UtilsPdfVo
 * Powered by GeorgeChen On 2023-03-03 15:20:08
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Data
public class UtilsPdfVo {
    private File[] imageFiles;
    private String name;
}
