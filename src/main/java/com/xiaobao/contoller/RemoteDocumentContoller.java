package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import com.xiaobao.model.RemoteDocument;
import com.xiaobao.service.ApiService;
import com.xiaobao.service.RemoteDocumentService;
import com.xiaobao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * API文档
 * @author 小暴
 */
@RestController
@RequestMapping("/api")
public class RemoteDocumentContoller {
    @Autowired
    private RemoteDocumentService remoteDocumentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApiService apiService;
    Json json=new Json();
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 创建API文档
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param Api API
     * @param Title 文档标题
     * @param Content 文档内容
     * @return
     */
    @RequestMapping("/AddFile")
    public Json AddFile(String username,String password,String Api,String Title,String Content){
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
        }else {
            if (userService.user_pass(username,password)==null){
                json.json(400,"密码错误",null);
            }else {
                if (apiService.Api(username,Api)==null){
                    json.json(404,"API不存在",null);
                }else {
                    if (Title.equals("")){
                        json.json(400,"标题不允许为空！",null);
                    }else if (Content.equals("")){
                        json.json(400,"内容不允许为空！",null);
                    }else if (remoteDocumentService.AddFile(Api,Title,Content,formatter.format(date))==1){
                        json.json(200,"创建成功",null);
                    }else {
                        json.json(400,"创建失败",null);
                    }
                }
            }
        }
        return json;
    }
    /**
     * 查询用户API文档信息
     * @param Api API
     * @param id ID
     * @param SecretKey Key文档密码
     * @return
     */
    @RequestMapping ("/RemoteDocument")
    public Json RemoteDocument(String Api,int id,String SecretKey){
        if (apiService.api(Api)==null){
            json.json(404,"API不存在",null);
        }else {
            try {
                List<RemoteDocument> list=remoteDocumentService.File(id,Api);
                if (list.get(0).getJurisdiction()==0){
                    json.json(200,"获取成功",list);
                }else{
                    if (SecretKey.equals(list.get(0).getSecretKey())){
                        json.json(200,"获取成功",list);
                    }else {
                        json.json(400,"请输入正确的密钥",null);
                    }
                }
            }catch (Exception e){
                if (e.getCause()==null){
                    json.json(404,"文档不存在",e);
                }else {
                    json.json(500,"内部错误，请联系开发者",e);
                }
            }
        }
        return json;
    }

    /**
     * 查询API文档列表
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param Api Api
     * @return
     */
    @RequestMapping("/FileList")
    public Json FileList(String username,String password,String Api){
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
        }else {
            if (userService.user_pass(username,password)==null){
                json.json(400,"密码错误",null);
            }else {
                if (apiService.Api(username,Api)==null){
                    json.json(404,"API不存在",null);
                }else {
                    json.json(200,"查询成功",remoteDocumentService.FileList(Api));
                }
            }
        }
        return json;
    }

    /**
     * Api文档修改
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param Api APi
     * @param id ID
     * @param Jurisdiction 权限，0是开放，1是私密，私密后必须有密码才能访问
     * @param SecretKey 文档密码
     * @param title 文档标题
     * @param Content 文档内容
     * @return
     */
    @RequestMapping("/UpdateFile")
    public Json UpdateFile(String username,String password,String Api, Integer id,Integer Jurisdiction,String SecretKey, String title, String Content){
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
        }else {
            if (userService.user_pass(username,password)==null){
                json.json(400,"密码错误",null);
            }else {
                if (apiService.Api(username,Api)==null){
                    json.json(404,"API不存在",null);
                }else {
                    int num=remoteDocumentService.UpdateFile(Api,id,Jurisdiction,SecretKey,title,Content,formatter.format(date));
                    if (num==1){
                        json.json(200,"修改成功",null);
                    }else {
                        json.json(400,"修改失败",null);
                    }
                }

            }
        }
        return json;
    }

    /**
     * API文档删除
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param Api API
     * @param id ID
     * @return
     */
    @RequestMapping("/DelFile")
    public Json DelFile(String username,String password,String Api,Integer id){
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
        }else {
            if (userService.user_pass(username,password)==null){
                json.json(400,"密码错误",null);
            }else {
                if (apiService.Api(username,Api)==null){
                    json.json(404,"API不存在",null);
                }else {
                    int num=remoteDocumentService.DelFile(id,Api);
                    if (num==1){
                        json.json(200,"删除成功",null);
                    }else {
                        json.json(400,"删除失败",null);
                    }
                }
            }
        }
        return json;
    }
}
