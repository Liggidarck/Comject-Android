package com.george.devil.Activitys.Tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.george.devil.R;
import com.george.devil.DataBases.TasksDataBase;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {

    TasksDataBase sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long taskId = 0;

    EditText name_task;
    TextView text_edit_task;
    String date;

    MaterialToolbar toolbar;
    MaterialCardView addNote;

    //TODO: Добавить возможность удаления элементов из базы данных TASKS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        name_task = findViewById(R.id.name_task);
        text_edit_task = findViewById(R.id.text_edit_task);
        toolbar = findViewById(R.id.topAppBar_add_task);
        addNote = findViewById(R.id.add_note_task_layout);

        toolbar.setNavigationOnClickListener(v -> save());
        addNote.setOnClickListener(v -> startActivity(new Intent(AddTaskActivity.this, AddTaskNoteActivity.class)));

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);
        date = "Последнее изменение:" + " " + dateText + " " + timeText;
        text_edit_task.setText(date);

        sqlHelper = new TasksDataBase(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            taskId = extras.getLong("id");

        if (taskId > 0) {
            userCursor = db.rawQuery("select * from " + TasksDataBase.TABLE + " where "
                    + TasksDataBase.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)});
            userCursor.moveToFirst();

            name_task.setText(userCursor.getString(1));
            text_edit_task.setText(userCursor.getString(2));

            userCursor.close();
        }
    }

    public void save() {
        if(!validateTask()){
            return;
        } else {
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);
            DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String timeText = timeFormat.format(currentDate);
            date = "Последнее изменение:" + " " + dateText + " " + timeText;

            ContentValues cv = new ContentValues();
            cv.put(TasksDataBase.COLUMN_TASK, name_task.getText().toString());
            cv.put(TasksDataBase.COLUMN_DATE_CREATE_TASK, date);

            if (taskId > 0)
                db.update(TasksDataBase.TABLE, cv, TasksDataBase.COLUMN_ID + "=" +
                        String.valueOf(taskId), null);
            else
                db.insert(TasksDataBase.TABLE, null, cv);

            goHome();
        }
    }

    public void goHome() {
        db.close();
        Intent intent = new Intent(this, MyTasksActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public boolean validateTask() {
        String check = name_task.getText().toString();
        if(check.isEmpty()){
            goHome();
            return false;
        } else
            return true;
    }

}