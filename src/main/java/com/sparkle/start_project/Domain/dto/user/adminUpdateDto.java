package com.sparkle.start_project.Domain.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@ApiModel("管理员更新用户dto")
public class adminUpdateDto implements Serializable {

    private Long id;

    private String userName;

    private String userUrl;

    private Integer userRole;



    @Serial
    private static final long serialVersionUID = 1L;

}
