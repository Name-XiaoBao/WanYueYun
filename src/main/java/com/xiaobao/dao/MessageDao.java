package com.xiaobao.dao;

import org.springframework.stereotype.Component;

/**
 * @author 小暴
 */
@Component("messageDao")
public interface MessageDao {
    /**
     * 添加消息
     *
     * @param api
     * @param user
     * @param message
     * @param send_msg
     * @return
     */
    int addMsg(String api, String user, String message, String send_msg);
}
