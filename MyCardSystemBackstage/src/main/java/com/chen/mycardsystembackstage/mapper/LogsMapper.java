package com.chen.mycardsystembackstage.mapper;

import com.chen.mycardsystembackstage.entity.Logs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Project : MyCardSystem - LogsMapper
 * Powered by GeorgeChen On 2022-12-16 23:09:54
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Mapper
public interface LogsMapper {

    public int addLogs(Logs logs);
    public List<Logs> getLogs(int type);
}
