package com.xiaobao.contoller;

import com.xiaobao.Util.EmailUtil;
import com.xiaobao.Util.Json;
import com.xiaobao.Util.LimitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * 发送邮件
 * @author 小暴
 */
@RestController
public class EmailContoller {
    @Autowired
    private EmailUtil emailUtil;
    Json json=new Json();

    /**
     * 发送邮件（间隔三分钟一次）
     * @param email 你的邮箱账号
     * @param title 标题
     * @param content 内容
     * @return
     */
    @LimitRequest(time = 180000,count = 1)
    @RequestMapping("/email")
    public Json email(String email,String title,String content){
        try {
            emailUtil.TemplateEmail(email,title,content);
            json.json(200,"发送成功",null);
        } catch (MessagingException e) {
            e.printStackTrace();
            json.json(400,"发送失败",e);
        }
        return json;
    }
}
