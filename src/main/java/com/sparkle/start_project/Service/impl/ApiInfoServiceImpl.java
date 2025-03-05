package com.sparkle.start_project.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparkle.start_project.Common.ErrorCode;
import com.sparkle.start_project.Constant.sortFields;
import com.sparkle.start_project.Domain.dto.api.adminAddApiDto;
import com.sparkle.start_project.Domain.dto.api.apiQueryDto;
import com.sparkle.start_project.Domain.entity.ApiInfo;
import com.sparkle.start_project.Exception.BusinessException;
import com.sparkle.start_project.Service.IApiInfoService;
import com.sparkle.start_project.Utils.SqlUtils;
import com.sparkle.start_project.mapper.ApiInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * 接口信息 服务实现类
 * </p>
 *
 * @author Sparkle
 * @since 2025-02-20
 */
@Service
public class ApiInfoServiceImpl extends ServiceImpl<ApiInfoMapper, ApiInfo> implements IApiInfoService {

    //adminAddDtoIsEmpty方法：有空字符即返回true
    @Override
    public boolean adminAddDtoIsEmpty(adminAddApiDto dto) {
        if(dto == null ){
            return false;
        }
//        //第一种写法
//        //三目表达式避免npe
//        String string = dto.getUserId()!=null?dto.getUserId().toString():"";
//        String desc = dto.getDescription();
//        String name = dto.getName();
//        String method = dto.getMethod();
//        String url = dto.getUrl();
//        String requestHeader = dto.getRequestHeader();
//        String responseHeader = dto.getResponseHeader();
//        String requestParameters = dto.getRequestParameters();
//        //有空字符即返回true
//        return StringUtils.isAnyBlank(desc, name, method, url, requestHeader, responseHeader, string, requestParameters);
        //第二种写法
        //多个Long和Integer可以使用，有一个即返回true
        boolean b = Stream.of(dto.getUserId()).anyMatch(Objects::isNull);
        //判断字符也可以用方法引用,有一个即返回true
        boolean a = Stream.of(
                        dto.getUrl(),
                        dto.getDescription(),
                        dto.getName(),
                        dto.getResponseHeader(),
                        dto.getMethod(),
                        dto.getRequestHeader(),
                        dto.getRequestParameters()
                )
                .anyMatch(StringUtils::isBlank);
        return a || b;
    }

    @Override
    public QueryWrapper<ApiInfo> queryWrapper(apiQueryDto dto) {
        if(dto == null || dto.getPageSize() <= 0 || dto.getCurrentPage()<=0){
            throw new BusinessException(ErrorCode.NULL_PARAM ,"分页参数错误");
        }
        String pageSortFields = dto.getPageSortFields();
        String pageSortOrder = dto.getPageSortOrder();
        QueryWrapper<ApiInfo> queryWrapper = getApiInfoQueryWrapper(dto);
        //常量左边
        queryWrapper.orderBy(SqlUtils.verifySqlFiled(pageSortFields),sortFields.ORDER_ASC.equals(pageSortOrder),pageSortFields);
        return queryWrapper;
    }
    //idea的优化
    private static QueryWrapper<ApiInfo> getApiInfoQueryWrapper(apiQueryDto dto) {
        String name = dto.getName();
        String description = dto.getDescription();
        String method = dto.getMethod();
        String url = dto.getUrl();
        String requestHeader = dto.getRequestHeader();
        String responseHeader = dto.getResponseHeader();
        String requestParameters = dto.getRequestParameters();
        Long userId = dto.getUserId();
        //加上判空操作,如果不判空，可能会导致 查询条件错误 或 SQL 语法异常，影响查询结果
        QueryWrapper<ApiInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(userId != null,"userId", userId);
        queryWrapper.like(StringUtils.isNotBlank(description),"description", description);
        queryWrapper.like(StringUtils.isNotBlank(name),"name", name);
        queryWrapper.eq(StringUtils.isNotBlank(method),"method", method);
        queryWrapper.like(StringUtils.isNotBlank(url),"url", url);
        queryWrapper.like( StringUtils.isNotBlank(requestHeader),"requestHeader", requestHeader);
        queryWrapper.like(StringUtils.isNotBlank(requestParameters) ,"requestParameters", requestParameters);
        queryWrapper.like(StringUtils.isNotBlank(responseHeader) ,"responseHeader", responseHeader);
        return queryWrapper;
    }

}
