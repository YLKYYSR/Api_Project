package com.sparkle.start_project.Utils;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL工具类
 */
public class SqlUtils {
    public static boolean verifySqlFiled(String sqlFiled) {
        if(StringUtils.isBlank(sqlFiled)) {
            return false;
        }
        //原方法包含则返回true, 但是我们是防止sql注入，拒绝这些字段
        return  !StringUtils.containsAny(sqlFiled, " ","(",")","=");
    }
}
