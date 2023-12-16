package com.xiaobao.service.Impl;

import com.xiaobao.dao.FileDao;
import com.xiaobao.model.File;
import com.xiaobao.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小暴
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao fileDao;
    /**
     * 添加文件直链到数据库
     *
     * @param Api
     * @param FileName
     * @param Url
     * @return
     */
    @Override
    public int addFile(String Api, String FileName, String Url) {
        return fileDao.addFile(Api, FileName, Url);
    }

    /**
     * 查询API文件列表
     *
     * @param Api
     * @return
     */
    @Override
    public List<File> file(String Api) {
        return fileDao.file(Api);
    }

    /**
     * 删除API文件
     *
     * @param filename
     * @return
     */
    @Override
    public int delFile(String filename) {
        return fileDao.delFile(filename);
    }

    /**
     * 删除API所有文件
     *
     * @param Api
     * @return
     */
    @Override
    public int AllDelFile(String Api) {
        return fileDao.AllDelFile(Api);
    }

    /**
     * 查询API是否存在该文件
     *
     * @param Api
     * @param filename
     * @return
     */
    @Override
    public Integer filename(String Api, String filename) {
        return fileDao.filename(Api, filename);
    }
}
