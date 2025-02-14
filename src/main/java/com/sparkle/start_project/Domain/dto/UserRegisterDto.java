package com.sparkle.start_project.Domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


    @Data
    @ApiModel("用户注册实体表单")
    public class userRegisterDto implements Serializable {
        private String username;
        private String password;
        private String checkpassword;
    }

