package com.sparkle.start_project.Aop;

import com.sparkle.start_project.Annotation.AouthCheck;

import com.sparkle.start_project.Common.ErrorCode;

import com.sparkle.start_project.Domain.entity.User;
import com.sparkle.start_project.Domain.enums.userRole;
import com.sparkle.start_project.Exception.BusinessException;
import com.sparkle.start_project.Service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Aspect
@Component
public class Authentication {

    @Resource
    private UserServiceImpl userService;

    @Around("@annotation(aouth)")
    public Object AuthCheck(ProceedingJoinPoint pjp, AouthCheck aouth) throws Throwable {
        Integer s = aouth.mustCheck();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= ((ServletRequestAttributes)requestAttributes).getRequest();

        //获取当前用户
        User loginUser = userService.getLoginUser(request);
        //不许要权限，直接放行
        userRole mustrole = userRole.getRoleEnum(s);
        if(mustrole == null){
            return  pjp.proceed();
        }
        userRole userrole = userRole.getRoleEnum(loginUser.getUserRole());

        //封禁直接ban
        if(Objects.equals(userrole, userRole.BANNED)){
            throw new BusinessException(ErrorCode.NO_PERMISSIONS);
        }
        //必须有注解的管理员字段
        if(Objects.equals(mustrole, userRole.ADMIN)){
            if(!Objects.equals(userrole, userRole.ADMIN)){
                throw new BusinessException(ErrorCode.NO_PERMISSIONS);
            }
        }
        return pjp.proceed();
    }
}
