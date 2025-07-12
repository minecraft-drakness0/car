package com.subati.crms.bean;
// 员工信息实体
public class EmployeeInfor {

    private int id;
    private String employee_id;
    private String employee_name;
    private int employee_salary;

    public EmployeeInfor() {
    }

    public EmployeeInfor(String employee_id, String employee_name, int employee_salary) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public void setEmployee_salary(int employee_salary) {
        this.employee_salary = employee_salary;
    }

    public int getId() {
        return id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public int getEmployee_salary() {
        return employee_salary;
    }

    @Override
    public String toString() {
        return "EmployeeInfor{" +
                "id=" + id +
                ", employee_id=" + employee_id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_salary=" + employee_salary +
                '}';
    }
}
