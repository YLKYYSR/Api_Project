package com.sparkle.start_project.Common;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private int code;
    private String msg;
    private T date;
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
