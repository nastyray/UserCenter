package com.ray.usercenter.once;
import java.util.Date;

import com.ray.usercenter.mapper.UserMapper;
import com.ray.usercenter.model.domain.User;
import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

/**
 * ClassName: InsertUsers
 * Package: com.ray.usercenter.once
 * Description: 插入10000000 用户
 *
 * @Author lil ray
 * @Create 2023/11/6 20:33
 * @Version 1.0
 */
@Component
public class InsertUsers {
    @Resource
    private UserMapper userMapper;

    /**
     * 插入1000条数据
     */


//    @Scheduled(initialDelay = 5000,fixedRate = Long.MAX_VALUE )
    public void doInsertuser(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 1000;
        for (int i = 0;i < INSERT_NUM;i++){
            User user = new User();
            user.setUsername("假用户");
            user.setUserAccount("lilray");
            user.setAvatarUrl("https://ray-web-tlias.oss-cn-beijing.aliyuncs.com/mmexport1688003322367.png");
            user.setGender(0);
            user.setUserPassword("12345678");
            user.setEmail("123456");
            user.setUserStatus(0);
            user.setPhone("18265492364");
            user.setTags("[]");
            user.setUserRole(0);
            user.setPlanetCode("111111");
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());


    }


}
