package com.george.devil.Activitys.Tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

import com.george.devil.R;
import com.george.devil.DataBases.TasksDataBase;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class AddTaskActivity extends AppCompatActivity {

    TasksDataBase sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long taskId = 0;

    EditText name_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        MaterialCardView addNote = findViewById(R.id.add_note_task_layout);
        addNote.setOnClickListener(v -> startActivity(new Intent(AddTaskActivity.this, AddTaskNoteActivity.class)));

        name_task = findViewById(R.id.name_task);

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

            userCursor.close();
        }

        MaterialToolbar toolbar = findViewById(R.id.topAppBar_add_task);
        toolbar.setNavigationOnClickListener(v -> save());

    }

    public void save() {
        ContentValues cv = new ContentValues();
        cv.put(TasksDataBase.COLUMN_TASK, name_task.getText().toString());

        if (taskId > 0)
            db.update(TasksDataBase.TABLE, cv, TasksDataBase.COLUMN_ID + "=" +
                    String.valueOf(taskId), null);
        else
            db.insert(TasksDataBase.TABLE, null, cv);

        goHome();
    }

    public void goHome() {
        db.close();
        Intent intent = new Intent(this, MyTasksActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}