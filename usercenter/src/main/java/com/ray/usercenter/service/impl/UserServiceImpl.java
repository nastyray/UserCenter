package com.ray.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ray.usercenter.common.ErrorCode;
import com.ray.usercenter.exception.BusinessException;
import com.ray.usercenter.model.domain.User;
import com.ray.usercenter.service.UserService;
import com.ray.usercenter.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.ray.usercenter.contant.UserContant.ADMIN_ROLE;
import static com.ray.usercenter.contant.UserContant.USER_LOGIN_STATE;

/**
 * @author lil ray
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-09-20 18:54:50
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;
    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "ray";


    /**
     *  用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return
     */
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode) {
        //1.校验
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if (userAccount.length() < 4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号过短");

        }
        if (userPassword.length() < 8 || checkPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户密码过短");

        }
        if (planetCode.length() > 5){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"星球编号过长");

        }

        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号不能包含特殊字符");
        }
        //密码和校验密码相同
        if (!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"两次输入密码不一致");
        }
        //账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号已存在");
        }
        //星球编号不能重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("planetCode",planetCode);
        count = userMapper.selectCount(queryWrapper);
        if (count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"编号已存在");

        }
        //2.加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        System.out.println(encryptPassword);
        //3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setPlanetCode(planetCode);
        boolean saveResult = this.save(user);
        if (!saveResult){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"注册失败");

        }
        return user.getId();

    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号密码为空");

        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号过短");

        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码过短");

        }
        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号不能包含特殊字符 ");

        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户不存在");

        }
        // 3. 用户脱敏
        User safetyUser = getsafetyUser(user);

        // 4. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);

        return safetyUser;
    }


    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    @Override
    public User getsafetyUser(User originUser){
        if (originUser == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户不存在");
        }
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setPlanetCode(originUser.getPlanetCode());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setTags(originUser.getTags());
        return safetyUser;
    }

    /**
     * 用户注销
     * @param request
     */
    @Override
    public int userLogout(HttpServletRequest request) {
        //移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;

    }

    /**
     * 根据标签搜索用户
     * @param tagNameList
     * @return
     */
    @Override
    public List<User> searcherUserByTags(List<String> tagNameList){
        //1.先判断是否为空，不能让用户什么都不输入，就查到所有信息
        if (CollectionUtils.isEmpty(tagNameList)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //2.开始查询
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        //拼接 and 查询
//        // like '%java%' and like '%Python%'
//        for (String tagName : tagNameList){
//            queryWrapper = queryWrapper.like("tags",tagName);
//        }
//        List<User> userList = userMapper.selectList(queryWrapper);
//        脱敏
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> userList = userMapper.selectList(queryWrapper);
        Gson gson = new Gson();
        //2.判断内存中是否包含要求的标签
        return userList.stream().filter(user -> {
            String tagstr = user.getTags();
            if (StringUtils.isBlank(tagstr)){
                return false;
            }
            Set<String> tempTagNameSet = gson.fromJson(tagstr,new TypeToken<Set<String>>(){}.getType());
            tempTagNameSet = Optional.ofNullable(tempTagNameSet).orElse(new HashSet<>());
            for (String tagName : tagNameList){
                if (!tempTagNameSet.contains(tagName)){
                    return false;
                }
            }
            return true;
        }).map(this::getsafetyUser).collect(Collectors.toList());
    }

    /**
     * 获取当前登录用户信息
     * @param request
     * @return
     */
    @Override
    public User getLoginUser(HttpServletRequest request) {
        if (request == null){
            return null;
        }
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        return (User) userObj;
    }

    /**
     * 修改用户信息
     * @param user
     * @param loginUser
     * @return
     */
    @Override
    public int updateUser(User user, User loginUser) {
        long userId = user.getId();
        if (userId <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //如果是管理员，允许更新任意用户
        //如果不是管理员，只允许更新自己的信息
        if (!isAdmin(loginUser) && userId != loginUser.getId()){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        User userold = userMapper.selectById(userId);
        if (userold == null){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        return userMapper.updateById(user);

    }

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    public boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }

    /**
     * 是否为管理员
     *
     * @param loginUser
     * @return
     */
    public boolean isAdmin(User loginUser) {
        return loginUser != null && loginUser.getUserRole() == ADMIN_ROLE;
    }


}





