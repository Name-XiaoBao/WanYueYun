package com.xiaobao.dao;

import com.xiaobao.model.User;
import org.springframework.stereotype.Component;

/**
 * @author 小暴
 */
@Component("userDao")
public interface UserDao {
    /**
     * 判断是否存在该用户
     * @param user
     * @return
     */
    User user(String user);

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    int Register(String username,String password);
    /**
     * 判断用户名和密码是否一致
     * @param user pass
     * @return
     */
    User user_pass(String user,String pass);

    /**
     * 修改用户密码
     * @param username
     * @param password
     * @return
     */
    int upuser(String username,String password);
}
