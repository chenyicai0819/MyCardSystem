package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.Mork;
import com.chen.mycardsystembackstage.mapper.MorkMapper;
import com.chen.mycardsystembackstage.service.MorkService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service.impl
 * @date 2021/10/21 23:13
 * @since 1.0
 */
@Service
public class MorkServiceImpl implements MorkService {
    @Resource
    private MorkMapper morkMapper;

    @Override
    public List<Mork> getMork(int page,int size) {
        PageHelper.startPage(page, size);
        return morkMapper.getMork();
    }

    @Override
    public List<Mork> showMork() {
        return morkMapper.getMork();
    }

    @Override
    public int addMork(Mork mork) {
        return morkMapper.addMork(mork);
    }

    @Override
    public int delMork(int id) {
        return morkMapper.delMork(id);
    }

    @Override
    public List<Mork> seaMork(String name) {
        return morkMapper.seaMork(name);
    }

    @Override
    public int upMork(Mork mork) {
        return morkMapper.upMork(mork);
    }

    @Override
    public int countMork() {
        return morkMapper.countMork();
    }
}
