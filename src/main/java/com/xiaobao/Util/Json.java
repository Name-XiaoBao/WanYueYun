package com.xiaobao.Util;

import lombok.Data;

/**
 * @author 小暴
 */
@Data
public class Json {
    private int code;
    private String msg;
    private Object data;
    public void json(int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data = data;
    }
}
