package com.xiaobao.service.Impl;

import com.xiaobao.dao.ApiUserDao;
import com.xiaobao.model.ApiUser;
import com.xiaobao.service.ApiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小暴
 */
@Service("apiUserService")
public class ApiUserServiceImpl implements ApiUserService {
    @Autowired
    private ApiUserDao apiUserDao;
    /**
     * 判断是否存在用户
     *
     * @param name
     * @param user
     * @return
     */
    @Override
    public ApiUser user(String name, String user) {
        return apiUserDao.user(name,user);
    }

    /**
     * 注册
     *
     * @param name
     * @param user
     */
    @Override
    public int Register(String name, String user, String pass) {
        return apiUserDao.Register(name, user, pass);
    }

    /**
     * 判断用户名和密码是否一致
     *
     * @param user pass
     * @param pass
     * @return
     */
    @Override
    public ApiUser user_pass(String user, String pass) {
        return apiUserDao.user_pass(user, pass);
    }

    /**
     * 判断是否存在此账号
     *
     * @param id
     * @param user
     * @param pass
     * @return
     */
    @Override
    public ApiUser id_user_pass(int id, String user, String pass) {
        return apiUserDao.id_user_pass(id, user, pass);
    }

    /**
     * API用户登录功能
     *
     * @param name
     * @param user
     * @param pass
     * @return
     */
    @Override
    public ApiUser login(String name, String user, String pass) {
        return apiUserDao.login(name, user, pass);
    }

    /**
     * 查询APi用户列表
     *
     * @param NameApi
     * @return
     */
    @Override
    public List<ApiUser> Userlist(String NameApi) {
        return apiUserDao.Userlist(NameApi);
    }

    /**
     * 查询API用户信息
     *
     * @param name
     * @param user
     * @param pass
     * @return
     */
    @Override
    public ApiUser userInfo(String name, String user, String pass) {
        return apiUserDao.userInfo(name,user,pass);
    }

    /**
     * 修改API用户密码
     *
     * @param id
     * @param pass
     * @return
     */
    @Override
    public int uppass(int id, String pass) {
        return apiUserDao.uppass(id, pass);
    }

    /**
     * 修改API用户积分
     *
     * @param id
     * @param Integral
     * @return
     */
    @Override
    public int upIntegral(int id, String Integral) {
        return apiUserDao.upIntegral(id, Integral);
    }

    /**
     * 修改API用户会员日期
     *
     * @param id
     * @param MembershipTime
     * @return
     */
    @Override
    public int upMembershipTime(int id, String MembershipTime) {
        return apiUserDao.upMembershipTime(id,MembershipTime);
    }

    /**
     * 删除API用户
     *
     * @param NameApi
     * @param user
     * @return
     */
    @Override
    public int deluser(String NameApi, String user) {
        return apiUserDao.deluser(NameApi,user);
    }

    /**
     * 删除API里的所有用户
     *
     * @param NameApi
     * @return
     */
    @Override
    public int delapiuser(String NameApi) {
        return apiUserDao.delapiuser(NameApi);
    }

}
