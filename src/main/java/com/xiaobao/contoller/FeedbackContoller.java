package com.xiaobao.contoller;

import com.xiaobao.Util.EmailUtil;
import com.xiaobao.Util.Json;
import com.xiaobao.Util.LimitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * 用户反馈（间隔三分钟一次）
 * @author 小暴
 */
@RestController
public class FeedbackContoller {
    @Autowired
    private EmailUtil emailUtil;
    Json json=new Json();

    /**
     * API用户反馈
     * @param email 你的QQ邮箱账号
     * @param Api 你的API名称
     * @param Content 反馈内容
     * @param ContactInformation 联系方式
     * @return
     */
    @LimitRequest(time = 180000,count = 1)
    @RequestMapping("/UserFeedback")
    public Json UserFeedback(String email,String Api,String Content,String ContactInformation){
        try {
            emailUtil.TemplateFeedbackEmail(email,Api,Content+"，联系方式："+ContactInformation);
            json.json(200,"发送成功",null);
            return json;
        } catch (MessagingException e) {
            json.json(501,"发送失败",e);
            return json;
        }
    }
    /**
     * 向挽悦云官方反馈
     * @param Content 内容
     * @param ContactInformation 联系方式
     * @return
     */
    @LimitRequest(time = 180000,count = 1)
    @RequestMapping("/Feedback")
    public Json Feedback(String Content, String ContactInformation) {
        try {
            emailUtil.TemplateFeedbackEmail("1753147761@qq.com","挽悦云",Content+"，联系方式："+ContactInformation);
            json.json(200,"发送成功",null);
            return json;
        } catch (MessagingException e) {
            json.json(501,"发送失败",e);
            return json;
        }
    }
}
