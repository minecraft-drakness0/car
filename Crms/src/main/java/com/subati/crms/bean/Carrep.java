package com.subati.crms.bean;

public class Carrep {

    private int id;
    private String car_number;
    private String employee_id;
    private String repairlist_id;
    private String repair_items;
    private int repair_time;

    public Carrep() {
    }

    public Carrep(String car_number, String employee_id, String repairlist_id, String repair_items, int repair_time) {
        this.car_number = car_number;
        this.employee_id = employee_id;
        this.repairlist_id = repairlist_id;
        this.repair_items = repair_items;
        this.repair_time = repair_time;
    }

    public String getCar_number() {
        return car_number;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public String getRepairlist_id() {
        return repairlist_id;
    }

    public String getRepair_items() {
        return repair_items;
    }

    public int getRepair_time() {
        return repair_time;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public void setRepairlist_id(String repairlist_id) {
        this.repairlist_id = repairlist_id;
    }

    public void setRepair_items(String repair_items) {
        this.repair_items = repair_items;
    }

    public void setRepair_time(int repair_time) {
        this.repair_time = repair_time;
    }

    @Override
    public String toString() {
        return "Carrep{" +
                "id=" + id +
                ", car_number='" + car_number + '\'' +
                ", employee_id='" + employee_id + '\'' +
                ", repairlist_id='" + repairlist_id + '\'' +
                ", repair_items='" + repair_items + '\'' +
                ", repair_time=" + repair_time +
                '}';
    }
}
