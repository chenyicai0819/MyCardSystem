package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.Ad;
import com.chen.mycardsystembackstage.entity.Logs;
import com.chen.mycardsystembackstage.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Project : MyCardSystem - LogsController
 * Powered by GeorgeChen On 2022-12-17 23:28:19
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/

@RestController
@RequestMapping("/logs")
public class LogsController {

    @Autowired
    private LogsService logsService;

    @GetMapping("/get")
    public List<Logs> getLogs(int page, int size,int type){
        List<Logs> logs=logsService.getLogs(page,size,type);
        return logs;
    }

    @GetMapping("/count")
    public int countLogs(int type){
        return logsService.showLogs(type).size();
    }
}
