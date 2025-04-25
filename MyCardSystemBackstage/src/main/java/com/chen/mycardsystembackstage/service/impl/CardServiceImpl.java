package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.Card;
import com.chen.mycardsystembackstage.mapper.CardMapper;
import com.chen.mycardsystembackstage.service.CardService;
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
 * @date 2021/10/21 21:55
 * @since 1.0
 */
@Service
public class CardServiceImpl implements CardService {
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
    private CardMapper cardMapper;
    @Autowired
    private GetIpUtil getIpUtil;
    @Autowired
    private HttpServletRequest request;

    @Override
    public List<Card> seaCard(String name) {
        return cardMapper.seaCard(name);
    }

    @Override
    public List<Card> getCard(int page,int size) {
        PageHelper.startPage(page, size);
        return cardMapper.getCard();
    }

    @Override
    public List<Card> showCard() {
        return cardMapper.getCard();
    }

    @Override
    public int delCard(String cardId) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",getIpUtil.getIpAddr(request));
        map.put("type","删除卡片");
        map.put("name",cardMapper.getNameById(cardId));
        map.put("time", dateFormat.format(date));
        // wcn.pushEdit(map);
        return cardMapper.delCard(cardId);
    }

    @Override
    public int upCard(Card card) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",getIpUtil.getIpAddr(request));
        map.put("type","更新卡片");
        map.put("name",cardMapper.getNameById(card.getCardId()));
        map.put("time", dateFormat.format(date));
        // wcn.pushEdit(map);
        return cardMapper.upCard(card);
    }

    @Override
    public int addCard(Card card) {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("ip",getIpUtil.getIpAddr(request));
        map.put("type","添加卡片");
        map.put("name",card.getCardName());
        map.put("time", dateFormat.format(date));
        // wcn.pushEdit(map);
        return cardMapper.addCard(card);
    }

    @Override
    public int countCard() {
        return cardMapper.countCard();
    }
}
