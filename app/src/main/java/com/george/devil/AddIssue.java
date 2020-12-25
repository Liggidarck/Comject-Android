package com.george.devil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddIssue extends AppCompatActivity {

    EditText name_issue, editText_add_due_date;

    IssuesDataBase sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long issuesId = 0;

    RelativeLayout priority_layout;
    AutoCompleteTextView choose_priority_TextEdit;

    String dateFullIssue;
    TextView dateViewIssue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_issue);

        MaterialCardView addNote = findViewById(R.id.add_note_issue_layout);
        addNote.setOnClickListener(v -> startActivity(new Intent(AddIssue.this, AddNoteIssue.class)));

        MaterialToolbar topAppBar_add_issue = findViewById(R.id.topAppBar_add_issue);
        topAppBar_add_issue.setNavigationOnClickListener(v -> save());

        name_issue = findViewById(R.id.name_issue);
        choose_priority_TextEdit = findViewById(R.id.choose_priority_TextEdit);
        editText_add_due_date = findViewById(R.id.editText_add_due_date);
        dateViewIssue = findViewById(R.id.date_issue);

        String[] items = new String[] {
                "High", "Medium", "Low"
        }; //        String[] countries = getResources().getStringArray(R.array.countries);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AddIssue.this,
                R.layout.dropdown_menu_categories,
                items
        );

        choose_priority_TextEdit.setAdapter(adapter);

        sqlHelper = new IssuesDataBase(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();

        if (extras != null)
            issuesId = extras.getLong("id");

//        Date currentDate = new Date();
//        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
//        String dateText = dateFormat.format(currentDate);
//        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
//        String timeText = timeFormat.format(currentDate);
//        dateFullIssue = "Последнее изменение:" + " " + dateText + " " + timeText;
//        dateViewIssue.setText(dateFullIssue);

        if (issuesId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + IssuesDataBase.TABLE + " where " + IssuesDataBase.COLUMN_ID + "=?", new String[]{String.valueOf(issuesId)});
            userCursor.moveToFirst();

            name_issue.setText(userCursor.getString(1));
            choose_priority_TextEdit.setText(userCursor.getString(2));
            editText_add_due_date.setText(userCursor.getString(3));

            userCursor.close();
        }


    }

    public void save() {
        ContentValues cv = new ContentValues();
        cv.put(IssuesDataBase.COLUMN_NAME_ISSUE, name_issue.getText().toString());
        cv.put(IssuesDataBase.COLUMN_PRIORITY, choose_priority_TextEdit.getText().toString());
        cv.put(IssuesDataBase.COLUMN_DATE, editText_add_due_date.getText().toString());

        if (issuesId > 0)
            db.update(IssuesDataBase.TABLE, cv, IssuesDataBase.COLUMN_ID + "=" + String.valueOf(issuesId), null);
        else
            db.insert(IssuesDataBase.TABLE, null, cv);

        goHome();
    }

    public void goHome() {

        db.close();

        Intent intent = new Intent(this, IsuuesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }

}