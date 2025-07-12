package com.subati.crms.dao;

import com.subati.crms.bean.PartsD;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartsDDao {

    public List<PartsD> getAllPartsD(@Param("part_number") String part_number, @Param("pageStart") int PageStart, @Param("pageSize") int pageSize);
    public int getPartsDCounts(@Param("part_number") String part_number);
    public int addPartsD(PartsD partsD);
    public int deletePartsD(int id);
    public List<PartsD> getAll();
    public PartsD getUpdatePartsD(int id);
    public int editPartsD(PartsD partsD);
}
