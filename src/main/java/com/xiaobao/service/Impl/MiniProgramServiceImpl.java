package com.xiaobao.service.Impl;

import com.xiaobao.dao.MiniProgramDao;
import com.xiaobao.model.MiniProgram;
import com.xiaobao.service.MiniProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("miniProgramService")
public class MiniProgramServiceImpl implements MiniProgramService {
    @Autowired
    private MiniProgramDao miniProgramDao;

    /**
     * 获取小程序列表
     *
     * @return
     */
    @Override
    public List<MiniProgram> getAllMiniProgram() {
        return miniProgramDao.getAllMiniProgram();
    }
}
