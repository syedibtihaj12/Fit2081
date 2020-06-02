package com.example.warehouseinventoryapp.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class itemViewModel extends AndroidViewModel {

    itemRepo item_repo;
    LiveData<List<item_table_schema>> my_items;

    public itemViewModel(@NonNull Application app) {
        super(app);

        item_repo = new itemRepo(app);
        my_items = item_repo.getAllItemsRepo();

    }
    public LiveData<List<item_table_schema>> getAllItemsVM() {
        return my_items;
    }

    public void insertItemVM(item_table_schema item) {
        item_repo.insertItemRepo(item);
    }

    public void deleteTaskByIdVM(int id) {
        item_repo.deleteItemById(id);
    }
}
