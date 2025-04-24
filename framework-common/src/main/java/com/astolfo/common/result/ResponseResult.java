package com.astolfo.common.result;

import com.astolfo.common.enums.HttpCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;


    public static <T> ResponseResult<T> errorResult(HttpCode httpCode) {
        return errorResult(httpCode, httpCode.getMessage());
    }

    public static <T> ResponseResult<T> errorResult(HttpCode httpCode, String message) {
        if (httpCode == null) {
            return errorResult(600, "未知错误");
        } else {
            return errorResult(httpCode.getCode(), message);
        }
    }

    public static <T> ResponseResult<T> errorResult(Integer code, String message) {
        return ResponseResult
                .<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    public static <T> ResponseResult<T> okResult() {
        return okResult(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMessage(), null);
    }

    public static <T> ResponseResult<T> okResult(T data) {
        return okResult(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMessage(), data);
    }

    public static <T> ResponseResult<T> okResult(
            Integer code,
            String message,
            T data
    ) {
        return ResponseResult
                .<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

}