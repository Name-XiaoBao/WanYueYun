package com.xiaobao.model;

import lombok.Data;

/**
 * @author 小暴
 */
@Data
public class Message {
    private Integer id;
    private String api;
    private String user;
    private String message;
    private String send_msg;
}
