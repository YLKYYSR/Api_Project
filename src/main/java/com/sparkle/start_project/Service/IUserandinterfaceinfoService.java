package com.sparkle.start_project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparkle.start_project.Domain.dto.api.adminAddApiDto;
import com.sparkle.start_project.Domain.dto.api.apiQueryDto;
import com.sparkle.start_project.Domain.dto.userandinterfaceinfo.addDto;
import com.sparkle.start_project.Domain.dto.userandinterfaceinfo.queryDto;
import com.sparkle.start_project.Domain.entity.ApiInfo;
import com.sparkle.start_project.Domain.entity.Userandinterfaceinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户和接口调用关系表 服务类
 * </p>
 *
 * @author Sparkle
 * @since 2025-03-03
 */
public interface IUserandinterfaceinfoService extends IService<Userandinterfaceinfo> {
    boolean addDtoIsEmpty(addDto dto);

    QueryWrapper<Userandinterfaceinfo> queryWrapper(queryDto dto);
}
