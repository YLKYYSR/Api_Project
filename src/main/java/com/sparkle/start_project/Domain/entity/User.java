package com.sparkle.start_project.Domain.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sparkle
 * @since 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userPassword;

    private String userName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableLogic
    @ApiModelProperty(value = "删除0， 不删除1")
    private Integer isDelete;

    @ApiModelProperty(value = "user 0, admin 1")
    private Integer userRole;

    private String userUrl;

    @ApiModelProperty(value = "男0 女1")
    private Integer gender;

    @ApiModelProperty(value = "正常0 ，异常1")
    private Integer userStatus;

    private String email;

    @ApiModelProperty(value = "公开密钥")
    private String accessKey;

    @ApiModelProperty(value = "加密密钥")
    private String secretKey;


}
