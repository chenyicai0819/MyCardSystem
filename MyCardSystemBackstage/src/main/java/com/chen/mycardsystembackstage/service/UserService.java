package com.chen.mycardsystembackstage.service;

import com.chen.mycardsystembackstage.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service
 * @date 2021/10/22 15:37
 * @since 1.0
 */
@Service
public interface UserService {
    int login(User user);
    int addOpenid(String id,String openid);
    User selUser(String id);
    String getPassword(String id);
    int checkUserBanStatus(String id);
    String getRole(String id);
    String getRolePermission(String id);
    String getPermission(String id);
    List<User> getAllUser();

}
