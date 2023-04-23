package com.xiaobao.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 小暴
 */
@Data
public class ApiUser {
    /**
     * API用户的ID
     */
    private Integer id;
    /**
     * 用户所属API
     */
    private String NameApi;
    /**
     * API的用户名
     */
    private String user;
    /**
     * API用户的密码
     */
    private String pass;
    /**
     * API用户的积分
     */
    private String Integral;
    /**
     * API用户的会员时间
     */
    private Date MembershipTime;
}
