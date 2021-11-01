package com.chen.mycardsystembackstage.mapper;

import com.chen.mycardsystembackstage.entity.Card;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.mapper
 * @date 2021/10/21 21:56
 * @since 1.0
 */
@Mapper
public interface CardMapper {
    public List<Card> getCard();
    public int delCard(String cardId);
    public List<Card> seaCard(String name);
    public int upCard(Card card);
    public int addCard(Card card);
    public int countCard();
}
