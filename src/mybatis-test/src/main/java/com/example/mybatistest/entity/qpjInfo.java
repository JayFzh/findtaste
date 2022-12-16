package com.example.mybatistest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class qpjInfo {
    private int request_id;
    private int xwd_id;
    private String username;
    private String introduction;
    private Date request_time;
    private Date modify_time;
    private int state;
}
