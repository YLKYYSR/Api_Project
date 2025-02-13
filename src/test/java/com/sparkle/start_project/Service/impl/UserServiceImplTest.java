package com.sparkle.start_project.Service.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {

    @Resource
    private UserServiceImpl userService;

    @Test
    void userRegister() {
        String username = "userfdsa";
        String password = "123456789";
        String password2 = "123456789";
        //不会触发依赖注入
        //com.sparkle.start_project.service.impl.UserServiceImpl userService = new com.sparkle.start_project.service.impl.UserServiceImpl();
        long l = userService.userRegister(username, password, password2);
        System.out.println(l);
    }

    @Test
    void userLogin() {
        String username = "userfdsa";
        String password = "123456789";
        System.out.println(userService.userLogin(username, password, null));
    }
}