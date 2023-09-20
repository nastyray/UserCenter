package com.ray.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ray.usercenter.model.domain.User;
import com.ray.usercenter.service.UserService;
import com.ray.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author lil ray
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-09-20 18:54:50
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




