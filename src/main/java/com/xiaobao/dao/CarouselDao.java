package com.xiaobao.dao;

import com.xiaobao.model.Carousel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小暴
 */
@Component("carouselDao")
public interface CarouselDao {
    /**
     * 获取轮播图列表
     *
     * @return
     */
    List<Carousel> Carousel();
}
