package com.sparkle.start_project.Constant;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


//通用dto,抽象为constant
@Data
public class adminDeleteDto implements Serializable {
    //这里要修改id字段，必须显示声明
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
}
