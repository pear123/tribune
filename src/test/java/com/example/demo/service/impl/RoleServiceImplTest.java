package com.example.demo.service.impl;

import com.example.demo.DemoApplication;
import com.example.demo.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class RoleServiceImplTest {
    @Autowired
   private RoleService roleService;

    @Test
    public void queryRoleList() {
        System.out.println(        roleService.queryRoleList().size()
        );
    }
}