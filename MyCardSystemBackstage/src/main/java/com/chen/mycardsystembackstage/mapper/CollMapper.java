package com.chen.mycardsystembackstage.mapper;

import com.chen.mycardsystembackstage.entity.Card;
import com.chen.mycardsystembackstage.entity.Coll;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.mapper
 * @date 2021/10/21 22:46
 * @since 1.0
 */
@Mapper
public interface CollMapper {
    public List<Coll> getColl();
    public int delColl(String id);
    public List<Coll> seaColl(String name);
    public int countColl();
    public int addColl(Coll coll);
    public int upColl(Coll coll);
}
