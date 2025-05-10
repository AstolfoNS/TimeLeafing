package com.astolfo.infrastructure.common.enumtype;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HttpCode {

    SUCCESS(200, "操作成功"),
    LOGIN_SUCCESS(200, "登录成功"),
    LOGOUT_SUCCESS(200, "退出成功"),

    NO_OPERATOR_AUTH(301, "当前没有权限操作"),

    UNAUTHORIZED(401, "用户认证失败"),
    FORBIDDEN(403, "用户权限不足"),

    NEED_LOGIN(501, "需要登录后操作"),
    USERNAME_EXIST(504, "该用户名已被使用"),
    EMAIL_EXIST(505, "该邮箱已被使用"),
    REQUIRE_USERNAME(506, "必须填写用户名"),
    LOGIN_FAILED(507, "用户名或密码错误"),
    USER_NOT_EXIST(508, "该用户不存在"),
    LOGOUT_FAILED(509, "退出失败"),

    ARTICLE_NOT_EXIST(601, "该文章不存在"),
    ARTICLE_PRIVATE(602, "该文章不公开"),
    ARTICLE_NOT_FOUND(603, "没有查找到文章");


    private final Integer code;

    private final String message;

}
