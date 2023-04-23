package com.xiaobao.dao;

import com.xiaobao.model.Interface;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小暴
 */
@Component("interfaceDao")
public interface InterfaceDao {
    /**
     * 查询接口列表
     * @return
     */
    List<Interface> list();
}
