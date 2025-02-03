package com.sparkle.start_project.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sparkle.start_project.Domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    @Select("SELECT u.* from user u Inner JOIN production p on u.id = p.id ${ew.customSqlSegment}")
    List<User> selectPandU(@Param("ew") QueryWrapper<User> wrapper);
}
