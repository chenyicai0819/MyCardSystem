package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.Manages;
import com.chen.mycardsystembackstage.mapper.ManaMapper;
import com.chen.mycardsystembackstage.service.ManaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service.impl
 * @date 2021/10/22 16:42
 * @since 1.0
 */
@Service
public class ManaServiceImpl implements ManaService {

    @Resource
    private ManaMapper manaMapper;
    @Override
    public List<Manages> getMana() {
        return manaMapper.getMana();
    }

    /**
     * 获取数据库表的字段名
     * @param head
     * @return
     */
    @Override
    public List<String> getHead(int head) {
        String name=getName(head);

        // List<String> list = manaMapper.getHead(name);
        // for (int i = 0; i < list.size(); i++) {
        //     System.out.println(list.get(0));
        // }
        // return null;
        return manaMapper.getHead(name);
    }

    private String getName(int head){
        String name=null;
        if (head==1){
            name="card";
        }else if (head==2){
            name="mork";
        }else if (head==3){
            name="coll";
        }else if (head==4){
            name="ad";
        }else if (head==5){
            name="imgs";
        }
        return name;
    }
}
