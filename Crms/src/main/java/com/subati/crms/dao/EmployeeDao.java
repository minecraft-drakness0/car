package com.subati.crms.dao;

import com.subati.crms.bean.EmployeeInfor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {

    public List<EmployeeInfor> getAllEmployee(@Param("employee_id") String employee_id,@Param("pageStart") int PageStart,@Param("pageSize") int pageSize);
    public int getEmployeeCounts(@Param("employee_id") String employee_id);
    public int addEmployee(EmployeeInfor employeeinfor);
    public int deleteEmployee(int id);
    public EmployeeInfor getUpdateEmployee(int id);
    public EmployeeInfor getEmployeeInfor(String eid);
    public int editEmployee(EmployeeInfor employeeinfor);
}
