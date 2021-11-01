package com.chen.mycardsystembackstage.mapper;

import com.chen.mycardsystembackstage.entity.Ad;
import com.chen.mycardsystembackstage.entity.Mork;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.mapper
 * @date 2021/11/1 22:11
 * @since 1.0
 */
@Mapper
public interface AdMapper {
    public List<Ad> getAd();
    public int addAd(Ad ad);
    public int delAd(String id);
    public List<Ad> seaAd(String name);
    public int upAd(Ad ad);
    public int countAd();
}
