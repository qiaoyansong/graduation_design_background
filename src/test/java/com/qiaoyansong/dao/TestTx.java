package com.qiaoyansong.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/4/6 10:11
 * description：测试事务
 */
@SpringBootTest
public class TestTx {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ActivityMapper activityMapper;

}
