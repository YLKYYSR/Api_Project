package com.sparkle.start_project.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparkle.start_project.Common.BaseResponse;
import com.sparkle.start_project.Common.ErrorCode;
import com.sparkle.start_project.Common.RusltUtils;
import com.sparkle.start_project.Domain.dto.userLoginDto;
import com.sparkle.start_project.Domain.dto.userRegisterDto;
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

import static com.sparkle.start_project.constant.userConstant.USER_LOGIN_STATUS;


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
     * @param
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody userRegisterDto userRegisterDto) {
        if (userRegisterDto == null) {
            throw new BusinessException(ErrorCode.ERROR_PARAM, "参数为空");
        }
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String checkpassword= userRegisterDto.getCheckpassword();
        if (StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException(ErrorCode.ERROR_PARAM, "参数为空");
        }
        long result = userServiceImpl.userRegister(username, password, checkpassword);
        return RusltUtils.success(result);
    }

    /**
     * 用户登录
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResponse<UserVo> userLogin(@RequestBody userLoginDto userLoginDto, HttpServletRequest request){
        if (userLoginDto == null) {
            throw new BusinessException(ErrorCode.ERROR_PARAM,"参数为空");
        }
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();
        if(StringUtils.isAnyBlank(username, password)){
            throw new BusinessException(ErrorCode.ERROR_PARAM,"参数为空");
        }
        UserVo userVo = userServiceImpl.userLogin(username, password, request);
        return RusltUtils.success(userVo);

    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/current")
    @ApiOperation("获取用户当前信息")
    public BaseResponse<UserVo> currentUser(HttpServletRequest request) {
        Object o = request.getSession().getAttribute( USER_LOGIN_STATUS);
        User user = (User) o;
        if(user == null){
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }
        User user1 = userServiceImpl.getById(user);
        UserVo userVo = userServiceImpl.getUserVo(user1);
        return RusltUtils.success(userVo);
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
    @GetMapping("/select")
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
    @PostMapping("/delete")
    public BaseResponse<Boolean> userDelete(@RequestBody long id, HttpServletRequest request) {
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
