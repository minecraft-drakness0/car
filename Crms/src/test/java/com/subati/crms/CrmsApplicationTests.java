package com.subati.crms;

import com.subati.crms.bean.Carrep;
import com.subati.crms.dao.CarrepDao;
import com.subati.crms.dao.PartsDDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CrmsApplicationTests {
    @Autowired
    PartsDDao partsDDao;
    @Test
    void contextLoads() {
        System.out.println(partsDDao.getAll());
    }

}
