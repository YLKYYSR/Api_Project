package com.sparkle.start_project.Domain.dto.api;

import com.sparkle.start_project.Common.PageFields;
import lombok.Data;

import java.io.Serializable;
@Data
public class apiQueryDto extends PageFields implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;


    private String description;

    private String url;


    private String responseHeader;


    private String requestHeader;


    private Long userId;


    private String method;

    private String RequestParameters;
}
