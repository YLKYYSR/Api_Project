package com.sparkle.start_project.Domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sparkle
 * @since 2025-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel("user表")
public class User implements Serializable {
    /**
     * 序列化字段
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 创建时间
     */
    private LocalDateTime creatTime;
    /**
     * 更新时间
     */
    private LocalDateTime updatTime;

    @ApiModelProperty(value = "删除1， 不删除0")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty(value = "user 0, admin 1")
    private Integer userRole;

    private String userUrl;

//    @ApiModelProperty(value = "男0 女1")
    private Integer gender;

    //该字段可以优化（0-user，1-管理员，2-封禁）复杂状态再单独定义
    @ApiModelProperty(value = "正常0 ，异常1")
    private Integer userStatus;

    private String email;


}
