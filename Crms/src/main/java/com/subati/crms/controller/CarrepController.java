package com.subati.crms.controller;

import com.alibaba.fastjson.JSON;
import com.subati.crms.bean.Carrep;
import com.subati.crms.bean.QueryInfo;
import com.subati.crms.dao.CarrepDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class CarrepController {

    @Autowired
    private CarrepDao carrepDao;

    @RequestMapping("/getcarrep")
    public String getCarrepList(QueryInfo queryInfo) {
        int numbers = carrepDao.getCarrepCounts("%" + queryInfo.getQuery() + "%");
        int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();

        List<Carrep> carreps = carrepDao.getAllCarrep("%" + queryInfo.getQuery() + "%",pageStart,queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",carreps);
        String string = JSON.toJSONString(res);
        return string;
    }

    @RequestMapping("/addcarrep")
    public String addCarrep(@RequestBody Carrep carrep) {
        int i = carrepDao.addCarrep(carrep);
        return i > 0 ? "success":"error";
    }
}
