package com.sparkle.start_project.Exception;

import com.sparkle.start_project.Common.BaseResponse;
import com.sparkle.start_project.Common.ErrorCode;
import com.sparkle.start_project.Common.RusltUtils;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Snife4j的注解
@Hidden
@Api("全局异常拦截器")
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessGlobalException(BusinessException e) {
        log.error("businessGlobalException: {}",e.getDesc(),e);
        return RusltUtils.error(e.getCode(), e.getDesc(), e.getMessage());
    }

    @ExceptionHandler
    public BaseResponse<?> runtimeGlobalException(RuntimeException e) {
        log.error("runtimeGlobalException: {}",e.getMessage(),e);
        return RusltUtils.error(ErrorCode.SYS_ERROR, e.getMessage(),"");
    }
}
