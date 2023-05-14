package com.xiaobao.service.Impl;

import com.xiaobao.dao.CarouselDao;
import com.xiaobao.model.Carousel;
import com.xiaobao.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小暴
 */
@Service("carouselService")
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselDao carouselDao;

    /**
     * 获取轮播图列表
     *
     * @return
     */
    @Override
    public List<Carousel> Carousel() {
        return carouselDao.Carousel();
    }
}
