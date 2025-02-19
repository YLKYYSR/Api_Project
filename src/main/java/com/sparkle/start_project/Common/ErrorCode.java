package com.sparkle.start_project.Common;

import lombok.Getter;

@Getter
public enum ErrorCode {
    SUCCESS(2000,"成功",""),
    ERROR_PARAM(2001,"参数错误",""),
    NULL_PARAM(2002,"参数为空",""),
    NO_LOGIN(2003,"未登录",""),
    NO_PERMISSIONS(2004,"无权限",""),
    OPERATE_ERROR(2005,"操作失败","") ,
    SYS_ERROR(5000,"系统错误","");

    private final int code;

    private final String message;

    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
}
