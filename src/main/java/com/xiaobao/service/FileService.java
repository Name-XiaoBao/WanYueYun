package com.xiaobao.service;

import com.xiaobao.model.File;

import java.util.List;

/**
 * @author 小暴
 */
public interface FileService {
    /**
     * 添加文件直链到数据库
     * @param Api
     * @param Url
     * @return
     */
    int addFile(String Api,String Url);

    /**
     * 查询API文件列表
     * @param Api
     * @return
     */
    List<File> file(String Api);

    /**
     * 删除API文件
     * @param filename
     * @return
     */
    int delFile(String filename);
    /**
     * 删除API所有文件
     * @param Api
     * @return
     */
    int AllDelFile(String Api);
}
