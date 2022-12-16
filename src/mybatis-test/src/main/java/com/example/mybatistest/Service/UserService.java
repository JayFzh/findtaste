package com.example.mybatistest.Service;

import com.example.mybatistest.entity.User;
import com.example.mybatistest.entity.xwdUser;
import com.example.mybatistest.entity.xwdInfo;
import com.example.mybatistest.mapper.UserMapper;
import com.example.mybatistest.entity.qpjInfo;
import com.example.mybatistest.entity.sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public int logincheck(String username,String password) {
        int exist = userMapper.logincheck1(username);
        if(exist == 1){
            String passwordInBase = null;
            passwordInBase = userMapper.logincheck2(username);
            System.out.print(password+"\n"+passwordInBase+'\n');
            if (password.equals(passwordInBase)){
                return 1;
            }
        }
        return 0;
    }
    public int register(xwdUser user){
        int exist = userMapper.logincheck1(user.getName());
        if(exist>0) return 0;
        int id = userMapper.getMaxId();
        user.setId(id+1);
        userMapper.register(user);
        return 1;
    }
    public xwdUser getUser(String username){
        return userMapper.getUser(username);
    }
    public void updateId(String username,String newname){
        userMapper.updateId(username,newname);
    }

    public void updatePwd(String username,String newPwd){
        userMapper.updatePwd(username,newPwd);
    }
    public List<xwdInfo> getInfos(String city){
        return userMapper.getInfos(city);
    }
    public String getnickname(String username){
        return userMapper.getnickname(username);
    }
    public String getCity(String username){
        return userMapper.getCity(username);
    }
    public List<qpjInfo> getRespond(int id){
        return userMapper.getRespond(id);
    } 
    public List<xwdInfo> getXwdInfos(String city){
        return userMapper.getXwdInfos(city);
    } 
    public int getMaxQpjId(){
        return userMapper.getMaxQpjId();
    }
    public void insertQpjInfo(qpjInfo info){
        userMapper.insertQpjInfo(info);
    }
    public void increaseRes(int id){
        userMapper.increaseRes(id);
    }
    public qpjInfo getQpjInfo(int id){
        return userMapper.getQpjInfo(id);
    }
    public void setstate(int id){
        userMapper.setstate(id);
    }
    public void setResState(int id){
        userMapper.setResState(id);
    }
    public void decreaseRes(int id){
        userMapper.decreaseRes(id);
    }
    public void deleteQpjInfo(int id){
        userMapper.deleteQpjInfo(id);
    }
    public List<sheet> getSheet(String city){
        return userMapper.getSheet(city);
    }
    public void changeSheet(int type){
        userMapper.changeSheet(type);
    }
    public int getMaxXwdId(){
        return userMapper.getMaxXwdId();
    }
    public void insertXwdInfo(xwdInfo info){
        userMapper.insertXwdInfo(info);
    }
    public int getSheetNum(sheet Sheet){
        return userMapper.getSheetNum(Sheet);
    }
    public void insertSheet(sheet Sheet){
        userMapper.insertSheet(Sheet);
    }
    public void deleteXwdInfo(int id){
        userMapper.deleteXwdInfo(id);
    }


    public int insert(User user){
        int exist = userMapper.Openid_exist(user.getOpenid());
        if(exist>0)return 0;
        else return userMapper.insert(user);
    }
    public List<User> findUser(){
        return userMapper.findAll();
    }

    public User getUserByOpenid(String openid){
        return userMapper.getUserByOpenid(openid);
    }
    public int deleteUser(int id){
        return userMapper.deleteUser(id);
    }

    public int updateUser(User user){return userMapper.updateUser(user);}

    public int updateFml(User user){return userMapper.updateFml(user);}
    public List<User> queryfml(int fmlid){return userMapper.queryfml(fmlid);}

    public String judge(float height, float weight, float diastolic_pressure,
                        float systolic_pressure, float blood_oxygen, float heart_rate) {
        String result = null;
        return result;
    }

}
