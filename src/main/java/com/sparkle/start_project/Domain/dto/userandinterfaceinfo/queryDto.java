package com.sparkle.start_project.Domain.dto.userandinterfaceinfo;

import com.sparkle.start_project.Common.PageFields;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class queryDto extends PageFields implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;


    private Long userId;

    private Long apiId;


    private Integer isDelete;


    private Integer status;


    private Integer totalNum;


    private Integer remainderNum;
}
