package com.sparkle.start_project.Exception;

import com.sparkle.start_project.Common.ErrorCode;

/**
 * 抛异常工具类
 */
public class ThrowUtils {
    /**
     * 条件不成立就抛异常
     */
    public static void throwIf(boolean condition ,RuntimeException e) {
        if(condition){
            throw e;
        }
    }
    /**
     * 条件不成立就抛异常
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }
    /**
     * 条件不成立就抛异常
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String desc) {
        throwIf(condition,new BusinessException(errorCode, desc));
    }
}
