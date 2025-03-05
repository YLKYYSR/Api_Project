package com.sparkle.start_project.Controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparkle.api_client_sdk.Client.ApiClient;
import com.sparkle.api_client_sdk.Domain.User;
import com.sparkle.start_project.Annotation.AouthCheck;
import com.sparkle.start_project.Common.BaseResponse;
import com.sparkle.start_project.Common.ErrorCode;
import com.sparkle.start_project.Common.RusltUtils;
import com.sparkle.start_project.Constant.adminDeleteDto;
import com.sparkle.start_project.Constant.userConstant;
import com.sparkle.start_project.Domain.dto.api.adminAddApiDto;
import com.sparkle.start_project.Domain.dto.api.adminUpdateApiDto;
import com.sparkle.start_project.Domain.dto.api.apiQueryDto;
import com.sparkle.start_project.Domain.dto.api.invokeApiDto;
import com.sparkle.start_project.Domain.entity.ApiInfo;
import com.sparkle.start_project.Domain.enums.apiStatus;
import com.sparkle.start_project.Domain.vo.ApiVo;
import com.sparkle.start_project.Exception.BusinessException;
import com.sparkle.start_project.Exception.ThrowUtils;
import com.sparkle.start_project.Service.impl.UserServiceImpl;
import com.sparkle.start_project.mapper.ApiInfoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 接口信息 前端控制器
 * </p>
 *
 * @author Sparkle
 * @since 2025-02-20
 */
@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "接口管理")
public class ApiInfoController {

    @Resource
    private com.sparkle.start_project.Service.impl.ApiInfoServiceImpl apiInfoService;

    @Resource
    private ApiClient apiClient;

    @Resource
    private com.sparkle.start_project.Service.impl.ApiInfoServiceImpl apiInfoServiceImpl;
    @Resource
    private ApiInfoMapper apiInfoMapper;

    @Resource
    private UserServiceImpl userServiceImpl;


    //只允许管理员添加接口
    @Operation(summary = "添加接口")
    @PostMapping("/add")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Long> addApi(@RequestBody adminAddApiDto adminAddApiDto, HttpServletRequest request) {
        //校验参数是否合法
        if (adminAddApiDto == null) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }

        //前端传当前用户id即为userId
        //Long id = userService.getLoginUser(request).getId();
        //传参不为空,该方法有空参即为true
        boolean b = apiInfoService.adminAddDtoIsEmpty(adminAddApiDto);
        if (b) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }
        ApiInfo apiInfo = new ApiInfo();
        BeanUtils.copyProperties(adminAddApiDto, apiInfo);
        log.info("开始添加接口，参数{}", apiInfo);
        boolean save = apiInfoService.save(apiInfo);
        ThrowUtils.throwIf(!save, ErrorCode.OPERATE_ERROR);
        log.info("成功添加接口,id{}", apiInfo.getId());
        return RusltUtils.success(apiInfo.getId());
    }

    @Operation(summary = "删除接口")
    @PostMapping("/delete")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Boolean> deleteApi(@RequestBody adminDeleteDto adminDeleteDto, HttpServletRequest request) {
        //校验参数是否合法
        if (request == null || adminDeleteDto.getId() <= 0) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }
        //修改数据库
        log.info("开始删除接口，参数{}", adminDeleteDto.getId());
        boolean b = apiInfoService.removeById(adminDeleteDto.getId());
        ThrowUtils.throwIf(!b, ErrorCode.OPERATE_ERROR);
        log.info("接口删除成功");
        //返回数据库调用结果
        return RusltUtils.success(true);
    }

    @Operation(summary = "更新接口")
    @PostMapping("/update")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Boolean> updateApi(@RequestBody adminUpdateApiDto adminUpdateDto, HttpServletRequest request) {
        //校验参数是否合法
        if (adminUpdateDto == null || adminUpdateDto.getId() <= 0) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }
        adminAddApiDto adminAddApiDto = new adminAddApiDto();
        BeanUtils.copyProperties(adminUpdateDto, adminAddApiDto);
        //传参不为空
        boolean b = apiInfoService.adminAddDtoIsEmpty(adminAddApiDto);
        if (b) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }
        //修改数据库
        //返回数据库调用结果
        ApiInfo apiInfo = new ApiInfo();
        BeanUtils.copyProperties(adminUpdateDto, apiInfo);
        log.info("开始更新数据库，参数{}", apiInfo);
        boolean b1 = apiInfoServiceImpl.updateById(apiInfo);
        ThrowUtils.throwIf(!b1, ErrorCode.OPERATE_ERROR);
        log.info("数据库更新成功");
        return RusltUtils.success(true);
    }


    //管理员看到的
    @Operation(summary = "查询ApiInfo")
    @GetMapping("/selectApi")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<ApiInfo> selectApi(Long id, HttpServletRequest request) {

        //校验参数是否合法
        if (id <= 0) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }
        log.info("开始根据id查询api所有信息，参数{}", id);
        ApiInfo apiInfo = apiInfoService.getById(id);
        log.info("查询成功，结果：{}", apiInfo);
        //返回数据库调用结果
        return RusltUtils.success(apiInfo);
    }

    //用户看到的
    @Operation(summary = "查询vo")
    @GetMapping("/selectApiVo")
    public BaseResponse<ApiVo> selectApiVo(Long id, HttpServletRequest request) {
        log.info("开始根据id查询apiVo信息，参数{}", id);
        BaseResponse<ApiInfo> apiInfoBaseResponse = selectApi(id, request);
        ApiInfo date = apiInfoBaseResponse.getDate();
        ApiVo apiVo = new ApiVo();
        BeanUtils.copyProperties(date, apiVo);
        log.info("查询成功{}", apiVo);
        return RusltUtils.success(apiVo);
    }

    //分页查询
    @Operation(summary = "管理员查询all")
    @PostMapping("/pageApi")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Page<ApiInfo>> selectPageApi(@RequestBody apiQueryDto apiQueryDto, HttpServletRequest request) {
        if (apiQueryDto == null) {
            throw new BusinessException(ErrorCode.NULL_PARAM, "查询参数为空");
        }
        int pageSize = apiQueryDto.getPageSize();
        int currentPage = apiQueryDto.getCurrentPage();
        //查询分页对象
        log.info("开始分页查询,分页参数：pageSize{}，currentPage{}", pageSize, currentPage);
        Page<ApiInfo> page = apiInfoService.page(new Page<>(currentPage, pageSize), apiInfoService.queryWrapper(apiQueryDto));
        log.info("查询结果：{}", page);
        return RusltUtils.success(page);
    }

    //分页查询（用户看到的）
    @Operation(summary = "查询vo")
    @PostMapping("/pageApiVo")
    public BaseResponse<Page<ApiVo>> selectPageApiVo(@RequestBody apiQueryDto apiQueryDto, HttpServletRequest request) {
        //  第一种写法
//        if(apiQueryDto == null){
//            throw new BusinessException(ErrorCode.NULL_PARAM);
//        }
//        //获取当前分页大小
//        int pageSize = apiQueryDto.getPageSize();
//        //获取当前页号
//        int currentPage = apiQueryDto.getCurrentPage()
        //限制爬虫
        //ThrowUtils.throwIf(date.getPages() > 20, ErrorCode.OPERATE_ERROR);
//        //得到ApiInfo分页对象
//        Page<ApiInfo> page = apiInfoService.page(new Page<>(currentPage, pageSize), apiInfoService.queryWrapper(apiQueryDto));
//        //得到一个新的ApiVot分页对象，但是没有数据
//        Page<ApiVo> objectPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
//        //先将ApiInfo分页对象取出数据，通过map映射到ApiVo分页对象里去(setRecords 和 map 操作一起)
//        Page<ApiVo> r = objectPage.setRecords(page.getRecords().stream().map(a -> {
//            ApiVo apiVo = new ApiVo();
//            BeanUtils.copyProperties(a, apiVo);
//            return apiVo;
//        }).collect(Collectors.toList()));
//        return RusltUtils.success(r);
        //第二种map和setRecords先后顺序
//        if(apiQueryDto == null){
//            throw new BusinessException(ErrorCode.NULL_PARAM);
//        }
//        int pageSize = apiQueryDto.getPageSize();
//        int currentPage = apiQueryDto.getCurrentPage();
        //限制爬虫
        //ThrowUtils.throwIf(date.getPages() > 20, ErrorCode.OPERATE_ERROR);
//        IPage<ApiInfo> page = apiInfoService.page(new Page<>(pageSize, currentPage), apiInfoServiceImpl.queryWrapper(apiQueryDto));
//        //map操做
//        List<ApiVo> collect = page.getRecords()
//                .stream()
//                .map(a -> {
//                    ApiVo apiVo = new ApiVo();
//                    BeanUtils.copyProperties(a, apiVo);
//                    return apiVo;
//                })
//                .toList();
//
        //new 一个ApiVo分页对象但是没有数据
//        Page<ApiVo> pageVo = new Page<>(page.getPages(), page.getSize(), page.getTotal());
        //设置值
//        pageVo.setRecords(collect);
//        return RusltUtils.success(pageVo);
        //第三种代码复用
        BaseResponse<Page<ApiInfo>> pageBaseResponse = selectPageApi(apiQueryDto, request);
        Page<ApiInfo> date = pageBaseResponse.getDate();

        //限制爬虫
        ThrowUtils.throwIf(date.getPages() > 20, ErrorCode.OPERATE_ERROR);
        //下面操作同上(map, setRecords)
        List<ApiVo> list = date
                .getRecords()
                .stream()
                .map(apiInfo -> {
                    ApiVo apiVo = new ApiVo();
                    BeanUtils.copyProperties(apiInfo, apiVo);
                    return apiVo;
                })
                .toList();
        //new一个ApiVo分页对象容器（无数据）
        Page<ApiVo> VoPage = new Page<>(date.getCurrent(), date.getSize(), date.getTotal());
        VoPage.setRecords(list);
        return RusltUtils.success(VoPage);
    }


    @Operation(summary = "上线接口")
    @PostMapping("/online")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Boolean> OnlineApi(Long id, HttpServletRequest request) {
        //校验参数是否合法
        if (id <= 0) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }
        //接口是列表展示，没有就查不出来，不用判断该接口是否存在
        //判断接口是否可用
        User user = new User();
        user.setUsername("Sparkle");
        String nameByPost = apiClient.getNameByPost(user);
        if (StringUtils.isBlank(nameByPost)) {
            throw new BusinessException(ErrorCode.NULL_PARAM, "接口不可用");
        }
        //第一种写法(同UpdateWrapper)
        //todo
        //为什么后面指定类型就不报static上下文错误
        LambdaUpdateWrapper<ApiInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<ApiInfo>()
                .eq(ApiInfo::getId, id)
                .set(ApiInfo::getStatus, apiStatus.ONLINE.getValue());
        log.info("开始上线接口，接口id{}", id);
        int update = apiInfoMapper.update(null, lambdaUpdateWrapper);
        log.info("数据库更新条数：{}",update);
        return RusltUtils.success(update > 0);
//        //第二种写法
//        QueryWrapper<ApiInfo> queryWrapper = new QueryWrapper<ApiInfo>().eq("id", id);
//        ApiInfo apiInfo = new ApiInfo();
//        apiInfo.setStatus(apiStatus.ONLINE.getValue());
//        log.info("开始上线接口，接口id{}", id);
//        int update = apiInfoMapper.update(apiInfo, queryWrapper);
//        log.info("接口上线成功");
//        return RusltUtils.success(update ==1);
//        //第三种写法
//        ApiInfo apiInfo = apiInfoMapper.selectById(id).setStatus(apiStatus.ONLINE.getValue());
//        log.info("开始上线接口，接口id{}", id);
//        boolean b = apiInfoService.updateById(apiInfo);
//        log.info("更新结果：{}",b);
//        return RusltUtils.success(b);


    }

    @Operation(summary = "下线接口")
    @PostMapping("/logoff")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Boolean> LogoffApi(Long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }
//        log.info("设置接口为下线状态，接口id:{}", id);
//        ApiInfo apiInfo = apiInfoMapper.selectById(id).setStatus(apiStatus.LOGOFF.getValue());
//        //更新成功返回true，失败false
//        boolean b = apiInfoService.updateById(apiInfo);
//        log.info("接口下线成功");
//        return RusltUtils.success(b);
        LambdaUpdateWrapper<ApiInfo> lambdaUpdateWrapper = new LambdaUpdateWrapper<ApiInfo>()
                .eq(ApiInfo::getId, id)
                .set(ApiInfo::getStatus, 1);
        //记录更新的条数>0，没有就是等于0;
        int update = apiInfoMapper.update(null, lambdaUpdateWrapper);
        return RusltUtils.success(update > 0);
    }

    //todo
    //传url和参数
    @Operation(summary = "调用接口")
    @PostMapping("/invoke")
    //采用字符串字面量设计多参数
    public BaseResponse<Object> InvokeApi(@RequestBody invokeApiDto invokeApiDto, HttpServletRequest request) {
        //判空
        if (invokeApiDto == null || invokeApiDto.getId() <= 0) {
            throw new BusinessException(ErrorCode.NULL_PARAM, "调用方法参数为空");
        }
        //是否为删除或者下线
        ApiInfo apiInfo = apiInfoService.getById(invokeApiDto.getId());
        if (apiInfo == null) {
            throw new BusinessException(ErrorCode.NULL_PARAM, "该方法不存在");
        }
        Integer status = apiInfo.getStatus();
        //实左npe
        if (!apiStatus.ONLINE.getValue().equals(status)) {
            throw new BusinessException(ErrorCode.OPERATE_ERROR, "该接口已下线");
        }
        log.info("查看用户登录态");
        com.sparkle.start_project.Domain.entity.User loginUser = userServiceImpl.getLoginUser(request);
        String accessKey = loginUser.getAccessKey();
        String secretKey = loginUser.getSecretKey();
        log.info("获取新的ApiClient对象");
        ApiClient apiClient1 = new ApiClient(accessKey, secretKey);
        String parameter = invokeApiDto.getParameter();
        //存疑
//        Gson gson = new Gson();
//        User user = gson.fromJson(parameter, User.class);
//        //todo
//        //用url调用
//        String s = apiClient1.getNameByPost(user);
        //目前参数直接是String
        User user = new User();
        user.setUsername("asfdsaf");
        log.info("开始调用接口");
        String s = apiClient1.getNameByPost(user);
        log.info("接口调用成功，结果：{}", s);
        return RusltUtils.success(s);
    }


}
