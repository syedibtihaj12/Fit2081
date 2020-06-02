package com.example.warehouseinventoryapp.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ItemContentProvider extends ContentProvider {

    itemDatabase item_database;
    public static final String CONTENT_AUTHORITY = "fit2081.app.db.items";
    public static final Uri CONTENT_URI = Uri.parse("Content://" + CONTENT_AUTHORITY);
    private static final int MULTIPLE_ROWS_ITEMS = 1;
    private static final int SINGLE_ROWS_ITEMS = 2;
    private static final int SINGLE_ROWS_USERS = 3;
    private static final int MULTIPLE_ROWS_USERS = 4;
    private static final UriMatcher myMatcher = createUriMatcher();


    private static UriMatcher createUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(CONTENT_AUTHORITY, item_table_schema.TABLE_NAME, MULTIPLE_ROWS_ITEMS);
        uriMatcher.addURI(CONTENT_AUTHORITY, item_table_schema.TABLE_NAME + "/#", SINGLE_ROWS_ITEMS);

        uriMatcher.addURI(CONTENT_AUTHORITY, "Users" + "/#", SINGLE_ROWS_ITEMS);
        uriMatcher.addURI(CONTENT_AUTHORITY, "Users", MULTIPLE_ROWS_ITEMS);

        return uriMatcher;
    }


    public ItemContentProvider() {
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.

        item_database = itemDatabase.getDBInstance(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        item_database.getOpenHelper()
                .getWritableDatabase()
                .delete(item_table_schema
                        .TABLE_NAME, selection, selectionArgs);

        int count = 0;


        return count;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri returnUri;
        long newid = item_database
                .getOpenHelper()
                .getWritableDatabase()
                .insert(item_table_schema.TABLE_NAME, 0, values);
        returnUri = ContentUris.withAppendedId(CONTENT_URI, newid);

        return returnUri;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Cursor cursor;
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(item_table_schema.TABLE_NAME);
        String query = builder.buildQuery(projection, selection, null, null, sortOrder, null);
        cursor = item_database.getOpenHelper().getReadableDatabase().query(query, selectionArgs);

        int matchId = myMatcher.match(uri);

        switch (matchId) {
            case MULTIPLE_ROWS_USERS:
                break;
            case SINGLE_ROWS_USERS:
                break;
            case MULTIPLE_ROWS_ITEMS:
                break;
            case SINGLE_ROWS_ITEMS:
                break;
        }

        return cursor;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count;
        count = item_database
                .getOpenHelper()
                .getWritableDatabase()
                .update(item_table_schema.TABLE_NAME, 0, values, selection, selectionArgs);

        return count;

    }
}
