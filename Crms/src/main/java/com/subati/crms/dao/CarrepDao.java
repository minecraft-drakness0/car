package com.subati.crms.dao;

import com.subati.crms.bean.Carrep;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrepDao {

    public List<Carrep> getAllCarrep(@Param("car_number") String car_number, @Param("pageStart") int PageStart, @Param("pageSize") int pageSize);
    public int getCarrepCounts(@Param("car_number") String car_number);
    public int addCarrep(Carrep carrep);
    public List<Carrep> getAll();
}
