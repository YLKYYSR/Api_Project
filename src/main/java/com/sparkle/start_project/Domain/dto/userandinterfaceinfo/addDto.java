package com.sparkle.start_project.Domain.dto.userandinterfaceinfo;



import lombok.Data;

import java.io.Serializable;

@Data
public class addDto implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long userId;

    private Long apiId;


    private Integer status;


    private Integer totalNum;


    private Integer remainderNum;


}
