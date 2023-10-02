package com.ray.usercenter.exception;

import com.ray.usercenter.common.ErrorCode;

/**
 * ClassName: BusinessException
 * Package: com.ray.usercenter.exception
 * Description:自定义异常类
 *
 * @Author lil ray
 * @Create 2023/9/29 16:37
 * @Version 1.0
 */
public class BusinessException extends RuntimeException{
    /**
     * 异常码
     */
    private final int code;

    /**
     * 描述
     */
    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    // https://t.zsxq.com/0emozsIJh

    public String getDescription() {
        return description;
    }

}
