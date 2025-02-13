package com.sparkle.start_project.Domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class userLoginDto implements Serializable {
    private String username;
    private String password;
}
