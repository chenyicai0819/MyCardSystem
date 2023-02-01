package com.chen.mycardsystembackstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.mycardsystembackstage.entity.SysScheduleTrigger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Project : MyCardSystem - SysScheduleTriggerMapper
 * Powered by GeorgeChen On 2023-01-30 16:06:51
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Mapper
public interface SysScheduleTriggerMapper extends BaseMapper<SysScheduleTrigger> {
    SysScheduleTrigger getAllByMethod(String method);
}
