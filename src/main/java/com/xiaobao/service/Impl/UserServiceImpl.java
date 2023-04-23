package com.xiaobao.service.Impl;

import com.xiaobao.dao.UserDao;
import com.xiaobao.model.User;
import com.xiaobao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 小暴
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 判断是否存在该用户
     *
     * @param user
     * @return
     */
    @Override
    public User user(String user) {
        return userDao.user(user);
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public int Register(String username, String password) {
        return userDao.Register(username, password);
    }

    /**
     * 判断用户名和密码是否一致
     *
     * @param user
     * @return
     */
    @Override
    public User user_pass(String user, String pass) {
        return userDao.user_pass(user, pass);
    }

    /**
     * 修改用户密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public int upuser(String username, String password) {
        return userDao.upuser(username, password);
    }
}
