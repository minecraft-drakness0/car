package com.subati.crms.controller;

import com.alibaba.fastjson.JSON;
import com.subati.crms.bean.PartsD;
import com.subati.crms.bean.QueryInfo;
import com.subati.crms.dao.PartsDDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class PartsDController {

    @Autowired
    private PartsDDao pddao;

    @RequestMapping("/getpartsd")
    public String getPartsDList(QueryInfo queryInfo) {
        int numbers = pddao.getPartsDCounts("%" + queryInfo.getQuery() + "%");// 获取数据总数
        int pageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();

        List<PartsD> Parts = pddao.getAllPartsD("%" + queryInfo.getQuery() + "%",pageStart,queryInfo.getPageSize());
        HashMap<String,Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",Parts);
        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    @RequestMapping("/addpd")
    public String addPartsD(@RequestBody PartsD partsD) {
        int i = pddao.addPartsD(partsD);
        return i > 0 ? "success":"error";
    }

    @RequestMapping("/deletepd")
    public String deletePartsD(int id) {
        int i = pddao.deletePartsD(id);
        return i > 0 ? "success":"error";
    }

    @RequestMapping("/getupdatepartsd")
    public String getUpdatePartsD(int id) {
        PartsD partsD = pddao.getUpdatePartsD(id);
        String string = JSON.toJSONString(partsD);
        return string;
    }

    @RequestMapping("/editpartsd")
    public String editPartsD(@RequestBody PartsD partsD) {
        int i = pddao.editPartsD(partsD);
        return i > 0 ? "success":"error";
    }
}
