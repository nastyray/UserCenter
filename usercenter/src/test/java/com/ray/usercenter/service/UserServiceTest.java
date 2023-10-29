package com.ray.usercenter.service;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ray.usercenter.model.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassName: UserServiceTest
 * Package: com.ray.usercenter.service
 * Description:
 *
 * @Author lil ray
 * @Create 2023/9/20 19:02
 * @Version 1.0
 */

/**
 * 用户服务测试
 * @author ray
 */
@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserService userService;
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("dogray");
        user.setUserAccount("123");
        user.setAvatarUrl("https://github.com/nastyray/UserCenter/blob/main/Image/1693641416492.jpg");
        user.setGender(0);
        user.setUserPassword("12345678");
        user.setEmail("456");
        user.setUserStatus(0);
        user.setPhone("123");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        assertTrue(result);

    }

    @Test
    void userRegister() {
        String userAccount = "yupi";
        String userPassword = "";
        String checkPassword = "123456";
        String planetCode = "1";
        long result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "yu";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "yupi";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "yu pi";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "dogYupi";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);
        userAccount = "yupi";
        result = userService.userRegister(userAccount, userPassword, checkPassword,planetCode);
        Assertions.assertEquals(-1, result);

    }

    @Test
    void testSearcherUserByTags(){
        //模拟一条数据
        List<String> tagNameList = Arrays.asList("java", "python");
        //调用方法查询
        List<User> userList = userService.searcherUserByTags(tagNameList);
        //判断是否查到
        Assert.assertNotNull(userList);

    }


}