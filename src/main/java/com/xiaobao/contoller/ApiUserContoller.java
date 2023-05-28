package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import com.xiaobao.Util.LimitRequest;
import com.xiaobao.service.ApiService;
import com.xiaobao.service.ApiUserService;
import com.xiaobao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API用户管理
 * @author 小暴
 */
@RestController
@RequestMapping("/api")
public class ApiUserContoller {
    @Autowired
    private ApiService apiService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApiUserService apiUserService;
    Json json=new Json();

    /**
     * API用户注册功能
     * @param username 挽悦云账号
     * @param name API
     * @param user API用户账号
     * @param pass API用户密码
     * @return
     */
    @LimitRequest(time = 60000,count = 1)
    @RequestMapping("/Register")
    public Json Register(String username, String name ,String user,String pass){
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
        }else {
            if (apiService.Api(username,name)==null){
                json.json(404,"API不存在",null);
            }else {
                if (apiUserService.user(name,user)==null){
                    int num = apiUserService.Register(name,user,pass);
                    if (num==0){
                        json.json(300,"注册失败",num);
                    }else {
                        json.json(200,"注册成功",num);
                    }
                }else {
                    json.json(200,"该用户存在",null);
                }
            }
        }
        return json;
    }

    /**
     * API用户登录功能
     * @param username 挽悦云账号
     * @param name API名称
     * @param user API用户账号
     * @param pass API用户密码
     * @return
     */
    @RequestMapping("/login")
    public Json login(String username, String name ,String user,String pass){
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
        }else {
            if (apiService.Api(username,name)==null){
                json.json(404,"API不存在",null);
            }else {
                if (apiUserService.user(name,user)==null){
                    json.json(404,"该用户不存在",null);
                }else {
                    if (apiUserService.user_pass(user,pass)==null){
                        json.json(501,"密码错误",null);
                    }else {
                        json.json(200,"登录成功",null);
                    }
                }
            }
        }
        return json;
    }

    /**
     * 查询API用户
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param name API名称
     * @return
     */
    @RequestMapping("/UserList")
    public Json list(String username, String password, String name){
        if (IfUser(username, password, name)) {
            return json;
        }
        json.json(200,"查询成功",apiUserService.Userlist(name));
        return json;
    }

    /**
     * 查询API用户信息
     * @param username 挽悦云账号
     * @param name API
     * @param user API用户名称
     * @param pass API用户密码
     * @return
     */
    @RequestMapping("/UserInfo")
    public Json UserInfo(String username, String name ,String user,String pass){
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
        }else {
            if (apiService.Api(username,name)==null){
                json.json(404,"API不存在",null);
            }else {
                if (apiUserService.user(name,user)==null){
                    json.json(404,"该用户不存在",null);
                }else {
                    json.json(200,"查询成功",apiUserService.userInfo(name,user,pass));
                }
            }
        }
        return json;
    }

    /**
     * 修改API用户密码
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param name API
     * @param id ID
     * @param user API用户账号
     * @param pass API用户原密码
     * @param Newpass API用户新密码
     * @return
     */
    @RequestMapping("/UpPass")
    public Json UpPass(String username, String password, String name,int id,String user, String pass,String Newpass){
        if (Iu(username, password, name, id, user, pass)) {
            return json;
        }
        int num=apiUserService.uppass(id,Newpass);
        if (num==0){
            json.json(501,"修改失败",null);
            return json;
        }
        json.json(200,"修改成功",null);
        return json;
    }

    /**
     * 修改API用户积分
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param name API
     * @param id ID
     * @param user API用户账号
     * @param pass API用户密码
     * @param Integral 积分
     * @return
     */
    @RequestMapping("/upIntegral")
    public Json upIntegral(String username, String password, String name,int id,String user, String pass,String Integral){
        if (Iu(username, password, name, id, user, pass)) {
            return json;
        }
        int num=apiUserService.upIntegral(id,Integral);
        if (num==0){
            json.json(501,"修改失败",null);
            return json;
        }
        json.json(200,"修改成功",null);
        return json;
    }

    /**
     * 修改API用户会员时间
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param name API
     * @param id ID
     * @param user API用户账号
     * @param pass API用户密码
     * @param MembershipTime 会员时间，格式为yyyy-MM-dd HH:mm:ss
     * @return
     */
    @RequestMapping("/upMembershipTime")
    public Json upMembershipTime(String username, String password, String name, int id, String user, String pass, String MembershipTime){
        if (Iu(username, password, name, id, user, pass)) {
            return json;
        }
        int num=apiUserService.upMembershipTime(id,MembershipTime);
        if (num==0){
            json.json(501,"修改失败",null);
            return json;
        }
        json.json(200,"修改成功",null);
        return json;
    }

    /**
     * 删除API用户
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param name API
     * @param user API用户账号
     * @return
     */
    @RequestMapping("/deluser")
    public Json deluser(String username, String password, String name,String user){
        if (IfUser(username, password, name)) {
            return json;
        }
        if (apiUserService.user(name,user)==null){
            json.json(404,"该用户不存在",null);
            return json;
        }
        int num=apiUserService.deluser(name, user);
        if (num==0){
            json.json(501,"删除失败",null);
            return json;
        }
        json.json(200,"删除成功",null);
        return json;
    }
    /**
     * 判断用户以及API是否存在
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param name API
     * @return
     */
    public boolean IfUser(String username, String password, String name) {
        if (ApiContoller.Iu(username, password, userService, json)) {
            return true;
        }
        if (apiService.Api(username, name) == null) {
            json.json(404, "API不存在", null);
            return true;
        }
        return false;
    }

    public boolean Iu(String username, String password, String name, int id, String user, String pass) {
        if (IfUser(username, password, name)) {
            return true;
        }
        if (apiUserService.id_user_pass(id, user, pass) == null) {
            json.json(404, "该用户不存在", null);
            return true;
        }
        return false;
    }

}
