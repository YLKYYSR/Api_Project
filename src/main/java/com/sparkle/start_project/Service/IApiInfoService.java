package com.sparkle.start_project.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparkle.start_project.Domain.dto.api.adminAddApiDto;
import com.sparkle.start_project.Domain.dto.api.apiQueryDto;
import com.sparkle.start_project.Domain.entity.ApiInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 接口信息 服务类
 * </p>
 *
 * @author Sparkle
 * @since 2025-02-20
 */
public interface IApiInfoService extends IService<ApiInfo> {
    //校验参数是否为空
    boolean adminAddDtoIsEmpty(adminAddApiDto dto);

    //获取QueryWrapper对象
    QueryWrapper<ApiInfo> queryWrapper(apiQueryDto dto);

}
