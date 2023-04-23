package com.xiaobao.service;

import com.xiaobao.model.ThirdPartyLogin;

/**
 * @author 小暴
 */
public interface ThirdPartyLoginService {
    /**
     * 添加标识到数据库
     * @param username
     * @param Identification
     * @param Key
     * @return
     */
    int AddIdentification(String username,String Identification,String Key);
    /**
     * 判断是否存在账号
     * @param username
     * @return
     */
    ThirdPartyLogin username(String username);
    /**
     * 判断用户名和标识是否一致
     * @param username
     * @param Identification
     * @return
     */
    ThirdPartyLogin username_Identification(String username,String Identification);
}
