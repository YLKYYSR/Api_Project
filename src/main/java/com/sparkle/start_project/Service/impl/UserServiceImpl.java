package com.sparkle.start_project.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sparkle.start_project.Domain.entity.User;
import com.sparkle.start_project.Domain.vo.UserVo;
import com.sparkle.start_project.mapper.UserMapper;
import com.sparkle.start_project.service.IUserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sparkle.start_project.constant.userConstant.ADMIN_ROLE;
import static com.sparkle.start_project.constant.userConstant.USER_LOGIN_STATUS;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sparkle
 * @since 2025-01-23
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Resource
    private UserMapper userMapper;


    private final String Salt = "Sparkle";


    /**
     * 用户注册
     * @param username
     * @param password
     * @param checkPassword
     * @return
     */
    @Override
    public  long userRegister(String username, String password, String checkPassword){
        //校验
        if(StringUtils.isAnyBlank(username,password,checkPassword)){
            return -1;
        }
        if(username.length()<4){
            return -1;
        }
        if(password.length()<8 || checkPassword.length()<8){
            return -1;
        }
        //不能包含特殊字符
        String s = "^[a-zA-Z0-9_\\-\\s]+$";
        Matcher matcher = Pattern.compile(s).matcher(username);
        if(!matcher.find()){
            return -1;
        }
        //账户不能重复
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Long l = userMapper.selectCount(wrapper);
        if(l>0){
            return -1;
        }
        //密码必须保持一致
        if(!password.equals(checkPassword)){
            return -1;
        }
        //密码加密
        String savePassword = DigestUtils.md5DigestAsHex((password +Salt).getBytes());

        User user = new User();
        user.setUserName(username);
        user.setUserPassword(savePassword);
        this.save(user);

        return user.getId();
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public UserVo userLogin(String username, String password, HttpServletRequest request){
        if(StringUtils.isAnyBlank(username,password)){
            return null;
        }
        if(username.length()<4){
            return null;
        }
        if(password.length()<8){
            return null;
        }
        //不能包含特殊字符
        String s = "^[a-zA-Z0-9_\\-\\s]+$";
        Matcher matcher = Pattern.compile(s).matcher(username);
        if(!matcher.find()){
            return null;
        }
        //密码加密
        String savePassword = DigestUtils.md5DigestAsHex((password + Salt).getBytes());

        //查询用户是否存在
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .eq("username", username)
                .eq("userPassword", savePassword);
        User user = userMapper.selectOne(wrapper);
        if(user==null){
            return null;
        }
        //判断用户是否删除
        if(user.getIsDelete() == 1){
            return null;
        }
        //用户脱敏
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        //记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATUS, user);
        return userVo;
    }

    //移除登录态
    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATUS);
        return 0;
    }

    //鉴权
    @Override
    public boolean isAdmin(HttpServletRequest request) {
        Object object = request.getSession().getAttribute(USER_LOGIN_STATUS);
        User user= (User)object;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }

    /**
     * 获取UserVo实体
     * @param user
     * @return
     */
    @Override
    public UserVo getUserVo(User user) {
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUserName(user.getUserName());
        userVo.setEmail(user.getEmail());
        userVo.setIsDelete(user.getIsDelete());
        userVo.setUserRole(user.getUserRole());
        userVo.setUserUrl(user.getUserUrl());
        userVo.setGender(user.getGender());
        return userVo;
    }
}
