package com.ellsom.bbs;

import com.ellsom.bbs.Mapper.UserMapper;
import com.ellsom.bbs.Service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BbsApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService iUserService;
    @Test
    void contextLoads() {

    }

}
