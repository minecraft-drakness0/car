package com.subati.crms.controller;

import com.alibaba.fastjson.JSON;
import com.subati.crms.bean.QueryInfo;
import com.subati.crms.bean.User;
import com.subati.crms.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao udao;

    //  用户列表’传送‘
    @RequestMapping("/allUser")
    public String getUserList(QueryInfo queryInfo) {
        //  获取做大列表数和当前编号
        int numbers = udao.getUserCounts("%" + queryInfo.getQuery() + "%");// 获取数据总数
        int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();

        List<User> users = udao.getAllUser("%" + queryInfo.getQuery() + "%",pageStart,queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",users);
        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    //  state按钮的方法
    @RequestMapping("/userstate")
    public String updateUserState(@RequestParam("id")Integer id, @RequestParam("state")Boolean state) {
        int i = udao.updateState(id, state);
        return i > 0 ? "success":"error";
    }

    //  添加用户的方法
    @RequestMapping("/adduser")
    public String addUser(@RequestBody User user) {
        user.setRole("普通用户");
        user.setState(false);
        int i = udao.addUser(user);
        return i > 0 ? "success":"error";
    }

    //  删除用户的方法
    @RequestMapping("/deleteuser")
    public String deleteUser(int id) {
        int i = udao.deleteUser(id);
        return i > 0 ? "success" : "error";
    }

    //  修改用户的方法1(获取信息)
    @RequestMapping("/getupdate")
    public String getUpdateUser(int id) {
        User user = udao.getUpdateUser(id);
        String string = JSON.toJSONString(user);
        return string;
    }

    //  修改用户的方法2
    @RequestMapping("/edituser")
    public String editUser(@RequestBody User user) {
        int i = udao.editUser(user);
        return i > 0 ? "success":"error";
    }
}
