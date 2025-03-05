package com.sparkle.start_project.Domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户和接口调用关系表
 * </p>
 *
 * @author Sparkle
 * @since 2025-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("userandinterfaceinfo")
@ApiModel(value="Userandinterfaceinfo对象", description="用户和接口调用关系表")
public class Userandinterfaceinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("userId")
    private Long userId;

    @ApiModelProperty(value = "接口id")
    @TableField("apiId")
    private Long apiId;

    @TableLogic
    @ApiModelProperty(value = "0存在，1删除")
    @TableField("isDelete")
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updateTime")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "0正常，1禁用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "该用户从账号存在开始总共调用的次数")
    @TableField("totalNum")
    private Integer totalNum;

    @ApiModelProperty(value = "剩余用户可接口调用次数")
    @TableField("remainderNum")
    private Integer remainderNum;


}
