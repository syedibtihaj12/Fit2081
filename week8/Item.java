package com.example.warehouseinventoryapp;

public class Item {
    private String item_name;
    private int quantity;
    private int cost;
    private String description;
    private Boolean is_frozen;

    public Item(String name, int quantity, int cost, String description, Boolean is_frozen) {
        this.item_name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.description = description;
        this.is_frozen = is_frozen;
    }

    public String getName() {
        return item_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIs_frozen() {
        return is_frozen;
    }
}
