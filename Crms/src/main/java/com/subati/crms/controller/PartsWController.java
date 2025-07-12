package com.subati.crms.controller;

import com.alibaba.fastjson.JSON;
import com.subati.crms.bean.PartsW;
import com.subati.crms.bean.QueryInfo;
import com.subati.crms.dao.PartsWDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class PartsWController {

    @Autowired
    private PartsWDao pwdao;

    @RequestMapping("/allpartsw")
    public String getPartsWList(QueryInfo queryInfo) {
        int numbers = pwdao.getPartsWCounts("%" + queryInfo.getQuery() + "%");// 获取数据总数
        int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();

        List<PartsW> Parts = pwdao.getAllPartsW("%" + queryInfo.getQuery() + "%",pageStart,queryInfo.getPageSize());
        HashMap<String,Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",Parts);
        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    @RequestMapping("/addpw")
    public String addPartsW(@RequestBody PartsW partsW){
        int i = pwdao.addPartsW(partsW);
        return i > 0 ? "success":"error";
    }

    @RequestMapping("/deletepartsw")
    public String deletePartsW(int id) {
        int i = pwdao.deletePartsW(id);
        return i > 0 ? "success":"error";
    }

    @RequestMapping("/getupdatepartsw")
    public String getUpdatePartsW(int id) {
        PartsW partsW = pwdao.getUpdatePartsW(id);
        String string = JSON.toJSONString(partsW);
        return string;
    }

    @RequestMapping("/editpartsw")
    public String editPartsW(@RequestBody PartsW partsW) {
        int i = pwdao.editPartsW(partsW);
        return i > 0 ? "success":"error";
    }
}
