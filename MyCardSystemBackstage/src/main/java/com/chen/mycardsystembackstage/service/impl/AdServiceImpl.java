package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.Ad;
import com.chen.mycardsystembackstage.mapper.AdMapper;
import com.chen.mycardsystembackstage.service.AdService;
import com.chen.mycardsystembackstage.utils.WeChatNotify;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service.impl
 * @date 2021/11/1 22:12
 * @since 1.0
 */
@Service
public class AdServiceImpl implements AdService {
    Map<String,Object> map=new HashMap<>();

    InetAddress addr;

    {
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    private WeChatNotify wcn;
    @Resource
    private AdMapper adMapper;

    @Override
    public List<Ad> getAd(int page,int size) {
        PageHelper.startPage(page, size);
        return adMapper.getAd();
    }

    @Override
    public List<Ad> showAd() {
        return adMapper.showAd();
    }

    @Override
    public int addAd(Ad ad) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",addr.getHostAddress());
        map.put("type","添加广告");
        map.put("name",ad.getAdName());
        map.put("time", dateFormat.format(date));
        wcn.pushEdit(map);
        return adMapper.addAd(ad);
    }

    @Override
    public int delAd(String id) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",addr.getHostAddress());
        map.put("type","删除广告");
        map.put("name",adMapper.getNameForId(id));
        map.put("time", dateFormat.format(date));
        wcn.pushEdit(map);
        return adMapper.delAd(id);
    }

    @Override
    public List<Ad> seaAd(String name) {
        return adMapper.seaAd(name);
    }

    @Override
    public int upAd(Ad ad) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",addr.getHostAddress());
        map.put("type","更新广告");
        map.put("name",adMapper.getNameForId(ad.getAdId()));
        map.put("time", dateFormat.format(date));
        wcn.pushEdit(map);
        return adMapper.upAd(ad);
    }

    @Override
    public int countAd() {
        return adMapper.countAd();
    }
}
