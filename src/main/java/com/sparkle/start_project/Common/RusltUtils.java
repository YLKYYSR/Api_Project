package com.sparkle.start_project.Common;

public class RusltUtils {
    public static <T>BaseResponse<T> success(T date){
        return new BaseResponse<>(0,"ok",date);
    }

    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }
    public static BaseResponse error(int code, String message, String desc){
        return  new BaseResponse(code, message, desc);
    }
    public static BaseResponse error(ErrorCode errorCode, String message, String desc){
        return new BaseResponse(errorCode.getCode(), message, desc);
    }
}
