package com.xiaobao.dao;

import com.xiaobao.model.MiniProgram;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("miniProgramDao")
public interface MiniProgramDao {
    /**
     * 获取小程序列表
     *
     * @return
     */
    List<MiniProgram> getAllMiniProgram();
}
