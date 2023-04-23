package com.xiaobao.service.Impl;

import com.xiaobao.dao.ApiDao;
import com.xiaobao.model.Api;
import com.xiaobao.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小暴
 */
@Service("apiService")
public class ApiServiceImpl implements ApiService {
    @Autowired
    private ApiDao apiDao;

    /**
     * 判断API是否存在
     *
     * @param username
     * @param name
     * @return
     */
    @Override
    public Api Api(String username, String name) {
        return apiDao.Api(username,name);
    }

    /**
     * 判断API是否存在
     * @param name
     * @return
     */
    @Override
    public Api api(String name) {
        return apiDao.api(name);
    }

    /**
     * 添加API
     * @param username
     * @param password
     * @param name
     * @return
     */
    @Override
    public int addApi(String username, String password, String name) {
        return apiDao.addApi(username, password, name);
    }

    /**
     * 修改API信息
     *
     * @param name
     * @return
     */
    @Override
    public int upApi(int id, String name) {
        return apiDao.upApi(id, name);
    }

    /**
     * 获取用户API列表
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public List<Api> apiList(String username, String password) {
        return apiDao.apiList(username, password);
    }

    /**
     * 删除用户API
     *
     * @param id
     * @return
     */
    @Override
    public int deapi(int id) {
        return apiDao.deapi(id);
    }

    /**
     * 修改API表里的用户密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public int upUserApi(String username, String password) {
        return apiDao.upUserApi(username,password);
    }

    /**
     * 根据API ID查询API信息
     *
     * @param id
     * @return
     */
    @Override
    public Api ApiName(int id) {
        return apiDao.ApiName(id);
    }

}
