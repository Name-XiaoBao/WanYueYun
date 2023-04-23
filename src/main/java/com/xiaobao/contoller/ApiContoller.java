package com.xiaobao.contoller;

import com.xiaobao.Util.File;
import com.xiaobao.Util.Json;
import com.xiaobao.dao.StartStatisticsDao;
import com.xiaobao.model.Api;
import com.xiaobao.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API管理
 * @author 小暴
 */
@RestController
public class ApiContoller {
    @Autowired
    private ApiService apiService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApiUserService apiUserService;
    @Autowired
    private RemoteDocumentService remoteDocumentService;
    @Autowired
    private StartStatisticsDao startStatisticsDao;
    @Autowired
    private FileService fileService;
    @Value("${web.upload-path}")
    public String path;
    File file= new File();
    Json json=new Json();
    /**
     * 创建API
     * @param username 用户名
     * @param password 密码
     * @param name API名称
     * @return
     */
    @RequestMapping("/addApi")
    public Json AddApi(String username,String password,String name){
        if (Ifuser(username, password)) {
            return json;
        }
        if (name.equals("")){
            json.json(400,"API名称不允许为空！",null);
        }else if(apiService.api(name)==null){
            int num =apiService.addApi(username,password,name);
            if (num==0){
                json.json(501,"创建失败",num);
            }else {
                json.json(200,"创建成功",num);
            }
        }else {
            json.json(500,"该名称已经被使用啦，换个名称试试吧~",null);
        }
        return json;
    }

    /**
     * 修改API信息
     * @param username 用户名
     * @param password 密码
     * @param id ID
     * @param name API名称
     * @return
     */
    @RequestMapping("/upApi")
    public Json upApi(String username,String password,Integer id,String name){
        if (Ifuser(username, password)) {
            return json;
        }
        int num =apiService.upApi(id,name);
        if (num==0){
            json.json(501,"修改失败",num);
            return json;
        }else {
            json.json(200,"修改成功",num);
            return json;
        }
    }

    /**
     * 查询用户API列表
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping("/ApiList")
    public Json ApiList(String username,String password){
        if (Ifuser(username, password)) {
            return json;
        }
        List<Api> list =apiService.apiList(username,password);
        if (list==null){
            json.json(404,"您还没有创建API哦~",null);
        }else {
            json.json(200,"查询成功",list);
        }
        return json;
    }

    /**
     * 删除用户API
     * @param username 用户名
     * @param password 密码
     * @param id ApiID
     * @return
     */
    @RequestMapping("/deApi")
    public Json deApi(String username,String password,int id){
        if (Ifuser(username, password)) {
            return json;
        }
        // 获取要删除的API的名称，以便于删除其它有关联的表数据
        String ApiName = apiService.ApiName(id).getName();
        // 删除API里的所有用户
        apiUserService.delapiuser(ApiName);
        // 删除API内的所有文档
        remoteDocumentService.Del(ApiName);
        // 删除API里的所有文件
        file.remove(new java.io.File(path + ApiName));
        fileService.AllDelFile(ApiName);
        // 删除启动次数API
        startStatisticsDao.del(ApiName);
        // 删除API
        int num =apiService.deapi(id);
        if (num==0){
            json.json(501,"删除失败",null);
        }else {
            json.json(200,"删除成功",null);
        }
        return json;
    }
    /**
     * 判断用户是否存在以及账号密码是否正确
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public boolean Ifuser(String username, String password) {
        if (Iu(username, password, userService, json)) {
            return true;
        }
        return false;
    }

    public static boolean Iu(String username, String password, UserService userService, Json json) {
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
            return true;
        }
        if (userService.user_pass(username,password)==null){
            json.json(501,"密码错误",null);
            return true;
        }
        return false;
    }
}
