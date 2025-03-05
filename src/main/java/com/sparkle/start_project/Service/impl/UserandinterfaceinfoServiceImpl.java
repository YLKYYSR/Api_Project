package com.sparkle.start_project.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sparkle.start_project.Common.ErrorCode;
import com.sparkle.start_project.Constant.sortFields;
import com.sparkle.start_project.Domain.dto.userandinterfaceinfo.addDto;
import com.sparkle.start_project.Domain.dto.userandinterfaceinfo.queryDto;
import com.sparkle.start_project.Domain.entity.Userandinterfaceinfo;
import com.sparkle.start_project.Exception.BusinessException;
import com.sparkle.start_project.Utils.SqlUtils;
import com.sparkle.start_project.mapper.UserandinterfaceinfoMapper;
import com.sparkle.start_project.service.IUserandinterfaceinfoService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <p>
 * 用户和接口调用关系表 服务实现类
 * </p>
 *
 * @author Sparkle
 * @since 2025-03-03
 */
@Service
public class UserandinterfaceinfoServiceImpl extends ServiceImpl<UserandinterfaceinfoMapper, Userandinterfaceinfo> implements IUserandinterfaceinfoService {
    //Long Integer 可以用Objects.isNull判断
    //String 可以用StringUtils.isAnyBlank(...)
    @Override
    public boolean addDtoIsEmpty(addDto dto) {
        if(dto == null ){
            return false;
        }
        Long apiId = dto.getApiId();
        Integer status = dto.getStatus();
        Integer remainderNum = dto.getRemainderNum();
        Long userId = dto.getUserId();
        Integer totalNum = dto.getTotalNum();
        //第一种写法
        //有空字符即为false
        //return (Objects.isNull(remainderNum)||Objects.isNull(apiId) || Objects.isNull(status) || Objects.isNull(userId) || Objects.isNull(totalNum));


        //第二种写法
        //有空字符即返回true
        return Stream.of(apiId, status, remainderNum, userId, totalNum).anyMatch(Objects::isNull);



    }

    @Override
    public QueryWrapper<Userandinterfaceinfo> queryWrapper(queryDto dto) {

        if(dto == null || dto.getPageSize() <= 0|| dto.getCurrentPage() <= 0 ){
            throw new BusinessException(ErrorCode.ERROR_PARAM,"参数错误");
        }
        //第一种写法
//        Long id = dto.getId();
//        Long userId = dto.getUserId();
//        Long apiId = dto.getApiId();
//        Integer isDelete = dto.getIsDelete();
//        Integer status = dto.getStatus();
//        Integer totalNum = dto.getTotalNum();
//        Integer remainderNum = dto.getRemainderNum();
//        String pageSortFields = dto.getPageSortFields();
//        String pageSortOrder = dto.getPageSortOrder();
//        QueryWrapper<Userandinterfaceinfo> userandinterfaceinfoQueryWrapper = new QueryWrapper<>();
//        //构造条件为true才执行查询操作
//        userandinterfaceinfoQueryWrapper.eq(!Objects.isNull(id),"id",id);
//        userandinterfaceinfoQueryWrapper.eq(!Objects.isNull(userId),"userId",userId);
//        userandinterfaceinfoQueryWrapper.eq(!Objects.isNull(apiId),"apiId",apiId);
//        userandinterfaceinfoQueryWrapper.eq(!Objects.isNull(totalNum),"totalNum",totalNum);
//        userandinterfaceinfoQueryWrapper.eq(!Objects.isNull(isDelete),"isDelete",isDelete);
//        userandinterfaceinfoQueryWrapper.eq(!Objects.isNull(status),"status",status);
//        userandinterfaceinfoQueryWrapper.eq(!Objects.isNull(remainderNum),"remainderNum",remainderNum);
//        userandinterfaceinfoQueryWrapper.orderBy(SqlUtils.verifySqlFiled(pageSortFields), sortFields.ORDER_ASC.equals(pageSortOrder),pageSortFields);
//        return userandinterfaceinfoQueryWrapper;
        //第二种写法
        QueryWrapper<Userandinterfaceinfo> queryWrapper = new QueryWrapper<>();
        Optional.ofNullable(dto.getId()).ifPresent(id ->
                queryWrapper.eq("id", id));
        Optional.ofNullable(dto.getUserId()).ifPresent(userId ->
                queryWrapper.eq("user_id", userId));
        Optional.ofNullable(dto.getApiId()).ifPresent(apiId ->
                queryWrapper.eq("api_id", apiId));
        Optional.ofNullable(dto.getIsDelete()).ifPresent(isDelete ->
                queryWrapper.eq("is_delete", isDelete));
        Optional.ofNullable(dto.getStatus()).ifPresent(status ->
                queryWrapper.eq("status", status));
        Optional.ofNullable(dto.getTotalNum()).ifPresent(totalNum ->
                queryWrapper.eq("total_num", totalNum));
        Optional.ofNullable(dto.getRemainderNum()).ifPresent(remainderNum ->
                queryWrapper.eq("remainder_num", remainderNum));
        queryWrapper.orderBy(SqlUtils.verifySqlFiled(dto.getPageSortFields()), sortFields.ORDER_ASC.equals(dto.getPageSortOrder()),dto.getPageSortFields());
        return queryWrapper;
    }
}
