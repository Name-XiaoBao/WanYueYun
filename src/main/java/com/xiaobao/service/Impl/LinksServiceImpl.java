package com.xiaobao.service.Impl;

import com.xiaobao.dao.LinksDao;
import com.xiaobao.model.Links;
import com.xiaobao.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小暴
 */
@Service("linksServics")
public class LinksServiceImpl implements LinksService {
    @Autowired
    private LinksDao linksDao;
    /**
     * 查询友情链接
     *
     * @return
     */
    @Override
    public List<Links> links() {
        return linksDao.links();
    }
}
