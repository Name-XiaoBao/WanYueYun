package com.xiaobao.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 小暴
 */
@Data
public class RemoteDocument {
    /**
     * ID
     */
    private Integer id;
    /**
     * 文档所属API
     */
    private String Api;
    /**
     * 文档的权限
     */
    private Integer Jurisdiction;
    /**
     * 文档的访问密钥
     */
    private String SecretKey;
    /**
     * 文档的标题
     */
    private String Title;
    /**
     * 文档的内容
     */
    private String Content;
    /**
     * 文档的更新时间
     */
    private Date UpdateTime;
}
