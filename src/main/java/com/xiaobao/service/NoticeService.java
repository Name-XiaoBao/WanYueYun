package com.xiaobao.service;

import com.xiaobao.model.Notice;

import java.util.List;

/**
 * @author 小暴
 */
public interface NoticeService {
    /**
     * 查询公告列表
     * @return
     */
    List<Notice> list();
}
