package com.ray.usercenter.common;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: BaseResponse
 * Package: com.ray.usercenter.common
 * Description: 通用返回类
 *
 * @Author lil ray
 * @Create 2023/9/29 14:43
 * @Version 1.0
 */
@Data
public class BaseResponse<T> implements Serializable {
    private int code;
    private T data;
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code,data,"");
    }
}
