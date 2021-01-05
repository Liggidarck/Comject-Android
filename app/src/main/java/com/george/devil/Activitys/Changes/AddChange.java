package com.george.devil.Activitys.Changes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.george.devil.DataBases.ChangesDataBase;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddChange extends AppCompatActivity {

    EditText name_chage, edit_text_due_day_change;
    TextView date_edit, text_note_change;

    ChangesDataBase sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long changesId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_change);

        MaterialCardView addNote = findViewById(R.id.add_note_change_layout);
        addNote.setOnClickListener(v -> startActivity(new Intent(AddChange.this, AddNoteChange.class)));

        MaterialToolbar topAppBar_add_change = findViewById(R.id.topAppBar_add_change);
        topAppBar_add_change.setNavigationOnClickListener(v -> save());

        name_chage = findViewById(R.id.name_change);
        edit_text_due_day_change = findViewById(R.id.edit_text_due_day_change);
        date_edit = findViewById(R.id.edit_date_change);
        text_note_change = findViewById(R.id.text_note_change);

        sqlHelper = new ChangesDataBase(this);
        db = sqlHelper.getWritableDatabase();

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);
        String fullDate = "Последнее изменение:" + " " + dateText + " " + timeText;
        date_edit.setText(fullDate);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            changesId = extras.getLong("id");

        if (changesId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + ChangesDataBase.TABLE + " where "
                    + ChangesDataBase.COLUMN_ID + "=?", new String[]{String.valueOf(changesId)});
            userCursor.moveToFirst();

            name_chage.setText(userCursor.getString(1));
            edit_text_due_day_change.setText(userCursor.getString(2));
            date_edit.setText(userCursor.getString(3));
            text_note_change.setText(userCursor.getString(4));

            userCursor.close();
        }

    }

    public void save() {
        String ckeckName = name_chage.getText().toString();
        if(ckeckName.isEmpty()){
            goHome();
        } else {
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);
            DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String timeText = timeFormat.format(currentDate);
            String fullDate = "Последнее изменение:" + " " + dateText + " " + timeText;

            ContentValues cv = new ContentValues();
            cv.put(ChangesDataBase.COLUMN_NAME_CHANGE, name_chage.getText().toString());
            cv.put(ChangesDataBase.COLUMN_DUE_DAY, edit_text_due_day_change.getText().toString());
            cv.put(ChangesDataBase.COLUMN_DATE_CREATE, fullDate);
            cv.put(ChangesDataBase.COLUMN_NOTE_CHANGE, "testd");

            if (changesId > 0)
                db.update(ChangesDataBase.TABLE, cv, ChangesDataBase.COLUMN_ID + "=" +
                        String.valueOf(changesId), null);
            else
                db.insert(ChangesDataBase.TABLE, null, cv);

            goHome();
        }
    }

    public void goHome() {
        db.close();

        Intent intent = new Intent(this, ChangesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() { save(); }
}