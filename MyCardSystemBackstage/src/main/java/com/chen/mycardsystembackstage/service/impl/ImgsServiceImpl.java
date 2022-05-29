package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.Imgs;
import com.chen.mycardsystembackstage.mapper.ImgsMapper;
import com.chen.mycardsystembackstage.service.ImgsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service.impl
 * @date 2022/5/27 15:50
 * @since 1.0
 */
@Service
public class ImgsServiceImpl implements ImgsService {

    @Autowired
    private ImgsMapper im;

    @Override
    public List<Imgs> AllImgs() {
        return im.AllImgs();
    }

    @Override
    public List<Imgs> GetImgs(int page, int size) {
        PageHelper.startPage(page, size);
        return im.AllImgs();
    }

    @Override
    public int AddImgs(Imgs imgs) {
        return im.AddImgs(imgs);
    }

    @Override
    public int Updata(Imgs imgs) {
        return im.Updata(imgs);
    }

    @Override
    public int DeleteImgs(int id) {
        return im.DeleteImgs(id);
    }

    @Override
    public int countImgs() {
        return im.countImgs();
    }
}
