package com.subati.crms.dao;

import com.subati.crms.bean.PartsW;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartsWDao {

    public List<PartsW> getAllPartsW(@Param("part_number") String part_number, @Param("pageStart") int PageStart, @Param("pageSize") int pageSize);
    public int getPartsWCounts(@Param("part_number") String part_number);
    public int addPartsW(PartsW partsW);
    public int deletePartsW(int id);
    public PartsW getUpdatePartsW(int id);
    public int editPartsW(PartsW partsW);
}
