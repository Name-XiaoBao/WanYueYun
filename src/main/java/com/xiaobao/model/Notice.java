package com.xiaobao.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 小暴
 */
@Data
public class Notice {
    /**
     * ID
     */
    private Integer id;
    /**
     * 标题
     */
    private String Title;
    /**
     * 内容
     */
    private String Content;
    /**
     * 时间
     */
    private Date ReleaseTime;
}
