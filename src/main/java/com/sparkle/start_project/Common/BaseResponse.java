package com.sparkle.start_project.Common;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {
    //状态码
    private int code;
    //消息
    private String msg;
    //数据
    private T date;
    //描述
    private String desc;

    public BaseResponse(int code, String msg, T date, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
        this.date = date;
    }
    public BaseResponse(int code, T date) {
        this(code, "", date, "");
    }
    public BaseResponse(int code, String msg, T date) {
        this(code, msg, date, "");
    }
    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage(),null,errorCode.getDescription());
    }
}
