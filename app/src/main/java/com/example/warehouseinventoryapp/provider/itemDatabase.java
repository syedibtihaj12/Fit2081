package com.example.warehouseinventoryapp.provider;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {item_table_schema.class}, version = 1)
public abstract class itemDatabase extends RoomDatabase {

    public abstract itemDao itemDao();

    public static final String DATABASE_NAME = "items_db";

    private static volatile itemDatabase database_instance;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService dbWrite = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static itemDatabase getDBInstance(Context context) {
        if (database_instance == null) {
            synchronized (itemDatabase.class) {
                if (database_instance == null) {
                    database_instance = Room.databaseBuilder(
                            context.getApplicationContext(), itemDatabase.class,
                            DATABASE_NAME).build();
                }
            }
        }

        return database_instance;
    }

}
