package com.sparkle.start_project.service;

import com.sparkle.start_project.Domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sparkle.start_project.Domain.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;

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
}
