package com.sparkle.start_project.Domain.vo;

import com.sparkle.start_project.Common.UserStatus;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("用户vo实体")
public class UserVo implements Serializable {
    private Long id;
    private String username;
    private String email;
    UserStatus isDelete;
    private Integer userRole;
    private String userUrl;
    private String gender;

}
