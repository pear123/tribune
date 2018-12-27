package com.example.demo.service;

import com.example.demo.entity.Power;

import java.util.List;

public interface PowerService {
    boolean addPower(Power power);
    boolean deletePower(Integer powerId);
    Power queryPowerById(Integer powerId);
    Power queryPowerByName(String name);
    List<Power> queryPowerList();
}
