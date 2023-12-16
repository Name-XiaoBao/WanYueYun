package com.xiaobao.service;

import com.xiaobao.model.MiniProgram;

import java.util.List;

public interface MiniProgramService {
    /**
     * 获取小程序列表
     *
     * @return
     */
    List<MiniProgram> getAllMiniProgram();
}
