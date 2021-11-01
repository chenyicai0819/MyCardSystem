package com.chen.mycardsystembackstage.service;

import com.chen.mycardsystembackstage.entity.Ad;
import com.chen.mycardsystembackstage.entity.Card;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service
 * @date 2021/11/1 22:12
 * @since 1.0
 */
@Service
public interface AdService {
    public List<Ad> getAd(int page,int size);
    public List<Ad> showAd();
    public int addAd(Ad ad);
    public int delAd(String id);
    public List<Ad> seaAd(String name);
    public int upAd(Ad ad);
    public int countAd();
}
