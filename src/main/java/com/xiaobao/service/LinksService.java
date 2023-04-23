package com.xiaobao.service;

import com.xiaobao.model.Links;

import java.util.List;

/**
 * @author 小暴
 */
public interface LinksService {
    /**
     * 查询友情链接
     * @return
     */
    List<Links> links();
}
