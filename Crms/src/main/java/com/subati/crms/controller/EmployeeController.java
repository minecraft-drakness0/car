package com.subati.crms.controller;

import com.alibaba.fastjson.JSON;
import com.subati.crms.bean.EmployeeInfor;
import com.subati.crms.bean.QueryInfo;
import com.subati.crms.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao edao;

    @RequestMapping("/allemployee")
    public String getEmployeeList(QueryInfo queryInfo) {
        int numbers = edao.getEmployeeCounts("%" + queryInfo.getQuery() + "%");// 获取数据总数
        int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();

        List<EmployeeInfor> Employees = edao.getAllEmployee("%" + queryInfo.getQuery() + "%",pageStart,queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",Employees);
        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    @RequestMapping("/addemployee")
    public String addEmployee(@RequestBody EmployeeInfor employeeinfor) {
        int i = edao.addEmployee(employeeinfor);
        return i > 0 ? "success":"error";
    }

    @RequestMapping("/deleteemployee")
    public String deleteEmployee(int id) {
        int i = edao.deleteEmployee(id);
        return i > 0 ? "success" : "error";
    }

    @RequestMapping("/getupdateemployee")
    public String getUpdateEmployee(int id) {
        EmployeeInfor employeeinfor = edao.getUpdateEmployee(id);
        String string = JSON.toJSONString(employeeinfor);
        return string;
    }

    @RequestMapping("/editemployee")
    public String editEmployee(@RequestBody EmployeeInfor imployeeinfor) {
        int i = edao.editEmployee(imployeeinfor);
        return i > 0 ? "success":"error";
    }
}
