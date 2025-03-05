package com.sparkle.start_project.Domain.dto.api;


import lombok.Data;


import java.io.Serializable;

@Data
public class adminUpdateApiDto implements Serializable {


    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;


    private String description;


    private String url;


    private String responseHeader;

    private String requestHeader;

    private Long userId;

    private String method;

    private String RequestParameters;
}
