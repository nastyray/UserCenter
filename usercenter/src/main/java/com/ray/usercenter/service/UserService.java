package com.ray.usercenter.service;

import com.ray.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lil ray
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-09-20 18:54:50
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户Id
     */
    long userRegister(String userAccount,String userPassword,String checkPassword);

}
