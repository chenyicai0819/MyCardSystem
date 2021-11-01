package com.chen.mycardsystembackstage.service;

import com.chen.mycardsystembackstage.entity.Mork;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service
 * @date 2021/10/21 23:12
 * @since 1.0
 */
@Service
public interface MorkService {
    public List<Mork> getMork(int page,int size);
    public List<Mork> showMork();
    public int addMork(Mork mork);
    public int delMork(int id);
    public List<Mork> seaMork(String name);
    public int upMork(Mork mork);
    public int countMork();
}
