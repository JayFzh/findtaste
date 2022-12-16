package com.example.mybatistest.mapper;

import com.example.mybatistest.entity.User;
import com.example.mybatistest.entity.qpjInfo;
import com.example.mybatistest.entity.xwdUser;
import com.example.mybatistest.entity.xwdInfo;
import com.example.mybatistest.entity.sheet;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    public List<User> findAll();
    public int insert(User user);
    public int deleteUser(int id);
    public User getUserById(int id);
    public User getUserByOpenid(String openid);
    public int Openid_exist(String openid);
    public int updateUser(User user);
    public int updateFml(User user);
    public List<User> queryfml(int fmlid);
    
    public int logincheck1(String username);
    public String logincheck2(String username);
    public int getMaxId();
    public int register(xwdUser user);
    public xwdUser getUser(String username);
    public void updateId(String username,String newname);
    public void updatePwd(String username,String newPwd);
    public List<xwdInfo> getInfos(String city);
    public String getnickname(String id);
    public String getCity(String id);
    public List<qpjInfo> getRespond(int id);
    public List<xwdInfo> getXwdInfos(String city);
    public int getMaxQpjId();
    public void insertQpjInfo(qpjInfo info);
    public void increaseRes(int id);
    public qpjInfo getQpjInfo(int id);
    public void setstate(int id);
    public void setResState(int id);
    public void decreaseRes(int id);
    public void deleteQpjInfo(int id);
    public List<sheet> getSheet(String city);
    public void changeSheet(int type);
    public int getMaxXwdId();
    public void insertXwdInfo(xwdInfo info);
    public int getSheetNum(sheet Sheet);
    public void insertSheet(sheet Sheet);
    public void deleteXwdInfo(int id);
}
