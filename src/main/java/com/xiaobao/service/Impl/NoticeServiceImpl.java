package com.xiaobao.service.Impl;

import com.xiaobao.dao.NoticeDao;
import com.xiaobao.model.Notice;
import com.xiaobao.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author 小暴
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;
    /**
     * 查询公告列表
     * @return
     */
    @Override
    public List<Notice> list() {
        return noticeDao.list();
    }
}
