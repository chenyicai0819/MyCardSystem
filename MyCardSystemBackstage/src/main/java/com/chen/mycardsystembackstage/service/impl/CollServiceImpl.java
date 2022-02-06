package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.Coll;
import com.chen.mycardsystembackstage.mapper.CollMapper;
import com.chen.mycardsystembackstage.service.CollService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service.impl
 * @date 2021/10/21 22:48
 * @since 1.0
 */
@Service
public class CollServiceImpl implements CollService {

    @Resource
    private CollMapper collMapper;
    @Override
    public List<Coll> getColl(int page,int size) {
        PageHelper.startPage(page, size);
        return collMapper.getColl();
    }

    @Override
    public List<Coll> showColl() {
        return collMapper.getColl();
    }

    @Override
    public int delColl(String id) {
        return collMapper.delColl(id);
    }

    @Override
    public List<Coll> seaColl(String name) {
        return collMapper.seaColl(name);
    }

    @Override
    public int countColl() {
        return collMapper.countColl();
    }

    @Override
    public int addColl(Coll coll) {
        return collMapper.addColl(coll);
    }

    @Override
    public int upColl(Coll coll) {
        return collMapper.upColl(coll);
    }

    @Override
    public String seaNameColl(String id) {
        return collMapper.seaNameColl(id);
    }
}
