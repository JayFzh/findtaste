package com.example.mybatistest.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class xwdUser {
    private int id;                 // 用户id
    private String name;            // 用户名字
    private String password;        // 密码
    private String city;            // 城市
    private int type;               // 用户类型
    private String phone;           // 手机号
    private String introduction;    // 简介
    private String username;        // 昵称
    private String certificate_type;// 证件类型
    private String certificate_num; // 证件号码
    private Date register_time;     // 注册时间
    private Date modify_time;       // 修改时间
    public xwdUser(int id){
        this.id = id;
    }
} 