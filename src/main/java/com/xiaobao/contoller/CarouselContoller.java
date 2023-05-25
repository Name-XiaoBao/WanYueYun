package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import com.xiaobao.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 轮播图
 *
 * @author 小暴
 */
@RestController
public class CarouselContoller {
    @Autowired
    private CarouselService carouselService;
    Json json = new Json();

    /**
     * 获取轮播图列表
     *
     * @return
     */
    @RequestMapping("/Carousel")
    public Json Carousel() {
        json.json(200, "查询成功", carouselService.Carousel());
        return json;
    }
}
