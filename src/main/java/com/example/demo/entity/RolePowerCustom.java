package com.example.demo.entity;

public class RolePowerCustom extends RolePower{
    private Role role;
    private Power power;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }
}
