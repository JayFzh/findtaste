package com.example.mybatistest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String openid;
    private String gender;
    private int age;
    private int hrts;
    private float bldO2;
    private float systolic_pressure;
    private float diastolic_pressure;
    private float height;
    private float weight;
    private int fmlid;
    public User(String openid) {
        this.openid = openid;
    }

}
