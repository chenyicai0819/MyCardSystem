package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.Logs;
import com.chen.mycardsystembackstage.mapper.LogsMapper;
import com.chen.mycardsystembackstage.service.LogsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project : MyCardSystem - LogsServiceImpl
 * Powered by GeorgeChen On 2022-12-17 09:42:53
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Service
public class LogsServiceImpl implements LogsService {
    @Autowired
    private LogsMapper logsMapper;

    @Override
    public int addLogs(Logs logs) {
        return logsMapper.addLogs(logs);
    }

    @Override
    public List<Logs> getLogs(int page,int size) {
        PageHelper.startPage(page, size);
        return logsMapper.getLogs();
    }

    @Override
    public List<Logs> showLogs() {
        return logsMapper.getLogs();
    }
}
