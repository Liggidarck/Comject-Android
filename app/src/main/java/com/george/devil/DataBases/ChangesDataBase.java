package com.george.devil.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChangesDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "changes.db";
    private static final int SCHEMA = 1;
    public static final String TABLE = "changes";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME_CHANGE = "name";
    public static final String COLUMN_DUE_DAY = "duedate";
    public static final String COLUMN_DATE_CREATE = "createddate";
    public static final String COLUMN_NOTE_CHANGE = "notechange";


    public ChangesDataBase(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    /**
     * Иницилализируем базу данных со столбцами ID, NAME_CHANGE, DUE_DATE, DATE_CREATE, NOTE_CHANGE.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE changes (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME_CHANGE + " TEXT, " + COLUMN_DUE_DAY + " TEXT, " + COLUMN_DATE_CREATE + " TEXT, " +
                COLUMN_NOTE_CHANGE + " TEXT);");
    }

    /**
     * Вызывается, когда необходимо обновить базу данных.
     * @param db база данных которую нужно обновить
     * @param oldVersion номер версии старой базы данынх
     * @param newVersion номер версии новой базы данных
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
