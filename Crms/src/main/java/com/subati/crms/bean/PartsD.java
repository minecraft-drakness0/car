package com.subati.crms.bean;

public class PartsD {

    private int id;
    private String part_number;
    private int delivery_quantity;
    private int inventory_quantity;
    private int price;

    public PartsD() {
    }

    public PartsD(String part_number, int delivery_quantity, int inventory_quantity, int price) {
        this.part_number = part_number;
        this.delivery_quantity = delivery_quantity;
        this.inventory_quantity = inventory_quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getPart_number() {
        return part_number;
    }

    public int getDelivery_quantity() {
        return delivery_quantity;
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

    public void setDelivery_quantity(int delivery_quantity) {
        this.delivery_quantity = delivery_quantity;
    }

    public void setInventory_quantity(int inventory_quantity) {
        this.inventory_quantity = inventory_quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PartsD{" +
                "id=" + id +
                ", part_number='" + part_number + '\'' +
                ", delivery_quantity=" + delivery_quantity +
                ", inventory_quantity=" + inventory_quantity +
                ", price=" + price +
                '}';
    }
}
