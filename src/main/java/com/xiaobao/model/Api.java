package com.xiaobao.model;

import lombok.Data;

/**
 * @author 小暴
 */
@Data
public class Api {
    /**
     * API的ID
     */
    private Integer id;
    /**
     * API所属的用户
     */
    private String user;
    /**
     * API所属用户的密码
     */
    private String pass;
    /**
     * API名称
     */
    private String name;
}
