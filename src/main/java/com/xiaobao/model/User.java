package com.xiaobao.model;

import lombok.Data;

/**
 * @author 小暴
 */
@Data
public class User {
    /**
     * ID
     */
    private Integer id;
    /**
     * 用户名
     */
    private String user;
    /**
     * 密码
     */
    private String pass;
    /**
     * 权限
     */
    private int jurisdiction;
}
