package com.example.warehouseinventoryapp.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class itemRepo {

    private itemDao itemDao;
    LiveData<List<item_table_schema>> item;

    public itemRepo(Application app) {
        itemDatabase db = itemDatabase.getDBInstance(app);
        itemDao = db.itemDao();
        item = itemDao.getAllTasks();
    }

    LiveData<List<item_table_schema>> getAllItemsRepo() {
        return itemDao.getAllTasks();
    }

    void insertItemRepo(item_table_schema item) {
        itemDatabase.dbWrite.execute(() -> itemDao.insertItem(item));
    }

    void deleteAllItemsRepo(){
        itemDatabase.dbWrite.execute(()-> itemDao.deleteAllItems());
    }

    void deleteItemById(int id){
        itemDatabase.dbWrite.execute(()-> itemDao.deleteItemById(id));
    }
}
