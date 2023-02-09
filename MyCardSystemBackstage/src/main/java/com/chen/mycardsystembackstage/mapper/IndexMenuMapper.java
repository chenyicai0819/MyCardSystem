package com.chen.mycardsystembackstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.mycardsystembackstage.entity.IndexMenu;
import com.chen.mycardsystembackstage.entity.SysScheduleTrigger;
import com.chen.mycardsystembackstage.entity.VO.IndexMenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Project : MyCardSystem - IndexMenuMapper
 * Powered by GeorgeChen On 2023-02-09 22:29:57
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/

@Mapper
public interface IndexMenuMapper extends BaseMapper<IndexMenu> {
    public List<IndexMenu> getIndexMenu(String parent);
}
