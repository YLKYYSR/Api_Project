package com.sparkle.start_project.Domain.enums;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@ApiModel("用户鉴权所需枚举类型")
public enum userRole {

    ADMIN("管理员", 1),
    USER("用户", 0),
    BANNED("封禁", 2);


    private final String role;

    private final Integer value;

    userRole(String role, Integer value) {
        this.role = role;
        this.value = value;
    }

    //获取List值列表
    public static List<Integer> getRoleValue() {
        List<Integer> list = Arrays.stream(values()).map(s -> s.value).toList();
        System.out.println(list);
        return list;
    }

    //根据value获取Enum字段
    public static userRole getRoleEnum(int value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (userRole roleEnum : userRole.values()) {
            if (Objects.equals(roleEnum.getValue(), value)) {
                return roleEnum;
            }
        }
        return null;

    }
}
