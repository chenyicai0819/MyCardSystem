package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.Ad;
import com.chen.mycardsystembackstage.mapper.AdMapper;
import com.chen.mycardsystembackstage.service.AdService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service.impl
 * @date 2021/11/1 22:12
 * @since 1.0
 */
@Service
public class AdServiceImpl implements AdService {

    @Resource
    private AdMapper adMapper;

    @Override
    public List<Ad> getAd(int page,int size) {
        PageHelper.startPage(page, size);
        return adMapper.getAd();
    }

    @Override
    public List<Ad> showAd() {
        return adMapper.getAd();
    }

    @Override
    public int addAd(Ad ad) {
        return adMapper.addAd(ad);
    }

    @Override
    public int delAd(String id) {
        return adMapper.delAd(id);
    }

    @Override
    public List<Ad> seaAd(String name) {
        return adMapper.seaAd(name);
    }

    @Override
    public int upAd(Ad ad) {
        return adMapper.upAd(ad);
    }

    @Override
    public int countAd() {
        return adMapper.countAd();
    }
}
