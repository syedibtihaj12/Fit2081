package com.example.warehouseinventoryapp.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class item_table_schema {
    public static final String TABLE_NAME = "items";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "itemId")
    private int id;

    @ColumnInfo(name = "itemName")
    private String item_name;

    @ColumnInfo(name = "itemQuantity")
    private int quantity;

    @ColumnInfo(name = "itemCost")
    private int cost;

    @ColumnInfo(name = "itemDesc")
    private String description;

    @ColumnInfo(name = "itemFrozen")
    private String is_frozen;

    public item_table_schema(String item_name, int quantity, int cost, String description, String is_frozen) {
        this.item_name = item_name;
        this.quantity = quantity;
        this.cost = cost;
        this.description = description;
        this.is_frozen = is_frozen;
    }

    public int getId() {
        return id;
    }

    public String getItem_name() {
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

    public String getIs_frozen() {
        return is_frozen;
    }

    public void setId(int id) {
        this.id = id;
    }
}
