package com.xiaobao.service.Impl;

import com.xiaobao.dao.EditionDao;
import com.xiaobao.model.Edition;
import com.xiaobao.service.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 小暴
 */
@Service("editionService")
public class EditionServiceImpl implements EditionService {
    @Autowired
    private EditionDao editionDao;
    /**
     * 查询版本信息
     *
     * @return
     */
    @Override
    public Edition list() {
        return editionDao.list();
    }
}
