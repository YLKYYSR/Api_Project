package com.sparkle.start_project.Domain.dto.userandinterfaceinfo;


import lombok.Data;

import java.io.Serializable;


@Data
public class updateDto implements Serializable {
    private Long id;


    private Long userId;

    private Long apiId;


    private Integer status;


    private Integer totalNum;


    private Integer remainderNum;
}
