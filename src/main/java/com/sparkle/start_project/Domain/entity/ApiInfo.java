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
 * 接口信息
 * </p>
 *
 * @author Sparkle
 * @since 2025-02-20
 */
//在实体类写入数据库配置信息
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("api")
@ApiModel(value="ApiInfo对象", description="接口信息")
public class ApiInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "接口名")
    private String name;


    @ApiModelProperty(value = "接口描述")
    private String description;

    @ApiModelProperty(value = "接口地址")
    private String url;

    @ApiModelProperty(value = "响应头")
    private String responseHeader;

    @ApiModelProperty(value = "请求头")
    private String requestHeader;

    @ApiModelProperty(value = "创建人")
    private Long userId;

    @ApiModelProperty(value = "接口状态,0开启，1关闭")
    private Integer status;

    @ApiModelProperty(value = "请求类型")
    private String method;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除，0正常，1删除")
    private Integer isDelete;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "跟新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "请求参数")
    private String RequestParameters;


}
