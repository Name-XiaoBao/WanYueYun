package com.xiaobao.dao;

import com.xiaobao.model.RemoteDocument;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小暴
 */
@Component("remoteDocumentDao")
public interface RemoteDocumentDao {
    /**
     * 创建API文档
     * @param id
     * @param Api
     * @param Title
     * @param Content
     * @param UpdateTime
     * @return
     */
    int AddFile(String Api, String Title, String Content, String UpdateTime);

    /**
     * 根据ID查询文档
     *
     * @param id
     * @return
     */
    List<RemoteDocument> File(int id,String Api);

    /**
     * 查询API文档列表
     * @param Api
     * @return
     */
    List<RemoteDocument> FileList(String Api);

    /**
     * 修改API文档
     * @param id
     * @param Jurisdiction
     * @param SecretKey
     * @param title
     * @param Content
     * @param UpdateTime
     * @return
     */
    int UpdateFile(String Api,Integer id, Integer Jurisdiction, String SecretKey, String title, String Content, String UpdateTime);

    /**
     * 删除API文档
     * @param id
     * @param Api
     * @return
     */
    int DelFile(Integer id,String Api);

    /**
     * 删除API内的所有文档
     * @param Api
     * @return
     */
    int Del(String Api);
}
