package com.sparkle.start_project.Common;

public enum UserStatus {
    ISDELETE(1,"已删除"),
    NOTDELETE(0,"未删除");

    private int id;

    private String description;

    UserStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
