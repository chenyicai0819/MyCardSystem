package com.chen.mycardsystembackstage.service;

import com.chen.mycardsystembackstage.entity.Card;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service
 * @date 2021/10/21 21:55
 * @since 1.0
 */
@Service
public interface CardService {
    public List<Card> seaCard(String name);
    public List<Card> getCard(int page,int size);
    public List<Card> showCard();
    public int delCard(String cardId);
    public int upCard(Card card);
    public int addCard(Card card);
    public int countCard();
}
