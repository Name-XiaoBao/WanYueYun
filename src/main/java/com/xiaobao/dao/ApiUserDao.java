package com.xiaobao.dao;

import com.xiaobao.model.ApiUser;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小暴
 */
@Component("apiUserDao")
public interface ApiUserDao {
    /**
     * 判断是否存在用户
     * @param name
     * @param user
     * @return
     */
    ApiUser user(String name,String user);
    /**
     * 注册
     * @param user
     */
    int Register(String name, String user, String pass);

    /**
     * 判断用户名和密码是否一致
     *
     * @param user pass
     * @return
     */
    ApiUser user_pass(String user, String pass);

    /**
     * 判断是否存在此账号
     *
     * @param id
     * @param user
     * @param pass
     * @return
     */
    ApiUser id_user_pass(int id, String user, String pass);

    /**
     * API用户登录功能
     *
     * @param name
     * @param user
     * @param pass
     * @return
     */
    ApiUser login(String name, String user, String pass);

    /**
     * 查询APi用户列表
     *
     * @param NameApi
     * @return
     */
    List<ApiUser> Userlist(String NameApi);

    /**
     * 查询API用户信息
     * @param user
     * @return
     */
    ApiUser userInfo(String name,String user,String pass);

    /**
     * 修改API用户密码
     * @return
     */
    int uppass(int id,String pass);

    /**
     * 修改API用户积分
     * @param id
     * @param Integral
     * @return
     */
    int upIntegral(int id,String Integral);

    /**
     * 修改API用户会员日期
     * @param id
     * @param MembershipTime
     * @return
     */
    int upMembershipTime(int id, String MembershipTime);

    /**
     * 删除API用户
     * @param NameApi
     * @param user
     * @return
     */
    int deluser(String NameApi,String user);

    /**
     * 删除API里的所有用户
     * @param NameApi
     * @return
     */
    int delapiuser(String NameApi);
}
