package com.sparkle.start_project.Domain.enums;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@Tag(name = "接口状态枚举")
public enum apiStatus {

    LOGOFF("下线", 1),
    ONLINE("上线", 0);


    private final String role;

    private final Integer value;

    apiStatus(String role, Integer value) {
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
    public static apiStatus getRoleEnum(int value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (apiStatus roleEnum : apiStatus.values()) {
            if (Objects.equals(roleEnum.getValue(), value)) {
                return roleEnum;
            }
        }
        return null;

    }
}
