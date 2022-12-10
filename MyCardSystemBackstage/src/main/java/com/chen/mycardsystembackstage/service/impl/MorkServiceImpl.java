package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.Mork;
import com.chen.mycardsystembackstage.mapper.MorkMapper;
import com.chen.mycardsystembackstage.service.MorkService;
import com.chen.mycardsystembackstage.utils.GetIpUtil;
import com.chen.mycardsystembackstage.utils.WeChatNotify;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
 * @date 2021/10/21 23:13
 * @since 1.0
 */
@Service
public class MorkServiceImpl implements MorkService {

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
    private MorkMapper morkMapper;
    @Autowired
    private GetIpUtil getIpUtil;
    @Autowired
    private HttpServletRequest request;

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
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",getIpUtil.getIpAddr(request));
        map.put("type","添加书签");
        map.put("name",mork.getMorkName());
        map.put("time", dateFormat.format(date));
        wcn.pushEdit(map);
        return morkMapper.addMork(mork);
    }

    @Override
    public int delMork(int id) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",getIpUtil.getIpAddr(request));
        map.put("type","删除书签");
        map.put("name",morkMapper.getNameById(id));
        map.put("time", dateFormat.format(date));
        wcn.pushEdit(map);
        return morkMapper.delMork(id);
    }

    @Override
    public List<Mork> seaMork(String name) {
        return morkMapper.seaMork(name);
    }

    @Override
    public int upMork(Mork mork) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",getIpUtil.getIpAddr(request));
        map.put("type","更新书签");
        map.put("name",morkMapper.getNameById(mork.getMorkId()));
        map.put("time", dateFormat.format(date));
        wcn.pushEdit(map);
        return morkMapper.upMork(mork);
    }

    @Override
    public int countMork() {
        return morkMapper.countMork();
    }

}
