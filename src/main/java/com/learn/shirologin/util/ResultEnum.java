package com.learn.shirologin.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultEnum {

    LOGIN_ERROR(500, "用户名或密码错误"),
    ADD_USER_ERROR(500, "新增用户失败"),
    ADD_ROLE_ERROR(500, "新增角色失败"),
    UPDATE_USER_ERROR(500, "更新用户失败"),
    GET_USER_LIST_ERROR(500, "获取用户列表失败"),
    CREATE_OBJECT_ERROR(500, "创建对象失败"),
    DEL_USER_ERROR(500, "删除用户失败"),
    DEL_ROLE_ERROR(500, "删除角色失败"),

    FILE_FIND_ERROR(500, "文件未发现");

    private Integer code;
    private String msg;
}
