package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserCustom;
import com.example.demo.entity.UserVo;

import java.util.Date;
import java.util.List;

public interface UserService {
    boolean register(User user);

    boolean updatePassword(UserVo userVo);

    User queryUserByPhone(String phone);

    boolean login(User user, Date date);

    User queryUserById(Integer id);

    String getCode();

    User queryUserByName(String name);

    List<UserCustom> queryUserList();

    boolean updateRole(Integer uId,Integer roleId) throws Exception;
}
