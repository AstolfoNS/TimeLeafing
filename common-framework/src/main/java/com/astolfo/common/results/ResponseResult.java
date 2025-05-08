package com.astolfo.common.results;

import com.astolfo.common.enums.HttpCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResponseResult<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;


    private static <T> ResponseResult<T> buildResult(
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

    public static <T> ResponseResult<T> okResult() {
        return buildResult(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMessage(), null);
    }

    public static <T> ResponseResult<T> okResult(T data) {
        return buildResult(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMessage(), data);
    }

    public static <T> ResponseResult<T> okResult(
            Integer code,
            String message,
            T data
    ) {
        return buildResult(code, message, data);
    }

    public static <T> ResponseResult<T> okResult(HttpCode code, T data) {
        return buildResult(code.getCode(), code.getMessage(), data);
    }

    public static ResponseResult<Void> okResult(HttpCode code) {
        return buildResult(code.getCode(), code.getMessage(), null);
    }

    public static <T> ResponseResult<T> errorResult(HttpCode httpCode) {
        if (Objects.isNull(httpCode)) {
            return errorResult(600, "Unknown error");
        } else {
            return errorResult(httpCode.getCode(), httpCode.getMessage());
        }
    }

    public static <T> ResponseResult<T> errorResult(Integer code, String message) {
        return buildResult(code, message, null);
    }
}
