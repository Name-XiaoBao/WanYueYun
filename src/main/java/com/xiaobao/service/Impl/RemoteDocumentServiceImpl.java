package com.xiaobao.service.Impl;

import com.xiaobao.dao.RemoteDocumentDao;
import com.xiaobao.model.RemoteDocument;
import com.xiaobao.service.RemoteDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小暴
 */
@Service("remoteDocumentService")
public class RemoteDocumentServiceImpl implements RemoteDocumentService {
    @Autowired
    private RemoteDocumentDao remoteDocumentDao;

    /**
     * 创建API文档
     *
     * @param Api
     * @param Title
     * @param Content
     * @param UpdateTime
     * @return
     */
    @Override
    public int AddFile(String Api, String Title, String Content, String UpdateTime) {
        return remoteDocumentDao.AddFile(Api,Title,Content,UpdateTime);
    }

    /**
     * 根据ID查询文档
     *
     * @param id
     * @return
     */
    @Override
    public List<RemoteDocument> File(int id,String Api) {
        return remoteDocumentDao.File(id,Api);
    }

    /**
     * 查询API文档列表
     *
     * @param Api
     * @return
     */
    @Override
    public List<RemoteDocument> FileList(String Api) {
        return remoteDocumentDao.FileList(Api);
    }

    /**
     * 修改API文档
     *
     * @param id
     * @param Jurisdiction
     * @param SecretKey
     * @param title
     * @param Content
     * @param UpdateTime
     * @return
     */
    @Override
    public int UpdateFile(String Api,Integer id, Integer Jurisdiction, String SecretKey, String title, String Content, String UpdateTime) {
        return remoteDocumentDao.UpdateFile(Api,id,Jurisdiction,SecretKey,title,Content,UpdateTime);
    }

    /**
     * 删除API文档
     *
     * @param id
     * @param Api
     * @return
     */
    @Override
    public int DelFile(Integer id, String Api) {
        return remoteDocumentDao.DelFile(id,Api);
    }

    /**
     * 删除API内的所有文档
     *
     * @param Api
     * @return
     */
    @Override
    public int Del(String Api) {
        return remoteDocumentDao.Del(Api);
    }
}
