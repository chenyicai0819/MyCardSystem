package com.chen.mycardsystembackstage.service;

import com.chen.mycardsystembackstage.entity.Coll;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service
 * @date 2021/10/21 22:48
 * @since 1.0
 */
@Service
public interface CollService {
    public List<Coll> getColl(int page,int size);
    public List<Coll> showColl();
    public int delColl(String id);
    public List<Coll> seaColl(String name);
    public int countColl();
    public int addColl(Coll coll);
    public int upColl(Coll coll);
    public String seaNameColl(String id);
}
