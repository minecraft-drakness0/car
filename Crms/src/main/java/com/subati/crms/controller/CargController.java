package com.subati.crms.controller;

import com.alibaba.fastjson.JSON;
import com.subati.crms.bean.Carreg;
import com.subati.crms.bean.QueryInfo;
import com.subati.crms.dao.CarregDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class CargController {

    @Autowired
    private CarregDao carregDao;

    @RequestMapping("/getcar")
    public String getCarregList(QueryInfo queryInfo) {
        int numbers = carregDao.getCarregCounts("%" + queryInfo.getQuery() + "%");
        int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();

        List<Carreg> carregs = carregDao.getAllCarreg("%" + queryInfo.getQuery() + "%",pageStart,queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",carregs);
        String string = JSON.toJSONString(res);
        return string;
    }

    @RequestMapping("/addcarreg")
    public String addCarreg(@RequestBody Carreg carreg) {
        int i = carregDao.addCarreg(carreg);
        return i > 0 ? "success":"error";
    }

    @RequestMapping("/deletecarreg")
    public String deleteCarreg(int id) {
        int i = carregDao.deleteCarreg(id);
        return i > 0 ? "success":"error";
    }

    @RequestMapping("/getupdatecarreg")
    public String getUpdateCarrag(int id) {
        Carreg carreg = carregDao.getUpdateCarrag(id);
        String string = JSON.toJSONString(carreg);
        return string;
    }

    @RequestMapping("/editcarreg")
    public String editCarreg(@RequestBody Carreg carreg) {
        int i = carregDao.editCarreg(carreg);
        return i > 0 ? "success":"error";
    }
}
