package com.astolfo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HttpCode {

    SUCCESS(200, "操作成功"),

    NO_OPERATOR_AUTH(301, "当前没有权限操作"),

    SYSTEM_ERROR(401, "系统错误"),

    NEED_LOGIN(501, "需要登录后操作"),
    USERNAME_EXIST(504, "该用户名已被使用"),
    EMAIL_EXIST(505, "该邮箱已被使用"),
    REQUIRE_USERNAME(506, "必须填写用户名"),
    LOGIN_FAILED(507, "用户名或密码错误"),
    USER_NOT_EXIST(508, "该用户不存在"),

    ARTICLE_NOT_EXIST(601, "该文章不存在"),
    ARTICLE_PRIVATE(602, "该文章不公开"),
    ARTICLE_NOT_FOUND(603, "没有查找到文章");



    final Integer code;

    final String message;


//    HttpCode(Integer code, String message) {
//        this.code = code;
//        this.message = message;
//    }

}
