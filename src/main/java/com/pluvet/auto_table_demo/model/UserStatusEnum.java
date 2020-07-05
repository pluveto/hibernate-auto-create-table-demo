package com.pluvet.auto_table_demo.model;


public enum UserStatusEnum {
    Normal(0, "正常"),
    Unverified(1, "未激活"),
    Blocked(2, "封禁"),
    Closed(99, "注销");

    UserStatusEnum(int status, String description) {
        this.status = status;
        this.description = description;
    }

    private final int status;

    private final String description;
}