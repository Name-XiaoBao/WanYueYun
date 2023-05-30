package com.xiaobao.Util;

import com.xiaobao.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 小暴
 */
@Component
public class File {
    @Value("${web.upload-path}")
    public String path;
    @Value("${WanYueYun.url}")
    public String url;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FileService fileService;
    Uuid uuid = new Uuid();

    /**
     * 上传文件并保存文件直链到数据库
     *
     * @param file 要上传的文件
     * @return 文件直链
     */
    public String uploadFile(String Api,MultipartFile file) {
        // 生成随机文件名
        String fileName = uuid.uuid();
        // 获取文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 拼接文件名和后缀名
        fileName += suffix;
        // 文件存储路径
        String filePath = path + Api + "/" + fileName;
        // 创建文件对象
        java.io.File dest = new java.io.File(filePath);
        try {
            // 判断文件夹是否存在，不存在则创建文件夹
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
                // 保存文件
                file.transferTo(dest);
                String fileUrl;
                if (".mp4".equals(suffix)) {
                    // 文件直链
                    fileUrl = url + "file/" + Api + "/" + fileName;
                    // 将文件直链保存到数据库
                } else {
                    // 文件直链
                    fileUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + Api + "/" + fileName;
                    // 将文件直链保存到数据库
                }
                fileService.addFile(Api, fileName, fileUrl);
                return fileUrl;
            }else {
                // 保存文件
                file.transferTo(dest);
                String fileUrl;
                if (".mp4".equals(suffix)) {
                    // 文件直链
                    fileUrl = url + "file/" + Api + "/" + fileName;
                    // 将文件直链保存到数据库
                } else {
                    // 文件直链
                    fileUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/file/" + Api + "/" + fileName;
                    // 将文件直链保存到数据库
                }
                fileService.addFile(Api, fileName, fileUrl);
                return fileUrl;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 删除指定文件夹下的全部内容
     * @param file
     */
    public void remove(java.io.File file) {
        java.io.File File = new java.io.File(file.toURI());
        //将file子目录及子文件放进文件数组
        java.io.File[] files = file.listFiles();
        //如果包含文件进行删除操作
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                //删除子文件
                if (files[i].isFile()) {
                    files[i].delete();
                    //通过递归方法删除子目录的文件
                } else if (files[i].isDirectory()) {
                    remove(files[i]);
                }
                //删除子目录
                files[i].delete();
            }
            File.delete();
        }
    }
}
