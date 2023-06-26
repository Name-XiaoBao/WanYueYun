package com.xiaobao.model;

import lombok.Data;

/**
 * 卡密
 *
 * @author 小暴
 */
@Data
public class Carmi {
    /**
     * 卡密的ID
     */
    private Integer id;
    /**
     * 卡密所属API名称
     */
    private String api;
    /**
     * 卡密的类型
     * Integral,Member
     */
    private String type;
    /**
     * 卡密
     */
    private String Carmi;
    /**
     * 卡密的内容
     * 类型为 Integral 时为具体数值
     * 类型为 Member 时为天数
     */
    private String Content;
}