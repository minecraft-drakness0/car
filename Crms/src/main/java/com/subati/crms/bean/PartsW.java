package com.subati.crms.bean;

public class PartsW {
     private int id;
     private String part_number;
     private int storage_quantity;
     private int inventory_quantity;
     private int price;

    public PartsW() {
    }

    public PartsW(String part_number, int storage_quantity, int inventory_quantity, int price) {
        this.part_number = part_number;
        this.storage_quantity = storage_quantity;
        this.inventory_quantity = inventory_quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getPart_number() {
        return part_number;
    }

    public int getStorage_quantity() {
        return storage_quantity;
    }

    public int getInventory_quantity() {
        return inventory_quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPart_number(String part_number) {
        this.part_number = part_number;
    }

    public void setStorage_quantity(int storage_quantity) {
        this.storage_quantity = storage_quantity;
    }

    public void setInventory_quantity(int inventory_quantity) {
        this.inventory_quantity = inventory_quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PartsW{" +
                "id=" + id +
                ", part_number='" + part_number + '\'' +
                ", storage_quantity=" + storage_quantity +
                ", inventory_quantity=" + inventory_quantity +
                ", price=" + price +
                '}';
    }
}
