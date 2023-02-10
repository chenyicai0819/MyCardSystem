package com.chen.mycardsystembackstage.service;

import com.chen.mycardsystembackstage.entity.IndexMenu;
import com.chen.mycardsystembackstage.entity.VO.IndexMenuVO;
import com.chen.mycardsystembackstage.utils.ResultBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project : MyCardSystem - IndexService
 * Powered by GeorgeChen On 2023-02-09 22:26:59
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/

public interface IndexService {
    List<IndexMenuVO> getIndexMenu();
    ResultBean addIndexMenu(IndexMenu indexMenu);
    ResultBean updateIndexMenu(IndexMenu indexMenu);
    ResultBean delIndexMenu(int id);
}
