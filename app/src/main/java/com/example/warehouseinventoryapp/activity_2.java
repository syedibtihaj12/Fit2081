package com.example.warehouseinventoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.warehouseinventoryapp.provider.DeleteItemInterfaceListener;
import com.example.warehouseinventoryapp.provider.itemViewModel;
import com.example.warehouseinventoryapp.provider.item_table_schema;

import java.util.ArrayList;
import java.util.List;

public class activity_2 extends AppCompatActivity implements DeleteItemInterfaceListener {

    List<item_table_schema> data;

    RecyclerView recyclerView;
    itemViewModel item_viewModel;
    RecyclerView.LayoutManager layoutManager;
    recycler_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        recyclerView = findViewById((R.id.my_recycler_view));

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        item_viewModel = new ViewModelProvider(this).get(itemViewModel.class);

        adapter = new recycler_adapter(null,this);
        recyclerView.setAdapter(adapter);

        item_viewModel.getAllItemsVM().observe(this, newData -> {
            adapter.setData(newData);
            adapter.notifyDataSetChanged();
        });

    }

    @Override
    public void onDeleteitemClick(int id) {
        item_viewModel.deleteTaskByIdVM(id);
    }
}
