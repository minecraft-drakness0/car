package com.subati.crms.controller;

import com.subati.crms.bean.Carrep;
import com.subati.crms.bean.PartsD;
import com.subati.crms.dao.CarrepDao;
import com.subati.crms.dao.EmployeeDao;
import com.subati.crms.dao.PartsDDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListController {

    @Autowired
    private PartsDDao pddao;
    @Autowired
    private EmployeeDao edao;
    @Autowired
    private CarrepDao carrepDao;

    @GetMapping("/listParts")
    public String getListParts()
    {
        List<PartsD> all = pddao.getAll();
        int sum = 0;
        for (PartsD partsD : all) {
            sum += partsD.getDelivery_quantity()*partsD.getPrice();
        }
        return String.valueOf(sum);
    }

    @GetMapping("/listRepair")
    public String getListRepair()
    {
        List<Carrep> all = carrepDao.getAll();
        int sum = 0;
        for (Carrep carrep : all) {
            int employee_salary = edao.getEmployeeInfor(carrep.getEmployee_id()).getEmployee_salary();
            int repair_time = carrep.getRepair_time();
            sum += employee_salary * repair_time;
        }
        return String.valueOf(sum);
    }
}
