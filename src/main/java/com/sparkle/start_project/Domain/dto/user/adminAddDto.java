package com.sparkle.start_project.Domain.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("管理员添加用户dto")
public class adminAddDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;

    private String password;

    private Integer userRole;

    private String ak;

    private String sk;
}
