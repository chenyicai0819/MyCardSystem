package com.chen.mycardsystembackstage.mapper;

import com.chen.mycardsystembackstage.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Project : MyCardSystem - StartMapper
 * Powered by GeorgeChen On 2022-12-16 23:10:20
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Mapper
public interface StartMapper {

    public int addStart(String ip,String userid);
    public int upStart(String ip,String userid);
    public String selStart(String ip);
    public int countStart(String ip);
}
