package com.sparkle.start_project.Domain.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


//可以更新自己的密码
//或者联系管理员
@ApiModel("用户更新信息dto")
@Data
public class userUpdateDto implements Serializable {
    //如果要修改字段方法等结构，建议显示保留该字段
    @Serial
    private static final long serialVersionUID = 1L;

    //这是已经登录的用户，Seccion已经保留了用户id字段
    private String userName;

    private String userUrl;

}
