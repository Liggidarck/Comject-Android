package com.george.devil.Activitys.Issues;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.george.devil.DataBases.IssuesDataBase;
import com.george.devil.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddIssue extends AppCompatActivity {

    //TODO: ПЕРЕДЕЛАТЬ ЭТОТ ПИЗДЕЦ В БОЛЕЕ ЧИТАЕМЫЙ С КОММЕНТАРИЯМИ

    EditText name_issue, editText_add_due_date;

    IssuesDataBase sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long issuesId = 0;

    RelativeLayout priority_layout;
    AutoCompleteTextView choose_priority_TextEdit;

    String dateFullIssue, noteissue;
    TextView dateViewIssue, text_note_issue;

    private static final String TAG = "AddIssueActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_issue);

        MaterialToolbar topAppBar_add_issue = findViewById(R.id.topAppBar_add_issue);
        topAppBar_add_issue.setNavigationOnClickListener(v -> save());

        ImageView delete_isseue = findViewById(R.id.delete_isseue);
        delete_isseue.setOnClickListener(v -> {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            boolean confirmDelet = preferences.getBoolean("delet_bool", true);

            if(confirmDelet){
                AlertDialog.Builder builder = new AlertDialog.Builder(AddIssue.this);
                builder.setTitle("Внимание");
                builder.setMessage("Вы действительно хотите удалить issue?");

                builder.setPositiveButton("Ок", (dialog, id) -> {
                    delete();
                    dialog.dismiss();
                });

                builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());

                builder.show();
            }else
                delete();


        });

        name_issue = findViewById(R.id.name_issue);
        choose_priority_TextEdit = findViewById(R.id.choose_priority_TextEdit);
        editText_add_due_date = findViewById(R.id.editText_add_due_date);
        dateViewIssue = findViewById(R.id.date_issue);
        text_note_issue = findViewById(R.id.text_note_issue);

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
        if (extras != null) {
            issuesId = extras.getLong("id");
            noteissue = extras.getString("noteIssue");
        }

        Log.i(TAG, "Полученое значение заметки - " + noteissue);
        text_note_issue.setText(noteissue);

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);
        dateFullIssue = "Последнее изменение:" + " " + dateText + " " + timeText;
        dateViewIssue.setText(dateFullIssue);

        if (issuesId > 0) {
            userCursor = db.rawQuery("select * from " + IssuesDataBase.TABLE + " where " + IssuesDataBase.COLUMN_ID + "=?", new String[]{String.valueOf(issuesId)});
            userCursor.moveToFirst();

            name_issue.setText(userCursor.getString(1));
            choose_priority_TextEdit.setText(userCursor.getString(2));
            editText_add_due_date.setText(userCursor.getString(3));
            dateViewIssue.setText(userCursor.getString(4));
            text_note_issue.setText(userCursor.getString(5));

            userCursor.close();
        }

        MaterialCardView addNote = findViewById(R.id.add_note_issue_layout);
        addNote.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddNoteIssue.class);
            intent.putExtra("name_issue", name_issue.getText().toString());
            startActivity(intent);
        });


    }

    public void save() {
        String chekName = name_issue.getText().toString();
        String checkPrioritet = choose_priority_TextEdit.getText().toString();

        if (!(chekName.isEmpty() | checkPrioritet.isEmpty())) {
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);
            DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String timeText = timeFormat.format(currentDate);
            String fullDate = "Последнее изменение:" + " " + dateText + " " + timeText;

            ContentValues cv = new ContentValues();
            cv.put(IssuesDataBase.COLUMN_NAME_ISSUE, name_issue.getText().toString());
            cv.put(IssuesDataBase.COLUMN_PRIORITY, choose_priority_TextEdit.getText().toString());
            cv.put(IssuesDataBase.COLUMN_DATE, editText_add_due_date.getText().toString());
            cv.put(IssuesDataBase.COLUMN_DATE_SAVE, fullDate);

            if (issuesId > 0)
                db.update(IssuesDataBase.TABLE, cv, IssuesDataBase.COLUMN_ID + "=" + issuesId, null);
            else
                db.insert(IssuesDataBase.TABLE, null, cv);

        }
        goHome();
    }

    public void goHome() {

        db.close();

        Intent intent = new Intent(this, IsuuesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }

    public void delete() {

        db.delete(IssuesDataBase.TABLE, "_id = ?", new String[]{String.valueOf(issuesId)});
        goHome();

    }

    @Override
    public void onBackPressed() {
        save();
    }
}