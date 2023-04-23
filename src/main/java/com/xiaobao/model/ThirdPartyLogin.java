package com.xiaobao.model;

import lombok.Data;

/**
 * @author 小暴
 */
@Data
public class ThirdPartyLogin {
    /**
     * id
     */
    private Integer id;
    /**
     * 挽悦云账号
     */
    private String username;
    /**
     * 通过登录接口获取的标识
     */
    private String Identification;
    /**
     * Key
     */
    private String UserKey;
}
