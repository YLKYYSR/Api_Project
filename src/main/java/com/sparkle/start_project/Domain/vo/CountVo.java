package com.sparkle.start_project.Domain.vo;


import lombok.Data;

import java.io.Serializable;


@Data
public class CountVo implements Serializable {

    private Integer totalNum;

    private Integer remainderNum;
}
