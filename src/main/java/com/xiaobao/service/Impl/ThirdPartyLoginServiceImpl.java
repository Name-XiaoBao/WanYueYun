package com.xiaobao.service.Impl;

import com.xiaobao.dao.ThirdPartyLoginDao;
import com.xiaobao.model.ThirdPartyLogin;
import com.xiaobao.service.ThirdPartyLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 小暴
 */
@Service("thirdPartyLoginService")
public class ThirdPartyLoginServiceImpl implements ThirdPartyLoginService {
    @Autowired
    public ThirdPartyLoginDao thirdPartyLoginDao;
    /**
     * 添加标识到数据库
     *
     * @param username
     * @param Identification
     * @return
     */
    @Override
    public int AddIdentification(String username, String Identification,String Key) {
        return thirdPartyLoginDao.AddIdentification(username,Identification,Key);
    }

    /**
     * 判断是否存在账号
     *
     * @param username
     * @return
     */
    @Override
    public ThirdPartyLogin username(String username) {
        return thirdPartyLoginDao.username(username);
    }

    /**
     * 判断用户名和标识是否一致
     *
     * @param username
     * @param Identification
     * @return
     */
    @Override
    public ThirdPartyLogin username_Identification(String username, String Identification) {
        return thirdPartyLoginDao.username_Identification(username,Identification);
    }
}
