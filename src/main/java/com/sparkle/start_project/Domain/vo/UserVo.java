package com.sparkle.start_project.Domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("用户vo实体")
public class UserVo implements Serializable {
    private Long id;

    private String userName;


    private Integer isDelete;


    private Integer userRole;

    private String userUrl;


    private Integer gender;

    private Integer userStatus;

    private String email;

}
