package com.sparkle.start_project.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sparkle.start_project.Domain.entity.Production;
import com.sparkle.start_project.Domain.entity.User;
import com.sparkle.start_project.StartProjectApplication;
import com.sparkle.start_project.mapper.ProductionMapper;
import com.sparkle.start_project.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = StartProjectApplication.class)
public class TestMybatisPlus {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductionMapper productionMapper;

    @org.junit.jupiter.api.Test
    void test1() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("id", "5").le("id", "10")
//                .eq("gender", "0")
//                .in("userRole", "1")
//                .like("userName", "a");
        ;
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void test2() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().orderByAsc("id");
    }

    @Test
    void test3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.apply("DATE_FORMAT(creatTime,'%Y-%m-%d') ='2025-01-01 10:00:00'");
    }

    @Test
    void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userRole", "0").eq("gender", "0");//and查询
        wrapper.eq("userRole", "0").or().eq("age", "0");//or查询
        wrapper.and(w -> w.eq("userRole", "0").lt("gender", "0"));//and和or组合
    }

    @Test
    void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("userName", "Li");
        User user = new User();
        user.setEmail("li@gmail.com");
        userMapper.update(user, wrapper);
    }

    @Test
    void test6() {
        List<Long> longs = List.of(1L, 4L, 5L);
        UpdateWrapper<Production> wrapper = new UpdateWrapper<Production>().setSql("price = price + 100").in("id", longs);
        productionMapper.update(null, wrapper);
    }

    @Test
    void test7() {
        //lambdaWrapper三种写法
        LambdaQueryWrapper<Production> wrapper = new QueryWrapper<Production>().lambda()//此处要指定类型，默认为Object
                .select(Production::getPrice, Production::getId, Production::getDecip, Production::getIsDelete).like(Production::getDecip, "一").eq(Production::getPrice, 199);
//        LambdaQueryWrapper<Production> wrapper = new LambdaQueryWrapper<Production>()
//                .select(Production::getPrice, Production::getId, Production::getDecip, Production::getIsDelete)
//                .like(Production::getDecip, "一")
//                .eq(Production::getPrice, 199);
//        QueryWrapper<Production> wrapper = new QueryWrapper<>();
//        wrapper.lambda()
//                .select(Production::getPrice, Production::getId, Production::getDecip, Production::getIsDelete)
//                .like(Production::getDecip, "一")
//                .eq(Production::getPrice, 199);
        productionMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test8() {
        //自定义多表查询
        QueryWrapper<User> wrapper = new QueryWrapper<User>()
                .in("u.id", List.of(1L, 2L, 3L))
                .like("p.decip", "一");
        userMapper.selectPandU(wrapper).forEach(System.out::println);
    }

    @Test
    void test9() {
        //分页查询
        Page<User> objectPage = new Page<>(1, 3);
        IPage<User> tp = userMapper.selectPage(objectPage, null);
    }
}
