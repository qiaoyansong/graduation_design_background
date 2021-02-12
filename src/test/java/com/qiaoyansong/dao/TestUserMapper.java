package com.qiaoyansong.dao;

import com.qiaoyansong.entity.background.User;
import com.qiaoyansong.entity.background.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/12 20:50
 * description：
 */
@SpringBootTest
public class TestUserMapper {
    @Autowired
    private UserMapper mapper;
    @Test
    public void testRegister(){
        User user = new User();
        user.setUserName("admin1");
        user.setPassword("qiao683586890");
        user.setMailbox("1012077930@qq.com");
        user.setType(UserType.ADMINISTRATOR_INFORMATION);
        this.mapper.register(user);
    }
    @Test
    public void testGetUserInfo(){
        User admin1 = this.mapper.getUserInfo("admin1");
        System.out.println(admin1);
    }
}
