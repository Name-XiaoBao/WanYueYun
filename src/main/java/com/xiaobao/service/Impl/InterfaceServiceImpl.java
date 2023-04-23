package com.xiaobao.service.Impl;

import com.xiaobao.dao.InterfaceDao;
import com.xiaobao.model.Interface;
import com.xiaobao.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("interfaceService")
public class InterfaceServiceImpl implements InterfaceService {
    @Autowired
    private InterfaceDao interfaceDao;
    /**
     * 查询接口列表
     *
     * @return
     */
    @Override
    public List<Interface> list() {
        return interfaceDao.list();
    }
}
