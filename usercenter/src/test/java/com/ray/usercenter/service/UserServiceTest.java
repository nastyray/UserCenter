package com.ray.usercenter.service;
import java.util.Date;

import com.ray.usercenter.model.domain.User;
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
        user.setUserPassword("xxx");
        user.setEmail("456");
        user.setUserStatus(0);
        user.setPhone("123");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        assertTrue(result);

    }

}