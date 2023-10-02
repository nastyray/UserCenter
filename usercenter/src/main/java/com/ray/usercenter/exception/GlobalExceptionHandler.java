package com.ray.usercenter.exception;

import com.ray.usercenter.common.BaseResponse;
import com.ray.usercenter.common.ErrorCode;
import com.ray.usercenter.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.ray.usercenter.exception
 * Description: 全局异常处理器
 *
 * @Author lil ray
 * @Create 2023/10/2 11:35
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e){
        log.error("businessException:" + e.getMessage(),e);
        return ResultUtils.error(e.getCode(),e.getMessage(),e.getDescription());


    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtiomeExceptionHandler(RuntimeException e){
        log.error("runtimeException",e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,e.getMessage(),"");
    }


}
