package com.sparkle.start_project.Exception;

import com.sparkle.start_project.Common.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    //描述信息
    private String desc;
    //状态码
    private int code;

    public BusinessException(String desc, int code, String message ) {
        super(message);
        this.desc = desc;
        this.code = code;
    }
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.desc = errorCode.getDescription();
    }
    public BusinessException(ErrorCode errorCode, String desc) {
        super(errorCode.getMessage());
        this.desc = desc;
        this.code = errorCode.getCode();
    }
}

