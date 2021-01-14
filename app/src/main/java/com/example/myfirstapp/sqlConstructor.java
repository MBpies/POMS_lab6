package com.example.myfirstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class sqlConstructor extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Results.db";

    /* Inner class that defines the table contents */
    public static class sqlData implements BaseColumns {
        public static final String _ID = "_id";
        public static final String TABLE_NAME = "operations";
        public static final String COLUMN_NAME_FIRST = "firstThing";
        public static final String COLUMN_NAME_SECOND = "secondThing";
        public static final String COLUMN_NAME_RESULT = "resultThing";
    }

    private static final String CREATE_TABLE = "Create table " + sqlData.TABLE_NAME + "(" + sqlData._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + sqlData.COLUMN_NAME_FIRST + " TEXT NOT NULL, "
            + sqlData.COLUMN_NAME_SECOND + " TEXT NOT NULL, "
            + sqlData.COLUMN_NAME_RESULT + " TEXT NOT NULL"
            + ");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + sqlData.TABLE_NAME;



    public sqlConstructor(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
