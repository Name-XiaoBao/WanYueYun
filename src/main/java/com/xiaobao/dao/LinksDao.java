package com.xiaobao.dao;

import com.xiaobao.model.Links;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小暴
 */
@Component("linksDao")
public interface LinksDao {
    /**
     * 查询友情链接
     * @return
     */
    List<Links> links();
}
