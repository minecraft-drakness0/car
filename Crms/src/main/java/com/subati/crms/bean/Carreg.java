package com.subati.crms.bean;

public class Carreg {

    private int id;
    private String driver_name;
    private String car_number;
    private String car_model;
    private String reg_time;

    public Carreg() {
    }

    public Carreg(String driver_name, String car_number, String car_model, String reg_time) {
        this.driver_name = driver_name;
        this.car_number = car_number;
        this.car_model = car_model;
        this.reg_time = reg_time;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public String getCar_number() {
        return car_number;
    }

    public String getCar_model() {
        return car_model;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    @Override
    public String toString() {
        return "Carreg{" +
                "id=" + id +
                ", driver_name='" + driver_name + '\'' +
                ", car_number='" + car_number + '\'' +
                ", car_model='" + car_model + '\'' +
                ", reg_time='" + reg_time + '\'' +
                '}';
    }
}
