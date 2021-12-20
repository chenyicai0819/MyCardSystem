package com.chen.mycardsystembackstage.service.impl;

import com.chen.mycardsystembackstage.entity.User;
import com.chen.mycardsystembackstage.mapper.UserMapper;
import com.chen.mycardsystembackstage.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service.impl
 * @date 2021/10/22 15:38
 * @since 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public int login(User user) {
        return userMapper.login(user);
    }

    @Override
    public int addOpenid(String id, String openid) {
        return userMapper.addOpenid(id,openid);
    }

    @Override
    public User selUser(String id) {
        return userMapper.selUser(id);
    }
}
