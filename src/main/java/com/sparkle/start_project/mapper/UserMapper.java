package com.sparkle.start_project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sparkle.start_project.Domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sparkle
 * @since 2025-01-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
