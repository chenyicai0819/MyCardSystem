package com.chen.mycardsystembackstage.service;

import com.chen.mycardsystembackstage.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service
 * @date 2021/10/22 15:37
 * @since 1.0
 */
@Service
public interface UserService {
    public int login(User user);
}
