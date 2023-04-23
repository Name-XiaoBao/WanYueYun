package com.xiaobao.dao;

import com.xiaobao.model.Notice;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小暴
 */
@Component("noticeDao")
public interface NoticeDao {
    /**
     * 查询公告列表
     * @return
     */
    List<Notice> list();
}
