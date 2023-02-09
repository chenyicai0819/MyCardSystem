package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.IndexMenu;
import com.chen.mycardsystembackstage.entity.VO.IndexMenuVO;
import com.chen.mycardsystembackstage.mapper.IndexMenuMapper;
import com.chen.mycardsystembackstage.service.IndexService;
import com.chen.mycardsystembackstage.utils.ResultBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Project : MyCardSystem - IndexServiceImpl
 * Powered by GeorgeChen On 2023-02-09 22:27:52
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private IndexMenuMapper indexMenuMapper;
    @Override
    public List<IndexMenuVO> getIndexMenu() {
        // 首先获取父菜单
        List<IndexMenu> parents = indexMenuMapper.getIndexMenu("null" );
        List<IndexMenuVO> menuVOS = new ArrayList<>();

        for (int i = 0; i < parents.size(); i++) {
            IndexMenuVO indexMenuVO = new IndexMenuVO();
            indexMenuVO.setId(parents.get(i).getId());
            indexMenuVO.setName(parents.get(i).getName());
            indexMenuVO.setLink(parents.get(i).getLink());
            indexMenuVO.setLogo(parents.get(i).getLogo());
            indexMenuVO.setLevel(parents.get(i).getLevel());
            indexMenuVO.setParent(parents.get(i).getParent());
            indexMenuVO.setChildren(indexMenuMapper.getIndexMenu(parents.get(i).getId()));
            menuVOS.add(indexMenuVO);
        }
        return menuVOS;
    }

    @Override
    public ResultBean addIndexMenu(IndexMenu indexMenu) {

        if (indexMenuMapper.insert(indexMenu)>0){
            return ResultBean.success();
        }else {
            return ResultBean.error();
        }
    }

    @Override
    public ResultBean updateIndexMenu(IndexMenu indexMenu) {

        if (indexMenuMapper.updateById(indexMenu)>0){
            return ResultBean.success();
        }else {
            return ResultBean.error();
        }
    }

    @Override
    public ResultBean delIndexMenu(String id) {
        if (indexMenuMapper.deleteById(id)>0){
             return ResultBean.success();
        }else {
            return ResultBean.error();
        }
    }
}
