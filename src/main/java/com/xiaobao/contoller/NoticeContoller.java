package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import com.xiaobao.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公告查询
 * @author 小暴
 */
@RestController
public class NoticeContoller {
    @Autowired
    private NoticeService noticeService;
    Json json=new Json();

    /**
     * 查询公告
     * @return
     */
    @RequestMapping("/Notice")
    public Json Notice(){
        json.json(200,"查询成功",noticeService.list());
        return json;
    }
}
