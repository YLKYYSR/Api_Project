package com.sparkle.start_project.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparkle.start_project.Common.BaseResponse;
import com.sparkle.start_project.Common.ErrorCode;
import com.sparkle.start_project.Common.RusltUtils;
import com.sparkle.start_project.Domain.dto.UserRegisterDto;
import com.sparkle.start_project.Domain.entity.User;
import com.sparkle.start_project.Domain.vo.UserVo;
import com.sparkle.start_project.Exception.BusinessException;
import com.sparkle.start_project.Service.impl.UserServiceImpl;
import com.sparkle.start_project.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Sparkle
 * @since 2025-01-21
 */
@Slf4j
@Api("用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userServiceImpl;
    @Resource
    private UserMapper userMapper;


    /**
     * 用户注册
     * @param userRegisterDto
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterDto userRegisterDto) {
        if (userRegisterDto == null) {
            throw new BusinessException(ErrorCode.ERROR_PARAM, "参数为空");
        }
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String checkpassword = userRegisterDto.getCheckpassword();
        if (StringUtils.isAnyBlank(username, password, checkpassword)) {
            throw new BusinessException(ErrorCode.ERROR_PARAM, "参数为空");
        }
        long result = userServiceImpl.userRegister(username, password, checkpassword);
        return RusltUtils.success(result);
    }

    /**
     * 用户登出
     * @param request
     * @return
     */
    @ApiOperation("用户登出")
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.ERROR_PARAM);
        }
        int i = userServiceImpl.userLogout(request);
        return RusltUtils.success(i);
    }

    @ApiOperation("查询用户")
    @PostMapping("/select")
    public BaseResponse<List<UserVo>> userSelect(String username, HttpServletRequest request) {
        if (!userServiceImpl.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_PERMISSIONS);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<UserVo> collect = userServiceImpl.list(queryWrapper).stream().map(user -> userServiceImpl.getUserVo(user)).collect(Collectors.toList());
        return RusltUtils.success(collect);
    }
    @ApiOperation("删除用户")
    @GetMapping("/delete")
    public BaseResponse<Boolean> userDelete(@RequestParam("id") Integer id, HttpServletRequest request) {
        if (!userServiceImpl.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_PERMISSIONS);
        }
        if (id < 0) {
            throw new BusinessException(ErrorCode.ERROR_PARAM);
        }
        boolean b = userServiceImpl.removeById(id);
        return RusltUtils.success(b);
    }
}
