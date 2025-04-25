package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.Coll;
import com.chen.mycardsystembackstage.mapper.CollMapper;
import com.chen.mycardsystembackstage.service.CollService;
import com.chen.mycardsystembackstage.utils.GetIpUtil;
import com.chen.mycardsystembackstage.utils.WeChatNotify;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
 * @date 2021/10/21 22:48
 * @since 1.0
 */
@Service
public class CollServiceImpl implements CollService {
    Map<String,Object> map=new HashMap<>();

    // InetAddress addr;
    //
    // {
    //     try {
    //         addr = InetAddress.getLocalHost();
    //     } catch (UnknownHostException e) {
    //         e.printStackTrace();
    //     }
    // }
    @Autowired
    private WeChatNotify wcn;
    @Resource
    private CollMapper collMapper;
    @Autowired
    private GetIpUtil getIpUtil;
    @Autowired
    private HttpServletRequest request;


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
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",getIpUtil.getIpAddr(request));
        map.put("type","删除书签分类");
        map.put("name",collMapper.seaNameColl(id));
        map.put("time", dateFormat.format(date));
        // wcn.pushEdit(map);
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
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",getIpUtil.getIpAddr(request));
        map.put("type","添加书签分类");
        map.put("name",coll.getCollName());
        map.put("time", dateFormat.format(date));
        // wcn.pushEdit(map);
        return collMapper.addColl(coll);
    }

    @Override
    public int upColl(Coll coll) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",getIpUtil.getIpAddr(request));
        map.put("type","更新书签分类");
        map.put("name",collMapper.seaNameColl(coll.getCollId()));
        map.put("time", dateFormat.format(date));
        // wcn.pushEdit(map);
        return collMapper.upColl(coll);
    }

    @Override
    public String seaNameColl(String id) {
        return collMapper.seaNameColl(id);
    }
}
