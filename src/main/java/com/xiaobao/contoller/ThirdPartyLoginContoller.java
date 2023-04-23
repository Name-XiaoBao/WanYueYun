package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import com.xiaobao.service.ThirdPartyLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第三方调用挽悦云账号登录
 * @author 小暴
 */
@RestController
public class ThirdPartyLoginContoller {
    @Autowired
    public ThirdPartyLoginService thirdPartyLoginService;
    Json json=new Json();

    /**
     * 第三方调用挽悦云账号登录
     * @param username 挽悦云账号
     * @param Identification 登录后获取的标识
     * @return
     */
    @PostMapping("/ThirdPartyLogin")
    public Json ThirdPartyLogin(String username,String Identification){
        if (thirdPartyLoginService.username_Identification(username,Identification)==null){
            json.json(400,"参数错误，请检查账号标识是否正确",null);
        }else {
            json.json(200,"登录成功！",thirdPartyLoginService.username_Identification(username,Identification));
        }
        return json;
    }
}
