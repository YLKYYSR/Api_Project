package com.sparkle.start_project.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparkle.start_project.Annotation.AouthCheck;
import com.sparkle.start_project.Common.BaseResponse;
import com.sparkle.start_project.Common.ErrorCode;
import com.sparkle.start_project.Common.RusltUtils;
import com.sparkle.start_project.Constant.userConstant;
import com.sparkle.start_project.Domain.dto.*;
import com.sparkle.start_project.Domain.entity.User;
import com.sparkle.start_project.Domain.vo.UserVo;
import com.sparkle.start_project.Exception.BusinessException;
import com.sparkle.start_project.Exception.ThrowUtils;
import com.sparkle.start_project.Service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
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
//局部解决跨域问题
//@CrossOrigin(origins = {"http://localhost:5173"}, allowCredentials = "true")
public class UserController {

    @Resource
    private UserServiceImpl userServiceImpl;

    private final String Salt = "Sparkle";


    /**
     * 用户注册
     *
     * @param
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody userRegisterDto userRegisterDto) {
        if (userRegisterDto == null) {
            throw new BusinessException(ErrorCode.ERROR_PARAM, "参数为空");
        }
        String username = userRegisterDto.getUserName();
        String password = userRegisterDto.getPassword();
        String checkpassword = userRegisterDto.getCheckpassword();
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
    @PostMapping(value = "/login", produces = "application/json")
    public BaseResponse<UserVo> userLogin(@RequestBody userLoginDto userLoginDto, HttpServletRequest request) {
        if (userLoginDto == null) {
            throw new BusinessException(ErrorCode.ERROR_PARAM, "参数为空");
        }
        String username = userLoginDto.getUserName();
        String password = userLoginDto.getPassword();
        if (StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException(ErrorCode.ERROR_PARAM, "参数为空");
        }
        UserVo userVo = userServiceImpl.userLogin(username, password, request);
        return RusltUtils.success(userVo);
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/current")
    @ApiOperation("获取用户当前信息")
    public BaseResponse<UserVo> getLoginUserVo(HttpServletRequest request) {
        User Vo = userServiceImpl.getLoginUser(request);
        UserVo userVo = userServiceImpl.getUserVo(Vo);
        return RusltUtils.success(userVo);
    }

    /**
     * 用户登出
     *
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

    @ApiOperation("增加用户")
    @PostMapping("/add")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Long> userAdd(@RequestBody adminAddDto adminAddDto, HttpServletRequest request) {
        if (adminAddDto == null) {
            throw new BusinessException(ErrorCode.ERROR_PARAM);
        }
        User user = new User();
        BeanUtils.copyProperties(adminAddDto, user);//属性拷贝
        String defaultPassword = "123456789";
        String s = DigestUtils.md5DigestAsHex((Salt + defaultPassword).getBytes());
        user.setUserPassword(s);
//        int insert = userMapper.insert(user);
//        if(insert== 0){
//            throw new BusinessException(ErrorCode.OPERATE_ERROR);
//        }
        boolean save = userServiceImpl.save(user);
        ThrowUtils.throwIf(!save, ErrorCode.OPERATE_ERROR);
        return RusltUtils.success(user.getId());
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
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Boolean> userDelete(@RequestBody adminDeleteDto adminDeleteDto, HttpServletRequest request) {
        if (adminDeleteDto == null) {
            throw new BusinessException(ErrorCode.ERROR_PARAM);
        }
        Long id = adminDeleteDto.getId();
        if (id < 0) {
            throw new BusinessException(ErrorCode.ERROR_PARAM);
        }
        boolean b = userServiceImpl.removeById(id);
        return RusltUtils.success(b);
    }

    @ApiOperation("更新用户")
    @PostMapping("/update/user")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Boolean> userUpdate(@RequestBody adminUpdateDto adminUpdateDto, HttpServletRequest request) {
        if (adminUpdateDto == null || adminUpdateDto.getId() <= 0) {
            throw new BusinessException(ErrorCode.ERROR_PARAM);
        }
        User user = new User();
        BeanUtils.copyProperties(adminUpdateDto, user);
        boolean b = userServiceImpl.updateById(user);
        ThrowUtils.throwIf(!b, ErrorCode.OPERATE_ERROR);
        return RusltUtils.success(true);
    }

    @ApiOperation("更新用户")
    @PostMapping("/update/userVo")
    public BaseResponse<Boolean> userVoUpdate(@RequestBody userUpdateDto userUpdateDto, HttpServletRequest request) {
        if (userUpdateDto == null) {
            throw new BusinessException(ErrorCode.ERROR_PARAM);
        }
        User user = new User();
        BeanUtils.copyProperties(userUpdateDto, user);
        User loginUser = userServiceImpl.getLoginUser(request);
        user.setId(loginUser.getId());
        boolean b = userServiceImpl.updateById(user);
        ThrowUtils.throwIf(!b, ErrorCode.OPERATE_ERROR);
        return RusltUtils.success(true);//idea优化的
    }

    @ApiOperation("根据id获取user")
    @GetMapping("/getuser")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<User> getUserById(Long id,HttpServletRequest request) {
        if (id <=0) {
            throw new BusinessException(ErrorCode.ERROR_PARAM);
        }
        User user = userServiceImpl.getById(id);
        return RusltUtils.success(user);
    }

    @ApiOperation("根据id获取userVo")
    @GetMapping("/getuservo")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<UserVo> getUserVoById(Long id,HttpServletRequest request) {
//        if (id <=0) {
//            throw new BusinessException(ErrorCode.ERROR_PARAM);
//        }
//        User user = userServiceImpl.getById(id);
//        UserVo userVo = userServiceImpl.getUserVo(user);
//        return RusltUtils.success(userVo);
        BaseResponse<User> userResponse = getUserById(id, request);//上面的方法复用
        User user = userResponse.getDate();
        return RusltUtils.success(userServiceImpl.getUserVo(user));
    }


    //需要管理员权限
    @ApiOperation("分页获取user")
    @GetMapping("/page")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    BaseResponse<Page<User>> getUserPage(@RequestBody userQueryDto userQueryDto, HttpServletRequest request) {
        if (userQueryDto == null || userQueryDto.getId() <= 0) {
            throw new BusinessException(ErrorCode.ERROR_PARAM);
        }
        Integer userRole = userQueryDto.getUserRole();
        int currentPage = userQueryDto.getCurrentPage();
        Page<User> page = userServiceImpl.page(new Page<>(currentPage, userRole), userServiceImpl.getUserVoQueue(userQueryDto));
        return RusltUtils.success(page);
    }

    @ApiOperation("分页获取Vo用户")
    @GetMapping("/page/vo")
    BaseResponse<Page<UserVo>> getPageUserVo(@RequestBody userQueryDto userQueryDto, HttpServletRequest request) {
        //判空
        if (userQueryDto == null) {
            throw new BusinessException(ErrorCode.ERROR_PARAM);
        }
        //获取页号和分页大小
        int pageSize = userQueryDto.getPageSize();
        int currentPage = userQueryDto.getCurrentPage();
        //查询当前用户信息
        Page<User> page = userServiceImpl.page(new Page<>(currentPage, pageSize), userServiceImpl.getUserVoQueue(userQueryDto));
        //将Page<User>转换为Page<UserVo>
        List<UserVo> userVos = page.getRecords().
                stream().
                map(user -> userServiceImpl.getUserVo(user)).
                toList();
        //创建UserVo分页对象
        Page<UserVo> userVoPage = new Page<>(currentPage, pageSize, page.getTotal());
        userVoPage.setRecords(userVos);
        return RusltUtils.success(userVoPage);

    }
}
