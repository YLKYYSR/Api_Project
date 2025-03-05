package com.sparkle.start_project.Controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparkle.start_project.Annotation.AouthCheck;
import com.sparkle.start_project.Common.BaseResponse;
import com.sparkle.start_project.Common.ErrorCode;
import com.sparkle.start_project.Common.RusltUtils;
import com.sparkle.start_project.Constant.adminDeleteDto;
import com.sparkle.start_project.Constant.userConstant;
import com.sparkle.start_project.Domain.dto.userandinterfaceinfo.addDto;
import com.sparkle.start_project.Domain.dto.userandinterfaceinfo.queryDto;
import com.sparkle.start_project.Domain.dto.userandinterfaceinfo.updateDto;
import com.sparkle.start_project.Domain.entity.Userandinterfaceinfo;
import com.sparkle.start_project.Domain.vo.CountVo;
import com.sparkle.start_project.Exception.BusinessException;
import com.sparkle.start_project.Exception.ThrowUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户和接口调用关系表 前端控制器
 * </p>
 *
 * @author Sparkle
 * @since 2025-03-03
 */
@Slf4j
@Tag(name = "用户和接口调用次数关系接口")
@RestController
@RequestMapping("/count")
public class UserandinterfaceinfoController {
    @Resource
    private com.sparkle.start_project.service.IUserandinterfaceinfoService countService; 

    @Resource
    private com.sparkle.start_project.Service.impl.UserandinterfaceinfoServiceImpl countImpl;


    //只允许管理员增加调用次数

    @Operation(summary = "给用户添加调用次数")
    @PostMapping("/addCount")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Long> addCount(@RequestBody addDto addDto, HttpServletRequest request) {
        //校验参数是否合法
        if (addDto == null) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }

        //前端传当前用户id即为userId
        //Long id = userService.getLoginUser(request).getId();
        //传参不为空
        boolean b = countImpl.addDtoIsEmpty(addDto);
        if (b) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }

        Userandinterfaceinfo userandinterfaceinfo = new Userandinterfaceinfo();
        BeanUtils.copyProperties(addDto, userandinterfaceinfo);
        log.info("开始创建用户接口关联记录，参数: {}", JSONUtil.toJsonStr(userandinterfaceinfo));
        boolean save = countService.save(userandinterfaceinfo);
        ThrowUtils.throwIf(!save, ErrorCode.OPERATE_ERROR,"插入addDto失败");
        //返回
        log.info("成功创建用户接口关联记录，ID: {}", userandinterfaceinfo.getId());
        return RusltUtils.success(userandinterfaceinfo.getId());
    }


    //管理员删除用户和接口次数调用关系
    @Operation(summary = "删除用户和接口次数调用关系")
    @PostMapping("/deleteCount")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Boolean> deleteCount(@RequestBody adminDeleteDto adminDeleteDto, HttpServletRequest request) {
        //校验参数是否合法
        if (request == null || adminDeleteDto.getId() <= 0) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }
        //修改数据库
        log.info("开始移除用户与接口的关系，参数：{}", JSONUtil.toJsonStr(adminDeleteDto));
        boolean b = countImpl.removeById(adminDeleteDto.getId());
        ThrowUtils.throwIf(!b, ErrorCode.OPERATE_ERROR,"移除用户与接口的关系失败");
        log.info("成功移除用户与接口的关系");
        //返回数据库调用结果
        return RusltUtils.success(true);
    }

    //管理员更新用户和接口次数调用关系
    @Operation(summary = "更新用户和接口次数调用关系")
    @PostMapping("/updateCount")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Boolean> updateCount(@RequestBody updateDto updateDto, HttpServletRequest request) {
        //校验参数是否合法
        if (updateDto == null || updateDto.getId() <= 0) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }
        addDto addDto = new addDto();
        BeanUtils.copyProperties(updateDto, addDto);
        //传参不为空
        boolean b = countImpl.addDtoIsEmpty(addDto);
        if (b) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }
        //修改数据库
        //返回数据库调用结果
        Userandinterfaceinfo userandinterfaceinfo = new Userandinterfaceinfo();
        BeanUtils.copyProperties(updateDto, userandinterfaceinfo);
        log.info("开始更新用户和接口调用次数关系，参数：{}", JSONUtil.toJsonStr(updateDto));
        boolean b1 = countImpl.updateById(userandinterfaceinfo);
        ThrowUtils.throwIf(!b1, ErrorCode.OPERATE_ERROR,"更新操作失败");
        log.info(("更新操作成功"));
        return RusltUtils.success(true);
    }


    //管理员看到的
    @Operation(summary = "查询all")
    @GetMapping("/selectCount")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Userandinterfaceinfo> selectCount(Long id, HttpServletRequest request) {

        //校验参数是否合法
        if (id <= 0) {
            throw new BusinessException(ErrorCode.NULL_PARAM);
        }
        log.info("开始查询所有Userandinterfaceinfo信息，参数{}", JSONUtil.toJsonStr(id));
        Userandinterfaceinfo userandinterfaceinfo = countImpl.getById(id);
        //返回数据库调用结果
        log.info("查询结果：{}", JSONUtil.toJsonStr(userandinterfaceinfo));
        return RusltUtils.success(userandinterfaceinfo);
    }

    //用户看到的
    @Operation(summary = "查询vo")
    @GetMapping("/selectCountVo")
    public BaseResponse<CountVo> selectCountVo(Long id, HttpServletRequest request) {
        //代码复用
        BaseResponse<Userandinterfaceinfo> userandinterfaceinfoBaseResponse = selectCount(id, request);
        Userandinterfaceinfo date = userandinterfaceinfoBaseResponse.getDate();
        CountVo countVo = new CountVo();
        //todo
        //拷贝优化
        log.info("开始Endity转换为Vo");
        BeanUtils.copyProperties(date, countVo);
        log.info("转换结果{}", JSONUtil.toJsonStr(countVo));
        return RusltUtils.success(countVo);
    }

    //分页查询
    @Operation(summary = "管理员查询all")
    @PostMapping("/pageCount")
    @AouthCheck(mustCheck = userConstant.ADMIN)
    public BaseResponse<Page<Userandinterfaceinfo>> selectPageCount(@RequestBody queryDto queryDto, HttpServletRequest request) {
        if (queryDto == null) {
            throw new BusinessException(ErrorCode.NULL_PARAM, "查询参数为空");
        }
        //获取分页大小
        int pageSize = queryDto.getPageSize();
        //获取当前页号
        int currentPage = queryDto.getCurrentPage();
        log.info("开始分页查询：pageSize{}  currentPage{}", pageSize, currentPage);
        //查询分页对象
        Page<Userandinterfaceinfo> page = countService.page(new Page<>(currentPage, pageSize), countImpl.queryWrapper(queryDto));
        log.info("分页查询结果：{}", JSONUtil.toJsonStr(page));
        return RusltUtils.success(page);
    }
      //用户用不着
//    //分页查询（用户看到的）
//    @Operation(summary = "查询vo")
//    @PostMapping("/pageCountVo")
//    public BaseResponse<Page<CountVo>> selectPageCountVo(@RequestBody queryDto queryDto, HttpServletRequest request) {
//        BaseResponse<Page<Userandinterfaceinfo>> pageBaseResponse = selectPageCount(queryDto, request);
//        Page<Userandinterfaceinfo> date = pageBaseResponse.getDate();
//        //限制爬虫
//        ThrowUtils.throwIf(date.getPages() > 20, ErrorCode.OPERATE_ERROR);
//        //下面操作同上(map, setRecords)
//        List<CountVo> list = date
//                .getRecords()
//                .stream()
//                .map(countAll -> {
//                    CountVo countVo = new CountVo();
//                    BeanUtils.copyProperties(countAll, countVo);
//                    return countVo;
//                })
//                .toList();
//        //new一个ApiVo分页对象容器（无数据）
//        Page<CountVo> VoPage = new Page<>(date.getCurrent(), date.getSize(), date.getTotal());
//        VoPage.setRecords(list);
//        return RusltUtils.success(VoPage);
//    }
    
    
}
