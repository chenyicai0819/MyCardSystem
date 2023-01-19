package com.chen.mycardsystembackstage.mapper;

import com.chen.mycardsystembackstage.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.mapper
 * @date 2021/10/22 15:34
 * @since 1.0
 */
@Mapper
public interface UserMapper {
    public int login(User user);
    public int addOpenid(String id,String openid);
    public User selUser(String id);
    String getPassword(String id);
    int checkUserBanStatus(String id);
    String getRole(String id);
    String getRolePermission(String id);
    String getPermission(String id);
    List<User> getAllUser();
}
