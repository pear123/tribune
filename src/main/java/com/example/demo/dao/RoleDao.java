package com.example.demo.dao;

import com.example.demo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    void addRole(Role role);
    void deleteRole(Integer roleId);
    Role queryRoleById(Integer roleId);
    List<Role> queryRoleList();
    void updateRole(Role role);
    Role queryRoleByName(String name);
}
