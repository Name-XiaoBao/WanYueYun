package com.xiaobao.model;

import lombok.Data;

/**
 * @author 小暴
 */
@Data
public class File {
    /**
     * id
     */
    private Integer id;
    /**
     * API
     */
    private String Api;
    /**
     * 文件直链
     */
    private String Url;
    /**
     * 文件名
     */
    private String FileName;
}
