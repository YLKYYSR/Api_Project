package com.sparkle.start_project.Domain.dto.api;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class invokeApiDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //接口id
    private Long id;

    //调用参数
    private String parameter;
}
