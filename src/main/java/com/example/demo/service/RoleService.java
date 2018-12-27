package com.example.demo.service;

import com.example.demo.entity.Role;

import java.util.List;

public interface RoleService {
    boolean addRole(Role role);
    boolean deleteRole(Integer roleId);
    Role queryRoleById(Integer roleId);
    List<Role> queryRoleList();
    boolean updateRole(Role role);
    Role queryRoleByName(String name);
}
