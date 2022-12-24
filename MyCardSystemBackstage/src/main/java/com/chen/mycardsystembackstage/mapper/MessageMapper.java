package com.chen.mycardsystembackstage.mapper;

import com.chen.mycardsystembackstage.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Project : MyCardSystem - MessageMapper
 * Powered by GeorgeChen On 2022-12-24 23:28:13
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Mapper
public interface MessageMapper {

    public List<Message> getMessage(int isRead);
}
