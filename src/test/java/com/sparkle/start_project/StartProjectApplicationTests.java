package com.sparkle.start_project;

import com.sparkle.start_project.Domain.entity.User;
import com.sparkle.start_project.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = StartProjectApplication.class)
class StartProjectApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void insert() {
        User user = new User();
        user.setUserPassword("123456");
        user.setUserName("Lfi");
        userMapper.insert(user);
    }
    @Test
    void select(){
        User user = userMapper.selectById(10L);
        System.out.println("user" + user);
    }

    @Test
    void selects(){
        List<User> users = userMapper.selectBatchIds(List.of(1L, 2L, 3L,6L, 8L));
        users.forEach(System.out::println);
    }
    @Test
    void update(){
        User user = new User();
        user.setId(2L);
        user.setUserPassword("123456");
        userMapper.updateById(user);
    }
    @Test
    void delete(){
        userMapper.deleteBatchIds(List.of(10L, 11L, 12L));
    }
}
