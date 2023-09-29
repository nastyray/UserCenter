package com.ray.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ray.usercenter.model.domain.User;
import com.ray.usercenter.model.domain.request.UserLoginRequest;
import com.ray.usercenter.model.domain.request.UserRegisterRequest;
import com.ray.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ray.usercenter.contant.UserContant.ADMIN_ROLE;
import static com.ray.usercenter.contant.UserContant.USER_LOGIN_STATE;

/**
 * ClassName: UserController
 * Package: com.ray.usercenter.controller
 * Description:用户接口
 *
 * @Author lil ray
 * @Create 2023/9/21 16:27
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest == null){
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword,planetCode)) {
            return null;
        }

        return userService.userRegister(userAccount, userPassword, checkPassword,planetCode);

    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @return
     */
    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest,HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.userLogin(userAccount, userPassword,request);

    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Integer userLogout(HttpServletRequest request) {
        if (request == null){
            return null;
        }
        return userService.userLogout(request);

    }


    /**
     * 获取当前用户信息
     * @param request
     * @return
     */
    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null){
            return null;
        }
        long userId = currentUser.getId();
        //todo 校验用户是否合法
        User user = userService.getById(userId);
        return userService.getsafetyUser(user);
    }


    /**
     * 查询用户
     * @param username
     * @return
     */
    @GetMapping("/search")
    public List<User> searchUsers(String username,HttpServletRequest request){

        if (!isAdmin(request)){
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(username)){
            queryWrapper.like("username",username);
        }
        List<User> userList = userService.list(queryWrapper);
        return userList.stream().map(user -> userService.getsafetyUser(user)).collect(Collectors.toList());
    }


    @PostMapping("delete")
    public boolean deleteUser(@RequestBody long id,HttpServletRequest request){
        if (!isAdmin(request)){
            return false;
        }

        if (id <= 0){
            return false;
        }

        return userService.removeById(id);//mybatis-plus会自动改造成逻辑删除
    }

    /**
     * 是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request){
        //仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }





}
