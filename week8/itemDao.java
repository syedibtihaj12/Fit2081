package com.example.warehouseinventoryapp.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface itemDao {

    @Insert
    void insertItem(item_table_schema item);

    @Query("select * from items")
    LiveData<List<item_table_schema>> getAllTasks();

    @Query("delete from items")
    void deleteAllItems();


    @Query("delete from items where itemId = :id")
    void deleteItemById(int id);
}
