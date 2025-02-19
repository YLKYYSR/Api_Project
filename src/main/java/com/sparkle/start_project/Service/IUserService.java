package com.sparkle.start_project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparkle.start_project.Domain.dto.userQueryDto;
import com.sparkle.start_project.Domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sparkle.start_project.Domain.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Queue;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sparkle
 * @since 2025-01-23
 */
public interface IUserService extends IService<User> {
    long userRegister(String username, String password, String checkPassword);

    UserVo userLogin(String username, String password, HttpServletRequest request);

    int userLogout(HttpServletRequest request);

    boolean isAdmin(HttpServletRequest request);

    UserVo getUserVo(User user);

    List<UserVo> getUserVoList(List<User> userList);

    QueryWrapper<User> getUserVoQueue(userQueryDto userQueryDto);

    User getLoginUser(HttpServletRequest request);

}
