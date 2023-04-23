package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MD5加密
 * @author 小暴
 */
@RestController
public class Md5Contoller {
    Json json=new Json();
    /**
     * Md5加密
     * @param text 内容
     * @return
     */
    @PostMapping("/Md5")
    public Json Md5(String text){
        json.json(200,"加密成功",DigestUtils.md5DigestAsHex(text.getBytes()));
        return json;
    }
}
