package com.sparkle.start_project.Domain.dto.user;

import com.sparkle.start_project.Common.PageFields;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ApiModel("批量查询dto")
@Data
public class userQueryDto extends PageFields implements Serializable {
    //下面的字段都是查询比较条件
    /**
     *序列化字段
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户账户
     */
    private String userName;
    /**
     * 用户角色
     */
    private Integer userRole;
    /**
     * 用户邮箱
     */
    private String email;
}
