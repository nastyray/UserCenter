package com.ray.usercenter.common;

/**
 * ClassName: ResultUtils
 * Package: com.ray.usercenter.common
 * Description:返回工具类
 *
 * @Author lil ray
 * @Create 2023/9/29 14:55
 * @Version 1.0
 */
public class ResultUtils {
    public static <T> BaseResponse<T>  success(T data){
        return new BaseResponse<>(0,data,"ok");

    }
}
