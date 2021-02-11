package com.george.devil.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notes.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    public static final String TABLE = "notes"; // название таблицы в бд

    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME_NOTE = "name";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_THEME = "theme";

    public NotesDatabase(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    /**
     * Иницилализируем базу данных со столбцами ID, NAME_NOTE, NOTE, DATE_CREATE, THEME_NOTE
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notes (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME_NOTE
                + " TEXT, " + COLUMN_NOTE + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_THEME + " TEXT);");
    }

    /**
     * Вызывается, когда необходимо обновить базу данных.
     * @param db база данных которую нужно обновить
     * @param oldVersion номер версии старой базы данынх
     * @param newVersion номер версии новой базы данных
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
