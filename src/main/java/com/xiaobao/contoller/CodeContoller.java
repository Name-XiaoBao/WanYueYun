package com.xiaobao.contoller;

import com.xiaobao.Util.EmailUtil;
import com.xiaobao.Util.Json;
import com.xiaobao.Util.LimitRequest;
import com.xiaobao.Util.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * 发送邮箱验证码（间隔1分钟一次）
 * @author 小暴
 */
@RestController
public class CodeContoller {
    @Autowired
    private EmailUtil emailUtil;
    Json json=new Json();
    VerificationCode Vc=new VerificationCode();
    /**
     * 发送验证码（间隔1分钟一次）
     * @param username 邮箱账号
     * @return
     */
    @LimitRequest(time = 60000,count = 1)
    @PostMapping("/VerificationCode")
    public Json sendAuthCodeEmail(String username) {
        //随机验证码
        String code=Vc.generateVerifyCode(6);
        try {
            emailUtil.TemplateCodeEmail(username,"挽悦云","您的验证码是："+code);
            // 没有出现异常，正常发送，返回true
            json.json(200,"发送成功", DigestUtils.md5DigestAsHex(code.getBytes()));
            return json;
        } catch (MessagingException e) {
            // 发送过程中，发生错误，打印错误信息，返回false
            e.printStackTrace();
            json.json(501,"发送失败",e);
            return json;
        }
    }
}
