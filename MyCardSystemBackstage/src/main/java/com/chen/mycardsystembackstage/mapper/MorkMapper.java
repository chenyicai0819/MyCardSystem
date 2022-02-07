package com.chen.mycardsystembackstage.mapper;

import com.chen.mycardsystembackstage.entity.Mork;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.mapper
 * @date 2021/10/21 23:10
 * @since 1.0
 */
@Mapper
public interface MorkMapper {
    public List<Mork> getMork();
    public int addMork(Mork mork);
    public int delMork(int id);
    public List<Mork> seaMork(String name);
    public int upMork(Mork mork);
    public int countMork();
    public String getNameById(int id);
}
