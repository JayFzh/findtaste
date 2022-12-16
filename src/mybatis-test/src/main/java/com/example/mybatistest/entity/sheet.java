package com.example.mybatistest.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class sheet {
    private Date month;
    private String city;
    private int type;
    private int dealtimes;
    private int income;
}
