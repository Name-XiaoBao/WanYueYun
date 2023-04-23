package com.xiaobao.model;

import lombok.Data;

/**
 * @author 小暴
 */
@Data
public class Edition {
    /**
     * ID
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 网页版本
     */
    private String webEdition;
    /**
     * 安卓版本
     */
    private String androidVersion;
    /**
     * 安卓下载地址
     */
    private String androidDownload;
    /**
     * 版本更新内容
     */
    private String versionUpdateContent;
}
