package com.xiaobao.dao;

import com.xiaobao.model.Api;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小暴
 */
@Component("apiDao")
public interface ApiDao {
    /**
     * 判断API是否存在
     * @param name
     * @return
     */
    Api Api(String username,String name);
    /**
     * 判断API是否存在
     * @param name
     * @return
     */
    Api api(String name);
    /**
     * 添加API
     * @param username
     * @param password
     * @param name
     * @return
     */
    int addApi(String username,String password,String name);

    /**
     * 修改API信息
     * @param name
     * @return
     */
    int upApi(int id,String name);

    /**
     * 获取用户API列表
     * @param username
     * @param password
     * @return
     */
    List<Api> apiList(String username, String password);

    /**
     * 删除用户API
     * @param id
     * @return
     */
    int deapi(int id);

    /**
     * 修改API表里的用户密码
     * @param username
     * @param password
     * @return
     */
    int upUserApi(String username,String password);

    /**
     * 根据API ID查询API信息
     * @param id
     * @return
     */
    Api ApiName(int id);
}
