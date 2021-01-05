package com.george.devil.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class IssuesDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "issues.db";
    private static final int SCHEMA = 1;
    public static final String TABLE = "issues";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME_ISSUE = "name";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_DATE_SAVE = "savedate";
    public static final String COLUMN_NOTE_ISSUE = "noteissue";

    public IssuesDataBase(Context context){
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE issues (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME_ISSUE + " TEXT, " + COLUMN_PRIORITY + " TEXT, " + COLUMN_DATE + " TEXT, "
                + COLUMN_DATE_SAVE + " TEXT, " + COLUMN_NOTE_ISSUE + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
