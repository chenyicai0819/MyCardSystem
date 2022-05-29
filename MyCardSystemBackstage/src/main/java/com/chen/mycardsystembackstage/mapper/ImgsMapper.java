package com.chen.mycardsystembackstage.mapper;

import com.chen.mycardsystembackstage.entity.Imgs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.mapper
 * @date 2022/5/27 15:50
 * @since 1.0
 */

@Mapper
public interface ImgsMapper {
    List<Imgs> AllImgs();
    int AddImgs(Imgs imgs);
    int Updata(Imgs imgs);
    int DeleteImgs(int id);
    int countImgs();
}
