package com.chen.mycardsystembackstage.entity;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.entity
 * @date 2021/10/21 21:47
 * @since 1.0
 */
public class Card {
    private String cardId;
    private String cardName;
    private String cardLink;
    private String cardImg;

    public Card() {
    }

    public Card(String cardId, String cardName, String cardLink, String cardImg) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cardLink = cardLink;
        this.cardImg = cardImg;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardLink() {
        return cardLink;
    }

    public void setCardLink(String cardLink) {
        this.cardLink = cardLink;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId='" + cardId + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardLink='" + cardLink + '\'' +
                ", cardImg='" + cardImg + '\'' +
                '}';
    }
}
