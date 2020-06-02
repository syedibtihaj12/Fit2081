package com.example.warehouseinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.warehouseinventoryapp.provider.itemViewModel;
import com.example.warehouseinventoryapp.provider.item_table_schema;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String COST_ID = "";
    private static final String NAME_ID = "";
    private static final String QUANTITY_ID = "";
    private static final String DESCRIPTION_ID = "";
    itemViewModel item_viewModel ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        registerReceiver(new myBroadCastReceiver(), new IntentFilter(SMSReceiver.INTENT_FILTER_ID));
        super.onCreate(savedInstanceState);

        //################################################################################################
        //SETTING THE LAYOUT
        //################################################################################################
        setContentView(R.layout.activity_drawer_layout);

        //################################################################################################
        //Element Initialisation
        //################################################################################################
        Toolbar toolbar;
        NavigationView navigationView;
        DrawerLayout drawerLayout;
        FloatingActionButton fabButton;


        //################################################################################################
        //DECLARING ELEMENTS
        //################################################################################################
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        fabButton = findViewById(R.id.fab);
        final Button clearItemButton = (Button) findViewById(R.id.clear_item_button);
        final Button addItem = (Button) findViewById(R.id.add_item_button);
        final EditText itemName_et = (EditText) findViewById(R.id.item_name_et);
        final EditText quantity_et = (EditText) findViewById(R.id.quantity_et);
        final EditText cost_et = (EditText) findViewById(R.id.cost_et);
        final EditText description_et = (EditText) findViewById(R.id.description_et);
        final EditText IsFrozen_et = (EditText) findViewById(R.id.isfrozen_et);
        item_viewModel = new ViewModelProvider(this).get(itemViewModel.class);



        //################################################################################################
        //SETTING UP THE TOOLBAR
        //################################################################################################
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new myNavListener());


        //################################################################################################
        //FLOATING ACTION BUTTON
        //################################################################################################
        fabButton.setOnClickListener(v -> Snackbar.make(v, "Item " + itemName_et.getText().toString() + " has been Added!", Snackbar.LENGTH_LONG)
                .setAction("Reset", null).show());

        //################################################################################################
        // ADD ITEM BUTTON HANDLER
        //################################################################################################
        addItem.setOnClickListener(v -> {
            Snackbar.make(v, "Item " + itemName_et.getText().toString() + " has been Added!", Snackbar.LENGTH_LONG)
                    .setAction("Reset", null).show();
            Toast.makeText(getApplicationContext(), "Item " + itemName_et.getText().toString() + " has been Added", Toast.LENGTH_SHORT).show();
            item_table_schema item = new item_table_schema(itemName_et.getText().toString(),
                    Integer.parseInt(quantity_et.getText().toString()),
                    Integer.parseInt(cost_et.getText().toString()),
                    description_et.getText().toString(),
                    IsFrozen_et.getText().toString());
            item_viewModel.insertItemVM(item);
            itemName_et.setText("");
            quantity_et.setText("");
            cost_et.setText("");
            description_et.setText("");
            IsFrozen_et.setText("");
        });


        //################################################################################################
        //CLEAR ITEMS BUTTON HANDLER
        //################################################################################################
        clearItemButton.setOnClickListener(v -> {
            itemName_et.setText("");
            quantity_et.setText("");
            cost_et.setText("");
            description_et.setText("");
        });

    }

    //################################################################################################
    //OPTIONS MENU
    //################################################################################################
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        final EditText itemName = (EditText) findViewById(R.id.item_name_et);
        final EditText quantity = (EditText) findViewById(R.id.quantity_et);
        final EditText cost = (EditText) findViewById(R.id.cost_et);
        final EditText description = (EditText) findViewById(R.id.description_et);

        switch (id) {
            case R.id.options_add_item:
                Toast.makeText(getApplicationContext(), "Item " + itemName.getText().toString() + " has been Added", Toast.LENGTH_SHORT).show();
                break;
            case R.id.options_clear_items:
                Toast.makeText(getApplicationContext(), "All Items Cleared", Toast.LENGTH_SHORT).show();
                itemName.setText("");
                quantity.setText("");
                cost.setText("");
                description.setText("");
                break;
        }
        return true;
    }


    //################################################################################################
    //LISTENER METHOD FOR THE TOOLBAR
    //################################################################################################
    class myNavListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            final EditText itemName_et = (EditText) findViewById(R.id.item_name_et);
            final EditText quantity_et = (EditText) findViewById(R.id.quantity_et);
            final EditText cost_et = (EditText) findViewById(R.id.cost_et);
            final EditText description_et = (EditText) findViewById(R.id.description_et);
            final EditText IsFrozen_et = (EditText) findViewById(R.id.isfrozen_et);
            int id = item.getItemId();
            if (id == R.id.add_item) {
                Toast.makeText(getApplicationContext(), "Item " + itemName_et.getText().toString() + " has been Added", Toast.LENGTH_SHORT).show();
                item_table_schema new_item = new item_table_schema(itemName_et.getText().toString(),
                        Integer.parseInt(quantity_et.getText().toString()),
                        Integer.parseInt(cost_et.getText().toString()),
                        description_et.getText().toString(),
                        IsFrozen_et.getText().toString());
                item_viewModel.insertItemVM(new_item);
                itemName_et.setText("");
                quantity_et.setText("");
                cost_et.setText("");
                description_et.setText("");
                IsFrozen_et.setText("");
                ;
            } else if (id == R.id.clear_items) {
                Toast.makeText(getApplicationContext(), "All Items Cleared", Toast.LENGTH_SHORT).show();
                itemName_et.setText("");
                quantity_et.setText("");
                cost_et.setText("");
                description_et.setText("");
                IsFrozen_et.setText("");
            } else if (id == R.id.list_all_items){
                Toast.makeText(getApplicationContext(), "Activity 2 Started!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), activity_2.class);
                startActivity(intent);
            }

            return true;
        }
    }

    //################################################################################################
    //BroadCast Receiver Class
    //################################################################################################
    class myBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String Message = intent.getStringExtra(SMSReceiver.MSG_KEY);
            final EditText itemName = (EditText) findViewById(R.id.item_name_et);
            itemName.setText(Message);
        }

    }

    public void clear_edit_text_handler(View view){
        clear_edit_text();
    }

    public void clear_edit_text(){
        final EditText itemName = (EditText) findViewById(R.id.item_name_et);
        final EditText quantity = (EditText) findViewById(R.id.quantity_et);
        final EditText cost = (EditText) findViewById(R.id.cost_et);
        final EditText description = (EditText) findViewById(R.id.description_et);

        itemName.setText("");
        quantity.setText("");
        cost.setText("");
        description.setText("");
    }


}
