package com.george.devil.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TasksDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasks.db";
    private static final int SCHEMA = 1;
    public static final String TABLE = "tasks";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_NOTE_TASK = "notetask";
    public static final String COLUMN_DATE_CREATE_TASK = "date";

    public TasksDataBase(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tasks (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TASK + " TEXT, " + COLUMN_NOTE_TASK + " TEXT, " + COLUMN_DATE_CREATE_TASK + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
