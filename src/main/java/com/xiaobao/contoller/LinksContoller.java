package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import com.xiaobao.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 友情链接
 * @author 小暴
 */
@RestController
public class LinksContoller {
    @Autowired
    private LinksService linksService;
    Json json=new Json();

    /**
     * 友情链接
     * @return
     */
    @RequestMapping("/links")
    public Json Links(){
        json.json(200,"查询成功",linksService.links());
        return json;
    }
}
