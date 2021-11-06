package com.chen.mycardsystembackstage.controller;

import com.chen.mycardsystembackstage.entity.Card;
import com.chen.mycardsystembackstage.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.controller
 * @date 2021/10/21 21:47
 * @since 1.0
 */
@RestController
@RequestMapping("/card")
public class CardController {

    @Resource
    private CardService cardService;

    @GetMapping("/get")
    @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.CardMapper::get")
    public List<Card> getCard(int page,int size){
        return cardService.getCard(page,size);
    }

    @GetMapping("/show")
    @Cacheable(value = "MYBATIS:com.chen.mycardsystembackstage.CardMapper::show")
    public List<Card> showCard(){
        return cardService.showCard();
    }

    @PostMapping("/del")
    public int delCard(String id){
        return cardService.delCard(id);
    }

    @PostMapping("/sea")
    public List<Card> seaCard(String name){
        return cardService.seaCard(name);
    }

    @PostMapping("/up")
    public int upCard(String id,String name,String link,String img){
        Card card=new Card();
        card.setCardLink(link);
        card.setCardImg(img);
        card.setCardName(name);
        card.setCardId(id);
        return cardService.upCard(card);

    }

    @PostMapping("/add")
    public int addCard(String id,String name,String link,String img){
        Card card=new Card();
        int count =cardService.countCard()+1;
        card.setCardLink(link);
        card.setCardImg(img);
        card.setCardName(name);
        card.setCardId("c"+count);
        return cardService.addCard(card);
    }

    @GetMapping("/count")
    public int countCard(){
        return cardService.countCard();
    }
}
