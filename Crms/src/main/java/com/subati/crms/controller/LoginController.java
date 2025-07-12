package com.subati.crms.controller;

import com.alibaba.fastjson.JSON;
import com.subati.crms.bean.User;
import com.subati.crms.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

//  LoginController是后端与前端的联系，还有调用dao层与数据库的操作

@RestController
public class LoginController {

    @Autowired
    UserDao userDao;
    /**
     * 在pom.xml已添加fastjson的依赖包
     * 创建map存放查询出的user和flag，转化json字符串，返回在pom.xml里添加json依赖，
     * 使用JSON.toJSONString转化对象为json
     */
    @RequestMapping("/login")
    public String login(@RequestBody User user) {
        String flag = "error";
        User us = userDao.getUserByMassage(user.getUsername(), user.getPassword());
        HashMap<String, Object> res = new HashMap<>();
        if (us!=null) {
            flag = "ok";
        }

        res.put("flag",flag);
        res.put("user",user);
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

}
