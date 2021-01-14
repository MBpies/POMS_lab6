package com.example.myfirstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbAssistant {

    private Context context;
    public dbAssistant(Context context) {
        this.context = context;
    }

    private sqlConstructor dbHelper;
    private SQLiteDatabase database;

    public dbAssistant open() throws SQLException {
        dbHelper = new sqlConstructor(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    private void insert(String firstThing, String secondThing, String resultThing) {
        ContentValues contentValue1 = new ContentValues();
        contentValue1.put(sqlConstructor.sqlData.COLUMN_NAME_FIRST, firstThing);
        contentValue1.put(sqlConstructor.sqlData.COLUMN_NAME_SECOND, secondThing);
        contentValue1.put(sqlConstructor.sqlData.COLUMN_NAME_RESULT, resultThing);
        database.insert(sqlConstructor.sqlData.TABLE_NAME, null, contentValue1);
    }

    private Cursor fetch() {
        String[] columns = new String[] { sqlConstructor.sqlData._ID,
                sqlConstructor.sqlData.COLUMN_NAME_FIRST,
                sqlConstructor.sqlData.COLUMN_NAME_SECOND,
                sqlConstructor.sqlData.COLUMN_NAME_RESULT};
        Cursor cursor = database.query(sqlConstructor.sqlData.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    private int update(long _id, String firstThing, String secondThing, String resultThing) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(sqlConstructor.sqlData.COLUMN_NAME_FIRST, firstThing);
        contentValue.put(sqlConstructor.sqlData.COLUMN_NAME_SECOND, secondThing);
        contentValue.put(sqlConstructor.sqlData.COLUMN_NAME_RESULT, resultThing);
        int i = database.update(sqlConstructor.sqlData.TABLE_NAME, contentValue, sqlConstructor.sqlData._ID + " = " + _id, null);
        return i;
    }

    private void delete(long _id) {
        database.delete(sqlConstructor.sqlData.TABLE_NAME, sqlConstructor.sqlData._ID + "=" + _id, null);
    }


    public void insert(ListItemH item) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(sqlConstructor.sqlData.COLUMN_NAME_FIRST, item.asSingleFirstThing());
        contentValue.put(sqlConstructor.sqlData.COLUMN_NAME_SECOND, item.asSingleSecondThing());
        contentValue.put(sqlConstructor.sqlData.COLUMN_NAME_RESULT, item.asSingleResultThing());
        database.insert(sqlConstructor.sqlData.TABLE_NAME, null, contentValue);
    }

    public void deleteAll() {
        database.delete(sqlConstructor.sqlData.TABLE_NAME,"",null);
    }

    public String getAllAsText(){
        String text = "";
        Cursor cursor = fetch();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String firstthing, secondthing, resultthing;
            firstthing = cursor.getString(cursor.getColumnIndex(sqlConstructor.sqlData.COLUMN_NAME_FIRST));
            secondthing = cursor.getString(cursor.getColumnIndex(sqlConstructor.sqlData.COLUMN_NAME_SECOND));
            resultthing = cursor.getString(cursor.getColumnIndex(sqlConstructor.sqlData.COLUMN_NAME_RESULT));
            ListItemH item = new ListItemH(firstthing, secondthing, resultthing);
            text += (item.asStringText() + "\n");
            cursor.moveToNext();
        }
        return text;
    }

}
