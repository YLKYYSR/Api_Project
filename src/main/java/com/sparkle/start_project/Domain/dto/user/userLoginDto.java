package com.sparkle.start_project.Domain.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户登录dto")
@Data
public class userLoginDto implements Serializable {
    /**
     * 用户账户
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
}
