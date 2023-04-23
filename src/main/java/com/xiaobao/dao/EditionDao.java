package com.xiaobao.dao;

import com.xiaobao.model.Edition;
import org.springframework.stereotype.Component;

/**
 * @author 小暴
 */
@Component("editionDao")
public interface EditionDao {
    /**
     * 查询版本信息
     * @return
     */
    Edition list();
}
