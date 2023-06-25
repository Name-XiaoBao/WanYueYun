package com.xiaobao.contoller;

import com.xiaobao.Util.File;
import com.xiaobao.Util.Json;
import com.xiaobao.service.ApiService;
import com.xiaobao.service.FileService;
import com.xiaobao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 云储存
 * @author 小暴
 */
@RestController
public class FileContoller {
    Json json=new Json();
    @Autowired
    private UserService userService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private File upload;
    @Autowired
    private FileService fileService;
    @Autowired
    private HttpServletRequest request;
    @Value("${web.upload-path}")
    public String path;
    /**
     * 上传API文件
     * @param username 挽悦云账号
     * @param Api 你的API名称
     * @param file 文件
     * @return
     */
    @PostMapping("/upload")
    public Json uploadFile(String username,String Api,@RequestParam("file") MultipartFile file) {
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
            return json;
        }
        if (apiService.Api(username,Api)==null){
            json.json(404,"API不存在",null);
            return json;
        }
        String files= upload.uploadFile(Api,file);
        json.json(200,"上传成功",files);
        return json;
    }

    /**
     * 查询API文件列表
     * @param username 挽悦云账号
     * @param Api API名称
     * @return
     */
    @RequestMapping("/file")
    public Json file(String username,String Api){
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
            return json;
        }
        if (apiService.Api(username,Api)==null){
            json.json(404,"API不存在",null);
            return json;
        }
        if (fileService.file(Api)==null){
            json.json(404,"暂无文件", null);
        }else {
            json.json(200,"查询成功", fileService.file(Api));
        }
        return json;
    }

    /**
     * 删除API文件
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param Api API名称
     * @param filename 文件名（必须要有文件后缀）
     * @return
     */
    @RequestMapping("/DelFile")
    public Json delfile(String username,String password,String Api,String filename){
        if (userService.user(username)==null){
            json.json(404,"该用户不存在",null);
            return json;
        }
        if (userService.user_pass(username,password)==null){
            json.json(403,"账号或密码错误！",null);
            return json;
        }
        if (apiService.Api(username, Api) == null) {
            json.json(404, "API不存在", null);
            return json;
        }
        if (fileService.delFile(filename) == 1) {
            java.io.File files = new java.io.File(path + Api + "/" + filename);
            if (files.delete()) {
                json.json(200, "删除成功", null);
            } else {
                json.json(403, "删除失败", null);
            }
        } else {
            json.json(403, "删除失败", null);
        }
        return json;
    }
}
