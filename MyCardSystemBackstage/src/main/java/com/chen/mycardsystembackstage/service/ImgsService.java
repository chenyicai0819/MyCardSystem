package com.chen.mycardsystembackstage.service;

import com.chen.mycardsystembackstage.entity.Imgs;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service
 * @date 2022/5/27 15:50
 * @since 1.0
 */
public interface ImgsService {
    List<Imgs> AllImgs();
    List<Imgs> GetImgs(int page,int size);
    int AddImgs(Imgs imgs);
    int Updata(Imgs imgs);
    int DeleteImgs(int id);
    int countImgs();
}
