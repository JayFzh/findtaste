package com.example.mybatistest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class xwdInfo {
    private int request_id;
    private String username;
    private String flavour_type;
    private String theme_type;
    private String introduction;
    private float price;
    private Date deadline;
    private String photo;
    private Date request_time;
    private Date modify_time;
    private int state;
    private int response;
    private String city;
}
// CREATE TABLE `xwdinfo` (
//   `request_id` int NOT NULL,
//   `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
//   `flavour_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
//   `theme_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
//   `introduction` varchar(255) COLLATE utf8_bin DEFAULT NULL,
//   `price` float DEFAULT NULL,
//   `deadline` datetime DEFAULT NULL,
//   `photo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
//   `request_time` datetime DEFAULT NULL,
//   `modify_time` datetime DEFAULT NULL,
//   `state` int DEFAULT NULL,
//   `response` int DEFAULT NULL,
//   PRIMARY KEY (`request_id`)
// ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;