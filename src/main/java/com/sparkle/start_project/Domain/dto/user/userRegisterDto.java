package com.sparkle.start_project.Domain.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel("用户注册dto")
public class userRegisterDto implements Serializable {
    /**
     * 用户名称
     *
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 二次检验密码
     */
    private String checkpassword;

}

