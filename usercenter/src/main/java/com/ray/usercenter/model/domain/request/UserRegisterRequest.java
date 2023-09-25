package com.ray.usercenter.model.domain.request;
import lombok.Data;

import java.io.Serializable;


/**
 * ClassName: UserRegisterRequest
 * Package: com.ray.usercenter.model.domain.request
 * Description: 用户注册请求体
 *
 * @Author lil ray
 * @Create 2023/9/21 16:45
 * @Version 1.0
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long seriaVersionUID = 3191241716373120793L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 校验密码
     */
    private String checkPassword;



}
