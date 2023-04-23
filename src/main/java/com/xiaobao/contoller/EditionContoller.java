package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import com.xiaobao.service.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 版本信息查询
 * @author 小暴
 */
@RestController
public class EditionContoller {
    @Autowired
    private EditionService editionService;
    Json json=new Json();

    /**
     * 查询版本信息
     * @return
     */
    @RequestMapping("/Edition")
    public Json Edition(){
        json.json(200,"查询成功",editionService.list());
        return json;
    }
}
