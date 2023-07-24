package com.xiaobao.service.Impl;

import com.xiaobao.dao.MessageDao;
import com.xiaobao.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 小暴
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    /**
     * 添加消息
     *
     * @param api
     * @param user
     * @param message
     * @param send_msg
     * @return
     */
    @Override
    public int addMsg(String api, String user, String message, String send_msg) {
        return messageDao.addMsg(api, user, message, send_msg);
    }
}
