package com.subati.crms.dao;

import com.subati.crms.bean.Carreg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarregDao {

    public List<Carreg> getAllCarreg(@Param("car_number") String car_number, @Param("pageStart") int PageStart, @Param("pageSize") int pageSize);
    public int getCarregCounts(@Param("car_number") String car_number);
    public int addCarreg(Carreg carreg);

    public int deleteCarreg(int id);
    public Carreg getUpdateCarrag(int id);
    public int editCarreg(Carreg carreg);
}
