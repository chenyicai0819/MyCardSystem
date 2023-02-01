package com.chen.mycardsystembackstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.mycardsystembackstage.entity.ColumnName;
import com.chen.mycardsystembackstage.entity.Manages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Objects;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.mapper
 * @date 2021/10/22 16:40
 * @since 1.0
 */
@Mapper
public interface ManaMapper {
    public List<Manages> getMana();
    public List<String> getHead(String head);
}
