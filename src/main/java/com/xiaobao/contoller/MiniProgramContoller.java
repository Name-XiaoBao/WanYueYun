package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import com.xiaobao.service.MiniProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiniProgramContoller {
    @Autowired
    private MiniProgramService miniProgramService;
    Json json = new Json();

    @PostMapping("/getAllMiniProgram")
    public Json getMiniProgram() {
        json.json(200, "获取成功", miniProgramService.getAllMiniProgram());
        return json;
    }
}
