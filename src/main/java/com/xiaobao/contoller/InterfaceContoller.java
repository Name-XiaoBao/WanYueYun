package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import com.xiaobao.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接口文档查询
 * @author 小暴
 */
@RestController
public class InterfaceContoller {
    @Autowired
    private InterfaceService interfaceService;
    Json json=new Json();

    /**
     * 查询API接口文档
     * @return
     */
    @RequestMapping("/Interface")
    public Json Interface(){
        json.json(200,"查询成功",interfaceService.list());
        return json;
    }
}
