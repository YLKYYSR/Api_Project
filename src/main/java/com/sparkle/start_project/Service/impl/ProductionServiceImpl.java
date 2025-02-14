package com.sparkle.start_project.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sparkle.start_project.Domain.entity.Production;
import com.sparkle.start_project.mapper.ProductionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sparkle
 * @since 2025-01-27
 */
@Service
public class ProductionServiceImpl extends ServiceImpl<ProductionMapper, Production> implements IService<Production> {

}
