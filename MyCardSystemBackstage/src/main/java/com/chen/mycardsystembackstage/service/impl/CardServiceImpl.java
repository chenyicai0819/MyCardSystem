package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.Card;
import com.chen.mycardsystembackstage.mapper.CardMapper;
import com.chen.mycardsystembackstage.service.CardService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service.impl
 * @date 2021/10/21 21:55
 * @since 1.0
 */
@Service
public class CardServiceImpl implements CardService {
    @Resource
    private CardMapper cardMapper;

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
        return cardMapper.delCard(cardId);
    }

    @Override
    public int upCard(Card card) {
        return cardMapper.upCard(card);
    }

    @Override
    public int addCard(Card card) {
        return cardMapper.addCard(card);
    }

    @Override
    public int countCard() {
        return cardMapper.countCard();
    }
}
