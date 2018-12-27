package com.example.demo.dao;

import com.example.demo.entity.Power;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PowerDao {
    void addPower(Power power);
    void deletePower(Integer powerId);
    Power queryPowerById(Integer powerId);
    List<Power> queryPowerList();
    Power queryPowerByName(String name);
}
