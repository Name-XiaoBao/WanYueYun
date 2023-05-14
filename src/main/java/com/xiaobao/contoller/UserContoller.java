package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import com.xiaobao.Util.Uuid;
import com.xiaobao.model.User;
import com.xiaobao.service.ApiService;
import com.xiaobao.service.ThirdPartyLoginService;
import com.xiaobao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 挽悦云用户管理
 * @author 小暴
 */
@RestController
public class UserContoller {
    @Autowired
    private UserService userService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private ThirdPartyLoginService thirdPartyLoginService;
    Json json=new Json();
    Uuid uuid=new Uuid();
    /**
     * 获取session
     * @param req
     * @return
     */
    @RequestMapping("/session")
    public Json session(HttpServletRequest req){
        HttpSession session=req.getSession();
        if (session.getAttribute("username")==null){
            json.json(501,"获取失败",session.getAttribute("username"));
            return json;
        }
        User user=new User();
        user.setUser((String) session.getAttribute("username"));
        user.setPass((String) session.getAttribute("password"));
        json.json(200,"获取成功",user);
        return json;
    }
    /**
     * 用户注册
     * @param username 账号
     * @param password 密码
     * @return
     */
    @RequestMapping("/Register")
    public Json Register(String username,String password){
        if (password.equals("") || password.equals("null")) {
            json.json(403, "账号密码不允许为空！", null);
            return json;
        }
        if (userService.user(username) == null) {
            int num = userService.Register(username, password);
            if (num == 0) {
                json.json(501, "注册失败", num);
                return json;
            }
            json.json(200, "注册成功", num);
        } else {
            json.json(501, "该用户已存在", null);
        }
        return json;
    }
    /**
     * 用户登录
     * @param req
     * @param username 账号
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public Json login(HttpServletRequest req,String username,String password){
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
            return json;
        }
        if (userService.user_pass(username,password)==null){
            json.json(501,"密码错误",null);
        }else {
            String uid=uuid.uuid();
            // 判断数据库是否存在该账号
            if (thirdPartyLoginService.username(username)==null){
                thirdPartyLoginService.AddIdentification(username,uid,DigestUtils.md5DigestAsHex((uuid.uuid()+password.getBytes()).getBytes()));
            }
            HttpSession session=req.getSession();
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            json.json(200,"登录成功",thirdPartyLoginService.username(username).getIdentification());
        }
        return json;
    }

    /**
     * 注销登录
     * @param req
     */
    @RequestMapping("end")
    public void end(HttpServletRequest req){
        HttpSession session=req.getSession();
        session.removeAttribute("username");
        session.removeAttribute("password");
    }
    /**
     * 修改用户密码
     * @param username 账号
     * @param password 密码原密码
     * @param NewPassword 账号新密码
     * @return
     */
    @RequestMapping("/up_user")
    public Json upUser(String username,String password,String NewPassword){
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
        }else {
            if (userService.user_pass(username,password)==null){
                json.json(501,"密码错误",null);
            }else {
                int num =userService.upuser(username,NewPassword);
                if (num==0){
                    json.json(501,"修改失败",num);
                }else {
                    int nums=apiService.upUserApi(username,NewPassword);
                    if (nums==0){
                        json.json(501,"修改失败",num);
                    }else {
                        json.json(200,"修改成功",num+nums);
                    }
                }
            }
        }
        return json;
    }
}
