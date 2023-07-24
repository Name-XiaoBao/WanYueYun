package com.xiaobao.service;

/**
 * @author 小暴
 */
public interface MessageService {
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
