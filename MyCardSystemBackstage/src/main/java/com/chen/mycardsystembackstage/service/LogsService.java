package com.chen.mycardsystembackstage.service;

import com.chen.mycardsystembackstage.entity.Logs;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project : MyCardSystem - LogsService
 * Powered by GeorgeChen On 2022-12-17 09:42:16
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/

@Service
public interface LogsService {

    public int addLogs(Logs logs);
    public List<Logs> getLogs(int page,int size,int type);
    public List<Logs> showLogs(int type);
}
