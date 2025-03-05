package com.sparkle.start_project.Domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class ApiVo implements Serializable {


    private String name;

    private String desc;

    private String url;

    private String responseHeader;

    private String requestHeader;

    private Long userId;

    private Integer status;

    private String method;

}
