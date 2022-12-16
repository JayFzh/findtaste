package com.example.mybatistest.controller;


import com.example.mybatistest.Service.UserService;
import com.example.mybatistest.entity.User;
import com.example.mybatistest.entity.xwdUser;
import com.example.mybatistest.entity.xwdInfo;
import com.example.mybatistest.entity.qpjInfo;
import com.example.mybatistest.entity.sheet;
import com.example.mybatistest.mapper.UserMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


@RestController
@CrossOrigin
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/findUser")
    public List<User> findUser(){
        return userService.findUser();
    }
    @GetMapping("/updateUser")
    public void change(String name,String gender,int age,float height,float weight,String openid){
        User user = userService.getUserByOpenid(openid);
        user.setName(name);
        user.setGender(gender);
        user.setAge(age);
        user.setHeight(height);
        user.setWeight(weight);
        userService.updateUser(user);
    }

    @GetMapping("/createFml")
    public void changefml(String openid){
        User user = userService.getUserByOpenid(openid);
        user.setFmlid(user.getId());
        userService.updateUser(user);
    }
    @PostMapping("/login")
    public JSONObject login(@RequestBody String data){
        JSONObject object = new JSONObject();       
        String username = null;
        String password = null;
        Map map= JSONObject.parseObject(data);
        Iterator<String> iterator=map.keySet().iterator();
        while (iterator.hasNext()){
            String key=iterator.next();
            if (key == "password"){
                password = (String) map.get(key);
            }
            if (key == "username"){
                username = (String) map.get(key);
            }
        }
        int result  = userService.logincheck(username, password);
        if(result == 1){
            object.put("status", 500);
            xwdUser user = userService.getUser(username);
            object.put("data", user);
        }
        else{
            object.put("status", 300);
        }
        return object;
    }
    @PostMapping("/register")
    public JSONObject register(@RequestBody Map<String,Object> map){
        JSONObject object = new JSONObject();
        xwdUser user = new xwdUser();
        String city = "";
        // System.out.println(map.get("position"));
        Map entity = (Map) map.get("position");
        Map entity_province = (Map) entity.get("province");
        Map entity_city = (Map) entity.get("city");
        Map entity_county = (Map) entity.get("county");
        city = (String)entity_province.get("label") + (String)entity_city.get("label") + (String) entity_county.get("label");
        // System.out.println(city);
        Date date = new Date(); 
        date.getTime(); 
        // {"username":"2414125@qq.com","password":"fzh123456","nickname":"srge","position":{"province":{"label":"山西省","value":"140000"},"city":{"label":"阳泉市","value":"140300","parent":"140000"},"county":{"label":"平定县","value":"140321","parent":"140300"}}}
        user.setCity(city);
        user.setName((String)map.get("username"));
        user.setPassword((String)map.get("password"));
        user.setUsername((String)map.get("nickname"));
        user.setType(0);
        user.setRegister_time(date);
        user.setModify_time(date);

        int result = userService.register(user);
        String ans = null;
        if(result == 1){
            object.put("status",500);
            object.put("data", user);
        }
        else{
            object.put("status",300);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH)+1;
        sheet Sheet = new sheet();
        Sheet.setCity(city);
        Sheet.setMonth(date);
        Sheet.setType(month);
        Sheet.setIncome(0);
        Sheet.setDealtimes(0);
        if(userService.getSheetNum(Sheet)==0){
            userService.insertSheet(Sheet);
        }
        return object;
    }
    
    @GetMapping("/getInfo")
    public JSONObject getInfo(String id){
        xwdUser user = userService.getUser(id);
        JSONObject  object = new JSONObject();
        object.put("nickname", user.getUsername());
        object.put("userId", user.getName());
        object.put("position", user.getCity());
        return object;
    }
    
    @PostMapping("/editNumber")
    public JSONObject editNumber(@RequestBody Map<String,Object> map){
        JSONObject object = new JSONObject();
        String userId = (String) map.get("userId");
        String newId = (String) map.get("newNumber");
        userService.updateId(userId,newId);
        xwdUser user = userService.getUser(userId);
        object.put("status",500);
        object.put("data",user);
        return object;
    }

    @PostMapping("/resetPwd")
    public JSONObject resetPwd(@RequestBody Map<String,Object> map){
        JSONObject object = new JSONObject();
        System.out.println(map);
        userService.updatePwd((String) map.get("userId"),(String) map.get("newPwd"));
        object.put("status",500);
        return object;
    }
    
    @PostMapping("/upload")
    public JSONObject upload(Map<String,Object> map){
        JSONObject object = new JSONObject();
        System.out.println(map);
        return object;
    }

    @GetMapping("/getPostList")
    public JSONObject getPostList(String id,String city){
        JSONObject object = new JSONObject();
        System.out.println(id+" "+city);
        List<xwdInfo> infos = userService.getInfos(city);
        for(xwdInfo info:infos){
            JSONObject temp = new JSONObject();
            String nickname = userService.getnickname(info.getUsername());
            temp.put("id", info.getRequest_id());
            temp.put("username",nickname);
            temp.put("realname",info.getUsername());
            temp.put("time",info.getRequest_time());
            temp.put("flavour",info.getFlavour_type());
            temp.put("state",info.getState());
            if(info.getState() == 1){
                temp.put("introduction",info.getIntroduction()+" 已完成!!!");
            }
            else{
                temp.put("introduction",info.getIntroduction());
            }
            temp.put("respondNum",info.getResponse());
            object.put(info.getIntroduction(),temp);
        }
        return object;
    }
    @PostMapping("/getRespond")
    public JSONObject getRespond(@RequestBody Map<String,Object> map){
        JSONObject object = new JSONObject();
        Object t = map.get("List");
        if(t == null){
            System.out.println(map);
        }
        else{
            int id = (int)((Map) t).get("id");
            List<qpjInfo> infos = userService.getRespond(id);
            JSONObject object2 = new JSONObject();
            for(qpjInfo info:infos){
                JSONObject temp = new JSONObject();
                String nickname = userService.getnickname(info.getUsername());
                temp.put("id", info.getRequest_id());
                temp.put("username",nickname);
                temp.put("time",info.getRequest_time());
                if(info.getState() == 1){       
                    temp.put("commentbody",info.getIntroduction()+" PS:已接受!!!");
                }
                else{
                    temp.put("commentbody",info.getIntroduction());
                }
                object2.put(info.getIntroduction(),temp);
            }
            object.put("data", object2);
        }
        
        object.put("status", 500);

        // username: "nihao",
        // commentbody: "nizaiganshenm",
        // time: 11
        //System.out.println(id);
        return object;
    }
    
    @GetMapping("/getFoundPost")
    public JSONObject getFoundPost(String id){
        JSONObject object = new JSONObject();
        String city = userService.getCity(id);
        List<xwdInfo> infos = userService.getXwdInfos(city);
        for(xwdInfo info:infos){
            JSONObject temp = new JSONObject();
            String nickname = userService.getnickname(info.getUsername());
            temp.put("id", info.getRequest_id());
            temp.put("username",nickname);
            temp.put("time",info.getRequest_time());
            temp.put("flavour",info.getFlavour_type());
            temp.put("introduction",info.getIntroduction());
            temp.put("respondNum",info.getResponse());
            object.put(info.getIntroduction(),temp);
        }
        return object;
    }


    @PostMapping("/newComment")
    public JSONObject newComment(@RequestBody Map<String,Object> map){
        System.out.println(map);
        JSONObject object = new JSONObject();
        qpjInfo info = new qpjInfo();
        int id = userService.getMaxQpjId()+1;

        Date date = new Date(); 
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH)+1;
        userService.changeSheet(month);

        info.setRequest_id(id);
        info.setUsername((String) map.get("userid"));
        info.setXwd_id((int) map.get("xwdid"));
        info.setIntroduction((String) map.get("commentbody"));
        info.setRequest_time(date);
        info.setModify_time(date);
        userService.insertQpjInfo(info);
        userService.increaseRes((int) map.get("xwdid"));

        List<qpjInfo> infos = userService.getRespond((int) map.get("xwdid"));
        JSONObject object2 = new JSONObject();
        for(qpjInfo info_:infos){
            JSONObject temp = new JSONObject();
            String nickname = userService.getnickname(info_.getUsername());
            temp.put("id", info_.getRequest_id());
            temp.put("username",nickname);
            temp.put("time",info_.getRequest_time());
            if(info_.getState() == 1){
                temp.put("commentbody",info_.getIntroduction()+" PS:已接受!!!");
            }
            else{
                temp.put("commentbody",info_.getIntroduction());
            }
            object2.put(info_.getIntroduction(),temp);
        }
        object.put("data", object2);
        object.put("status", 500);
        return object;
    }

    @PostMapping("/acceptRespond")
    public JSONObject acceptRespond(@RequestBody Map<String,Object> map){
        JSONObject object = new JSONObject();
        // System.out.println(map);
        int qpjId = (int) map.get("commentId");
        qpjInfo info = userService.getQpjInfo(qpjId);
        int xwdid = info.getXwd_id();
        userService.setstate(xwdid);
        userService.setResState(qpjId);
        Date date = new Date(); 
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH)+1;
        userService.changeSheet(month);
        return object;
    }

    @PostMapping("/deleteRespond")
    public JSONObject deleteRespond(@RequestBody Map<String,Object> map){
        JSONObject object = new JSONObject();
        // System.out.println(map);
        int qpjId = (int) map.get("commentId");
        qpjInfo info = userService.getQpjInfo(qpjId);
        int xwdid = info.getXwd_id();
        userService.decreaseRes(xwdid);
        userService.deleteQpjInfo(qpjId);
        // userService.setstate(xwdid);
        // userService.setResState(qpjId);
        return object;
    }

    @PostMapping("/modifyRespond")
    public JSONObject modifyRespond(@RequestBody Map<String,Object> map){
        JSONObject object = new JSONObject();
        int qpjId = (int) map.get("commentId");
        String commentbody = (String) map.get("commentBody");
        qpjInfo info = userService.getQpjInfo(qpjId);
        info.setIntroduction(commentbody);
        userService.deleteQpjInfo(qpjId);
        userService.insertQpjInfo(info);
        return object;
    }

    @PostMapping("/getChart")
    public JSONObject getChart(@RequestBody Map<String,Object> map){
        JSONObject object = new JSONObject();
        String city = "";
        Map entity = (Map) map.get("position");
        Map entity_province = (Map) entity.get("province");
        Map entity_city = (Map) entity.get("city");
        Map entity_county = (Map) entity.get("county");
        city = (String)entity_province.get("label") + (String)entity_city.get("label") + (String) entity_county.get("label");
        List<sheet> sheetList = userService.getSheet(city);
        List<String> months = new ArrayList<>();
        List<Integer> incomes = new ArrayList<>();
        for(sheet temp:sheetList){
            Calendar cal = Calendar.getInstance();
            cal.setTime(temp.getMonth());
            String month = String.valueOf(cal.get(Calendar.MONTH)+1);
            months.add(month+"月");
            incomes.add(temp.getIncome());
        }
        object.put("month",months);
        object.put("income", incomes);
        return object;
    }

    @PostMapping("/submit")
    public void submit(@RequestBody Map<String,Object> map){
        Date date = new Date();
        String id = (String) map.get("id");
        String taste = (String) map.get("taste");
        String body = (String) map.get("body");
        xwdInfo info = new xwdInfo();
        String city = userService.getCity(id);
        int reqId = userService.getMaxXwdId()+1;
        info.setRequest_id(reqId);
        info.setUsername(id);
        info.setFlavour_type(taste);
        info.setIntroduction(body);
        info.setDeadline(date);
        info.setRequest_time(date);
        info.setModify_time(date);
        info.setState(0);
        info.setResponse(0);
        info.setCity(city);
        userService.insertXwdInfo(info);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH)+1;
        userService.changeSheet(month);
    }
    @PutMapping("/deletePost")
    public void deletePost(String id,String postId){
        int ID = Integer.parseInt(postId);
        // int id = (int) map.get("postId");
        userService.deleteXwdInfo(ID);
    }
    @GetMapping("/dissolveFml")
    public void dissolvefml(String openid){
        User user = userService.getUserByOpenid(openid);
        user.setFmlid(0);
        userService.updateUser(user);
    }

    @GetMapping("/joinFml")
    public int joinfml(String openid,String invite){
        User user = userService.getUserByOpenid(openid);
        User host = userService.getUserByOpenid(invite);
        if(user!=null){
            user.setFmlid(host.getFmlid());
            userService.updateUser(user);
            return 1;
        }
        else {
            return 0;
        }
    }

    @RequestMapping("/queryFml")
    public List<User> queryFml(String openid){
        User user = userService.getUserByOpenid(openid);
        return userService.queryfml(user.getFmlid());
    }
    @GetMapping("/addUser")
    public String saveUser(String openid){
        User user = new User(openid);
        userService.insert(user);
        String json=JSON.toJSONString(user);
        return json;
    }
    //表单和json格式的区别
    //    @PostMapping("/")
    //    public void saveUser(@RequestBody Map<String,Object> map){
    //        System.out.println(map.get("name"));
    //    }


    @GetMapping("/deleteUser")
    public void deleteUser(int id){
        userService.deleteUser(id);
    }

    @RequestMapping("/Judge")
    public String judge(float height, float weight, float diastolic_pressure,
                        float systolic_pressure, float blood_oxygen, float heart_rate) {
        String result = userService.judge(height,weight,diastolic_pressure,systolic_pressure,blood_oxygen,heart_rate);
        return result;
    }
}
